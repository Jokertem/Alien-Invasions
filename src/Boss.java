import java.awt.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;


public class Boss extends Oponent {


    private int lives;
    public static boolean laser;

    public Boss(int x, int y, Directions move) {
        super(x, y, move);
        setSize(new Dimension(150, 90));
        this.lives = 80;

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
                    if (lives <= 0) {
                        Utils.oMissles.clear();
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
                        Utils.oMissles.clear();
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
            Utils.oMissles.add(new Laser(x + getSize().width / 2-76/2+4, y + getSize().height + 10));



        }


        //Shot
        boolean probably = rnd.nextInt(15) == 0;
        if (probably && !laser) {


            OMissle oMissle1 = new OMissle(x + getSize().width / 2 - 10 / 2 -60, y + getSize().height + 10);
            OMissle oMissle2 = new OMissle(x + getSize().width / 2 - 10 /2 -30 , y + getSize().height + 10);
            OMissle oMissle3 = new OMissle(x + getSize().width / 2 - 10 / 2+30 , y + getSize().height + 10);
            OMissle oMissle4 = new OMissle(x + getSize().width / 2 - 10 / 2+60 , y + getSize().height + 10);
            OMissle oMissle5 = new OMissle(x + getSize().width / 2 - 10 / 2, y + getSize().height + 10);
            Utils.oMissles.add(oMissle1);
            Utils.oMissles.add(oMissle2);
            Utils.oMissles.add(oMissle3);
            Utils.oMissles.add(oMissle4);
            Utils.oMissles.add(oMissle5);

        }


        if (x < 0) {
            Utils.oponents.getFirst().move = Directions.RIGHT;
            laser = false;
            if (Utils.oMissles.size() != 0) Utils.oMissles.removeLast();

        }
        if (x + getSize().width > Utils.frameSize.width) {
            Utils.oponents.getFirst().move = Directions.LEFT;
            laser = false;
            if (Utils.oMissles.size() != 0) Utils.oMissles.removeLast();

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
