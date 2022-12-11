package pairmatching.controller;

import java.util.Optional;
import pairmatching.constants.Command;
import pairmatching.domain.PairMatchResult;
import pairmatching.domain.PairMatcher;
import pairmatching.domain.PairMatchingRequest;
import pairmatching.dto.PairMatchingRequestDto;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMachingMachine {
    private final InputView inputView;
    private final OutputView outputView;
    private final PairMatcher pairMatcher;

    public PairMachingMachine(InputView inputView, OutputView outputView, PairMatcher pairMatcher) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.pairMatcher = pairMatcher;
    }

    public void run() {
        Command command = Command.NOTHING;
        while (!command.isQuit()) {
            command = inputView.selectFunction();
            if (command.isMatching()) {
                //페어 매칭 수행
                pairMatching();
                continue;
            }

            if (command.isRead()) {
                //조회 수행
                continue;
            }

            if (command.isInitialization()) {
                //초기화 진행
                pairMatcher.clear();
            }
        }
    }

    private void pairMatching() {
        outputView.printMenu();
        PairMatchingRequestDto dto;
        PairMatchingRequest request;
        Optional<PairMatchResult> result;

        outer:
        while (true) {
            dto = inputView.pairMatching();
            request = new PairMatchingRequest(dto);
            result = pairMatcher.pairMatchResult(request, false);

            while (result.isEmpty()) {  // 결과가 빈 경우, 중복 결과가 있는 것이므로 재매칭 물어야함
                String rematching = inputView.rematching();
                if (rematching.equals("네")) {
                    result = pairMatcher.pairMatchResult(request, true);
                    continue;
                }

                if (rematching.equals("아니오")) {
                    // 아니오를 말했을 때 위 과정을 다시 해야 함
                    continue outer;
                }
            }

            break;
        }

        // 출력
        PairMatchResult pairMatchResult = result.get();
        outputView.printResult(pairMatchResult);
    }
}
