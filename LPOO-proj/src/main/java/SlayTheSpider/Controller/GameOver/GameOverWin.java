package SlayTheSpider.Controller.GameOver;

public class GameOverWin implements GameOverType {
    @Override
    public String getTitle() {
        return "You defeated the final boss! Press any key to leave.";
    }

    @Override
    public boolean victory() {
        return true;
    }
}
