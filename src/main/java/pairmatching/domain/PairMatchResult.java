package pairmatching.domain;

import java.util.List;
import java.util.stream.Collectors;

public class PairMatchResult {
    private final List<List<Crew>> matchResult;

    public PairMatchResult(List<List<Crew>> matchResult) {
        this.matchResult = matchResult;
    }

    public boolean hasAlreadyMatchedInSameLevel(List<PairMatchResult> sameLevelResults) {
        List<List<Crew>> sortedResults = this.sortedResults();

        return sameLevelResults.stream()
                .flatMap(sameLevelResult -> sameLevelResult.sortedResults().stream())
                .anyMatch(crews -> sortedResults.contains(crews));
    }

    private List<List<Crew>> sortedResults() {
        return matchResult.stream()
                .map(crews -> crews.stream().sorted().collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
