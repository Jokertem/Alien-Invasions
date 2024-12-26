import java.awt.*;
import java.util.ArrayList;

public class Utils {
    private static final Toolkit scren = Toolkit.getDefaultToolkit();
    private static final Dimension screenSieze = scren.getScreenSize();
    public static Dimension frameSize = new Dimension(screenSieze);
    public static ArrayList<PMissile> missiles = new ArrayList<>();
    public static ArrayList<OMissle> oMissles = new ArrayList<>();
    public static ArrayList<Oponent> oponents = new ArrayList<>();
    public static boolean start = false;
    public static boolean pauza = false;
    public static int level = 0;
    public static int maxLevel = 5;

    public static void changeLVL() {
        missiles.clear();
        oMissles.clear();
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

                oponents.add(new Oponent(frameSize.width / 2 - 50 / 2 + 100 - 100 * i, 50, Directions.LEFT));

                oponents.add(new Oponent(frameSize.width / 2 - 50 / 2 - 100 + 100 * i, 50, Directions.LEFT));

                oponents.add(new Oponent(frameSize.width / 2 - 50 / 2 + 100 - 100 * i, 250, Directions.RIGHT));

                oponents.add(new Oponent(frameSize.width / 2 - 50 / 2 - 100 + 100 * i, 250, Directions.RIGHT));


            }
            System.out.println(oponents.size());
            oponents.add(new Oponent(oponents.getLast().getX(), 150, Directions.UP));
            oponents.add(new Oponent(oponents.get(18).getX(), 150, Directions.DOWN));
            System.out.println(oponents.getLast().getX());

        }
        //System.out.println(level);
        if (level > maxLevel) level = 1;
    }
}
