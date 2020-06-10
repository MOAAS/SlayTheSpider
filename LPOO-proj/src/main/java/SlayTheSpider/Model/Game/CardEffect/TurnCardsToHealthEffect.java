package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Player;

public class TurnCardsToHealthEffect extends CardEffectSelf {
    private final int healPerCard;

    public TurnCardsToHealthEffect(int healPerCard) {
        this.healPerCard = healPerCard;
    }

    @Override
    public void applyEffect(Player player) {
        int cardsPlayed = player.getPlayerStats().getNumCardsPlayed().turn();
        player.heal(cardsPlayed * healPerCard);
    }

    @Override
    public String getDescription() {
        return "Heals " + healPerCard + " for every card played this turn.";
    }
}
