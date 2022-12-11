package pairmatching.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PairMatchResultRepository {
    private final Map<PairMatchingRequest, PairMatchResult> matchingResults = new HashMap<>();

    public boolean hasDuplicatedResult(PairMatchingRequest pairMatchingRequest) {
        return matchingResults.containsKey(pairMatchingRequest);
    }

    public boolean hasSameLevelResult(PairMatchingRequest pairMatchingRequest) {
        return matchingResults.keySet().stream()
                .filter(request -> !request.equals(pairMatchingRequest))
                .anyMatch(request -> request.isSameLevel(pairMatchingRequest));
    }

    public Optional<PairMatchResult> save(PairMatchingRequest request, PairMatchResult result) {
        matchingResults.put(request, result);
        return Optional.of(result);
    }

    public List<PairMatchResult> findSameLevelResult(PairMatchingRequest pairMatchingRequest) {
        return matchingResults.keySet().stream()
                .filter(request -> !request.equals(pairMatchingRequest))
                .filter(request -> request.isSameLevel(pairMatchingRequest))
                .map(matchingResults::get)
                .collect(Collectors.toList());
    }

    public void deleteAll() {
        matchingResults.clear();
    }

    public Optional<PairMatchResult> findByRequest(PairMatchingRequest request) {
        return Optional.ofNullable(matchingResults.get(request));
    }
}
