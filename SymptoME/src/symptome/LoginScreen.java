package symptome;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginScreen implements Screen{
    private JPanel loginPanel;
    JTextField usernameField;
    JPasswordField passwordField;
    
    public LoginScreen() {
        loginPanel = setupLoginPanel();
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
        usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField(20);
        
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleLoginButtonPressed(); }
        });
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleRegisterButtonPressed(); }
        });
        
        loginPanel.add(titleLabel);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);
        
        return loginPanel;
    }
    
    private Screen handleLoginButtonPressed() {
        String username = usernameField.getText();
        // get hash of password
        // validate user
        // if success
            return (ApplicationWindow.Instance().setScreen(ScreenType.HOME));
        // if fail
        //  idk
    }
    
    private Screen handleRegisterButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.REGISTRATION));
    }
}
