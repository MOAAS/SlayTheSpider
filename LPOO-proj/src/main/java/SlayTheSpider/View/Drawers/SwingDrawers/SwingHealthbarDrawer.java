package SlayTheSpider.View.Drawers.SwingDrawers;

import SlayTheSpider.Model.Game.HealthBar.HealthBar;
import SlayTheSpider.Model.Position.Position2D;

import java.awt.*;

public abstract class SwingHealthbarDrawer implements SwingDrawer {
    protected void drawHealth(int width, int height, Graphics g) {
        int middle = (int)Math.ceil(getHealthBar().getRemainingHealthPerc() * width);

        Position2D posBar = this.getPosition();
        Position2D posMid = posBar.right(middle);
        Position2D posHP = posBar.right(width + 4).down(5 + height / 2);

        g.setColor(new Color(155, 26, 26));
        g.fillRect(posBar.getX() - 2, posBar.getY() - 2, width + 4, height + 4);

        g.setColor(new Color(255, 0, 0));
        g.fillRect(posBar.getX(), posBar.getY(), middle, height);

        g.setColor(new Color(194, 31, 31));
        g.fillRect(posMid.getX(), posMid.getY(), width - middle, height);

        g.setColor(new Color(255, 0, 0));

        g.setFont(new Font("Tahoma", Font.BOLD, 12));
        g.drawString(getHealthBar().getHealth() + "/" + getHealthBar().getMaxHealth(), posHP.getX(), posHP.getY());
    }

    protected abstract Position2D getPosition();
    protected abstract HealthBar getHealthBar();
}
