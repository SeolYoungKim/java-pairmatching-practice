package pairmatching.domain;

import java.util.List;
import java.util.stream.Collectors;

public class CrewRepository {
    private final CrewNameFileReader crewNameFileReader;

    public CrewRepository(CrewNameFileReader crewNameFileReader) {
        this.crewNameFileReader = crewNameFileReader;
    }

    public List<Crew> findByCourse(String course) {
        List<String> crewNames = crewNameFileReader.readFileBy(course);
        return crewNames.stream()
                .map(Crew::new)
                .collect(Collectors.toList());
    }
}
