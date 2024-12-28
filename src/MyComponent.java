import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class MyComponent extends JComponent {
    ArrayList<Star> stars = new ArrayList<>();
    Random rnd = new Random();
    private final Player player = Player.getPlayer();
    private final Player player2 = Player.getPlayer2();


    public MyComponent() {
        int starsCount = 15;
        for (int i = 0; i < starsCount; i++) {
            int randomX = rnd.nextInt(Utils.frameSize.width);
            int randomY = rnd.nextInt(Utils.frameSize.height);
            stars.add(new Star(randomX, randomY));

        }
        Timer delay = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player.getShot() < Utils.shotDelay + 0.1F && player.getShot() > 0) {
                    player.setShot(player.getShot() - 1);
                }
                if (player2.getShot() < Utils.shotDelay + 0.1F && player2.getShot() > 0) {
                    player2.setShot(player2.getShot() - 1);
                }


            }
        });
        Timer timer = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Utils.pauza) {
                    Star.updatePoss(stars);
                    if (Utils.start){
                    player.movement();
                    if (Utils.twoPlayers) player2.movement();
                    }
                    PMissile.movement();
                    PMissile.collisons();
                    OMissle.drop();
                    if (Utils.start && Utils.oponents.isEmpty()) Utils.changeLVL();
                    for (int j = 0; j < Utils.oponents.size(); j++) {
                        Oponent oponent = Utils.oponents.get(j);
                        oponent.movement();
                    }
                    if (!Utils.twoPlayers) {
                        if (player.getLives() <= 0) Utils.gameOver = true;

                    } else {
                        if (player.getLives() <= 0 && player2.getLives() <= 0) Utils.gameOver = true;


                    }
                }

                repaint();
            }

        });
        timer.start();
        delay.start();


    }

    @Override
    protected void paintComponent(Graphics g) {
        Toolkit.getDefaultToolkit().sync();
        Graphics2D graphics2D = (Graphics2D) g;

        if (Utils.start) {
            graphics2D.setPaint(Color.WHITE);
            Star.getShape(stars, graphics2D);
            graphics2D.setPaint(Color.GREEN);
            PMissile.getShape(graphics2D);
            graphics2D.fill(player.getShape());

            graphics2D.setPaint(Color.blue);
            Font font = new Font("Arial", Font.PLAIN, 18);
            graphics2D.setFont(font);
            String pl1Score = "Player One Score:" + player.getScore();
            String pl2Score = "Player Two Score:" + player2.getScore();
            FontMetrics metrics = g.getFontMetrics(font);
            int textWidth = metrics.stringWidth(pl1Score);
            graphics2D.drawString(pl1Score, Utils.frameSize.width - 50 - textWidth, Utils.frameSize.height - 90);

            graphics2D.setPaint(Color.RED);
            Heart.draw(graphics2D);
            for (Oponent oponent : Utils.oponents) graphics2D.fill(oponent.getShape());
            graphics2D.setPaint((Color.RED));
            OMissle.getShape(graphics2D);
            if (Utils.twoPlayers) {
                graphics2D.setPaint(Color.YELLOW);
                graphics2D.fill(player2.getShape());
                graphics2D.setPaint(Color.BLUE);
                graphics2D.drawString(pl2Score, 50, Utils.frameSize.height - 90);

            }

        } else if (!Utils.start) {
            graphics2D.setPaint(Color.white);
            Star.getShape(stars, graphics2D);
            graphics2D.setPaint(Color.BLUE);
            Font font = new Font("Arial", Font.PLAIN, 25);
            String text = "Press F1 to start";
            graphics2D.setFont(font);
            FontMetrics metrics = g.getFontMetrics(font);
            int textWidth = metrics.stringWidth(text);
            graphics2D.setFont(font);
            graphics2D.drawString(text, Utils.frameSize.width / 2 - textWidth / 2, Utils.frameSize.height / 2);
        }

        if (Utils.start && Utils.pauza) {
            graphics2D.setPaint(Color.BLUE);
            Font font = new Font("Arial", Font.PLAIN, 25);
            String text = "Pauza";
            FontMetrics metrics = g.getFontMetrics(font);
            int textWidth = metrics.stringWidth(text);
            graphics2D.setFont(font);
            graphics2D.drawString(text, Utils.frameSize.width / 2 - textWidth / 2, Utils.frameSize.height / 2);
        }
    }


}
