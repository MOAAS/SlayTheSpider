package SlayTheSpider.Model;

import SlayTheSpider.Model.Game.Fight;
import SlayTheSpider.Model.Game.FileStorage.SpriteStorage;
import SlayTheSpider.Model.Game.Player;

public class FightModel {
    private final Fight fight;
    private final OverworldModel overworldModel;

    public FightModel(OverworldModel overworldModel, Fight fight) {
        this.overworldModel = overworldModel;
        this.fight = fight;
        this.overworldModel.getPlayer().beginFight(fight);
    }

    public SpriteStorage getSpriteStorage() {
        return overworldModel.getSpriteStorage();
    }

    public Fight getFight() {
        return fight;
    }

    public OverworldModel getOverworldModel() {
        return overworldModel;
    }

    public Player getPlayer() {
        return overworldModel.getPlayer();
    }
}
