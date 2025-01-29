import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class MyComponent extends JComponent {
    ArrayList<Star> stars = new ArrayList<>();
    Random rnd = new Random();
    private final Player player = Player.getPlayer();
    private final Player player2 = Player.getPlayer2();
    private final Heart heart = new Heart();
    private final ImageIcon BackGround = new ImageIcon(getClass().getResource("/Assets/BackGround.png"));


    public MyComponent() {
        int starsCount = 15;
//        for (int i = 0; i < starsCount; i++) {
//            int randomX = rnd.nextInt(Utils.frameSize.width);
//            int randomY = rnd.nextInt(Utils.frameSize.height);
//            stars.add(new Star(randomX, randomY));
//
//        }
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
                    //Star.updatePoss(stars);
                    if (Utils.start) {
                        player.movement();
                        if (Utils.twoPlayers) player2.movement();
                    }
                    PMissile.movement();
                    PMissile.collisons();
                    for (int i = 0; i < Utils.oMissles.size(); i++) {
                        OMissle oMissle = Utils.oMissles.get(i);
                        oMissle.drop(i);
                    }
                    for (int i = 0; i < Utils.powerUps.size(); i++) {
                        PowerUp powerUp = Utils.powerUps.get(i);
                        powerUp.movement(i);
                    }
                    if (Utils.powerUps.size() < 2) {
                        boolean probably = rnd.nextInt(300) == 0;
                        if (probably) Utils.powerUps.add(new PowerUp(rnd.nextInt(Utils.frameSize.width), 0));
                    }

                    Utils.rocks.forEach(Rock::movement);
                    if (Utils.start && Utils.oponents.isEmpty() && Utils.level != 3) {
                        Utils.oMissles.clear();
                        Utils.oponents.clear();
                        Utils.changeLVL();


                    }

                    for (int j = 0; j < Utils.oponents.size(); j++) {
                        Oponent oponent = Utils.oponents.get(j);
                        oponent.movement();
                    }

                    if (!Utils.twoPlayers) {
                        if (player.getLives() <= 0) {

                            Utils.missiles.clear();
                            Utils.missiles2.clear();
                            Utils.gameOver = true;
                        }

                    } else {
                        if (player.getLives() <= 0 && player2.getLives() <= 0) {
                            Utils.missiles.clear();
                            Utils.missiles2.clear();
                            Utils.gameOver = true;
                        }
                        if (player.getLives() <= 0) player.setX(Utils.frameSize.width + 10000);
                        if (player2.getLives() <= 0) player2.setX(Utils.frameSize.width + 10000);


                    }

                    if (Utils.rock >= 10) Utils.changeLVL();
                    if (Utils.level == 3) {
                        if (Utils.rocks.size() < Utils.rockMax) {
                            int size = rnd.nextInt(4);
                            switch (size) {
                                case 1 ->
                                        Utils.rocks.add(new Rock(rnd.nextInt(Utils.frameSize.width), 0 - rnd.nextInt(500), 3, 64, 10));
                                case 2 ->
                                        Utils.rocks.add(new Rock(rnd.nextInt(Utils.frameSize.width), 0 - rnd.nextInt(500), 5, 128, 20));
                                case 3 ->
                                        Utils.rocks.add(new Rock(rnd.nextInt(Utils.frameSize.width), 0 - rnd.nextInt(500), 8, 162, 30));

                            }


                        }

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
        graphics2D.drawImage(BackGround.getImage(), 0, 0, Utils.frameSize.width, Utils.frameSize.height, null);
        if (Utils.start && !Utils.gameOver) {
            graphics2D.setPaint(Color.WHITE);
            //Star.getShape(stars, graphics2D);
            graphics2D.setPaint(Color.GREEN);
            PMissile.getShape(graphics2D);
            graphics2D.drawImage(player.getShape().getImage(), player.getX(), player.getY(), player.size.width, player.size.height, null);

            graphics2D.setPaint(Color.blue);
            Font font = new Font("Arial", Font.PLAIN, 18);
            graphics2D.setFont(font);
            String pl1Score = "Player One Score:" + player.getScore();
            String pl2Score = "Player Two Score:" + player2.getScore();
            FontMetrics metrics = g.getFontMetrics(font);
            int textWidth = metrics.stringWidth(pl1Score);
            graphics2D.drawString(pl1Score, Utils.frameSize.width - 50 - textWidth, Utils.frameSize.height - 90);
            graphics2D.setPaint(Color.RED);
            Utils.rocks.forEach(rock -> {
                graphics2D.drawImage(rock.getShape().getImage(), rock.getX()
                        , rock.getY(), rock.getX() + rock.getSize(),
                        rock.getY() + rock.getSize(), 30, 250, 250, 500, null);
            });

            heart.draw(graphics2D);
            for (Oponent oponent : Utils.oponents)
                graphics2D.drawImage(oponent.getShape().getImage(), oponent.getX(), oponent.getY(), oponent.size.width, oponent.size.height, null);
            graphics2D.setPaint((Color.RED));
            OMissle.getShape(graphics2D);
            if (Utils.twoPlayers) {
                graphics2D.setPaint(Color.YELLOW);
                graphics2D.drawImage(player2.getShape().getImage(), player2.getX(), player2.getY(), player2.size.width, player2.size.height, null);
                graphics2D.setPaint(Color.BLUE);
                graphics2D.drawString(pl2Score, 50, Utils.frameSize.height - 90);

            }
            graphics2D.setPaint(Color.GREEN);
            Utils.powerUps.forEach(powerUp -> graphics2D.fill(powerUp.getShape()));

        } else if (!Utils.start) {
            graphics2D.setPaint(Color.white);
            //Star.getShape(stars, graphics2D);
            graphics2D.setPaint(Color.BLUE);
            Font font = new Font("Arial", Font.PLAIN, 25);
            String text = "Press F1 to start one Player";
            String text2 = "Press F2 to start two Players";
            String text3 = "Press Esc to Exit";
            graphics2D.setFont(font);
            FontMetrics metrics = g.getFontMetrics(font);
            int textWidth = metrics.stringWidth(text);
            int text2Width = metrics.stringWidth(text2);
            int text3Width = metrics.stringWidth(text3);
            graphics2D.setFont(font);
            graphics2D.drawString(text, Utils.frameSize.width / 2 - textWidth / 2, Utils.frameSize.height / 2 - 20);
            graphics2D.drawString(text2, Utils.frameSize.width / 2 - text2Width / 2, Utils.frameSize.height / 2 + 20);
            graphics2D.drawString(text3, Utils.frameSize.width / 2 - text3Width / 2, Utils.frameSize.height / 2 + 60);
        } else if (Utils.gameOver) {
            graphics2D.setPaint(Color.WHITE);
            //Star.getShape(stars, graphics2D);
            Font font = new Font("Arial", Font.PLAIN, 25);
            graphics2D.setFont(font);
            graphics2D.setPaint(Color.BLUE);
            String text = "Game Over";
            String text2 = "Press F1 to new Game";
            String text3 = "Press Esc to Exit";
            FontMetrics metrics = g.getFontMetrics();
            int textWidth = metrics.stringWidth(text);
            int text2Width = metrics.stringWidth(text2);
            int text3Width = metrics.stringWidth(text3);
            graphics2D.drawString(text, Utils.frameSize.width / 2 - textWidth / 2, Utils.frameSize.height / 2 - 20);
            graphics2D.drawString(text2, Utils.frameSize.width / 2 - text2Width / 2, Utils.frameSize.height / 2 + 20);
            graphics2D.drawString(text3, Utils.frameSize.width / 2 - text3Width / 2, Utils.frameSize.height / 2 + 60);
            Font fontScore = new Font("Arial", Font.PLAIN, 18);
            String pl1Score = "Player One Score:" + player.getScore();
            String pl2Score = "Player Two Score:" + player2.getScore();
            int textScoreW = metrics.stringWidth(pl1Score);
            graphics2D.setFont(fontScore);
            graphics2D.drawString(pl1Score, Utils.frameSize.width - 50 - textScoreW, Utils.frameSize.height - 90);
            if (Utils.twoPlayers) graphics2D.drawString(pl2Score, 50, Utils.frameSize.height - 90);

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
