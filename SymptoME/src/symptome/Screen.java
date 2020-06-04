package symptome;

import javax.swing.JPanel;

public abstract class Screen extends Subject{
    protected JPanel screenPanel = null;
    
    public JPanel getPanel() {
        if (screenPanel == null) { return new JPanel(); }
        return screenPanel;
    }
    
    protected void toLoginScreen() {        
        this.setState(ScreenType.LOGIN);
        this.notifyObservers();
    }
    protected void toRegistrationScreen() {
        this.setState(ScreenType.REGISTRATION);
        this.notifyObservers();
    }
    protected void toHomeScreen() {
        this.setState(ScreenType.HOME);
        this.notifyObservers();
    }
    protected void toProfileScreen() {
        this.setState(ScreenType.PROFILE);
        this.notifyObservers();        
    }
    protected void toSurveyScreen() {
        this.setState(ScreenType.SURVEY);
        this.notifyObservers();      
    }
    protected void toHistoryScreen() {
        this.setState(ScreenType.HISTORY);
        this.notifyObservers();        
    }
    protected void toInsightsScreen() {
        this.setState(ScreenType.INSIGHTS);
        this.notifyObservers();        
    }
    protected void toNullScreen() {
        this.setState(ScreenType.NULL);
        this.notifyObservers();        
    }
}
