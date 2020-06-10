package SlayTheSpider.Model.Game.FileStorage;

import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Game.Card;
import SlayTheSpider.Model.Game.Fight;
import SlayTheSpider.Model.Game.Player;

import java.util.List;

public interface GameStorage {
   // Player getPlayer();
    Card getCard(String name);
    Card getRandomCard();

   // Deck getInitialDeck();

    Sprite getSprite(String name);
    List<Fight> getFights();
    List<Fight> getBosses();

    SpriteStorage getSpriteStorage();

    Player getNewPlayer();
}
