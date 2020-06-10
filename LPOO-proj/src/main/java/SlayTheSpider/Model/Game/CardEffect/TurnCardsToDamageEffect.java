package SlayTheSpider.Model.Game.CardEffect;

import SlayTheSpider.Model.Game.Character;
import SlayTheSpider.Model.Game.Player;

public class TurnCardsToDamageEffect extends CardEffectSingle {
    private final int damagePerCard;

    public TurnCardsToDamageEffect(int damagePerCard) {
        this.damagePerCard = damagePerCard;
    }

    @Override
    public void applyEffect(Character target, Player user) {
        int cardsPlayed = user.getPlayerStats().getNumCardsPlayed().turn();
        user.attack(target, cardsPlayed * damagePerCard);
    }

    @Override
    public String getDescription() {
        return "Deals " + damagePerCard + " damage for every card played this turn.";
    }
}
