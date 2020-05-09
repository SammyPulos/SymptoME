package symptome;

import java.awt.FlowLayout;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

// TODO: make this a singleton
public class ApplicationWindow {
    private static ApplicationWindow instance_ = new ApplicationWindow();
    public static ApplicationWindow Instance() {
        return instance_;
    }
    
    private final Integer WIDTH = 1280;
    private final Integer HEIGHT = 720;
    private final JFrame applicationFrame;
    private JPanel currentPanel;
    
    private ApplicationWindow() {
        applicationFrame = new JFrame();
        applicationFrame.setSize(WIDTH, HEIGHT);
        applicationFrame.setLayout(new FlowLayout());
        applicationFrame.setVisible(true);
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        applicationFrame.setTitle("SymptoME");
    }
    
    // TODO: are screens going to extend panels?
    public Screen setScreen(ScreenType desiredScreen) {
        try {
            Screen screen = ScreenFactory.Instance().getScreenOfType(desiredScreen);
            if (currentPanel != null ) { applicationFrame.remove(currentPanel); }
            currentPanel = screen.getPanel();
            applicationFrame.getContentPane().add(currentPanel);
            applicationFrame.invalidate();
            applicationFrame.revalidate();
            applicationFrame.repaint();
            return screen;
        } catch (Exception ex) {
            System.err.println("Could not get screen of type " + desiredScreen.toString() + " due to exception:\n " + ex.getMessage());
            return null;
        }
    }
}
