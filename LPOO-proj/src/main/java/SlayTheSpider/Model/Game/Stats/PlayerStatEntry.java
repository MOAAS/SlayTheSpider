package SlayTheSpider.Model.Game.Stats;

public class PlayerStatEntry {
    private int game = 0;
    private int fight = 0;
    private int lastTurn = 0;
    private int turn = 0;


    public int game() {
        return game;
    }

    public int fight() {
        return fight;
    }

    public int turn() {
        return turn;
    }

    public int lastTurn() {
        return lastTurn;
    }

    public void resetFight() {
        this.fight = 0;
        this.turn = 0;
        this.lastTurn = 0;
    }

    public void resetTurn() {
        this.lastTurn = this.turn;
        this.turn = 0;
    }

    public void increment(int amount) {
        game += amount;
        fight += amount;
        turn += amount;
    }
}
