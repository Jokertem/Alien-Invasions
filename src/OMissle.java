import java.awt.*;

public class OMissle extends PMissile {
    private int x;
    private int y;

    public OMissle(int x, int y) {
        super(x, y);
    }

    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public Dimension getSize() {
        return super.getSize();
    }

    public static void drop() {
        for (int i = 0; i < Utils.oMissles.size(); i++) {
            OMissle oMissile = Utils.oMissles.get(i);
            if (oMissile.getY() + oMissile.getSize().height > Utils.frameSize.height) {
                Utils.oMissles.remove(i);

            }
                oMissile.setY(oMissile.getY()+ oMissile.getSpeed());
        }
    }

    public static void getShape(Graphics2D graphics2D) {
        Utils.oMissles.forEach(oMissle -> {
            Point point = new Point(oMissle.getX(), oMissle.getY());
            Rectangle rectangle = new Rectangle(point, oMissle.getSize());
            graphics2D.fill(rectangle);
        });
    }
}
