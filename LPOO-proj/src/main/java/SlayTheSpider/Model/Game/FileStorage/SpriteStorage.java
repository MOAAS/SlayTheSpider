package SlayTheSpider.Model.Game.FileStorage;

import SlayTheSpider.Model.Sprites.Sprite;

import java.util.List;

public abstract class SpriteStorage {
    public abstract List<Sprite> getSprites();

    public Sprite getSprite(String name) {
        for (Sprite sprite : this.getSprites()) {
            if (sprite.getName().equals(name))
                return sprite;
        }
        throw new NullPointerException();
    }
}
