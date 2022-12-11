package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PairMatcher {
    private final CrewRepository crewRepository;
    private final PairMatchResultRepository pairMatchResultRepository;

    public PairMatcher(CrewRepository crewRepository,
            PairMatchResultRepository pairMatchResultRepository) {
        this.crewRepository = crewRepository;
        this.pairMatchResultRepository = pairMatchResultRepository;
    }

    public Optional<PairMatchResult> pairMatchResult(PairMatchingRequest pairMatchingRequest,
            boolean wantRematch) {
        if (pairMatchResultRepository.hasDuplicatedResult(pairMatchingRequest) && !wantRematch) {
            return Optional.empty();
        }

        PairMatchResult pairMatchResult = pairMatch(pairMatchingRequest);
        return pairMatchResultRepository.save(pairMatchingRequest, pairMatchResult);
    }

    private PairMatchResult pairMatch(PairMatchingRequest request) {
        if (pairMatchResultRepository.hasSameLevelResult(request)) {
            return pairMatchOfSameLevel(request);
        }

        List<List<Crew>> results = matchResult(request);
        return new PairMatchResult(results);
    }

    private PairMatchResult pairMatchOfSameLevel(PairMatchingRequest request) {
        List<List<Crew>> results = matchResult(request);
        PairMatchResult pairMatchResult = new PairMatchResult(results);

        List<PairMatchResult> sameLevelResults = pairMatchResultRepository.findSameLevelResult(request);
        pairMatchResult = rematchOrNot(request, pairMatchResult, sameLevelResults);

        return pairMatchResult;
    }

    private PairMatchResult rematchOrNot(PairMatchingRequest request,
            PairMatchResult pairMatchResult, List<PairMatchResult> sameLevelResults) {
        int rematchCount = 0;
        while (pairMatchResult.hasAlreadyMatchedInSameLevel(sameLevelResults)) {
            pairMatchResult = new PairMatchResult(matchResult(request));
            rematchCount++;

            if (rematchCount > 3) {
                throw new IllegalArgumentException("재매칭에 실패했습니다.");
            }
        }
        return pairMatchResult;
    }

    private List<List<Crew>> matchResult(PairMatchingRequest request) {
        List<Crew> shuffledList = crewRepository.shuffledCrews(request.course());

        List<List<Crew>> results = new ArrayList<>();
        int size = shuffledList.size();
        for (int i = 0; i < size - 1; i += 2) {
            results.add(new ArrayList<>(List.of(shuffledList.get(i), shuffledList.get(i + 1))));
        }

        if (size % 2 != 0) {
            results.get(results.size() - 1).add(shuffledList.get(size - 1));
        }
        return results;
    }

    public void clear() {
        pairMatchResultRepository.deleteAll();
    }

    public Optional<PairMatchResult> findBy(PairMatchingRequest request) {
        return pairMatchResultRepository.findByRequest(request);
    }
}
