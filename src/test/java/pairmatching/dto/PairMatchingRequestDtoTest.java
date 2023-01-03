package pairmatching.dto;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PairMatchingRequestDtoTest {

    @DisplayName("잘못된 값을 입력받았을 경우")
    @Nested
    class constructorTest {
        @DisplayName("과정 입력이 잘못된 경우")
        @Test
        void generateByIllegalCourseName() {
            String[] input = {"DevOps", "레벨1", "자동차경주"};
            assertThatThrownBy(() -> new PairMatchingRequestDto(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("과정은 백엔드/프론트엔드만 입력해 주세요.");
        }

        @DisplayName("레벨과 미션 입력이 잘못된 경우 (레벨에 맞지 않는 미션인 경우)")
        @Test
        void generateByIllegalLevelAndMission() {
            String[] input = {"백엔드", "레벨1", "배포"};
            assertThatThrownBy(() -> new PairMatchingRequestDto(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("레벨 내 미션이 아닙니다.");
        }
    }
}