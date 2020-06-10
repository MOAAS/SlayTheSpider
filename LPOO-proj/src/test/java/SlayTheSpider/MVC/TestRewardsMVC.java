package SlayTheSpider.MVC;

import SlayTheSpider.Controller.OverworldController;
import SlayTheSpider.Factory.GameEvent;
import SlayTheSpider.Model.Game.FightRewards.Reward;
import SlayTheSpider.Model.Game.FightRewards.RewardList;
import SlayTheSpider.Model.Game.HealthBar.PlayerHealthBar;
import SlayTheSpider.Model.Game.Overworld.Floor;
import SlayTheSpider.Model.Game.Player;
import SlayTheSpider.Model.ListCycler.ArrayListCycler;
import SlayTheSpider.Controller.ControllerObserver;
import SlayTheSpider.Controller.RewardController;
import SlayTheSpider.Model.OverworldModel;
import SlayTheSpider.Model.RewardModel;
import SlayTheSpider.View.RewardViewLanterna;
import SlayTheSpider.View.RewardViewSwing;
import SlayTheSpider.View.View;
import SlayTheSpider.View.ViewFactory;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import SlayTheSpider.View.TextWindow.LanternaWindow;
import SlayTheSpider.View.TextWindow.SwingGamePanel;
import SlayTheSpider.View.TextWindow.SwingWindow;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class TestRewardsMVC {

    @Test
    public void TestModel() {
        RewardList listMock = Mockito.mock(RewardList.class);
        OverworldModel owMock = Mockito.mock(OverworldModel.class);
        Player playerMock = Mockito.mock(Player.class);
        Mockito.when(owMock.getPlayer()).thenReturn(playerMock);

        RewardModel model = new RewardModel(owMock, listMock);

        assertSame(model.getRewards(), listMock);
        assertSame(model.getPlayer(), playerMock);
        assertSame(model.getOverworldModel(), owMock);
    }

    @Test
    public void TestViewL() {
        LanternaWindow windowMock = Mockito.mock(LanternaWindow.class);
        LanternaGraphics graphics = Mockito.mock(LanternaGraphics.class);
        RewardModel modelMock = Mockito.mock(RewardModel.class);
        Mockito.when(windowMock.getTextDrawer()).thenReturn(graphics);

        RewardList listMock = Mockito.mock(RewardList.class);
        Reward rewardMock = Mockito.mock(Reward.class);
        ArrayListCycler cyclerMock = Mockito.mock(ArrayListCycler.class);
        Mockito.when(modelMock.getRewards()).thenReturn(listMock);
        Mockito.when(listMock.getLeft()).thenReturn(2);
        Mockito.when(listMock.getRewardList()).thenReturn(cyclerMock);
        Mockito.when(cyclerMock.getSelected()).thenReturn(rewardMock);
        Mockito.when(cyclerMock.getList()).thenReturn(new ArrayList());
        Mockito.when(rewardMock.getColor()).thenReturn("ffffff");

        new RewardViewLanterna(modelMock, windowMock).draw();
        Mockito.verify(graphics, atLeastOnce()).drawString(any(), any());
    }

    @Test
    public void TestViewS() {
        SwingWindow windowMock = Mockito.mock(SwingWindow.class);
        Mockito.when(windowMock.getPanel()).thenReturn(Mockito.mock(SwingGamePanel.class));

        Graphics graphics = Mockito.mock(Graphics.class);
        RewardModel modelMock = Mockito.mock(RewardModel.class);

        RewardList listMock = Mockito.mock(RewardList.class);
        Reward rewardMock = Mockito.mock(Reward.class);
        ArrayListCycler cyclerMock = Mockito.mock(ArrayListCycler.class);
        Mockito.when(modelMock.getRewards()).thenReturn(listMock);
        Mockito.when(listMock.getLeft()).thenReturn(2);
        Mockito.when(listMock.getRewardList()).thenReturn(cyclerMock);
        Mockito.when(cyclerMock.getSelected()).thenReturn(rewardMock);
        Mockito.when(cyclerMock.getList()).thenReturn(new ArrayList());
        Mockito.when(rewardMock.getColor()).thenReturn("ffffff");
        Mockito.when(graphics.getFontMetrics()).thenReturn(Mockito.mock(FontMetrics.class));

        new RewardViewSwing(modelMock, windowMock).draw(graphics);
        Mockito.verify(graphics, atLeastOnce()).drawString(anyString(), anyInt(), anyInt());
    }

    @Test
    public void TestController() {
        RewardModel modelMock = Mockito.mock(RewardModel.class);
        RewardList listMock = Mockito.mock(RewardList.class);
        OverworldModel owMock = Mockito.mock(OverworldModel.class);
        Player playerMock = Mockito.mock(Player.class);
        PlayerHealthBar barMock = Mockito.mock(PlayerHealthBar.class);
        Floor floorMock = mock(Floor.class);
        Mockito.when(modelMock.getPlayer()).thenReturn(playerMock);
        Mockito.when(playerMock.getHealthBar()).thenReturn(barMock);
        Mockito.when(modelMock.getOverworldModel()).thenReturn(owMock);
        Mockito.when(modelMock.getRewards()).thenReturn(listMock);
        Mockito.when(owMock.getFloor()).thenReturn(floorMock);

        ViewFactory factory = Mockito.mock(ViewFactory.class);
        View viewMock = Mockito.mock(View.class);
        ControllerObserver observerMock = Mockito.mock(ControllerObserver.class);
        Mockito.when(factory.makeRewardView(any())).thenReturn(viewMock);

        RewardController controller = new RewardController(modelMock, factory, observerMock);

        controller.drawView();
        Mockito.verify(viewMock, times(1)).setTitle(anyString());
        Mockito.verify(viewMock, times(1)).draw();

        controller.processEvent(GameEvent.ArrowRight);
        Mockito.verify(listMock, times(1)).selectRight();

        controller.processEvent(GameEvent.ArrowUp);
        Mockito.verify(listMock, times(1)).selectLeft();

        controller.processEvent(GameEvent.ArrowLeft);
        Mockito.verify(listMock, times(2)).selectLeft();

        controller.processEvent(GameEvent.ArrowDown);
        Mockito.verify(listMock, times(2)).selectRight();

        controller.processEvent(GameEvent.Enter);
        Mockito.verify(listMock, times(1)).consumeReward(playerMock);

        controller.processEvent(GameEvent.Esc);
        Mockito.verify(listMock, times(1)).skip();

        Mockito.when(listMock.done()).thenReturn(true).thenReturn(false).thenReturn(true);
        Mockito.when(floorMock.isBossDead()).thenReturn(false).thenReturn(true).thenReturn(true);

        controller.update();
        Mockito.verify(owMock, never()).makeFloor();

        controller.update();
        Mockito.verify(owMock, never()).makeFloor();

        controller.update();
        Mockito.verify(owMock, times(1)).makeFloor();
        Mockito.verify(barMock, times(1)).refillHealth();
        Mockito.verify(observerMock, times(2)).setController(any(OverworldController.class));
    }
}
