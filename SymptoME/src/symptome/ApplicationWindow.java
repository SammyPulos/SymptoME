package symptome;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

// TODO: make this a singleton
public class ApplicationWindow {
    private final Integer WIDTH = 1280;
    private final Integer HEIGHT = 720;
    private final JFrame applicationFrame;
    private JPanel currentPanel;
    
    public ApplicationWindow() {
        applicationFrame = new JFrame();
        applicationFrame.setSize(WIDTH, HEIGHT);
        applicationFrame.setLayout(new FlowLayout());
        applicationFrame.setVisible(true);
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        applicationFrame.setTitle("SymptoME");
    }
    
    // TODO: are screens going to extend panels?
    public ApplicationWindow SetScreen(Screen desiredScreen) {
        if (currentPanel != null ) { applicationFrame.remove(currentPanel); }
        currentPanel = desiredScreen.getPanel();
        applicationFrame.getContentPane().add(currentPanel);
        applicationFrame.invalidate();
        applicationFrame.revalidate();
        applicationFrame.repaint();
        return this;
    }
}
