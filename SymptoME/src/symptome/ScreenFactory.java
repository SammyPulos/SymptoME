package symptome;

enum ScreenType {
    LOGIN,
    REGISTRATION,
    HOME
}

public class ScreenFactory {
    private static ScreenFactory instance_ = new ScreenFactory();
    private ScreenFactory() { ; }
    public static ScreenFactory Instance() {
        return instance_;
    }
    
    public Screen getScreenOfType(ScreenType screenType) {
        switch(screenType) {
            case LOGIN:
                return new LoginScreen();
            case REGISTRATION:
                return new RegistrationScreen();
            case HOME:
                return new HomeScreen();
            default:
                throw new IllegalArgumentException("passed screenType not in enum");
        }
    }
}
