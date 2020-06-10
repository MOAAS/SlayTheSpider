package SlayTheSpider.View.Drawers.LanternaDrawers;

import SlayTheSpider.Model.Game.HealthBar.HealthBar;
import SlayTheSpider.View.TextWindow.LanternaGraphics;
import SlayTheSpider.Model.Position.Position2D;

public abstract class LanternaHealthBarDrawer implements LanternaDrawer {
    protected abstract HealthBar getHealthBar();
    protected abstract Position2D getPosition();

    protected void drawHealth(int width, LanternaGraphics drawer) {
        Position2D position = this.getPosition();

        int middle = (int)Math.ceil(getHealthBar().getRemainingHealthPerc() * width);

        drawer.setBackgroundColor("ff0000");
        drawer.drawRect(position, middle, 1);
        drawer.setBackgroundColor("8c2323");
        drawer.drawRect(position.right(middle), width - middle, 1);

        drawer.setBackgroundColor("000000");
        drawer.setTextColor("ff0000");
        drawer.drawString(getHealthBar().getHealth() + "/" + getHealthBar().getMaxHealth(), position.right(width));
    }

}