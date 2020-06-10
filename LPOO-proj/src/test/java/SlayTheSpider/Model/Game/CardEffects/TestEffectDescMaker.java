package SlayTheSpider.Model.Game.CardEffects;

import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import SlayTheSpider.Model.Game.CardEffect.Conditions.CardEffectCondition;
import SlayTheSpider.Model.Game.CardEffect.Conditions.CardEffectConditionGroup;
import SlayTheSpider.Model.Game.CardEffect.EffectDescMaker;
import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.Player;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestEffectDescMaker {
    @Test
    public void TestDescMaker() {
        CardEffectCondition conditionMock1 = Mockito.mock(CardEffectCondition.class);
        CardEffectCondition conditionMock2 = Mockito.mock(CardEffectCondition.class);
        CardEffectCondition conditionMock3 = Mockito.mock(CardEffectCondition.class);
        CardEffect effectMock = Mockito.mock(CardEffect.class);

        EffectDescMaker maker = new EffectDescMaker(effectMock);

        Mockito.when(effectMock.getDescription()).thenReturn("Does X.");
        Mockito.when(conditionMock1.getDescription()).thenReturn("A");
        Mockito.when(conditionMock2.getDescription()).thenReturn("B");
        Mockito.when(conditionMock3.getDescription()).thenReturn("C");

        CardEffectConditionGroup groupStub = new CardEffectConditionGroup() {
            @Override
            public boolean satisfies(Player user, Character target) { return false; }

            @Override
            public List<CardEffectCondition> getList() {
                return new ArrayList<>();
            }
        };

        Mockito.when(effectMock.getConditions()).thenReturn(groupStub);
        assertEquals(maker.getDescription(), "Does X.");

        groupStub = new CardEffectConditionGroup() {
            @Override
            public boolean satisfies(Player user, Character target) { return false; }

            @Override
            public List<CardEffectCondition> getList() {
                List<CardEffectCondition> list = new ArrayList<>();
                list.add(conditionMock1);
                return list;
            }
        };

        Mockito.when(effectMock.getConditions()).thenReturn(groupStub);
        assertEquals(maker.getDescription(), "If A, Does X.");

        groupStub = new CardEffectConditionGroup() {
            @Override
            public boolean satisfies(Player user, Character target) { return false; }

            @Override
            public List<CardEffectCondition> getList() {
                List<CardEffectCondition> list = new ArrayList<>();
                list.add(conditionMock1);
                list.add(conditionMock2);
                list.add(conditionMock3);
                return list;
            }
        };

        Mockito.when(effectMock.getConditions()).thenReturn(groupStub);
        assertEquals(maker.getDescription(), "If A, B, and C, Does X.");
    }
}
