package pairmatching.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.function.Supplier;
import pairmatching.constants.Command;
import pairmatching.dto.PairMatchingRequestDto;

public class InputView {
    public Command command() {
        System.out.println(
                "기능을 선택하세요.\n"
                + "1. 페어 매칭\n"
                + "2. 페어 조회\n"
                + "3. 페어 초기화\n"
                + "Q. 종료"
        );

        return untilInputIsCorrect(() -> Command.of(readLine()));
    }

    public PairMatchingRequestDto pairMatching() {
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

        return untilInputIsCorrect(() -> new PairMatchingRequestDto(readLine().split(",")));
    }

    private <T> T untilInputIsCorrect(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }
}
