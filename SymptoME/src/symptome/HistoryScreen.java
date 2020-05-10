package symptome;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HistoryScreen implements Screen{
    private JPanel screenPanel;
    
    public HistoryScreen() {
        screenPanel = setupScreenPanel();
    }
    
    @Override
    public JPanel getPanel() {
        return screenPanel;
    }
    
    private JPanel setupScreenPanel() {
        screenPanel = new JPanel();        
        screenPanel.setLayout(new BoxLayout(screenPanel, BoxLayout.Y_AXIS));                
        
        JLabel titleLabel = new JLabel("History:");

        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleHomeButtonPressed(); }
        });        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleLogoutButtonPressed(); }
        });
        
        screenPanel.add(titleLabel);
        screenPanel.add(homeButton);
        screenPanel.add(logoutButton);
        
        return screenPanel;
    }
    
    private Screen handleHomeButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.HOME));
    }
    
    private Screen handleLogoutButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.LOGIN));
    }    
    
}
