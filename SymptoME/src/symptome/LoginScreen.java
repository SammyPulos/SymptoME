package symptome;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginScreen extends Screen{
    private final LoginQueryDB loginQueryDB;
    
    JTextField usernameField;
    JPasswordField passwordField;
    JLabel notificationLabel;
    
    public LoginScreen() throws SQLException {
        screenPanel = setupScreenPanel();
        loginQueryDB = new LoginQueryDB();
    }
    
    private JPanel setupScreenPanel() {
        
        screenPanel = new JPanel();        
        screenPanel.setBackground(Color.white);
        screenPanel.setLayout(null); 
        
        usernameField = new JTextField();
        usernameField.setBounds(675, 340, 320, 36);
        usernameField.setFont(new Font("SegoeUI", Font.PLAIN, 24));
        usernameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        screenPanel.add(usernameField);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(675, 433, 320, 36);
        passwordField.setFont(new Font("SegoeUI", Font.PLAIN, 24));
        passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        screenPanel.add(passwordField);
        
        notificationLabel = new JLabel("");
        notificationLabel.setForeground(Color.red);
        notificationLabel.setFont(new Font("SegoeUI", Font.BOLD, 16));
        notificationLabel.setBounds(660, 474, 300, 39);
        screenPanel.add(notificationLabel);
        
        JLabel background;
        try { 
            background = new JLabel(new ImageIcon(ImageIO.read(new File("SymptoMeLogin.png"))));
            background.setBounds(0, 0, 1280, 720);
            screenPanel.add(background); 
        } catch (IOException ex) { 
            System.out.println("Cannot load background for login screen"); 
        }
        
        JButton loginButton;
        try { 
            loginButton = new JButton(new ImageIcon(ImageIO.read(new File("SymptoMeLoginButton.png"))));
            loginButton.setBounds(660, 507, 126, 39);
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
            screenPanel.add(loginButton); 
        } catch (IOException ex) { 
            System.out.println("Cannot load login button for login screen"); 
        }
        
        JButton registerButton;
        try { 
            registerButton = new JButton(new ImageIcon(ImageIO.read(new File("SymptoMeSignupButton.png"))));
            registerButton.setBounds(660, 555, 126, 39);
            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { handleRegisterButtonPressed(); }
            });
            screenPanel.add(registerButton); 
        } catch (IOException ex) { 
            System.out.println("Cannot load signup button for login screen"); 
        }

        return screenPanel;
    }
    
    private void handleLoginButtonPressed() throws SQLException {
        // TODO: get hash of password and dont use getText
        if (usernameField.getText().equals("") || passwordField.getText().equals("")) {
            notificationLabel.setText("Missing username or password.");
        } else if (loginQueryDB.validateUser(usernameField.getText(), passwordField.getText())) {
            SessionData.instance().setUsername(usernameField.getText());
            this.toHomeScreen(); 
        } else {
            notificationLabel.setText("Incorrect username or password.");
            System.out.println("Incorrect username or password.");
        }
    }
    
    private void handleRegisterButtonPressed() {
        this.toRegistrationScreen();
    }
}
