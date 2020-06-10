package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.Game.CardEffect.CardEffect;
import SlayTheSpider.Model.Game.CardEffect.EffectDescMaker;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private String description;
    private final int cost;
    private final String name;
    private String color;
    private List<CardEffect> effects = new ArrayList<>();

    public Card(String name, int cost, String color) {
        this.name = name;
        this.cost = cost;
        this.color = color;
        this.description = "";
    }

    public Card(Card card) {
        this.name = card.name;
        this.cost = card.cost;
        this.color = card.color;
        this.effects = card.effects;
        this.description = card.description;
    }

    public void addEffect(CardEffect effect) {
        this.effects.add(effect);
    }

    public List<CardEffect> getEffects() {
        return effects;
    }

    public void use(Player owner) {
        for (CardEffect effect : effects)
            effect.use(owner);
    }

    public List<String> getDescription() {
        List<String> description = new ArrayList<>();
        if (this.description.isEmpty()) {
            for (CardEffect effect : effects) {
                description.add(new EffectDescMaker(effect).getDescription());
            }
        }
        else description.add(this.description);
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean needsTargetSelection() {
        for (CardEffect effect: effects) {
            if (effect.needsTargetSelection())
                return true;
        }
        return false;
    }

    public int getCost() {
        return cost;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }
}
