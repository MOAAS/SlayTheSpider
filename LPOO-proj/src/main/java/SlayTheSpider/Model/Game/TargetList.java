package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.ListCycler.ArrayListCycler;
import SlayTheSpider.Model.ListCycler.ListCycler;

import java.util.ArrayList;
import java.util.List;

public class TargetList {
    private ListCycler<Character> targets;
    private List<Enemy> enemies;
    private Player player;

    public TargetList(List<Enemy> enemies, Player player) {
        this.enemies = enemies;
        this.player = player;
        this.targets = new ArrayListCycler<>(new ArrayList<>());
        this.targets.add(player);
        for (Enemy enemy : enemies) {
            this.targets.add(enemy);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public List<Enemy> getEnemies() {
        return  enemies;
    }

    public List<Character> getCharacters() {
        return targets.getList();
    }

    public Character getTarget() {
        return targets.getSelected();
    }

    public void clearDeadCharacters() {
        targets.getList().removeIf(Character::isDead);
        enemies.removeIf(Character::isDead);
    }

    public void selectLeft() {
        targets.selectLeft();
    }

    public void selectRight() {
        targets.selectRight();
    }

    public void selectPlayer() {
        targets.selectFirst();
    }
}
