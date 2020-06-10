package SlayTheSpider.Model.Game.Overworld;

import SlayTheSpider.Model.Game.Fight;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.Tile;
import SlayTheSpider.Model.Game.Overworld.Rooms.*;
import SlayTheSpider.Model.Game.OverworldPlayer;
import SlayTheSpider.Model.Position.Position2D;

import java.util.List;

public class Floor {
    private final OverworldPlayer owPlayer;

    private FloorStructureList structureList;

    public Floor(FloorStructureList structureList, OverworldPlayer owPlayer) {
        this.structureList = structureList;
        this.owPlayer = owPlayer;
    }

    public boolean isValidTile(Position2D position){
        for(EnemyRoom enemyRoom : structureList.getEnemyRooms()) {
            if(enemyRoom.wallCollision(position))
                return false;
        }
        for(FloorStructure structure : structureList.getList()) {
            if (structure.containsPos(position))
                return true;
        }
        return false;
    }

    public void updateVisibility() {
        for(FloorStructure structure : structureList.getList())
            structure.updateVisibility(owPlayer.getPosition());
        for(Tile tile : structureList.getUnwalkableTiles())
            tile.updateVisibility(owPlayer.getPosition());
    }

    public void updateRoomStatus(){
        for(EnemyRoom room : structureList.getEnemyRooms()){
            room.updateStatus(owPlayer.getPosition());
        }
    }

    public Fight checkFightCollision(){
        BossRoom bossRoom = structureList.getBossRoom();
        if (bossRoom.doesBossCollide(owPlayer.getPosition()))
            return bossRoom.checkBossCollision(owPlayer.getPosition()).getBossFight();
        for(EnemyRoom room : structureList.getEnemyRooms()) {
            if (room.doesEnemyCollide(owPlayer.getPosition()))
                return room.checkEnemyCollision(owPlayer.getPosition()).getFight();
        }
        return null;
    }

    public boolean doesFightCollide(){
        if (structureList.getBossRoom().doesBossCollide(owPlayer.getPosition()))
            return true;
        for(EnemyRoom room : structureList.getEnemyRooms()) {
            if (room.doesEnemyCollide(owPlayer.getPosition()))
                return true;
        }
        return false;
    }


    public FloorStructureList getStructures() {
        return structureList;
    }

    public void setFights(List<Fight> enemies, List<Fight> bosses) {
        this.setEnemies(enemies);
        this.setBoss(bosses);
    }

    private void setEnemies(List<Fight> possibleFights) {
        for (EnemyRoom room : structureList.getEnemyRooms()) {
            room.insertEnemies(possibleFights);
        }
    }

    private void setBoss(List<Fight> possibleBosses) {
        structureList.getBossRoom().insertBoss(possibleBosses);
    }

    public boolean isBossDead() {
        return structureList.getBossRoom().areBossesDead();
    }


}
