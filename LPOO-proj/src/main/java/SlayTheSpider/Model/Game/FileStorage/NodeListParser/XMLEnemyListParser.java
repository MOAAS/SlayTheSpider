package SlayTheSpider.Model.Game.FileStorage.NodeListParser;

import SlayTheSpider.Model.Game.FileStorage.SpriteStorage;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Game.Enemy;
import SlayTheSpider.Model.Position.Position;
import org.w3c.dom.Element;

public class XMLEnemyListParser extends XMLNodeListParser<Enemy> {
    private final SpriteStorage storage;

    public XMLEnemyListParser(SpriteStorage storage) {
        this.storage = storage;
    }

    @Override
    public Enemy parseElement(Element element) {
        String spriteName = element.getTextContent();
        Sprite enemySprite = this.storage.getSprite(spriteName);

        String pos = element.getAttribute("pos");
        String maxHP = element.getAttribute("maxHP");
        String damage = element.getAttribute("damage");
        String damageScale = element.getAttribute("damageScale");

        return new Enemy(new Position(pos), enemySprite, Integer.parseInt(maxHP), Integer.parseInt(damage), Double.parseDouble(damageScale));
    }
}
