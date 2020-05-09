package symptome;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginScreen implements Screen{
    private JPanel loginPanel;
    private final LoginQueryDB loginQueryDB;
    
    JTextField usernameField;
    JPasswordField passwordField;
    
    public LoginScreen() throws SQLException {
        loginPanel = setupLoginPanel();
        loginQueryDB = new LoginQueryDB();
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
            public void actionPerformed(ActionEvent e) { 
                try {
                    handleLoginButtonPressed();
                } catch (SQLException ex) {
                    Logger.getLogger(LoginScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
    
    private Screen handleLoginButtonPressed() throws SQLException {
        // TODO: get hash of password
        // validate user
        if (loginQueryDB.validateUser(usernameField.getText(), passwordField.getText()))
           return (ApplicationWindow.Instance().setScreen(ScreenType.HOME));
        else{
            System.out.println("Incorrect username or password."); //add this to LoginScreen
            return (ApplicationWindow.Instance().setScreen(ScreenType.LOGIN));
        }
    }
    
    private Screen handleRegisterButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.REGISTRATION));
    }
}
