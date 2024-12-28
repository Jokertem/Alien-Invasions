import java.awt.event.WindowAdapter;

public class WindowEvent extends WindowAdapter {

    @Override
    public void windowDeactivated(java.awt.event.WindowEvent e) {
        if (Utils.start) Utils.pauza=true;


    }
}
