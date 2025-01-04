import java.awt.*;

public class Boss extends Oponent{
    public Boss(int x, int y, Directions move) {
        super(x, y, move);
    }
    private Dimension size = new Dimension(50,35);

    @Override
    public Dimension getSize() {
        return size;
    }
}
