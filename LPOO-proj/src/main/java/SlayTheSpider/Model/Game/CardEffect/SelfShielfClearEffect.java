package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Player;

public class SelfShielfClearEffect extends CardEffectSelf {
    @Override
    public void applyEffect(Player player) {
        player.getHealthBar().clearShield();
    }

    @Override
    public String getDescription() {
        return "Clears shield.";
    }
}
