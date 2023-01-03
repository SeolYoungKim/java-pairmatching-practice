package pairmatching.constants;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LevelTest {
    @ParameterizedTest(name = "정의되지 않은 값을 찾는 경우 예외를 발생시킨다.")
    @ValueSource(strings = {"레벨6", "2"})
    void ofFail(String input) {
        assertThatThrownBy(() -> Level.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("레벨은 1~5까지 있습니다. '레벨1'과 같이 입력해 주세요.");
    }
}