import java.awt.*;

public class OMissle extends PMissile {

    public OMissle(int x, int y) {
        super(x, y);
    }

    @Override
    public int getSpeed() {
        return 10;
    }

    @Override
    public Dimension getSize() {
        return super.getSize();
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public void drop(int index) {

        if (y + size.height > Utils.frameSize.height) {
            Utils.oMissles.remove(index);
        }
        y += 10;


    }

    public static void getShape(Graphics2D graphics2D) {
        Utils.oMissles.forEach(oMissle -> {
            Point point = new Point(oMissle.getX(), oMissle.getY());
            Rectangle rectangle = new Rectangle(point, oMissle.getSize());
            graphics2D.fill(rectangle);
        });
    }
}
