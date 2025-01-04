import java.awt.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;


public class Boss extends Oponent {


    private int lives;

    public Boss(int x, int y, Directions move) {
        super(x, y, move);
        setSize(new Dimension(150, 90));
        this.lives = 30;


    }


    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Directions getMove() {
        return move;
    }

    public static boolean laser = false;

    @Override
    public void movement() {
        //Collisions with player missiles
        for (int i = 0; i < Utils.missiles.size(); i++) {
            PMissile missile = Utils.missiles.get(i);
            for (int j = 0; j < Utils.oponents.size(); j++) {
                Oponent oponent = Utils.oponents.get(j);

                if (missile.getX() + missile.getSize().width > oponent.getX() && missile.getX() < oponent.getX() + oponent.getSize().width && missile.getY() + missile.getSize().height > oponent.getY() && missile.getY() < oponent.getY() + oponent.getSize().height) {
                    Utils.missiles.remove(i);
                    lives--;
                    player.setScore(player.getScore() + oponent.getScoreValue());
                    if (lives <= 0) {
                        Utils.oponents.remove(j);
                        player.setScore(player.getScore() + 50);
                    }
                }
            }
        }
        for (int i = 0; i < Utils.missiles2.size(); i++) {
            PMissile missile = Utils.missiles2.get(i);
            for (int j = 0; j < Utils.oponents.size(); j++) {
                Oponent oponent = Utils.oponents.get(j);
                if (missile.getX() + missile.getSize().width > oponent.getX() && missile.getX() < oponent.getX() + oponent.getSize().width && missile.getY() + missile.getSize().height > oponent.getY() && missile.getY() < oponent.getY() + oponent.getSize().height) {
                    Utils.missiles2.remove(i);
                    lives--;
                    player2.setScore(player2.getScore() + oponent.getScoreValue());
                    if (lives <= 0) {
                        Utils.oponents.remove(j);
                        player2.setScore(player.getScore() + 50);

                    }

                }
            }
        }
        Random rnd = new Random();

        //Laser
        boolean laserProbably = rnd.nextInt(250) == 0;
        if (laserProbably && !checkLaser()) {
            laser = true;
            Utils.oMissles.add(new Laser(x + 150 / 2 - 75 / 2, y + getSize().height + 10));

        }


        //Shot
        boolean probably = rnd.nextInt(15) == 0;
        if (probably && !laser) {

            for (int i = 0; i < 4; i++) {
                OMissle oMissle = new OMissle(x + getSize().width / 2 - 10 / 2 - 50 + 30 * i, y + getSize().height + 10);
                Utils.oMissles.add(oMissle);
            }
        }


        if (x < 0) {
            Utils.oponents.getFirst().move = Directions.RIGHT;
            laser = false;
            if (Utils.oMissles.size() != 0) Utils.oMissles.removeFirst();

        }
        if (x + getSize().width > Utils.frameSize.width) {
            Utils.oponents.getFirst().move = Directions.LEFT;
            laser = false;
            if (Utils.oMissles.size() != 0) Utils.oMissles.removeFirst();

        }


        int speed = 5;
        switch (move) {
            case LEFT -> x -= speed;

            case RIGHT -> x += speed;

            case UP -> y -= speed;

            case DOWN -> y += speed;

        }
    }

    public boolean checkLaser() {
        AtomicBoolean find = new AtomicBoolean(false);
        Utils.oMissles.forEach(oMissle -> {
            if (oMissle instanceof Laser) {
                find.set(true);
            }
        });
        return find.get();
    }
}
