import java.awt.*;

public class Laser extends OMissle{
    public Laser(int x, int y) {
        super(x, y);
        setSize(new Dimension(75,50));

    }

    @Override
    public void drop(int index) {
        Oponent boss = Utils.oponents.getFirst();
        x = boss.getX()+boss.getSize().width/2-75/2;
        setSize(new Dimension(75,getSize().height+5));
    }
}
