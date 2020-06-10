package SlayTheSpider.Model.Game.CardEffect;


import SlayTheSpider.Model.Game.Player;

public class SelfShieldEffect extends CardEffectSelf {
    private final int shield;

    public SelfShieldEffect(int shield) {
        this.shield = shield;
    }

    @Override
    public void applyEffect(Player player) {
        player.getHealthBar().addShield(shield);
    }

    @Override
    public String getDescription() {
        return "Restores " + shield + " shield to self.";
    }
}
