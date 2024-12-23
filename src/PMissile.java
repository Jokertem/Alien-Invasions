import java.awt.*;
import java.util.Collection;
import java.util.stream.IntStream;

public class PMissile {
    private int x;
    private int y;
    private final int speed = 5;
    private final Dimension size = new Dimension(10, 30);

    public PMissile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public Dimension getSize() {
        return size;
    }

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

    public static void movement() {


        for (int i = 0; i < Utils.missiles.size(); i++) {
            PMissile pMissile = Utils.missiles.get(i);
            if (pMissile.y < 0) Utils.missiles.remove(i);
            pMissile.y -= pMissile.speed;


        }
    }

    public static void getShape(Graphics2D graphics2D) {
        for (PMissile pMissile : Utils.missiles) {
            Rectangle rectangle = new Rectangle(pMissile.x, pMissile.y, pMissile.size.width, pMissile.size.height);
            graphics2D.fill(rectangle);
        }

    }

    ;
}
