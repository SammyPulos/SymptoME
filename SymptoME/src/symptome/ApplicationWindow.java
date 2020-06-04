package symptome;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ApplicationWindow implements Observer {
    private static ApplicationWindow instance_ = new ApplicationWindow();
    public static ApplicationWindow Instance() {
        return instance_;
    }
    
    private final Integer WIDTH = 1280;
    private final Integer HEIGHT = 720;
    private final JFrame applicationFrame;
    private Screen currentScreen;
    
    private ApplicationWindow() {
        applicationFrame = new JFrame();
        applicationFrame.setSize(WIDTH, HEIGHT);
        applicationFrame.getContentPane().setMinimumSize(new Dimension(WIDTH, HEIGHT));
        applicationFrame.getContentPane().setMaximumSize(new Dimension(WIDTH, HEIGHT));
        applicationFrame.setLayout(new FlowLayout());
        applicationFrame.setVisible(true);
        applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        applicationFrame.setTitle("SymptoME");
        
        setScreen(ScreenType.LOGIN);
        displayScreen();
    }
    
    public void update(Subject subject) {
        stopDisplayScreen();
        if (subject == null) { 
            setScreen(ScreenType.NULL); 
        } else {
            setScreen((ScreenType)(subject.getState()));
        }
        displayScreen();
    }
    
    private Screen setScreen(ScreenType desiredScreenType) {
        Screen desiredScreen;
        try {
            desiredScreen = ScreenFactory.Instance().getScreenOfType(desiredScreenType);
        } catch (Exception ex) {
            System.err.println("Could not get screen of type " + desiredScreenType.toString() + " due to exception:\n " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
        if (currentScreen != null ) { currentScreen.detach(this); }
        currentScreen = desiredScreen;
        currentScreen.attach(this);
        return currentScreen;
    }
    
    private void stopDisplayScreen() {
        applicationFrame.remove(currentScreen.getPanel());
    }
    
    private Screen displayScreen() {
        if (currentScreen == null) { this.setScreen(ScreenType.NULL); }
        applicationFrame.setContentPane(currentScreen.getPanel());
        applicationFrame.invalidate();
        applicationFrame.revalidate();
        applicationFrame.repaint();
        return currentScreen;
    }
}
