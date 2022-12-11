package pairmatching.constants;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MissionTest {
    @ParameterizedTest(name = "정의되지 않은 값을 찾는 경우 예외를 발생시킨다.")
    @ValueSource(strings = {"블랙잭", "지하철미션"})
    void ofFail(String input) {
        assertThatThrownBy(() -> Mission.of(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("없는 미션 이름입니다.");
    }
}