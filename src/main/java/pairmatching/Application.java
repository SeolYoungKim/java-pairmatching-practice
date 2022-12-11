package pairmatching;

import pairmatching.controller.PairMachingMachine;
import pairmatching.domain.CrewNameFileReader;
import pairmatching.domain.CrewRepository;
import pairmatching.domain.PairMatchResultRepository;
import pairmatching.domain.PairMatcher;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    public static void main(String[] args) {
        PairMachingMachine pairMachingMachine = new PairMachingMachine(
                new InputView(),
                new OutputView(),
                new PairMatcher(
                        new CrewRepository(new CrewNameFileReader()),
                        new PairMatchResultRepository())
        );

        pairMachingMachine.run();
    }
}
