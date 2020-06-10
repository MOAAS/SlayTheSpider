package SlayTheSpider.Model.Game.Overworld.Rooms;

import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Game.Fight;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldEnemy;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldObstacle;
import SlayTheSpider.Model.Game.Overworld.OverWorldElements.OverworldWall;
import SlayTheSpider.Model.Game.Overworld.Rooms.RoomStatus.EnemyRoomStatus;
import SlayTheSpider.Model.Game.Overworld.Rooms.RoomStatus.RoomNotVisited;
import SlayTheSpider.Model.Position.MapAreaRandomizer;
import SlayTheSpider.Model.Position.Position;
import SlayTheSpider.Model.Position.Position2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EnemyRoom extends Room {
    private final Sprite wallSprite;
    private EnemyRoomStatus status;
    private static final int MIN_ENEMIES = 1;
    private static final int MAX_ENEMIES = 3;

    private static final int MIN_ENEMY_DIST = 3;

    protected List<OverworldWall> walls = new ArrayList<>();
    protected List<OverworldEnemy> enemies = new ArrayList<>();

    public EnemyRoom(MapAreaRandomizer randomizer, Sprite wallSprite){
        super(randomizer);
        this.wallSprite = wallSprite;
        status = new RoomNotVisited();
    }

    public void insertEnemies(List<Fight> fights) {
        int numEnemies = ThreadLocalRandom.current().nextInt(MIN_ENEMIES, MAX_ENEMIES + 1);
        for(int i = 0; i < numEnemies; i++){
            Fight fight = fights.get(new Random().nextInt(fights.size()));
            OverworldEnemy newEnemy = new OverworldEnemy(mapArea.getRandomPos(), fight.getSprite(), new Fight(fight));
            if(enemyFits(newEnemy))
                enemies.add(newEnemy);
        }
    }

    public boolean enemyFits(OverworldEnemy newEnemy) {
        for (OverworldEnemy enemy : enemies)
            if (enemy.getPosition().distance(newEnemy.getPosition()) <= MIN_ENEMY_DIST)
                return false;
        return true;
    }

    public  List<OverworldEnemy> getEnemies(){
        return enemies;
    }

    public boolean noEnemies(){
        return enemies.size()==0;
    }

    public void buildWalls(){
        this.clearWalls();
        for (int i = mapArea.getX1() - 1; i <= mapArea.getX2() + 1; i++) {
            walls.add(new OverworldWall(new Position(i,mapArea.getY1() - 1),wallSprite));
            walls.add(new OverworldWall(new Position(i,mapArea.getY2() + 1),wallSprite));
        }

        for (int i = mapArea.getY1(); i <= mapArea.getY2(); i++) {
            walls.add(new OverworldWall(new Position(mapArea.getX1() - 1, i),wallSprite));
            walls.add(new OverworldWall(new Position(mapArea.getX2() + 1, i),wallSprite));
        }
    }

    public void clearWalls() {
        this.walls.clear();
    }

    public List<OverworldWall> getWalls() {
        return walls;
    }

    public OverworldEnemy checkEnemyCollision(Position2D playerPos){
        for(OverworldEnemy enemy : enemies)
            if(enemy.collides(playerPos)){
                this.enemies.remove(enemy);
                return enemy;
            }
        return null;
    }

    public boolean doesEnemyCollide(Position2D playerPos) {
        for(OverworldEnemy enemy : enemies)
            if(enemy.collides(playerPos))
                return true;
        return false;
    }

    public boolean wallCollision(Position2D p) {
        for (OverworldWall wall : walls){
            if(wall.collides(p))
                return true;
        }
        return false;
    }

    @Override
    public List<OverworldObstacle> getObstacles() {
        List<OverworldObstacle> obstacles = new ArrayList<>(enemies);
        obstacles.addAll(walls);
        return obstacles;
    }

    @Override
    public void updateVisibility(Position2D pos){
        super.updateVisibility(pos);
        for(OverworldEnemy enemy : enemies) {
            enemy.updateVisibility(pos);
        }
        for(OverworldWall wall : walls){
            wall.updateVisibility(pos);
        }
    }

    public void setStatus(EnemyRoomStatus status) {
        this.status = status;
    }

    public void updateStatus(Position2D playerPos) {
        status.updateRoomStatus(this, playerPos);
    }

    @Override
    public String getColor() {
        return "333333";
    }
}
