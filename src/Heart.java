import javax.swing.*;
import java.awt.*;

public class Heart {

    private static final int size = 48;
    private static final Player player = Player.getPlayer();
    private static final Player player2 = Player.getPlayer2();
    private static final Image img = new ImageIcon("Assets/Health.png").getImage();

    public static void draw(Graphics2D graphics2D) {
        for (int i = 0; i < player.getLives(); i++) {

            graphics2D.drawImage(img, Utils.frameSize.width - 205 + 50 * i,
                    Utils.frameSize.height - 70, Utils.frameSize.width - 205 + 50 * i + size,
                    Utils.frameSize.height - 70 + size, 0, 0, 10, 10, null);

        }
        if (Utils.twoPlayers){
            for (int i = 0; i < player2.getLives(); i++) {

                graphics2D.drawImage(img, 50 + 50 * i,
                        Utils.frameSize.height - 70, 50 + 50 * i + size,
                        Utils.frameSize.height - 70 + size, 0, 0, 10, 10, null);

            }
        }

    }
}
