package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Game.FightRewards.RewardList;

import java.util.ArrayList;
import java.util.List;

public class Fight {
    private List<Enemy> enemies = new ArrayList<>();

    private final Sprite sprite;
    private RewardList rewards;

    public Fight(Sprite sprite, RewardList rewards) {
        this.sprite = sprite;
        this.rewards = rewards.copy();
    }

    public Fight(Fight fight) {
        this.rewards = fight.rewards.copy();
        this.sprite = fight.sprite;
        for (Enemy enemy : fight.getEnemies())
            this.addEnemy(enemy);
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(new Enemy(enemy));
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public RewardList getRewards() {
        return rewards;
    }

    public void clearDeadCharacters() {
        enemies.removeIf(Character::isDead);
    }

    public boolean isEmpty() {
        return enemies.size() == 0;
    }

    public void endTurn(Player player) {

        for (Enemy enemy : enemies) {
            enemy.attack(player);
            enemy.endTurn();
        }

        player.endTurn();
    }
}
