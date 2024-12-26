import java.awt.*;
import java.util.Random;

public class Oponent {
    private int x;
    private int y;
    private Directions move;

    public Oponent(int x, int y, Directions move) {
        this.x = x;
        this.y = y;
        this.move = move;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Dimension getSize() {
        return size;
    }

    private final Dimension size = new Dimension(50, 30);

    public void movement() {
        //Shot
        Random rnd = new Random();
        boolean probably = rnd.nextInt(800) == 0;
        if (probably) {

            Oponent randomAlien = Utils.oponents.get(rnd.nextInt(Utils.oponents.size()));
            OMissle oMissle = new OMissle(randomAlien.getX() + randomAlien.getSize().width / 2, randomAlien.y);
            Utils.oMissles.add(oMissle);
        }


        switch (Utils.level) {
            case 1 -> {
                if (x < 0) Utils.oponents.forEach(oponent -> oponent.move = Directions.RIGHT);
                if (x + size.width > Utils.frameSize.width)
                    Utils.oponents.forEach(oponent -> oponent.move = Directions.LEFT);
            }
            case 2 -> {
                if (x <= Utils.frameSize.width / 2 - 50 / 2 + 100 - 100 * 5 && y >= 50) move = Directions.DOWN;
                if (x <= Utils.frameSize.width / 2 - 50 / 2 + 100 - 100 * 5 && y >= 250) move = Directions.RIGHT;
                if (x >= Utils.frameSize.width / 2 - 50 / 2 - 100 + 100 * 5 && y >= 250) move = Directions.UP;
                if (x >= Utils.frameSize.width / 2 - 50 / 2 - 100 + 100 * 5 && y <= 50) move = Directions.LEFT;

            }
        }

        int speed = 5;
        switch (move) {
            case LEFT -> x -= speed;

            case RIGHT -> x += speed;

            case UP -> y -= speed;

            case DOWN -> y += speed;

        }
    }

    public Rectangle getShape() {

        return new Rectangle(x, y, size.width, size.height);
    }
}
