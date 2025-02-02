import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    ImageIcon Logo = new ImageIcon(getClass().getResource("/Assets/AlienShip.png"));

    public MyFrame() {
        Toolkit screen = Toolkit.getDefaultToolkit();
        Dimension screenSize = screen.getScreenSize();
        setTitle("Alien Invasions");
        setIconImage(Logo.getImage());
        setBounds(screenSize.width / 2 - Utils.frameSize.width / 2, screenSize.height / 2 - Utils.frameSize.height / 2, Utils.frameSize.width, Utils.frameSize.height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        MyComponent component = new MyComponent();
        add(component);
        getContentPane().setBackground(Color.BLACK);
        addKeyListener(new Events(component));
        addWindowListener(new WindowEvent());

    }

    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        frame.setVisible(true);

    }
}
