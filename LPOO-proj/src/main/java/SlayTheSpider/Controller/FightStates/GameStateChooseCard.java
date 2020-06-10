package SlayTheSpider.Controller.FightStates;

import SlayTheSpider.Model.Game.Deck;
import SlayTheSpider.Model.Game.Player;
import SlayTheSpider.Model.FightModel;

public class GameStateChooseCard implements GameStateFighting {
    @Override
    public void onArrowUp(FightModel model) {
        Deck deck = model.getPlayer().getDeck();
        deck.selectCardLeft();
    }

    @Override
    public void onArrowDown(FightModel model) {
        Deck deck = model.getPlayer().getDeck();
        deck.selectCardRight();
    }

    @Override
    public void onArrowLeft(FightModel model) {
        Deck deck = model.getPlayer().getDeck();
        deck.selectCardLeft();
    }

    @Override
    public void onArrowRight(FightModel model) {
        Deck deck = model.getPlayer().getDeck();
        deck.selectCardRight();
    }

    @Override
    public GameStateFighting onEnter(FightModel model) {
        Player player = model.getPlayer();
        if (player.getDeck().getSelectedCard() == null || !player.canAffordCard())
            return this;
        if (player.getDeck().getSelectedCard().needsTargetSelection()) {
            player.getTargets().selectRight();
            return new GameStateChooseEnemy();
        }
        player.useSelectedCard();
        return this;
    }

    @Override
    public GameStateFighting onEsc(FightModel model) {
        return new GameStateEnemyTurn();
    }

    @Override
    public String getTitle() {
        return "Choose card, (Arrow Keys + Enter) or End Turn! (Esc)";
    }
}
