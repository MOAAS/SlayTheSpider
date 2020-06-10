package SlayTheSpider.MVC;

import SlayTheSpider.Controller.FightStates.GameStateChooseCard;
import SlayTheSpider.Controller.FightStates.GameStateChooseEnemy;
import SlayTheSpider.Controller.FightStates.GameStateEnemyTurn;
import SlayTheSpider.Controller.FightStates.GameStateFighting;
import SlayTheSpider.Model.Game.*;
import SlayTheSpider.Model.FightModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestGameState {
    private FightModel modelMock = Mockito.mock(FightModel.class);
    private Player playerMock = Mockito.mock(Player.class);
    private Card cardMock = Mockito.mock(Card.class);
    private Deck deckMock = Mockito.mock(Deck.class);
    private Fight fightMock = Mockito.mock(Fight.class);
    private TargetList targetListMock = Mockito.mock(TargetList.class);

    @Before
    public void setup() {
        modelMock = Mockito.mock(FightModel.class);
        playerMock = Mockito.mock(Player.class);
        cardMock = Mockito.mock(Card.class);
        fightMock = Mockito.mock(Fight.class);
        deckMock = Mockito.mock(Deck.class);
        targetListMock = Mockito.mock(TargetList.class);

        Mockito.when(modelMock.getPlayer()).thenReturn(playerMock);
        Mockito.when(modelMock.getFight()).thenReturn(fightMock);

        Mockito.when(deckMock.getSelectedCard()).thenReturn(cardMock);

        Mockito.when(playerMock.getTargets()).thenReturn(targetListMock);
        Mockito.when(playerMock.getDeck()).thenReturn(deckMock);

    }


    @Test
    public void TestChooseCard() {
        GameStateFighting state = new GameStateChooseCard();

        state.onArrowUp(modelMock);
        state.onArrowUp(modelMock);
        Mockito.verify(deckMock, times(2)).selectCardLeft();
        Mockito.verify(deckMock, times(0)).selectCardRight();

        state.onArrowDown(modelMock);
        state.onArrowDown(modelMock);
        state.onArrowDown(modelMock);
        Mockito.verify(deckMock, times(2)).selectCardLeft();
        Mockito.verify(deckMock, times(3)).selectCardRight();

        state.onArrowLeft(modelMock);
        Mockito.verify(deckMock, times(3)).selectCardLeft();
        Mockito.verify(deckMock, times(3)).selectCardRight();

        state.onArrowRight(modelMock);
        Mockito.verify(deckMock, times(3)).selectCardLeft();
        Mockito.verify(deckMock, times(4)).selectCardRight();

        Mockito.when(playerMock.canAffordCard()).thenReturn(true);
        Mockito.when(cardMock.needsTargetSelection()).thenReturn(true);
        assertTrue(state.onEnter(modelMock) instanceof GameStateChooseEnemy);
        Mockito.verify(playerMock, never()).useSelectedCard();
        Mockito.verify(targetListMock, times(1)).selectRight();

        Mockito.when(playerMock.canAffordCard()).thenReturn(false);
        Mockito.when(cardMock.needsTargetSelection()).thenReturn(true);
        assertTrue(state.onEnter(modelMock) instanceof GameStateChooseCard);
        Mockito.verify(playerMock, never()).useSelectedCard();

        Mockito.when(playerMock.canAffordCard()).thenReturn(true);
        Mockito.when(cardMock.needsTargetSelection()).thenReturn(false);
        assertTrue(state.onEnter(modelMock) instanceof GameStateChooseCard);
        Mockito.verify(playerMock, times(1)).useSelectedCard();

        state.onEsc(modelMock);
        assertTrue(state.onEsc(modelMock) instanceof GameStateEnemyTurn);
        assertEquals(state.getTitle(), "Choose card, (Arrow Keys + Enter) or End Turn! (Esc)");
    }
    @Test
    public void TestEnemyTurn() {
        GameStateFighting state = new GameStateEnemyTurn();
        state.onArrowUp(modelMock);
        state.onArrowDown(modelMock);
        state.onArrowLeft(modelMock);
        state.onArrowRight(modelMock);

        Mockito.verifyZeroInteractions(modelMock);

        assertTrue(state.onEsc(modelMock) instanceof GameStateChooseCard);

        assertTrue(state.onEnter(modelMock) instanceof GameStateChooseCard);
        Mockito.verify(fightMock, times(1)).endTurn(playerMock);
        assertEquals(state.getTitle(),  "Enemy Turn! (Press ENTER to continue)");
    }

    @Test
    public void TestChooseEnemy() {
        GameStateFighting state = new GameStateChooseEnemy();

        state.onArrowUp(modelMock);
        Mockito.verify(targetListMock, times(1)).selectLeft();
        Mockito.verify(targetListMock, times(0)).selectRight();

        state.onArrowDown(modelMock);
        Mockito.verify(targetListMock, times(1)).selectLeft();
        Mockito.verify(targetListMock, times(1)).selectRight();

        state.onArrowLeft(modelMock);
        Mockito.verify(targetListMock, times(2)).selectLeft();
        Mockito.verify(targetListMock, times(1)).selectRight();

        state.onArrowRight(modelMock);

        Mockito.verify(targetListMock, times(2)).selectLeft();
        Mockito.verify(targetListMock, times(2)).selectRight();

        assertTrue(state.onEnter(modelMock) instanceof GameStateChooseCard);
        Mockito.verify(playerMock, times(1)).useSelectedCard();
        Mockito.verify(targetListMock, times(1)).selectPlayer();


        assertTrue(state.onEsc(modelMock) instanceof GameStateChooseCard);
        Mockito.verify(targetListMock, times(2)).selectPlayer();
        assertEquals(state.getTitle(), "Choose enemy! (Arrow Keys + Enter)");
    }
}
