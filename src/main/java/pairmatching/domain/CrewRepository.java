package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CrewRepository {
    private final CrewNameFileReader crewNameFileReader;

    public CrewRepository(CrewNameFileReader crewNameFileReader) {
        this.crewNameFileReader = crewNameFileReader;
    }

    public List<Crew> shuffledCrews(String course) {
        List<String> crewNames = crewNameFileReader.readFileBy(course);
        List<String> shuffledNames = new ArrayList<>(Randoms.shuffle(crewNames));
        return shuffledNames.stream()
                .map(Crew::new)
                .collect(Collectors.toList());
    }
}
