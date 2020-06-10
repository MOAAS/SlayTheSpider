package SlayTheSpider.Controller.GameOver;

public class GameOverLoss implements GameOverType {
    @Override
    public String getTitle() {
        return "You lost! Press any key to leave.";
    }

    @Override
    public boolean victory() {
        return false;
    }
}
