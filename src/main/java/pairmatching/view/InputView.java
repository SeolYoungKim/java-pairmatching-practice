package pairmatching.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import camp.nextstep.edu.missionutils.Console;
import java.util.function.Supplier;
import pairmatching.constants.Command;
import pairmatching.dto.PairMatchingRequestDto;

public class InputView {
    public Command selectFunction() {
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
                + "과정, 레벨, 미션을 선택하세요.\n"
                + "ex) 백엔드, 레벨1, 자동차경주");

        return untilInputIsCorrect(() -> new PairMatchingRequestDto(readLine().split(",")));
    }

    public String rematching() {
        System.out.println(""
                + "매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n"
                + "네 | 아니오");
        return Console.readLine();
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
