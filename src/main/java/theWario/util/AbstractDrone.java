package theWario.util;

import java.awt.*;

public class AbstractDrone {
    public int position;
    public Point location;

    public AbstractDrone(int position2, int x, int y) {
        position = position2;
        location = new Point(x, y);
    }
}
