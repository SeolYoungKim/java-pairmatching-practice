package pairmatching.constants;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Level {
    LEVEL1("레벨1"),
    LEVEL2("레벨2"),
    LEVEL3("레벨3"),
    LEVEL4("레벨4"),
    LEVEL5("레벨5");

    private static final Map<String, Level> NAME_TO_LEVEL = Arrays.stream(values())
            .collect(Collectors.toUnmodifiableMap(
                    level -> level.name,
                    level -> level
            ));

    public static Level of(String name) {
        if (!NAME_TO_LEVEL.containsKey(name)) {
            throw new IllegalArgumentException("레벨은 1~5까지 있습니다. '레벨1'과 같이 입력해 주세요.");
        }

        return NAME_TO_LEVEL.get(name);
    }

    private final String name;

    Level(String name) {
        this.name = name;
    }
}
