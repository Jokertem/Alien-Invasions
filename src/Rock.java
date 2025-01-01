import javax.swing.*;
import java.util.Random;

public class Rock {
    private int x;
    private int y;
    private int lives;
    private int size;


    private int scoreValue;

    public Rock(int x, int y, int lives, int size, int scoreValue) {
        this.x = x;
        this.y = y;
        this.lives = lives;
        this.size = size;
        this.scoreValue = scoreValue;
    }

    private final int speed = 9;

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

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSpeed() {
        return speed;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void movement() {

        if (y > Utils.frameSize.height) {
            Random rnd = new Random();
            x = rnd.nextInt(Utils.frameSize.width);
            y = 0 - rnd.nextInt(500);
        }
        y += speed;

    }

    public ImageIcon getShape() {
        return new ImageIcon(getClass().getResource("/Assets/Rock.png"));
    }
}
