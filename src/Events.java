import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Events implements KeyListener {
    Player player = Player.getPlayer();
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


            case KeyEvent.VK_SPACE -> {
                if (!Utils.pauza) {
                    PMissile pMissile = new PMissile(player.getX() + player.size.width / 2, player.getY());
                    Utils.missiles.add(pMissile);
                }
            }
            case KeyEvent.VK_F1 -> Utils.start = true;
            case KeyEvent.VK_P -> Utils.pauza = !Utils.pauza;
            case  KeyEvent.VK_ESCAPE -> System.exit(0);


        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT -> player.setLeft(false);


            case KeyEvent.VK_RIGHT -> player.setRight(false);


        }


    }
}


