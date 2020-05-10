package symptome;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeScreen implements Screen{
    private JPanel screenPanel;
    
    public HomeScreen() {
        screenPanel = setupScreenPanel();
    }
    
    @Override
    public JPanel getPanel() {
        return screenPanel;
    }
    
    private JPanel setupScreenPanel() {
        screenPanel = new JPanel();        
        screenPanel.setLayout(new BoxLayout(screenPanel, BoxLayout.Y_AXIS));                
        
        JLabel titleLabel = new JLabel("Home");
        
        JButton profileButton = new JButton("Profile");
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleProfileButtonPressed(); }
        });
        JButton surveyButton = new JButton("Survey");
        surveyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleSurveyButtonPressed(); }
        });
        JButton insightsButton = new JButton("Insights");
        insightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleInsightsButtonPressed(); }
        });
        JButton historyButton = new JButton("History");
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleHistoryButtonPressed(); }
        });
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleLogoutButtonPressed(); }
        });

        screenPanel.add(titleLabel);
        screenPanel.add(profileButton);
        screenPanel.add(surveyButton);
        screenPanel.add(insightsButton);
        screenPanel.add(historyButton);
        screenPanel.add(logoutButton);
        
        return screenPanel;
    }
    
    private Screen handleProfileButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.PROFILE));
    }
    private Screen handleSurveyButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.SURVEY));
    }
    private Screen handleInsightsButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.INSIGHTS));
    }
    private Screen handleHistoryButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.HISTORY));
    }
    private Screen handleLogoutButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.LOGIN));
    }
    
}
