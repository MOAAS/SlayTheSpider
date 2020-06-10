package SlayTheSpider.Model.Sprites;

public class PlayerSpriteSheet {
    private final Sprite manaFull;
    private final Sprite manaEmpty;
    private final Sprite player;

    public PlayerSpriteSheet(Sprite player, Sprite manaFull, Sprite manaEmpty) {
        this.player = player;
        this.manaFull = manaFull;
        this.manaEmpty = manaEmpty;
    }

    public Sprite getManaFull() {
        return manaFull;
    }

    public Sprite getManaEmpty() {
        return manaEmpty;
    }

    public Sprite getPlayer() {
        return player;
    }
}
