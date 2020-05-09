package symptome;

import java.awt.Color;
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
    private JPanel screenPanel;
    private final LoginQueryDB loginQueryDB;
    
    JTextField usernameField;
    JPasswordField passwordField;
    JLabel notificationLabel;
    
    public LoginScreen() throws SQLException {
        screenPanel = setupScreenPanel();
        loginQueryDB = new LoginQueryDB();
    }
    
    @Override
    public JPanel getPanel() {
        return screenPanel;
    }
    
    private JPanel setupScreenPanel() {
        screenPanel = new JPanel();        
        screenPanel.setLayout(new FlowLayout());                
        
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
        
        notificationLabel = new JLabel("");
        notificationLabel.setForeground(Color.red);
        
        screenPanel.add(titleLabel);
        screenPanel.add(usernameLabel);
        screenPanel.add(usernameField);
        screenPanel.add(passwordLabel);
        screenPanel.add(passwordField);
        screenPanel.add(loginButton);
        screenPanel.add(registerButton);
        screenPanel.add(notificationLabel);
        return screenPanel;
    }
    
    private Screen handleLoginButtonPressed() throws SQLException {
        // TODO: get hash of password and dont use getText
        // validate user
        if (usernameField.getText().equals("") || passwordField.getText().equals("")) {
            notificationLabel.setText("Please enter both a password and username.");
            return (this);
        } else if (loginQueryDB.validateUser(usernameField.getText(), passwordField.getText())) {
            return (ApplicationWindow.Instance().setScreen(ScreenType.HOME));
        } else {
            notificationLabel.setText("Incorrect username or password.");
            System.out.println("Incorrect username or password.");
            return (this);
        }
    }
    
    private Screen handleRegisterButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.REGISTRATION));
    }
}
