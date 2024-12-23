import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Random;

public class Star {
    private int x;
    private int y;
    private int speed = 2;
    private final int size = 10;
    private final Random rnd = new Random();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Star(int x, int y) {
        this.x = x;
        this.y = y;


    }

    public static void getShape(ArrayList<Star> stars, Graphics2D graphics2D) {
        for (Star star : stars) {
            Ellipse2D ellipse2D = new Ellipse2D.Float(star.x, star.y, star.size, star.size);
            graphics2D.fill(ellipse2D);
        }

    }

    public static void updatePoss(ArrayList<Star> stars) {

        for (Star star : stars) {
            if (star.y > Utils.frameSize.height) {
                star.x = star.rnd.nextInt(Utils.frameSize.width);
                star.y = -star.size - 10;
            }
            star.y += star.speed;
        }


    }
}