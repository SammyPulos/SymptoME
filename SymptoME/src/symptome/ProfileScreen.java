package symptome;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProfileScreen implements Screen {
    private JPanel screenPanel;
    
    public ProfileScreen() {
        screenPanel = setupScreenPanel();
    }
    
    @Override
    public JPanel getPanel() {
        return screenPanel;
    }
    
    private JPanel setupScreenPanel() {
        screenPanel = new JPanel();        
        screenPanel.setLayout(new BoxLayout(screenPanel, BoxLayout.Y_AXIS));                
        
        JLabel titleLabel = new JLabel("Profile:");
        
        // TODO: fetch values from database
        JLabel usernameLabel = new JLabel("Username:");
        JLabel usernameValueLabel = new JLabel("GET USERNAME FROM DB");
        usernameValueLabel.setForeground(Color.red);
        JLabel zipCodeLabel = new JLabel("Zip code:");
        JLabel zipCodeValueLabel = new JLabel("GET ZIP FROM DB");
        zipCodeValueLabel.setForeground(Color.red);
        JLabel dobLabel = new JLabel("Date of birth:");
        JLabel dobValueLabel = new JLabel("GET DoB FROM DB");
        dobValueLabel.setForeground(Color.red);
        
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
        screenPanel.add(usernameLabel);
        screenPanel.add(usernameValueLabel);
        screenPanel.add(zipCodeLabel);
        screenPanel.add(zipCodeValueLabel);
        screenPanel.add(dobLabel);
        screenPanel.add(dobValueLabel);
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
