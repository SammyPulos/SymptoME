package symptome;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InsightsScreen implements Screen{
    private JPanel screenPanel;
    
    public InsightsScreen() throws SQLException {
        screenPanel = setupScreenPanel();
    }
    
    @Override
    public JPanel getPanel() {
        return screenPanel;
    }
    
    private JPanel setupScreenPanel() throws SQLException {
        screenPanel = new JPanel();        
        screenPanel.setLayout(new BoxLayout(screenPanel, BoxLayout.Y_AXIS));                
        
        JLabel titleLabel = new JLabel("Insights:");

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
        
        SetupInsights();
        
        screenPanel.add(homeButton);
        screenPanel.add(logoutButton);
        
        return screenPanel;
    }
    
    private Screen SetupInsights() throws SQLException {
        if (screenPanel == null) { return this; }
        
        InsightsFacade insightsFacade = new InsightsFacade(); // TODO: need to actually get/generate this
        
        JLabel percentSame = new JLabel("<html><br/>Overall " + insightsFacade.getPercentSameSymptoms() + "% users have recorded the same symptoms as yours at some point</html>");
        JLabel percentSameSymptomsTested = new JLabel("<html><br/>Out of people with symptoms matching your most recent report " + insightsFacade.getPercentSameSymptomsTested() + "% have been tested for COVID-19</html>");
        JLabel percentSameSymptomsResults = new JLabel("    Out of these " + insightsFacade.getPercentSameSymptomsPositive() + "% have tested positive and " + insightsFacade.getPercentSameSymptomsNegative() + "% have tested negative");
        JLabel percentSameLocationTested = new JLabel("<html><br/>In your area " + insightsFacade.getPercentSameLocationTested() + "% of users have been tested for COVID-19</html>");
        JLabel percentSameLocationResults = new JLabel("    Out of these " + insightsFacade.getPercentSameLocationPositive() + "% have tested positive and " + insightsFacade.getPercentSameLocationNegative() + "% have tested negative");
        JLabel feelingLabel = new JLabel("<html><br/>Feeling trend: [REMEMBER TO ADD A GRAPH]</html>");
        feelingLabel.setForeground(Color.red);
        
        screenPanel.add(percentSame);
        screenPanel.add(percentSameSymptomsTested);
        screenPanel.add(percentSameSymptomsResults);
        screenPanel.add(percentSameLocationTested);
        screenPanel.add(percentSameLocationResults);
        screenPanel.add(feelingLabel);
        
        return this;
    }
    
    private Screen handleHomeButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.HOME));
    }
    
    private Screen handleLogoutButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.LOGIN));
    }    
    
}
