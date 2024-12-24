import java.awt.*;
import java.util.ArrayList;

public class Utils {
    public static Dimension frameSize = new Dimension(1200, 650);
    public static ArrayList<PMissile> missiles = new ArrayList<PMissile>();
    public static ArrayList<OMissle> oMissles = new ArrayList<OMissle>();
    public static ArrayList<Oponent> oponents = new ArrayList<>();
    public static boolean start = false;
    public static boolean pauza = false;
    public static int level = 0;
    public static int maxLevel = 5;

    public static void changeLVL() {

        level++;
        if (level > maxLevel) level = 1;
        if (level == 1) {
            int rows =8;
            int columns =4;

            for (int i = 0; i <columns ; i++) {
                for (int j = 0; j <rows ; j++) {
                    oponents.add(new Oponent(230+100*j,20+70*i,Directions.LEFT));
                }
            }

        }

        System.out.println(level);

    }
}
