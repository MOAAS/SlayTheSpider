package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Enemy;
import SlayTheSpider.Model.Game.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class CardEffectAoE extends CardEffectTargeted {
    @Override
    public void use(Player owner) {
        for (Enemy enemy : this.getValidTargets(owner)) {
            applyEffect(enemy, owner);
        }
    }

    @Override
    public boolean needsTargetSelection() {
        return false;
    }

    private List<Enemy> getValidTargets(Player owner) {
        List<Enemy> targets = new ArrayList<>();
        for (Enemy enemy : owner.getTargets().getEnemies()) {
            if (cardEffectConditions.satisfies(owner, enemy))
                targets.add(enemy);
        }
        return targets;
    }
}
