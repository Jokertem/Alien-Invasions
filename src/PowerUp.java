import java.awt.geom.Ellipse2D;

public class PowerUp {
    private int x;
    private int y;
    private int speed = 10;
    private final int size = 20;
    private final Player player = Player.getPlayer();
    private final Player player2 = Player.getPlayer2();

    public PowerUp(int x, int y) {
        this.x = x;
        this.y = y;
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

    public int getSize() {
        return size;
    }


    public int getSpeed() {
        return speed;
    }

    public void movement(int index) {
        if (y > Utils.frameSize.height) Utils.powerUps.remove(index);

        if (player.getLives() > 0 && x + size > player.getX() && x < player.getX() + player.size.width && y + size >
                player.getY() && y
                < player.getY() + player.size.height) {
            Utils.powerUps.remove(index);
            if (player.getPower() < 4) player.setPower(player.getPower() + 1);
            player.setScore(player.getScore() + 30);
        }

        if (Utils.twoPlayers && player2.getLives() > 0 && x + size > player2.getX() && x < player2.getX() + player2.size.width && y + size >
                player2.getY() && y
                < player2.getY() + player2.size.height) {
            Utils.powerUps.remove(index);
            if (player2.getPower() < 4) player2.setPower(player2.getPower() + 1);

            player2.setScore(player2.getScore() + 30);
        }

        y += speed;
    }

    public Ellipse2D getShape() {
        return new Ellipse2D.Float(x, y, size, size);
    }


}
