package pairmatching.view;

import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Crew;
import pairmatching.domain.PairMatchResult;

public class OutputView {
    public void printMenu() {
        System.out.println(""
                + "#############################################\n"
                + "과정: 백엔드 | 프론트엔드\n"
                + "미션:\n"
                + "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n"
                + "  - 레벨2: 장바구니 | 결제 | 지하철노선도\n"
                + "  - 레벨3: \n"
                + "  - 레벨4: 성능개선 | 배포\n"
                + "  - 레벨5: \n"
                + "############################################");
    }

    public void printResult(PairMatchResult result) {
        List<List<Crew>> results = result.matchResult();
        String string = results.stream()
                .map(crews -> crews.stream()
                        .map(Crew::name)
                        .collect(Collectors.joining(" : ")))
                .collect(Collectors.joining("\n"));

        System.out.println("페어 매칭 결과입니다.");
        System.out.println(string);
    }

    public void printNoResultMessage() {
        System.out.println("해당되는 페어 매칭 결과가 없습니다.");
    }
}
