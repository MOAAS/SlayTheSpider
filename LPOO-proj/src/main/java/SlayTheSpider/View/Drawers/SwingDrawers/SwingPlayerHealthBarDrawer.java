package SlayTheSpider.View.Drawers.SwingDrawers;

import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.View.TextWindow.SwingGraphics;
import SlayTheSpider.Model.Game.HealthBar.HealthBar;
import SlayTheSpider.Model.Game.HealthBar.PlayerHealthBar;
import SlayTheSpider.Model.Position.Position2D;

import java.awt.*;

public class SwingPlayerHealthBarDrawer extends SwingHealthbarDrawer {
    private static final Font SHIELD_FONT = new Font("Tahoma", Font.BOLD, 12);
    private static final Color SHIELD_COLOR = new Color(0, 120, 255);

    private static final Font MANA_FONT = new Font("Tahoma", Font.BOLD, 15);
    private static final Color MANA_COLOR = new Color(0, 226, 144);

    private final PlayerHealthBar healthBar;
    private final Position2D position;
    private final Sprite manaFull;
    private final Sprite manaEmpty;

    public SwingPlayerHealthBarDrawer(PlayerHealthBar healthBar, Position2D healthBarPos, Sprite manaFull, Sprite manaEmpty) {
        this.healthBar = healthBar;
        this.position = healthBarPos;
        this.manaFull = manaFull;
        this.manaEmpty = manaEmpty;
    }

    @Override
    public void draw(Graphics g) {
        this.drawHealth(100, 15, g);
        this.drawShield(g);
        this.drawMana(g);
    }

    private void drawShield(Graphics g) {
        Position2D shieldPos = this.position.left(12).down(12);
        if (this.healthBar.getShield() >= 10)
            shieldPos.setLeft(12);
        g.setColor(SHIELD_COLOR);
        g.setFont(SHIELD_FONT);
        g.drawString(Integer.toString(this.healthBar.getShield()), shieldPos.getX(), shieldPos.getY());
    }

    private void drawMana(Graphics g) {
        Position2D manaPos = this.position.right(2).down(22);
        for(int i = 0 ; i < this.healthBar.getCurrentMana(); i++){
            this.manaFull.draw(new SwingGraphics(g), manaPos);
            manaPos.setRight(20);
        }
        for(int i = this.healthBar.getCurrentMana() ; i < this.healthBar.getMaxMana(); i++){
            this.manaEmpty.draw(new SwingGraphics(g), manaPos);
            manaPos.setRight(20);
        }
        manaPos.setDown(10);
        g.setFont(MANA_FONT);
        g.setColor(MANA_COLOR);
        g.drawString(this.healthBar.getCurrentMana() + "/" + this.healthBar.getMaxMana(), manaPos.getX(), manaPos.getY() + 5);
    }

    @Override
    protected Position2D getPosition() {
        return position;
    }

    @Override
    protected HealthBar getHealthBar() {
        return healthBar;
    }
}
