package SlayTheSpider.Model.Game.Overworld.Rooms;

import SlayTheSpider.Model.Game.Fight;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.*;
import SlayTheSpider.Model.Position.MapAreaRandomizer;
import SlayTheSpider.Model.Position.Position2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BossRoom extends Room {
    private List<OverworldBoss> bosses = new ArrayList<>();


    public BossRoom(MapAreaRandomizer randomizer){
        super(randomizer);
    }

    public OverworldBoss checkBossCollision(Position2D playerPos){
        for(OverworldBoss boss : bosses) {
            if(boss.collides(playerPos)){
                this.bosses.remove(boss);
                return boss;
            }
        }
        return null;
    }

    public boolean doesBossCollide(Position2D playerPos) {
        for(OverworldBoss boss : bosses) {
            if(boss.collides(playerPos))
                return true;
        }
        return false;
    }

    @Override
    public void updateVisibility(Position2D pos) {
        super.updateVisibility(pos);
        for(OverworldBoss boss : bosses) {
            boss.updateVisibility(pos);
        }
    }

    @Override
    public List<OverworldObstacle> getObstacles() {
        return new ArrayList<>(bosses);
    }

    @Override
    public String getColor() {
        return "890012";
    }

    public void insertBoss(List<Fight> fights) {
        Fight fight = fights.get(new Random().nextInt(fights.size()));
        bosses.clear();
        bosses.add(new OverworldBoss(mapArea.getCenter().up(1).left(1), fight.getSprite(), new Fight(fight)));
    }

    public List<OverworldBoss> getBosses(){
        return bosses;
    }

    public boolean areBossesDead() {
        return bosses.size() == 0;
    }
}
