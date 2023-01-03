package pairmatching.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pairmatching.dto.PairMatchingRequestDto;

class PairMatchResultRepositoryTest {
    private final PairMatchResultRepository resultRepository = new PairMatchResultRepository();

    @BeforeEach
    void setUp() {
        resultRepository.save(
                new PairMatchingRequest(
                        new PairMatchingRequestDto(new String[]{"백엔드", "레벨1", "자동차경주"})),
                new PairMatchResult(new ArrayList<>(List.of(
                        List.of(new Crew("포비"), new Crew("크롱")),
                        List.of(new Crew("철수"), new Crew("민수"))
                )))
        );

        resultRepository.save(
                new PairMatchingRequest(
                        new PairMatchingRequestDto(new String[]{"백엔드", "레벨1", "숫자야구게임"})),
                new PairMatchResult(new ArrayList<>(List.of(
                        List.of(new Crew("포비"), new Crew("민수")),
                        List.of(new Crew("크롱"), new Crew("철수"))
                )))
        );
    }

    @AfterEach
    void tearDown() {
        resultRepository.deleteAll();
    }

    @DisplayName("중복 여부를 체크하는 로직")
    @Nested
    class hasDuplicatedResult {
        @DisplayName("중복된 결과가 있으면 true를 반환한다")
        @Test
        void trueResult() {
            PairMatchingRequest pairMatchingRequest = new PairMatchingRequest(
                    new PairMatchingRequestDto(new String[]{"백엔드", "레벨1", "자동차경주"}));

            boolean actual = resultRepository.hasDuplicatedResult(pairMatchingRequest);
            assertThat(actual).isTrue();
        }

        @DisplayName("중복된 결과가 없으면 false를 반환한다")
        @Test
        void falseResult() {
            PairMatchingRequest pairMatchingRequest = new PairMatchingRequest(
                    new PairMatchingRequestDto(new String[]{"프론트엔드", "레벨1", "자동차경주"}));

            boolean actual = resultRepository.hasDuplicatedResult(pairMatchingRequest);
            assertThat(actual).isFalse();
        }
    }

    @DisplayName("중복되진 않지만, 같은 코스의 같은 레벨을 조회할 때")
    @Nested
    class hasSameLevelResult {
        @DisplayName("같은 코스, 같은 레벨일 경우 true를 반환한다.")
        @Test
        void trueResult() {
            PairMatchingRequest pairMatchingRequest = new PairMatchingRequest(
                    new PairMatchingRequestDto(new String[]{"백엔드", "레벨1", "로또"}));

            boolean actual = resultRepository.hasSameLevelResult(pairMatchingRequest);
            assertThat(actual).isTrue();
        }

        @DisplayName("같은 코스지만 같은 레벨이 아닐 경우 false를 반환한다.")
        @Test
        void falseResult1() {
            PairMatchingRequest pairMatchingRequest = new PairMatchingRequest(
                    new PairMatchingRequestDto(new String[]{"백엔드", "레벨4", "성능개선"}));

            boolean actual = resultRepository.hasSameLevelResult(pairMatchingRequest);
            assertThat(actual).isFalse();
        }

        @DisplayName("다른 코스지만 같은 레벨일 경우 false를 반환한다.")
        @Test
        void falseResult2() {
            PairMatchingRequest pairMatchingRequest = new PairMatchingRequest(
                    new PairMatchingRequestDto(new String[]{"프론트엔드", "레벨1", "로또"}));

            boolean actual = resultRepository.hasSameLevelResult(pairMatchingRequest);
            assertThat(actual).isFalse();
        }
    }

    @DisplayName("중복되지 않는 같은 코스, 같은 레벨의 결과를 모두 조회한다.")
    @Test
    void findSameLevelResult() {
        PairMatchingRequest pairMatchingRequest = new PairMatchingRequest(
                new PairMatchingRequestDto(new String[]{"백엔드", "레벨1", "로또"}));

        List<PairMatchResult> actual = resultRepository.findSameLevelResult(pairMatchingRequest);
        int expected = 2;
        assertThat(actual).hasSize(expected);
    }
}