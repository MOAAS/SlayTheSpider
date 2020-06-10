package SlayTheSpider.Model.Position;

import java.util.concurrent.ThreadLocalRandom;

public class MapAreaRandomizer {
    private final int minSize;
    private final int maxSize;
    private final int xLimit;
    private final int yLimit;

    public MapAreaRandomizer(int minSize, int maxSize, int xLimit, int yLimit) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.xLimit = xLimit;
        this.yLimit = yLimit;
    }

    public MapArea randomize() {
        int width = ThreadLocalRandom.current().nextInt(minSize, maxSize+ 1);
        int height = ThreadLocalRandom.current().nextInt(minSize, maxSize + 1);

        int x1 = ThreadLocalRandom.current().nextInt(1, xLimit - width + 1);
        int y1 = ThreadLocalRandom.current().nextInt(1, yLimit - height + 1);

        return new MapArea(new Position(x1, y1), width, height);
    }


}
