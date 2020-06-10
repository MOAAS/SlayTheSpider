package SlayTheSpider.Model.Game.CardEffects;

import SlayTheSpider.Model.Game.CardEffect.Conditions.CardEffectCondition;
import SlayTheSpider.Model.Game.CardEffect.Conditions.CardEffectConditionList;
import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.Player;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;

public class TestConditionList {
    @Test
    public void TestConditionList() {
        CardEffectConditionList conditionList = new CardEffectConditionList();

        assertTrue(conditionList.getList().size() == 0);
        assertTrue(conditionList.getList() instanceof ArrayList);

        CardEffectCondition conditionMock1 = Mockito.mock(CardEffectCondition.class);
        CardEffectCondition conditionMock2 = Mockito.mock(CardEffectCondition.class);

        List<CardEffectCondition> cardEffectConditionList = new ArrayList<>();
        cardEffectConditionList.add(conditionMock1);
        cardEffectConditionList.add(conditionMock2);

        conditionList = new CardEffectConditionList(cardEffectConditionList);

        Mockito.when(conditionMock1.verify(any(), any())).thenReturn(true);
        Mockito.when(conditionMock2.verify(any(), any())).thenReturn(false);
        assertFalse(conditionList.satisfies(Mockito.mock(Player.class), Mockito.mock(Character.class)));

        Mockito.when(conditionMock1.verify(any(), any())).thenReturn(true);
        Mockito.when(conditionMock2.verify(any(), any())).thenReturn(true);
        assertTrue(conditionList.satisfies(Mockito.mock(Player.class), Mockito.mock(Character.class)));
    }
}
