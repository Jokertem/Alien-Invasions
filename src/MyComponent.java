import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class MyComponent extends JComponent {
    ArrayList<Star> stars = new ArrayList<>();
    private int starsCount = 10;
    Random rnd = new Random();
    private final Player player = Player.getPlayer();


    public MyComponent() {

        for (int i = 0; i < starsCount; i++) {
            int randomX = rnd.nextInt(Utils.frameSize.width);
            int randomY = rnd.nextInt(Utils.frameSize.height);
            stars.add(new Star(randomX, randomY));


            Timer timer = new Timer(25, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!Utils.pauza) {
                        Star.updatePoss(stars);
                        player.movement();
                        PMissile.movement();
                        PMissile.collisons();
                        OMissle.drop();
                        if (Utils.start && Utils.oponents.isEmpty()) Utils.changeLVL();
                        for (int j = 0; j < Utils.oponents.size(); j++) {
                            Oponent oponent = Utils.oponents.get(j);
                            oponent.movement();
                        }
                    }
                    repaint();
                }

            });
            timer.start();
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        Toolkit.getDefaultToolkit().sync();
        Graphics2D graphics2D = (Graphics2D) g;
        if (Utils.start) {
            graphics2D.setPaint(Color.WHITE);
            Star.getShape(stars, graphics2D);
            graphics2D.setPaint(Color.GREEN);
            graphics2D.fill(player.getShape());
            PMissile.getShape(graphics2D);
            graphics2D.setPaint(Color.RED);
            for (Oponent oponent : Utils.oponents) graphics2D.fill(oponent.getShape());
            graphics2D.setPaint(Color.red);
            OMissle.getShape(graphics2D);
        } else if (!Utils.start) {
            graphics2D.setPaint(Color.white);
            Star.getShape(stars, graphics2D);
            graphics2D.setPaint(Color.BLUE);
            Font font = new Font("Arial", Font.PLAIN, 25);
            String text = "Press F1 to start";
            FontMetrics metrics = g.getFontMetrics(font);
            int textWidth = metrics.stringWidth(text);
            graphics2D.setFont(font);
            graphics2D.drawString(text, Utils.frameSize.width / 2 - textWidth / 2, Utils.frameSize.height / 2);
        }


    }
}
