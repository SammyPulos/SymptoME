package symptome;

import java.sql.SQLException;

enum ScreenType {
    LOGIN,
    REGISTRATION,
    HOME,
    PROFILE,
    SURVEY,
    INSIGHTS,
    HISTORY,
    NULL
}

public class ScreenFactory {
    private static ScreenFactory instance_ = new ScreenFactory();
    private ScreenFactory() { ; }
    public static ScreenFactory Instance() {
        return instance_;
    }
    
    public Screen getScreenOfType(ScreenType screenType) throws SQLException, IllegalArgumentException {
        switch(screenType) {
            case LOGIN:
                return new LoginScreen();
            case REGISTRATION:
                return new RegistrationScreen();
            case HOME:
                return new HomeScreen();
            case PROFILE:
                return new ProfileScreen();
            case SURVEY:
                return new SurveyScreen();
            case INSIGHTS:
                return new InsightsScreen();
            case HISTORY:
                return new HistoryScreen();
            default:
                return new NullScreen();
        }
    }
}
