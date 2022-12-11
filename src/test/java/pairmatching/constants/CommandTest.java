package pairmatching.constants;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTest {
    @DisplayName("정의되지 않은 값을 찾는 경우 예외를 발생시킨다.")
    @Test
    void ofFail1() {
        assertThatThrownBy(() -> Command.of("4"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("없는 커맨드 입니다. 1, 2, 3, Q 중에 입력해 주세요.");
    }

    @DisplayName("Nothing이 입력된 경우 예외를 발생시킨다.(enum 내부에 정의됨)")
    @Test
    void ofFail2() {
        assertThatThrownBy(() -> Command.of("Nothing"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("없는 커맨드 입니다. 1, 2, 3, Q 중에 입력해 주세요.");
    }
}