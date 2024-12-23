import java.awt.*;

public class Oponent {
    private int x;
    private int y;
    private int speed = 1;
    private Directions move;

    public Oponent(int x, int y, Directions move) {
        this.x = x;
        this.y = y;
        this.move = move;
    }

    private Dimension size = new Dimension(50, 30);

    public void movement() {
        switch (Utils.level) {
            case 1 -> {
                if (x<0) Utils.oponents.forEach(oponent -> oponent.move=Directions.RIGHT);
                if (x+size.width>Utils.frameSize.width-17) Utils.oponents.forEach(oponent -> oponent.move=Directions.LEFT);
            }
        }

        switch (move) {
            case LEFT -> {
                x -= speed;
            }
            case RIGHT -> {
                x += speed;
            }
            case UP -> {
                y -= speed;
            }
            case DOWN -> {
                y += speed;
            }
        }
    }

    public Rectangle getShape() {

        return new Rectangle(x, y, size.width, size.height);
    }
}
