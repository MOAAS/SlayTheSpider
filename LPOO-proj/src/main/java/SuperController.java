import SlayTheSpider.Controller.OverworldController;
import SlayTheSpider.Factory.GameEvent;
import SlayTheSpider.Factory.GameEventProcessor;
import SlayTheSpider.Factory.LanternaFactory;
import SlayTheSpider.Factory.SwingFactory;
import SlayTheSpider.Model.Game.FileStorage.GameStorage;
import SlayTheSpider.Model.Game.FileStorage.XMLStorage;
import SlayTheSpider.Model.Game.MainMenu.MainMenuCommands.MainMenuObserver;
import SlayTheSpider.Model.Game.MainMenu.MainMenuGenerator;
import SlayTheSpider.Model.Game.Overworld.FloorGenerator;
import SlayTheSpider.Controller.Controller;
import SlayTheSpider.Controller.ControllerObserver;
import SlayTheSpider.Controller.MainMenuController;
import SlayTheSpider.Model.MainMenuModel;
import SlayTheSpider.Model.OverworldModel;
import SlayTheSpider.View.ViewFactory;
import SlayTheSpider.View.TextWindow.LanternaWindow;
import SlayTheSpider.View.TextWindow.SwingWindow;

public class SuperController implements MainMenuObserver, ControllerObserver, GameEventProcessor {
    private static int FLOOR_WIDTH = 120;
    private static int FLOOR_HEIGHT = 40;

    private static final ViewFactory LANTERNA = new LanternaFactory(new LanternaWindow(120, 40));
    private static final ViewFactory SWING = new SwingFactory(new SwingWindow(1200, 800));
    private ViewFactory viewFactory = SWING;

    private boolean active;
    private Controller controller;
    private GameStorage storage;
    private final MainMenuModel menuModel;

    private boolean changingGraphics = false;

    public SuperController() {
        this.menuModel = new MainMenuModel(new MainMenuGenerator().makeMenu(this));
        this.active = true;
        this.setup();
    }

    private void setup() {
        this.storage = new XMLStorage(viewFactory.createSpriteStorage());

        this.controller = new MainMenuController(menuModel, viewFactory);
        this.viewFactory.getWindow().open();
        this.viewFactory.setupControls(this);
    }

    public void run() throws InterruptedException {
        while (this.active || this.changingGraphics) {

            synchronized (this) {
                this.controller.update();
                this.viewFactory.beginDraw();
                this.controller.drawView();
                this.viewFactory.endDraw();
            }
            Thread.sleep(20);
        }
        this.viewFactory.getWindow().close();
    }

    @Override
    public void notifiedQuitGame() {
        this.active = false;
    }

    @Override
    public void notifiedNewGame() {
        FloorGenerator generator = new FloorGenerator(FLOOR_WIDTH, FLOOR_HEIGHT);
        this.controller = new OverworldController(new OverworldModel(menuModel, storage, generator), viewFactory, this);
    }

    @Override
    public void notifiedChangeGraphics() {
        this.changingGraphics = true;
        this.viewFactory.resetWindow();
        if (this.viewFactory == LANTERNA)
            this.viewFactory = SWING;
        else this.viewFactory = LANTERNA;
        this.setup();
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void processEvent(GameEvent event) {
        if (event == GameEvent.Close) {
            this.active = this.changingGraphics;
            this.changingGraphics = false;
        }
        else this.controller.processEvent(event);
    }
}
