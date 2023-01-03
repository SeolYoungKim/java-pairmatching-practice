package pairmatching.domain;

import java.util.Objects;

public class Crew implements Comparable<Crew> {
    private final String name;

    public Crew(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Crew crew = (Crew) o;
        return Objects.equals(name, crew.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Crew{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Crew that) {
        return this.name.compareTo(that.name);
    }
}
