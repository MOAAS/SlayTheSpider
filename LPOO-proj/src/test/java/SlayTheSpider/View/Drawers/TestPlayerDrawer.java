package SlayTheSpider.View.Drawers;

import SlayTheSpider.View.Drawers.LanternaDrawers.LanternaPlayerDrawer;
import SlayTheSpider.View.Drawers.SwingDrawers.SwingPlayerDrawer;
import SlayTheSpider.Model.Game.Enemy;
import SlayTheSpider.Model.Game.HealthBar.PlayerHealthBar;
import SlayTheSpider.Model.Game.Player;
import SlayTheSpider.Model.Game.StatusEffect.StatusEffect;
import SlayTheSpider.Model.Game.StatusEffect.StatusEffectGroup;
import SlayTheSpider.Model.Game.StatusEffect.StatusEffectListEntry;
import SlayTheSpider.Model.Game.TargetList;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Sprites.PlayerSpriteSheet;
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

public class TestPlayerDrawer {
    private Player player = Mockito.mock(Player.class);
    private Sprite targetSprite = Mockito.mock(Sprite.class);
    private Sprite playerSprite = Mockito.mock(Sprite.class);
    private Sprite manaFull = Mockito.mock(Sprite.class);
    private Sprite manaEmpty = Mockito.mock(Sprite.class);
    private PlayerSpriteSheet spriteSheet = Mockito.mock(PlayerSpriteSheet.class);
    private Enemy target = Mockito.mock(Enemy.class);
    private PlayerHealthBar healthBar = Mockito.mock(PlayerHealthBar.class);
    private StatusEffectGroup effects = Mockito.mock(StatusEffectGroup.class);

    @Before
    public void setup() {
        player = Mockito.mock(Player.class);
        playerSprite = Mockito.mock(Sprite.class);
        target = Mockito.mock(Enemy.class);
        healthBar = Mockito.mock(PlayerHealthBar.class);
        manaFull = Mockito.mock(Sprite.class);
        manaEmpty = Mockito.mock(Sprite.class);
        spriteSheet = Mockito.mock(PlayerSpriteSheet.class);
        effects = Mockito.mock(StatusEffectGroup.class);
        targetSprite = Mockito.mock(Sprite.class);

        List<Enemy> enemies = new ArrayList<>();
        enemies.add(target);

        List<StatusEffectListEntry> entries = new ArrayList<>();
        entries.add(Mockito.mock(StatusEffectListEntry.class));
        entries.add(Mockito.mock(StatusEffectListEntry.class));

        StatusEffect effect1 = Mockito.mock(StatusEffect.class);
        StatusEffect effect2 = Mockito.mock(StatusEffect.class);
        Mockito.when(effect1.getColor()).thenReturn("aaAAaa");
        Mockito.when(effect1.getName()).thenReturn("effect name");
        Mockito.when(effect2.getColor()).thenReturn("aaFFaa");
        Mockito.when(effect2.getName()).thenReturn("affect name");

        Mockito.when(target.getPosition()).thenReturn(new Position(0, 0));
        Mockito.when(player.getTargets()).thenReturn(new TargetList(enemies, player));
        Mockito.when(player.getTarget()).thenReturn(target);
        Mockito.when(player.getHealthBar()).thenReturn(healthBar);
        Mockito.when(player.getStatusEffects()).thenReturn(effects);
        Mockito.when(player.getSprite()).thenReturn(playerSprite);
        Mockito.when(effects.getList()).thenReturn(entries);

        Mockito.when(healthBar.getMaxMana()).thenReturn(5);
        Mockito.when(healthBar.getCurrentMana()).thenReturn(4);
        Mockito.when(healthBar.getShield()).thenReturn(5);
        Mockito.when(entries.get(0).getEffect()).thenReturn(effect1);
        Mockito.when(entries.get(1).getEffect()).thenReturn(effect2);

        Mockito.when(playerSprite.getHeight()).thenReturn(10);
        Mockito.when(playerSprite.getWidth()).thenReturn(20);

        Mockito.when(targetSprite.getHeight()).thenReturn(15);
        Mockito.when(targetSprite.getWidth()).thenReturn(25);

        Mockito.when(spriteSheet.getPlayer()).thenReturn(playerSprite);
        Mockito.when(spriteSheet.getManaEmpty()).thenReturn(manaEmpty);
        Mockito.when(spriteSheet.getManaFull()).thenReturn(manaFull);

        Mockito.when(target.getSprite()).thenReturn(targetSprite);
    }

    @Test
    public void TestDrawLanterna() {
        LanternaGraphics graphics = Mockito.mock(LanternaGraphics.class);
        LanternaPlayerDrawer drawer = new LanternaPlayerDrawer(player, playerSprite);

        Mockito.when(player.getTarget()).thenReturn(target);
        drawer.draw(graphics);
        Mockito.verify(playerSprite, times(1)).draw(graphics, new Position(2, 10));
        Mockito.verify(graphics, times(10)).drawString(anyString(), any());
        Mockito.verify(graphics, times(6)).setTextColor(anyString());

        // Mana and effects
        Mockito.verify(graphics, times(1)).drawString("4/5", new Position(2, 21));
        Mockito.verify(graphics, times(1)).drawString("e", new Position( 6, 9));
        Mockito.verify(graphics, times(1)).drawString("a", new Position( 6, 9));


        // Target
        Mockito.verify(graphics, times(1)).drawString(" | ", new Position(1, -3));
        Mockito.verify(graphics, times(1)).drawString(" | ", new Position(1, -2));
        Mockito.verify(graphics, times(1)).drawString(" V", new Position(1, -1));
        Mockito.when(player.getTarget()).thenReturn(player);
        drawer.draw(graphics);
        Mockito.verify(graphics, times(20)).drawString(anyString(), any());
        Mockito.verify(graphics, times(12)).setTextColor(anyString());
        Mockito.verify(graphics, times(1)).drawString(" | ", new Position(3, 7));

        // Shield
        Mockito.verify(graphics, times(2)).drawString("5", new Position(1, 20));
        Mockito.when(healthBar.getShield()).thenReturn(10);
        drawer.draw(graphics);
        Mockito.verify(graphics, times(1)).drawString("10", new Position(0, 20));
    }

    @Test
    public void TestDrawSwing() {
        Graphics graphics = Mockito.mock(Graphics.class);
        SwingPlayerDrawer drawer = new SwingPlayerDrawer(player, spriteSheet);

        Mockito.when(player.getTarget()).thenReturn(target);
        drawer.draw(graphics);
        Mockito.verify(playerSprite, times(1)).draw(any(), eq(new Position(30, 180)));
        Mockito.verify(manaFull, times(4)).draw(any(), eq(new Position(132, 222)));
        Mockito.verify(manaEmpty, times(1)).draw(any(), eq(new Position(132, 222)));
        Mockito.verify(graphics, times(10)).drawString(anyString(), anyInt(), anyInt());
        Mockito.verify(graphics, times(9)).setColor(any());
        Mockito.verify(graphics, times(3)).setFont(any());

        // Mana and effects
        Mockito.verify(graphics, times(1)).drawString("4/5", 132, 227);
        Mockito.verify(graphics, times(1)).drawString("e", 30, 180);
        Mockito.verify(graphics, times(1)).drawString("a", 50, 180);

        // Target
        Mockito.verify(graphics, times(1)).drawString("|", 12, -20);
        Mockito.verify(graphics, times(1)).drawString("|", 12, -10);
        Mockito.verify(graphics, times(1)).drawString("V", 12, 0);
        Mockito.when(player.getTarget()).thenReturn(player);
        drawer.draw(graphics);
        Mockito.verify(graphics, times(1)).drawString("|", 40, 160);

        // Shield
        Mockito.verify(graphics, times(2)).drawString("5", 18, 202);
        Mockito.when(healthBar.getShield()).thenReturn(10);
        drawer.draw(graphics);
        Mockito.verify(graphics, times(1)).drawString("10", 6, 202);
    }
}
