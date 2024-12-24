import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public MyFrame() {
        Toolkit screen = Toolkit.getDefaultToolkit();
        Dimension screenSize = screen.getScreenSize();
        setTitle("Alien Invasions");
        setBounds(screenSize.width / 2 - Utils.frameSize.width / 2, screenSize.height / 2 - Utils.frameSize.height / 2, Utils.frameSize.width, Utils.frameSize.height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        MyComponent component = new MyComponent();
        add(component);
        getContentPane().setBackground(Color.BLACK);
        addKeyListener(new Events(component));

    }

    public static void main(String[] args) {

        MyFrame frame = new MyFrame();
        frame.setVisible(true);

    }
}
