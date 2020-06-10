package SlayTheSpider.Model.Game.Overworld.OverWorldElements.Visibility;

import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldElement;

public class ElementInvisible extends ElementVisibility {
    @Override
    public VisibilityType getType() {
        return VisibilityType.Invisible;
    }

    @Override
    protected void updateElementVisibility(OverworldElement element, double distToPlayer) {
        if (distToPlayer <= ELEMENT_VIEW_RANGE)
            element.setVisibilitiy(new ElementVisibleNear());
    }
}
