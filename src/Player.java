import java.awt.*;

public class Player {
    private int x;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }


    private final int y;

    public int getY() {
        return y;
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
    private int lives = 3;


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    private static final int w = 90;
    private static final int h = 20;
    public Dimension size;
    private static final Player player = new Player(Utils.frameSize.width / 2 - w / 2, Utils.frameSize.height - 90 - h);


    private Player(int x, int y) {
        this.x = x;
        this.y = y;
        size = new Dimension(w, h);
    }

    public static Player getPlayer() {
        return player;
    }

    public void movement() {

        if (x < 0) x = 0;
        if (x + size.width >= Utils.frameSize.width) x = Utils.frameSize.width - size.width;


        if (left) x -= getSpeed();
        if (right) x += getSpeed();

        //Colisions with oponent missles
        for (int i = 0; i < Utils.oMissles.size(); i++) {
            OMissle oMissle = Utils.oMissles.get(i);
            if (x + size.width > oMissle.getX() && x < oMissle.getX() + oMissle.getSize().width && y + size.height > oMissle.getY() && y < oMissle.getY() + oMissle.getSize().height) {
                x = Utils.frameSize.width / 2 - w / 2;
                Utils.missiles.clear();
                Utils.oMissles.clear();
            }
        }

    }


    public Rectangle getShape() {
        Point point = new Point(x, y);
        return new Rectangle(point, size);
    }
}
