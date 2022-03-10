package controller;

import java.util.List;
import dto.InitGameDto;
import dto.NamesDto;
import service.BlackJackService;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private final BlackJackService service;

    public BlackJackController(BlackJackService service) {
        this.service = service;
    }

    public void initGame() {
        List<String> names = InputView.inputPlayerNames();
        NamesDto namesDto = new NamesDto(names);
        InitGameDto initGameDto = service.initGame(namesDto);
        OutputView.printInit(initGameDto);
    }

    public void hitPlayers() {
        List<String> names = service.getPlayerNames();
        for (String name : names) {
            hit(name);
        }
    }

    private void hit(String name) {
        boolean decidedToHit;
        do {
            decidedToHit = service.canReceiveCard(name) && InputView.inputHitResponse(name);
            OutputView.printParticipatorHit(decidedToHit, service.tryToHit(name));
        } while (decidedToHit);
    }
}
