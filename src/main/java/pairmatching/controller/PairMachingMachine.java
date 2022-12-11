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
                pairMatching();
                continue;
            }

            if (command.isRead()) {
                readPair();
                continue;
            }

            if (command.isInitialization()) {
                pairMatcher.clear();
            }
        }
    }

    private void pairMatching() {
        outputView.printMenu();
        Optional<PairMatchResult> result;

        outer:
        while (true) {
            PairMatchingRequestDto dto = inputView.selectCondition();
            PairMatchingRequest request = new PairMatchingRequest(dto);
            result = pairMatcher.pairMatchResult(request, false);

            while (result.isEmpty()) {
                String rematching = inputView.rematching();
                if (rematching.equals("네")) {
                    result = pairMatcher.pairMatchResult(request, true);
                    continue;
                }

                if (rematching.equals("아니오")) {
                    continue outer;
                }
            }

            break;
        }

        PairMatchResult pairMatchResult = result.get();
        outputView.printResult(pairMatchResult);
    }

    private void readPair() {
        outputView.printMenu();
        PairMatchingRequest request = new PairMatchingRequest(inputView.selectCondition());

        Optional<PairMatchResult> result = pairMatcher.findBy(request);
        if (result.isEmpty()) {
            outputView.printNoResultMessage();
            return;
        }

        outputView.printResult(result.get());
    }
}
