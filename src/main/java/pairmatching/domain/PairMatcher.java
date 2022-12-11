package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
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
        // 중복된 조회인 경우, 되돌리고 재선택할건지 물어봐야 함 -> 재매칭!!
        if (pairMatchResultRepository.hasDuplicatedResult(pairMatchingRequest) && !wantRematch) {
            return Optional.empty();
        }

        List<Crew> crews = crewRepository.findByCourse(pairMatchingRequest.course());

        PairMatchResult pairMatchResult = pairMatch(pairMatchingRequest, crews);
        return pairMatchResultRepository.save(pairMatchingRequest, pairMatchResult);
    }

    private PairMatchResult pairMatch(PairMatchingRequest request, List<Crew> crews) {
        if (pairMatchResultRepository.hasSameLevelResult(request)) {
            return pairMatchOfSameLevel(request, crews);
        }

        List<List<Crew>> results = matchResult(crews);

        return new PairMatchResult(results);
    }

    private PairMatchResult pairMatchOfSameLevel(PairMatchingRequest request, List<Crew> crews) {
        List<List<Crew>> results = matchResult(crews);
        PairMatchResult pairMatchResult = new PairMatchResult(results);

        List<PairMatchResult> sameLevelResults = pairMatchResultRepository.findSameLevelResult(request);
        pairMatchResult = rematchOrNot(crews, pairMatchResult, sameLevelResults);

        return pairMatchResult;
    }

    private PairMatchResult rematchOrNot(List<Crew> crews,
            PairMatchResult pairMatchResult, List<PairMatchResult> sameLevelResults) {
        int rematchCount = 0;
        while (pairMatchResult.hasAlreadyMatchedInSameLevel(sameLevelResults)) {
            pairMatchResult = new PairMatchResult(matchResult(crews));
            rematchCount++;

            if (rematchCount > 3) {
                throw new IllegalArgumentException("재매칭에 실패했습니다.");
            }
        }
        return pairMatchResult;
    }

    private static List<List<Crew>> matchResult(List<Crew> crews) {
        List<Crew> shuffledList = Randoms.shuffle(crews);

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
}
