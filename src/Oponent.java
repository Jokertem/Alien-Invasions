import java.awt.*;
import java.util.Random;

public class Oponent {
    protected int x;
    protected int y;
    protected Directions move;
    private final int scoreValue = 5;
    private Dimension size = new Dimension(50, 30);
    Player player = Player.getPlayer();
    Player player2 = Player.getPlayer2();

    public int getScoreValue() {
        return scoreValue;
    }

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

    public void setSize(Dimension size) {
        this.size = size;
    }

    public int getLives() {
        return -1;
    }

    public void movement() {

        //Collisions with player missiles
        for (int i = 0; i < Utils.missiles.size(); i++) {
            PMissile missile = Utils.missiles.get(i);
            for (int j = 0; j < Utils.oponents.size(); j++) {
                Oponent oponent = Utils.oponents.get(j);

                if (missile.getX() + missile.getSize().width > oponent.getX() && missile.getX() < oponent.getX() + oponent.getSize().width && missile.getY() + missile.getSize().height > oponent.getY() && missile.getY() < oponent.getY() + oponent.getSize().height) {
                    Utils.missiles.remove(i);
                    Utils.oponents.remove(j);
                    player.setScore(player.getScore() + oponent.getScoreValue());

                }
            }
        }
        for (int i = 0; i < Utils.missiles2.size(); i++) {
            PMissile missile = Utils.missiles2.get(i);
            for (int j = 0; j < Utils.oponents.size(); j++) {
                Oponent oponent = Utils.oponents.get(j);
                if (missile.getX() + missile.getSize().width > oponent.getX() && missile.getX() < oponent.getX() + oponent.getSize().width && missile.getY() + missile.getSize().height > oponent.getY() && missile.getY() < oponent.getY() + oponent.getSize().height) {
                    Utils.missiles2.remove(i);
                    Utils.oponents.remove(j);
                    player2.setScore(player2.getScore() + oponent.getScoreValue());


                }
            }
        }

        //Shot
        Random rnd = new Random();
        boolean probably = rnd.nextInt(90) == 0;
        if (probably && Utils.oponents.size() >=1) {
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
            case 4 -> {
                if (y <= 50) Utils.oponents.forEach(oponent -> oponent.move = Directions.DOWN);
                if (y + size.height >= Utils.frameSize.height - 150)
                    Utils.oponents.forEach(oponent -> oponent.move = Directions.UP);
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
