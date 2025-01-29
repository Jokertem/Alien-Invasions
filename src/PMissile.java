import java.awt.*;


public class PMissile {
    protected int x;
    protected int y;
    private final int speed = 15;
    protected Dimension size = new Dimension(10, 30);
    private static final Player player = Player.getPlayer();
    private static final Player player2 = Player.getPlayer2();

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
            //if (pMissile.y < 0) Utils.missiles.remove(i);
            pMissile.y -= pMissile.speed;


        }
        for (int i = 0; i < Utils.missiles2.size(); i++) {
            PMissile pMissile = Utils.missiles2.get(i);
            // if (pMissile.y < 0) Utils.missiles2.remove(i);
            pMissile.y -= pMissile.speed;


        }
    }

    public static void collisons() {


        for (int i = 0; i < Utils.missiles.size(); i++) {
            PMissile missile = Utils.missiles.get(i);
            for (int j = 0; j < Utils.rocks.size(); j++) {
                Rock rock = Utils.rocks.get(j);
                if (missile.x + missile.size.width > rock.getX() && missile.x < rock.getX() + rock.getSize() && missile.y + missile.size.height > rock.getY() && missile.y < rock.getY() + rock.getSize()) {
                    Utils.missiles.remove(missile);
                    rock.setLives(rock.getLives() - 1);
                    if (rock.getLives() <= 0) {
                        Utils.rocks.remove(rock);
                        player.setScore(player.getScore() + 20);
                        Utils.rock++;

                    }


                }
            }
        }

        for (int i = 0; i < Utils.missiles2.size(); i++) {
            PMissile missile = Utils.missiles2.get(i);
            for (int j = 0; j < Utils.rocks.size(); j++) {
                Rock rock = Utils.rocks.get(j);
                if (missile.x + missile.size.width > rock.getX() && missile.x < rock.getX() + rock.getSize() && missile.y + missile.size.height > rock.getY() && missile.y < rock.getY() + rock.getSize()) {
                    Utils.missiles2.remove(i);
                    rock.setLives(rock.getLives() - 1);
                    if (rock.getLives() <= 0) {
                        Utils.rocks.remove(j);
                        player2.setScore(player2.getScore() + 20);
                        Utils.rock++;

                    }


                }
            }
        }


    }

    public static void getShape(Graphics2D graphics2D) {
        for (PMissile pMissile : Utils.missiles) {

            Rectangle rectangle = new Rectangle(pMissile.x, pMissile.y, pMissile.size.width, pMissile.size.height);
            graphics2D.fill(rectangle);
        }
        for (PMissile pMissile : Utils.missiles2) {

            Rectangle rectangle = new Rectangle(pMissile.x, pMissile.y, pMissile.size.width, pMissile.size.height);
            graphics2D.fill(rectangle);
        }

    }


}
