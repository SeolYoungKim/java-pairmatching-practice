package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PairMatchResultTest {

    @DisplayName("매칭 이력을 조회할 때")
    @Nested
    class hasAlreadyMatchedInSameLevel {

        private PairMatchResult pairMatchResult;

        @BeforeEach
        void setUp() {
            pairMatchResult = new PairMatchResult(new ArrayList<>(List.of(
                    List.of(new Crew("철수"), new Crew("영희")),
                    List.of(new Crew("민수"), new Crew("박수")),
                    List.of(new Crew("소율"), new Crew("하율"))
            )));
        }

        @DisplayName("매칭이 되었던 크루가 존재하면 true를 반환한다.")
        @Test
        void resultTrue() {
            PairMatchResult sameLevelResults = new PairMatchResult(new ArrayList<>(List.of(
                    List.of(new Crew("영희"), new Crew("철수")),
                    List.of(new Crew("포비"), new Crew("크롱"))
            )));

            List<PairMatchResult> pairMatchResults = new ArrayList<>(List.of(sameLevelResults));

            boolean actual = pairMatchResult.hasAlreadyMatchedInSameLevel(pairMatchResults);
            Assertions.assertThat(actual).isTrue();
        }

        @DisplayName("매칭이 되었던 크루가 존재하지 않으면 false를 반환한다.")
        @Test
        void resultFalse() {
            PairMatchResult sameLevelResult = new PairMatchResult(new ArrayList<>(List.of(
                    List.of(new Crew("박수"), new Crew("철수")),
                    List.of(new Crew("포비"), new Crew("크롱"))
            )));

            List<PairMatchResult> pairMatchResults = new ArrayList<>(List.of(sameLevelResult));

            boolean actual = pairMatchResult.hasAlreadyMatchedInSameLevel(pairMatchResults);
            Assertions.assertThat(actual).isFalse();
        }

    }
}