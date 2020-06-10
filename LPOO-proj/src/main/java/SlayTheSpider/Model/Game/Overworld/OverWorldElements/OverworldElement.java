package SlayTheSpider.Model.Game.Overworld.OverWorldElements;

import SlayTheSpider.Model.Position.MapArea;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Visibility.ElementInvisible;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Visibility.ElementVisibility;
import SlayTheSpider.Model.Position.Position2D;

public abstract class OverworldElement {
    protected ElementVisibility visibility;
    protected MapArea hitbox;

    public OverworldElement(Position2D position, int size){
        this.hitbox = new MapArea(position, size, size);
        this.visibility = new ElementInvisible();
    }

    public boolean collides(Position2D p){
        return hitbox.containsPos(p);
    }

    public Position2D getPosition() {
        return hitbox.getPosition();
    }

    public MapArea getHitbox() {
        return hitbox;
    }

    public void updateVisibility(Position2D playerPos){
        visibility.updateState(this, playerPos);
    }

    public void setVisibilitiy(ElementVisibility visibility) {
        this.visibility = visibility;
    }

    public ElementVisibility getVisibilitiy() {
        return this.visibility;
    }
}
