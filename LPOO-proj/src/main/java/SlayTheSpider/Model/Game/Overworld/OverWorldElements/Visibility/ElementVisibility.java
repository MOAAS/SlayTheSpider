package SlayTheSpider.Model.Game.Overworld.OverWorldElements.Visibility;

import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldElement;
import SlayTheSpider.Model.Position.Position2D;

public abstract class ElementVisibility {
    public enum VisibilityType {
        Invisible,
        VisibleNear,
        VisibleFar
    }

    public static final int ELEMENT_VIEW_RANGE = 4;

    public void updateState(OverworldElement element, Position2D playerPos) {
        double distToPlayer = element.getHitbox().getCenter().distance(playerPos);
        this.updateElementVisibility(element, distToPlayer);
    }

    public abstract VisibilityType getType();

    protected abstract void updateElementVisibility(OverworldElement element, double distToPlayer);
}
