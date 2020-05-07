package symptome;

import java.util.concurrent.TimeUnit;

public class SymptoME {

    public static void main(String[] args) {
        // Setup main window frame
        ApplicationWindow applicationWindow = new ApplicationWindow();
        
        applicationWindow.SetScreen(new LoginScreen());
        
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            System.out.println("Can't sleep, thinking about where I went wrong");
        }
        
        applicationWindow.SetScreen(new RegistrationScreen());
        
        // Screen swithing will happe here (I think)
    }
    
}
