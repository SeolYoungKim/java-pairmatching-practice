package pairmatching.constants;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum Mission {
    RACING(Level.LEVEL1, "자동차 경주"),
    LOTTO(Level.LEVEL1, "로또"),
    NUMBER_BASEBALL(Level.LEVEL1, "숫자야구게임"),
    PERFORMANCE_IMPROVEMENT(Level.LEVEL4, "성능개선"),
    DEPLOY(Level.LEVEL4, "배포"),;

    private static final Map<String, Mission> NAME_TO_MISSION = Arrays.stream(values())
            .collect(Collectors.toUnmodifiableMap(
                    mission -> mission.name,
                    mission -> mission
            ));

    public static Mission of(String name) {
        if (!NAME_TO_MISSION.containsKey(name)) {
            throw new IllegalArgumentException("없는 미션 이름입니다.");
        }

        return NAME_TO_MISSION.get(name);
    }


    private final Level level;
    private final String name;

    Mission(Level level, String name) {
        this.level = level;
        this.name = name;
    }

    public boolean consistOf(Level level) {
        return this.level == level;
    }
}
