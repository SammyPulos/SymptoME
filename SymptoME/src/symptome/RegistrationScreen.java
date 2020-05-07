package symptome;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegistrationScreen implements Screen {
    private JPanel registrationPanel;
    
    public RegistrationScreen() {
        registrationPanel = setupRegistrationPanel();
        setupLogic();
    }
    
    @Override
    public JPanel getPanel() {
        return registrationPanel;
    }
    
    private JPanel setupRegistrationPanel() {
        registrationPanel = new JPanel();        
        registrationPanel.setLayout(new FlowLayout());                
        
        JLabel titleLabel = new JLabel("Register for SymptoME");
        
        JLabel usernameLabel = new JLabel("Username");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password");
        JPasswordField passwordField = new JPasswordField(20);
        
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        
        registrationPanel.add(titleLabel);
        registrationPanel.add(usernameLabel);
        registrationPanel.add(usernameField);
        registrationPanel.add(passwordLabel);
        registrationPanel.add(passwordField);
        registrationPanel.add(loginButton);
        registrationPanel.add(registerButton);
        
        return registrationPanel;
    }
    
    private RegistrationScreen setupLogic() {
        return this;
    }
        
}
