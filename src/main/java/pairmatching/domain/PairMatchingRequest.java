package pairmatching.domain;

import java.util.Objects;
import pairmatching.constants.Level;
import pairmatching.constants.Mission;

public class PairMatchingRequest {
    private final String course;
    private final Level level;
    private final Mission mission;

    public PairMatchingRequest(String course, Level level, Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public boolean isSameLevel(PairMatchingRequest other) {
        return this.level.equals(other.level);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PairMatchingRequest that = (PairMatchingRequest) o;
        return Objects.equals(course, that.course) && level == that.level
                && mission == that.mission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, level, mission);
    }
}
