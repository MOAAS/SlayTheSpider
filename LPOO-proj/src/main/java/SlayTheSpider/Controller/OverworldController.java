package SlayTheSpider.Controller;

import SlayTheSpider.Factory.GameEvent;
import SlayTheSpider.Model.FightModel;
import SlayTheSpider.Model.OverworldModel;
import SlayTheSpider.View.View;
import SlayTheSpider.View.ViewFactory;
import SlayTheSpider.Model.Game.Overworld.Floor;
import SlayTheSpider.Model.Game.Player;

public class OverworldController implements Controller {
    private final OverworldModel model;
    private final View view;
    private final ViewFactory factory;
    private ControllerObserver observer;

    public OverworldController(OverworldModel model, ViewFactory factory, ControllerObserver observer) {
        this.model = model;
        this.view = factory.makeOWView(model);
        this.factory = factory;
        this.observer = observer;
    }

    @Override
    public void processEvent(GameEvent event) {
        switch (event) {
            case ArrowUp: this.moveUp(model.getPlayer()); break;
            case ArrowLeft: this.moveLeft(model.getPlayer()); break;
            case ArrowDown: this.moveDown(model.getPlayer()); break;
            case ArrowRight: this.moveRight(model.getPlayer()); break;
            default: return;
        }

        this.update();
        Floor floor = model.getFloor();
        if (floor.doesFightCollide())
            this.observer.setController(new FightController(new FightModel(model, floor.checkFightCollision()), factory, observer));

    }

    private void moveRight(Player player) {
        if (model.getFloor().isValidTile(player.getMapPosition().right(1)))
            player.getOWPlayer().moveRight();
    }

    private void moveDown(Player player) {
        if (model.getFloor().isValidTile(player.getMapPosition().down(1)))
            player.getOWPlayer().moveDown();
    }

    private void moveLeft(Player player) {
        if (model.getFloor().isValidTile(player.getMapPosition().left(1)))
            player.getOWPlayer().moveLeft();
    }

    private void moveUp(Player player) {
        if (model.getFloor().isValidTile(player.getMapPosition().up(1)))
            player.getOWPlayer().moveUp();
    }

    @Override
    public void update() {
        model.getFloor().updateVisibility();
        model.getFloor().updateRoomStatus();
    }

    @Override
    public void drawView() {
        this.view.setTitle("Arrow Keys to Move");
        this.view.draw();
    }
}
