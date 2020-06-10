package SlayTheSpider.Model.Game.FightRewards;

import SlayTheSpider.Model.Game.Card;
import SlayTheSpider.Model.Game.Deck;
import SlayTheSpider.Model.Game.HealthBar.PlayerHealthBar;
import SlayTheSpider.Model.Game.Player;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class TestReward {
    @Test
    public void TestList() {
        RewardList rewardList = new RewardList(2);

        assertEquals(rewardList.getLeft(), 2);

        assertFalse(rewardList.done());
        assertEquals(rewardList.getRewardList().size(), 0);

        Reward rewardMock1 = Mockito.mock(Reward.class);
        Reward rewardMock2 = Mockito.mock(Reward.class);
        Reward rewardMock3 = Mockito.mock(Reward.class);
        rewardList.addReward(rewardMock1);
        rewardList.addReward(rewardMock2);
        rewardList.addReward(rewardMock3);
        assertEquals(rewardList.getRewardList().size(), 3);

        assertSame(rewardList.getRewardList().getSelected(), rewardMock1);

        rewardList.selectRight();
        rewardList.selectRight();
        assertSame(rewardList.getRewardList().getSelected(), rewardMock3);

        rewardList.selectLeft();
        assertSame(rewardList.getRewardList().getSelected(), rewardMock2);

        Player playerMock = Mockito.mock(Player.class);

        rewardList.consumeReward(playerMock);
        Mockito.verify(rewardMock2, times(1)).applyReward(playerMock);
        assertSame(rewardList.getRewardList().getSelected(), rewardMock3);
        assertEquals(rewardList.getRewardList().size(), 2);
        assertEquals(rewardList.getLeft(), 1);
        assertFalse(rewardList.done());

        rewardList.consumeReward(playerMock);
        Mockito.verify(rewardMock3, times(1)).applyReward(playerMock);
        assertSame(rewardList.getRewardList().getSelected(), rewardMock1);
        assertEquals(rewardList.getRewardList().size(), 1);
        assertEquals(rewardList.getLeft(), 0);
        assertTrue(rewardList.done());

        rewardList.consumeReward(playerMock);
        Mockito.verify(rewardMock3, times(1)).applyReward(playerMock);
        Mockito.verify(rewardMock1, times(0)).applyReward(playerMock);
        assertSame(rewardList.getRewardList().getSelected(), rewardMock1);
        assertEquals(rewardList.getRewardList().size(), 1);
        assertEquals(rewardList.getLeft(), 0);
        assertTrue(rewardList.done());

        RewardList rewardList2 = new RewardList(4);
        rewardList2.addReward(rewardMock1);
        assertEquals(rewardList2.getLeft(), 4);

        rewardList2.consumeReward(playerMock);
        assertEquals(rewardList2.getLeft(), 3);

        rewardList2.consumeReward(playerMock);
        assertEquals(rewardList2.getLeft(), 3);

        rewardList2.skip();
        assertEquals(rewardList2.getLeft(), 0);
    }

    @Test
    public void TestListCopy() {
        RewardList rewardList = new RewardList(2);
        Reward rewardMock1 = Mockito.mock(Reward.class);
        Reward rewardMock2 = Mockito.mock(Reward.class);
        Reward rewardMock3 = Mockito.mock(Reward.class);
        Mockito.when(rewardMock1.reroll()).thenReturn(rewardMock1);
        Mockito.when(rewardMock2.reroll()).thenReturn(rewardMock2);
        Mockito.when(rewardMock3.reroll()).thenReturn(rewardMock3);

        rewardList.addReward(rewardMock1);
        rewardList.addReward(rewardMock2);
        rewardList.addReward(rewardMock3);

        RewardList copy = rewardList.copy();

        assertSame(copy.getRewardList().getList().get(0), rewardMock1);
        assertSame(copy.getRewardList().getList().get(1), rewardMock2);
        assertSame(copy.getRewardList().getList().get(2), rewardMock3);
        assertEquals(copy.getLeft(), 2);
    }

    @Test
    public void TestApple() {
        AppleReward reward = new AppleReward(3, 3);
        AppleReward rerolled = reward.reroll();

        assertNotSame(reward, rerolled);
        assertEquals(rerolled.getName(), reward.getName());
        assertEquals(rerolled.getDescription(), reward.getDescription());
        assertEquals(rerolled.getColor(), "ff0000");
        assertEquals(rerolled.getDetails().size(), 1);


        PlayerHealthBar playerHealthBarMock = Mockito.mock(PlayerHealthBar.class);
        Player playerMock = Mockito.mock(Player.class);

        Mockito.when(playerMock.getHealthBar()).thenReturn(playerHealthBarMock);

        assertEquals(reward.getName(), "Healthy apple");
        assertEquals(reward.getDescription(), "Permanently increases MaxHP by 3");

        reward.applyReward(playerMock);

        Mockito.verify(playerHealthBarMock, times(1)).increaseMaxHealth(3);
        Mockito.verify(playerMock, times(1)).heal(3);
    }

    @Test
    public void TestCard() {
        List<Card> cardList = new ArrayList<>();
        Card cardMock = Mockito.mock(Card.class);
        cardList.add(cardMock);

        List<String> description = new ArrayList<>();
        description.add("a");
        description.add("b");
        when(cardMock.getName()).thenReturn("Name");
        when(cardMock.getColor()).thenReturn("00ffaa");
        when(cardMock.getDescription()).thenReturn(description);

        Player playerMock = Mockito.mock(Player.class);
        Deck deckMock = Mockito.mock(Deck.class);
        Mockito.when(playerMock.getDeck()).thenReturn(deckMock);

        CardReward reward = new CardReward(cardList);
        CardReward rerolled = reward.reroll();

        assertNotSame(reward, rerolled);
        assertEquals(rerolled.getName(), reward.getName());
        assertEquals(rerolled.getDescription(), reward.getDescription());
        assertEquals(rerolled.getColor(), "00ffaa");
        assertEquals(rerolled.getDetails().size(), 2);

        assertEquals(reward.getName(), "(0) Card: Name");
        assertEquals(reward.getDescription(), "Permanently adds a Name to your deck.");

        reward.applyReward(playerMock);
        Mockito.verify(deckMock, times(1)).addCard(cardMock);


    }

    @Test
    public void TestMana() {
        ManaReward reward = new ManaReward();

        PlayerHealthBar playerHealthBarMock = Mockito.mock(PlayerHealthBar.class);
        Player playerMock = Mockito.mock(Player.class);

        Mockito.when(playerMock.getHealthBar()).thenReturn(playerHealthBarMock);

        reward.applyReward(playerMock);

        Mockito.verify(playerHealthBarMock, times(1)).increaseManaCap(1);
        assertEquals(reward.getName(), "Mana Point");
        assertEquals(reward.getDescription(), "Increases mana cap by 1");
        assertEquals(reward.getColor(), "00D890");
        assertEquals(reward.getDetails().size(), 1);

        assertSame(reward, reward.reroll());
    }

    @Test
    public void TestNull() {
        NullReward reward = new NullReward();
        reward.applyReward(null);

        assertEquals(reward.getName(), "Null");
        assertEquals(reward.getDescription(), "Error: Null reward.");
        assertEquals(reward.getColor(), "cccccc");
        assertEquals(reward.getDetails().size(), 0);

        assertSame(reward, reward.reroll());
    }
}
