package SlayTheSpider.View.Drawers;

import SlayTheSpider.View.Drawers.LanternaDrawers.LanternaEnemyDrawer;
import SlayTheSpider.View.Drawers.SwingDrawers.SwingEnemyDrawer;
import SlayTheSpider.Model.Game.Enemy;
import SlayTheSpider.Model.Game.HealthBar.EnemyHealthBar;
import SlayTheSpider.Model.Game.StatusEffect.StatusEffect;
import SlayTheSpider.Model.Game.StatusEffect.StatusEffectGroup;
import SlayTheSpider.Model.Game.StatusEffect.StatusEffectListEntry;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;

public class TestEnemyDrawer {
    private Enemy enemyMock = Mockito.mock(Enemy.class);
    private Sprite targetSprite = Mockito.mock(Sprite.class);
    private StatusEffectGroup effects = Mockito.mock(StatusEffectGroup.class);
    private EnemyHealthBar healthBar = Mockito.mock(EnemyHealthBar.class);

    @Before
    public void setup() {
        enemyMock = Mockito.mock(Enemy.class);
        targetSprite = Mockito.mock(Sprite.class);
        effects = Mockito.mock(StatusEffectGroup.class);

        List<StatusEffectListEntry> entries = new ArrayList<>();
        entries.add(Mockito.mock(StatusEffectListEntry.class));
        entries.add(Mockito.mock(StatusEffectListEntry.class));

        StatusEffect effect1 = Mockito.mock(StatusEffect.class);
        StatusEffect effect2 = Mockito.mock(StatusEffect.class);
        Mockito.when(effect1.getColor()).thenReturn("aaAAaa");
        Mockito.when(effect1.getName()).thenReturn("effect name");
        Mockito.when(effect2.getColor()).thenReturn("aaffaa");
        Mockito.when(effect2.getName()).thenReturn("affect name");

        Mockito.when(effects.getList()).thenReturn(entries);
        Mockito.when(entries.get(0).getEffect()).thenReturn(effect1);
        Mockito.when(entries.get(1).getEffect()).thenReturn(effect2);

        Mockito.when(enemyMock.getPosition()).thenReturn(new Position(0, 0));
        Mockito.when(enemyMock.getDamage()).thenReturn(99);
        Mockito.when(enemyMock.getDamageScale()).thenReturn(99.0);
        Mockito.when(enemyMock.getHealthBar()).thenReturn(healthBar);
        Mockito.when(enemyMock.getStatusEffects()).thenReturn(effects);
        Mockito.when(enemyMock.getSprite()).thenReturn(targetSprite);

        Mockito.when(healthBar.getRemainingHealthPerc()).thenReturn(0.5);
        Mockito.when(targetSprite.getWidth()).thenReturn(25);
    }

    @Test
    public void TestDrawLanterna() {
        LanternaGraphics graphics = Mockito.mock(LanternaGraphics.class);
        LanternaEnemyDrawer drawer = new LanternaEnemyDrawer(enemyMock);

        drawer.draw(graphics);
        Mockito.verify(targetSprite, times(1)).draw(graphics, new Position(0, 0));
        Mockito.verify(graphics, times(6)).drawString(anyString(), any());
        Mockito.verify(graphics, times(4)).setTextColor(anyString());
        Mockito.verify(graphics, times(3)).setBackgroundColor(anyString());
        Mockito.verify(graphics, times(2)).drawRect(any(), anyInt(), anyInt());

        // effect
        Mockito.verify(graphics, times(1)).drawString("e", new Position( 4, -2));
        Mockito.verify(graphics, times(1)).drawString("a", new Position( 4, -2));

        // hp bar
        Mockito.verify(graphics, times(1)).drawRect(new Position(4,0), 9, 1);
    }

    @Test
    public void TestDrawSwing() {
        Graphics graphics = Mockito.mock(Graphics.class);
        SwingEnemyDrawer drawer = new SwingEnemyDrawer(enemyMock);

        drawer.draw(graphics);
        Mockito.verify(targetSprite, times(1)).draw(any(), eq(new Position(0, 0)));
        Mockito.verify(graphics, times(6)).drawString(anyString(), anyInt(), anyInt());
        Mockito.verify(graphics, times(7)).setColor(any());
        Mockito.verify(graphics, times(3)).fillRect(anyInt(),anyInt(), anyInt(), anyInt());

        // effect
        Mockito.verify(graphics, times(1)).drawString("e",-10, -10);
        Mockito.verify(graphics, times(1)).drawString("a",10, -10);

        // hp bar
        Mockito.verify(graphics, times(1)).fillRect(-6, 3, 36, 14);

    }


}
