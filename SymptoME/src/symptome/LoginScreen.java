package symptome;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LoginScreen implements Screen{
    private JPanel loginPanel;
    
    public LoginScreen() {
        loginPanel = setupLoginPanel();
        setupLogic();
    }
    
    @Override
    public JPanel getPanel() {
        return loginPanel;
    }
    
    private JPanel setupLoginPanel() {
        loginPanel = new JPanel();        
        loginPanel.setLayout(new FlowLayout());                
        
        JLabel titleLabel = new JLabel("Login to SymptoME");
        
        JLabel usernameLabel = new JLabel("Username");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password");
        JPasswordField passwordField = new JPasswordField(20);
        
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        
        loginPanel.add(titleLabel);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);
        
        return loginPanel;
    }
    
    private LoginScreen setupLogic() {
        return this;
    }
    
}
