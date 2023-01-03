package pairmatching.dto;

import pairmatching.constants.Level;
import pairmatching.constants.Mission;

public class PairMatchingRequestDto {
    private final String course;
    private final Level level;
    private final Mission mission;

    public PairMatchingRequestDto(String[] strings) {
        validateCourse(strings[0]);

        this.course = strings[0].trim();
        this.level = Level.of(strings[1].trim());
        this.mission = Mission.of(strings[2].trim());

        validateMission(mission, level);
    }

    private void validateCourse(String course) {
        if (!course.equals("백엔드") && !course.equals("프론트엔드")) {
            throw new IllegalArgumentException("과정은 백엔드/프론트엔드만 입력해 주세요.");
        }
    }

    private void validateMission(Mission mission, Level level) {
        if (!mission.consistOf(level)) {
            throw new IllegalArgumentException("레벨 내 미션이 아닙니다.");
        }
    }

    public String course() {
        return course;
    }

    public Level level() {
        return level;
    }

    public Mission mission() {
        return mission;
    }
}
