import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Utils {
    private static final Toolkit scren = Toolkit.getDefaultToolkit();
    private static final Dimension screenSieze = scren.getScreenSize();
    public static Dimension frameSize = new Dimension(screenSieze);
    public static ArrayList<PMissile> missiles = new ArrayList<>();
    public static ArrayList<PMissile> missiles2 = new ArrayList<>();
    public static ArrayList<OMissle> oMissles = new ArrayList<>();
    public static ArrayList<Oponent> oponents = new ArrayList<>();
    public static ArrayList<Rock> rocks = new ArrayList<>();
    public static ArrayList<PowerUp> powerUps = new ArrayList<>();
    public static boolean start = false;
    public static boolean pauza = false;
    public static boolean gameOver = false;
    public static boolean twoPlayers = false;
    public static int level = 4;
    public static int maxLevel = 5;
    public static float shotDelay = 0.1F;
    public static int rock = 0;
    public static int rockMax = 10;


    public static void changeLVL() {
        oponents.clear();
        missiles.clear();
        missiles2.clear();
        oMissles.clear();
        rocks.clear();
        rock = 0;
        level++;
        if (level > maxLevel) level = 1;

        if (level == 1) {
            int rows = 8;
            int columns = 4;

            for (int i = 0; i < columns; i++) {
                for (int j = 0; j < rows; j++) {
                    oponents.add(new Oponent(230 + 100 * j, 20 + 70 * i, Directions.LEFT));
                }
            }


        }

        if (level == 2) {
            int rows = 5;


            for (int i = 1; i < rows + 1; i++) {

                oponents.add(new Oponent(frameSize.width / 2 - 50 / 2 + 100 - 100 * i,
                        50, Directions.LEFT));

                oponents.add(new Oponent(frameSize.width / 2 - 50 / 2 - 100 + 100 * i,
                        50, Directions.LEFT));

                oponents.add(new Oponent(frameSize.width / 2 - 50 / 2 + 100 - 100 * i,
                        250, Directions.RIGHT));

                oponents.add(new Oponent(frameSize.width / 2 - 50 / 2 - 100 + 100 * i,
                        250, Directions.RIGHT));


            }
            oponents.removeFirst();
            oponents.remove(2);
            oponents.add(new Oponent(oponents.getLast().getX(), 150, Directions.UP));
            oponents.add(new Oponent(oponents.get(16).getX(), 150, Directions.DOWN));


        }
        if (level == 4) {
            int rows = 4;
            int columns = 3;

            for (int i = 0; i < columns; i++) {
                for (int j = 1; j < rows + 1; j++) {
                    oponents.add(new Oponent(Utils.frameSize.width / 2 - 50 / 2 + 100 - 100 * j, 50 + 100 * i, Directions.DOWN));
                    oponents.add(new Oponent(Utils.frameSize.width / 2 - 50 / 2 - 100 + 100 * j, 50 + 100 * i, Directions.DOWN));

                }
            }

            oponents.removeFirst();
            oponents.remove(15);
            oponents.remove(7);


        }
        if (level == 5) {
            Random rnd = new Random();
            Directions directions;
            if (rnd.nextInt(2) == 0) directions = Directions.LEFT;
            else directions = Directions.RIGHT;
            oponents.add(new Boss(Utils.frameSize.width / 2 - 150 / 2, 50, Directions.RIGHT));

        }

        if (level > maxLevel) level = 1;
    }
}
