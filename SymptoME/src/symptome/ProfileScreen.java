package symptome;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProfileScreen implements Screen {
    private JPanel screenPanel;
    private ProfileQueryDB profileQueryDB;
    
    public ProfileScreen() throws SQLException {
        profileQueryDB = new ProfileQueryDB();
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
        JLabel usernameValueLabel = new JLabel(SessionData.instance().getUsername());
        JLabel zipCodeLabel = new JLabel("Zip code:");
        JLabel zipCodeValueLabel = new JLabel(profileQueryDB.retrieveZipCode(SessionData.instance().getUsername()));
        JLabel dobLabel = new JLabel("Date of birth:");
        JLabel dobValueLabel = new JLabel(profileQueryDB.retrieveDOB(SessionData.instance().getUsername()));
        
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
