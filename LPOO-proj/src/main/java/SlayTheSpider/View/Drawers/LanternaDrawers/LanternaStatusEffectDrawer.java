package SlayTheSpider.View.Drawers.LanternaDrawers;

import SlayTheSpider.Model.Game.StatusEffect.StatusEffectListEntry;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import SlayTheSpider.Model.Position.Position2D;

public class LanternaStatusEffectDrawer implements LanternaDrawer {
    private final StatusEffectListEntry effect;
    private final Position2D position;

    public LanternaStatusEffectDrawer(StatusEffectListEntry effect, Position2D position) {
        this.effect = effect;
        this.position = position;
    }

    public void draw(LanternaGraphics drawer) {
        drawer.setTextColor(effect.getEffect().getColor());
        drawer.drawString(effect.getEffect().getName().substring(0, 1), position);
        drawer.drawString("" + effect.getRoundsLeft(), position.up(1));
    }
}
