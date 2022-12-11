package pairmatching.constants;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Command {
    MATCHING("1"),
    READ("2"),
    INITIALIZATION("3"),
    QUIT("Q"),;

    private static final Map<String, Command> VALUE_TO_COMMAND = Arrays.stream(values())
            .collect(Collectors.toUnmodifiableMap(
                    command -> command.value,
                    command -> command
            ));

    public static Command of(String value) {
        if (!VALUE_TO_COMMAND.containsKey(value)) {
            throw new IllegalArgumentException("없는 커맨드 입니다. 1, 2, 3, Q 중에 입력해 주세요.");
        }

        return VALUE_TO_COMMAND.get(value);
    }

    private final String value;

    Command(String value) {
        this.value = value;
    }
}
