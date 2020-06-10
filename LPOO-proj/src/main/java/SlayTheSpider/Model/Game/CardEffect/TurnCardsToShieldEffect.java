package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Player;

public class TurnCardsToShieldEffect extends CardEffectSelf {
    private final int shieldPerCard;

    public TurnCardsToShieldEffect(int shieldPerCard) {
        this.shieldPerCard = shieldPerCard;
    }

    @Override
    public void applyEffect(Player player) {
        int cardsPlayed = player.getPlayerStats().getNumCardsPlayed().turn();
        player.getHealthBar().addShield(cardsPlayed * shieldPerCard);
    }

    @Override
    public String getDescription() {
        return "Shields " + shieldPerCard + " for every card played this turn.";
    }
}
