package symptome;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeScreen implements Screen{
    private JPanel homePanel;
    
    public HomeScreen() {
        homePanel = setupRegistrationPanel();
    }
    
    @Override
    public JPanel getPanel() {
        return homePanel;
    }
    
    private JPanel setupRegistrationPanel() {
        homePanel = new JPanel();        
        homePanel.setLayout(new FlowLayout());                
        
        JLabel titleLabel = new JLabel("Home");
        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener((ActionEvent e) -> {
            handleLogoutButtonPressed();
        });
        
        homePanel.add(titleLabel);
        homePanel.add(logoutButton);
        
        return homePanel;
    }
    
    private Screen handleLogoutButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.LOGIN));
    }
    
}
