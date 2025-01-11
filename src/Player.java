import java.awt.*;

public class Player {
    private Player(int x, int y) {
        this.x = x;
        this.y = y;
        size = new Dimension(w, h);
    }

    private int x;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }


    private int y;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return 12;
    }

    private boolean left = false;

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    private boolean right = false;
    private int score = 0;


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private int lives = 3;

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    private int power = 1;

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    private static final int w = 90;
    private static final int h = 20;
    public Dimension size;
    private static final Player player = new Player(Utils.frameSize.width / 2 - w / 2,
            Utils.frameSize.height - 90 - h);
    private static final Player player2 = new Player(Utils.frameSize.width / 2 - w / 2 - 90, Utils.frameSize.height - 90 - h);
    private float shot = 0;

    public float getShot() {
        return shot;
    }

    public void setShot(float shot) {
        this.shot = shot;
    }

    public static Player getPlayer() {
        return player;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public void movement() {

        if (x < 0) x = 0;
        if (x + size.width >= Utils.frameSize.width) x = Utils.frameSize.width - size.width;


        if (left) x -= getSpeed();
        if (right) x += getSpeed();

        //Colisions with oponent missles
        for (int i = 0; i < Utils.oMissles.size(); i++) {
            OMissle oMissle = Utils.oMissles.get(i);
            if (player.getLives() > 0 && player.x + player.size.width > oMissle.getX() && player.x <
                    oMissle.getX() + oMissle.getSize().width && player.y + player.size.height >
                    oMissle.getY() && player.y < oMissle.getY() + oMissle.getSize().height) {
                Utils.missiles.clear();
                Utils.missiles2.clear();
                Utils.oMissles.clear();
                player.lives--;
                player.power = 1;
                Boss.laser = false;
                if (!Utils.twoPlayers) {
                    player.x = Utils.frameSize.width / 2 - w / 2;
                } else {
                    if (player.lives > 0) player.x = Utils.frameSize.width / 2 - w / 2 + 90;
                    if (player2.lives > 0) player2.x = Utils.frameSize.width / 2 - w / 2 - 90;
                }

            }
        }
        if (Utils.twoPlayers) {
            for (int i = 0; i < Utils.oMissles.size(); i++) {
                OMissle oMissle = Utils.oMissles.get(i);
                if (player2.getLives() > 0 && player2.x + player2.size.width > oMissle.getX() && player2.x < oMissle.getX() + oMissle.getSize().width && player2.y + player2.size.height >
                        oMissle.getY() && player2.y < oMissle.getY() + oMissle.getSize().height) {
                    Utils.missiles.clear();
                    Utils.missiles2.clear();
                    Utils.oMissles.clear();
                    player2.lives--;
                    player2.power = 1;
                    player.x = Utils.frameSize.width / 2 - w / 2 + 90;
                    player2.x = Utils.frameSize.width / 2 - w / 2 - 90;
                    Boss.laser = false;


                }
            }

        }
        //Colisions with oponent rocks
        for (int i = 0; i < Utils.rocks.size(); i++) {
            Rock rock = Utils.rocks.get(i);
            if (player.lives > 0 && player.x + player.size.width > rock.getX() && player.x <
                    rock.getX() + rock.getSize() && player.y + player.size.height >
                    rock.getY() && player.y < rock.getY() + rock.getSize()) {
                Utils.missiles.clear();
                Utils.missiles2.clear();
                Utils.rocks.clear();
                player.lives--;
                player.power=1;
                if (!Utils.twoPlayers) {
                    player.x = Utils.frameSize.width / 2 - w / 2;
                } else {
                    if (player.lives > 0) player.x = Utils.frameSize.width / 2 - w / 2 + 90;
                    if (player2.lives > 0) player2.x = Utils.frameSize.width / 2 - w / 2 - 90;
                }

            }

        }
        if (Utils.twoPlayers) {
            for (int i = 0; i < Utils.rocks.size(); i++) {
                Rock rock = Utils.rocks.get(i);
                if (player2.lives > 0 && player2.x + player2.size.width > rock.getX() && player2.x < rock.getX() + rock.getSize() && player2.y + player2.size.height >
                        rock.getY() && player2.y < rock.getY() + rock.getSize()) {
                    Utils.missiles.clear();
                    Utils.missiles2.clear();
                    Utils.rocks.clear();
                    player2.lives--;
                    player2.power=1;
                    player.x = Utils.frameSize.width / 2 - w / 2 + 90;
                    player2.x = Utils.frameSize.width / 2 - w / 2 - 90;

                }
            }

        }

    }


    public Rectangle getShape() {
        Point point = new Point(x, y);
        return new Rectangle(point, size);
    }
}
