package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CrewNameFileReaderTest {

    @ParameterizedTest(name = "{0}를 입력하면 {0} 크루의 이름 리스트를 반환한다.")
    @CsvSource({"백엔드,백호", "프론트엔드,보노"})
    void fileReadTest(String course, String firstCrewName) {
        CrewNameFileReader crewNameFileReader = new CrewNameFileReader();
        List<String> crewNames = crewNameFileReader.readFileBy(course);

        assertThat(crewNames.get(0)).isEqualTo(firstCrewName);
    }
}