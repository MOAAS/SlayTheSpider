package SlayTheSpider.Model.Game;

import SlayTheSpider.Model.Game.HealthBar.PlayerHealthBar;
import SlayTheSpider.Model.Game.Stats.PlayerStats;
import SlayTheSpider.Model.Sprites.Sprite;
import SlayTheSpider.Model.Position.Position2D;

import java.util.ArrayList;

public class Player extends Character<PlayerHealthBar>  {
    PlayerStats playerStats;
    TargetList targets;
    private OverworldPlayer owPlayer;
    private Deck deck;

    public Player(Sprite sprite, int maxHealth) {
        super(sprite, maxHealth);
        this.owPlayer = new OverworldPlayer();
        this.playerStats = new PlayerStats();
        this.deck = new Deck();
        this.targets = new TargetList(new ArrayList<>(), this);
    }

    @Override
    protected PlayerHealthBar createHealthBar(int maxHealth) {
        return new PlayerHealthBar(maxHealth);
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    public TargetList getTargets() {
        return this.targets;
    }

    public Character getTarget() {
        return this.targets.getTarget();
    }

    public void beginFight(Fight fight) {
        this.deck.startFight(5);
        this.healthBar.resetFight();
        this.playerStats.resetFight();
        this.statusEffects.clear();
        this.targets = new TargetList(fight.getEnemies(), this);
    }

    public void useSelectedCard() {
        Card card = this.getDeck().getSelectedCard();
        this.getHealthBar().reduceMana(card.getCost());
        this.deck.discardSelectedCard();
        card.use(this);
        this.playerStats.logCardUse();
    }

    public boolean canAffordCard() {
        return this.getHealthBar().getCurrentMana() >= this.getDeck().getSelectedCard().getCost();
    }

    public void endTurn() {
        this.getDeck().drawCards(1);
        this.healthBar.resetTurn();
        this.playerStats.resetTurn();
        this.statusEffects.endTurn();
    }

    @Override
    protected void notifyHeal(int heal) {
        this.playerStats.logHeal(heal);
    }

    @Override
    protected void notifyDamageTaken(int damage) {
        this.playerStats.logHit(damage);
    }

    @Override
    protected void notifyDamageDealt(int damage) {
        this.playerStats.logAttack(damage);
    }

    public Position2D getMapPosition() {
        return owPlayer.getPosition();
    }

    public void setMapPosition(Position2D pos) {
        this.owPlayer.setPosition(pos);
    }

    public OverworldPlayer getOWPlayer() {
        return owPlayer;
    }

}


