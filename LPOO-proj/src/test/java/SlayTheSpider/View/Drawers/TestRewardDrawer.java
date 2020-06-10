package SlayTheSpider.View.Drawers;

import SlayTheSpider.View.Drawers.LanternaDrawers.LanternaRewardDrawer;
import SlayTheSpider.View.Drawers.SwingDrawers.SwingRewardDrawer;
import SlayTheSpider.Model.Game.FightRewards.Reward;
import SlayTheSpider.Model.Game.FightRewards.RewardList;
import SlayTheSpider.Model.ListCycler.ArrayListCycler;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;

public class TestRewardDrawer {
    private RewardList rewards = Mockito.mock(RewardList.class);
    @Before
    public void setup() {
        rewards = Mockito.mock(RewardList.class);

        ArrayListCycler<Reward> listCycler = new ArrayListCycler<>(new ArrayList<>());

        Reward reward1 = Mockito.mock(Reward.class);
        Reward reward2 = Mockito.mock(Reward.class);
        Reward reward3 = Mockito.mock(Reward.class);
        Mockito.when(reward1.getDescription()).thenReturn("DESC1");
        Mockito.when(reward2.getDescription()).thenReturn("DESC2");
        Mockito.when(reward3.getDescription()).thenReturn("DESC3");

        Mockito.when(reward1.getName()).thenReturn("NAME1");
        Mockito.when(reward2.getName()).thenReturn("NAME2");
        Mockito.when(reward3.getName()).thenReturn("NAME3");

        Mockito.when(reward1.getColor()).thenReturn("1");
        Mockito.when(reward2.getName()).thenReturn("2");
        Mockito.when(reward3.getName()).thenReturn("3");

        List<String> details1 = new ArrayList<>();
        List<String> details2 = new ArrayList<>();
        List<String> details3 = new ArrayList<>();
        details1.add("Details1");
        details2.add("Details2");
        details3.add("Details3");

        Mockito.when(reward1.getDetails()).thenReturn(details1);
        Mockito.when(reward2.getDetails()).thenReturn(details2);
        Mockito.when(reward3.getDetails()).thenReturn(details3);

        listCycler.add(reward1);
        listCycler.add(reward2);
        listCycler.add(reward3);

        Mockito.when(rewards.getLeft()).thenReturn(2);
        Mockito.when(rewards.getRewardList()).thenReturn(listCycler);
    }

    @Test
    public void TestDrawSwing() {
        FontMetrics metrics = Mockito.mock(FontMetrics.class);
        Graphics graphics = Mockito.mock(Graphics.class);
        Mockito.when(graphics.getFontMetrics()).thenReturn(metrics);
        Mockito.when(metrics.stringWidth(anyString())).thenReturn(12);
        SwingRewardDrawer drawer = new SwingRewardDrawer(rewards);

        drawer.draw(graphics);
        Mockito.verify(graphics, times(7)).drawString(anyString(), anyInt(), anyInt());
        Mockito.verify(graphics, times(6)).setColor(any());
        Mockito.verify(graphics, times(3)).setFont(any());
        Mockito.verify(graphics, times(1)).drawString("--> NAME1: DESC1", 30, 90);
        Mockito.verify(graphics, times(1)).drawString("  2: DESC2", 30, 115);
        Mockito.verify(graphics, times(1)).fillRect(805, 55, 310, 310);
        Mockito.verify(graphics, times(1)).fillRect(810, 60, 300, 300);

    }

    @Test
    public void TestDrawLanterna() {
        LanternaGraphics graphics = Mockito.mock(LanternaGraphics.class);
        LanternaRewardDrawer drawer = new LanternaRewardDrawer(rewards);

        drawer.draw(graphics);
        Mockito.verify(graphics, times(5)).drawString(anyString(), any());
        Mockito.verify(graphics, times(3)).setTextColor(any());
        Mockito.verify(graphics, times(1)).drawString("--> NAME1: DESC1", new Position(0, 2));
        Mockito.verify(graphics, times(1)).drawString(" 2: DESC2",  new Position(0, 3));

    }
}
