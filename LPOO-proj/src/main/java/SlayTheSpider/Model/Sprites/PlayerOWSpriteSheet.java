package SlayTheSpider.Model.Sprites;

public class PlayerOWSpriteSheet {
    private final Sprite front;
    private final Sprite back;
    private final Sprite left;
    private final Sprite right;

    public PlayerOWSpriteSheet(Sprite front, Sprite back, Sprite left, Sprite right) {
        this.front = front;
        this.back = back;
        this.left = left;
        this.right = right;
    }

    public Sprite getBack() {
        return back;
    }

    public Sprite getFront() {
        return front;
    }

    public Sprite getLeft() {
        return left;
    }

    public Sprite getRight() {
        return right;
    }
}
