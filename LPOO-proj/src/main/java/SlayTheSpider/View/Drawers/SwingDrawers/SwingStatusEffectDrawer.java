package SlayTheSpider.View.Drawers.SwingDrawers;

import SlayTheSpider.Model.Game.StatusEffect.StatusEffectListEntry;
import SlayTheSpider.Model.Position.Position2D;

import java.awt.*;

public class SwingStatusEffectDrawer implements SwingDrawer {
    private final StatusEffectListEntry effect;
    private final Position2D pos;

    public SwingStatusEffectDrawer(StatusEffectListEntry effect, Position2D position) {
        this.effect = effect;
        this.pos = position;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.decode("#" + effect.getEffect().getColor()));
        g.drawString(effect.getEffect().getName().substring(0, 1), pos.getX(), pos.getY());
        g.drawString("" + effect.getRoundsLeft(), pos.getX(), pos.up(10).getY());
    }
}
