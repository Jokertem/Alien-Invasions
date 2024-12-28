import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Events implements KeyListener {
    Player player = Player.getPlayer();
    Player player2 = Player.getPlayer2();
    Component component;

    public Events(Component component) {
        this.component = component;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT -> player.setLeft(true);
            case KeyEvent.VK_RIGHT -> player.setRight(true);

            case KeyEvent.VK_A -> player2.setLeft(true);
            case KeyEvent.VK_D -> player2.setRight(true);


            case KeyEvent.VK_ENTER -> {
                if (!Utils.pauza) {
                    if (player.getShot() <= 0) {
                        PMissile pMissile = new PMissile(player.getX() + player.size.width / 2, player.getY() - player.size.height);
                        Utils.missiles.add(pMissile);
                        player.setShot(Utils.shotDelay);

                    }
                }
            }
            case KeyEvent.VK_SPACE -> {
                if (!Utils.pauza && Utils.twoPlayers) {
                    if (player2.getShot() <= 0) {
                        PMissile pMissile = new PMissile(player2.getX() + player2.size.width / 2, player2.getY() - player2.size.height);
                        Utils.missiles2.add(pMissile);
                        player2.setShot(Utils.shotDelay);


                    }
                }
            }
            case KeyEvent.VK_F1 -> Utils.start = true;
            case KeyEvent.VK_F2 -> {
                if (!Utils.start) player.setX(player.getX() + 90);
                Utils.twoPlayers = true;
                Utils.start = true;
            }
            case KeyEvent.VK_P -> Utils.pauza = !Utils.pauza;
            case KeyEvent.VK_ESCAPE -> System.exit(0);


        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT -> player.setLeft(false);

            case KeyEvent.VK_RIGHT -> player.setRight(false);

            case KeyEvent.VK_A -> player2.setLeft(false);
            case KeyEvent.VK_D -> player2.setRight(false);

        }


    }
}


