package SlayTheSpider.Model;

import SlayTheSpider.Model.Game.FileStorage.GameStorage;
import SlayTheSpider.Model.Game.FileStorage.SpriteStorage;
import SlayTheSpider.Model.Game.Overworld.Floor;
import SlayTheSpider.Model.Game.Overworld.FloorGenerator;
import SlayTheSpider.Model.Game.Player;

public class OverworldModel implements Model {
    private final MainMenuModel menuModel;
    private final GameStorage storage;
    private final FloorGenerator floorGenerator;
    private final Player player;
    private int floorNum;
    private Floor floor;

    public OverworldModel(MainMenuModel menuModel, GameStorage storage, FloorGenerator floorGenerator) {
        this.menuModel = menuModel;
        this.storage = storage;
        this.floorGenerator = floorGenerator;
        this.player = storage.getNewPlayer();
        this.floorNum = 0;
        this.makeFloor();
    }

    public void makeFloor() {
        this.floorNum++;
        this.floorGenerator.setWallSprite(storage.getSprite("WallSprite"));
        this.floor = new Floor(floorGenerator.generate(8 + floorNum * 2), player.getOWPlayer());
        this.floor.setFights(storage.getFights(), storage.getBosses());
        this.player.setMapPosition(floor.getStructures().getSpawnRoom().getMapArea().getRandomPos());
    }

    public Floor getFloor() {
        return floor;
    }

    public Player getPlayer() {
        return player;
    }

    public SpriteStorage getSpriteStorage() {
        return storage.getSpriteStorage();
    }

    public int getFloorNum() {
        return floorNum;
    }

    public MainMenuModel getMenuModel() {
        return menuModel;
    }
}
