package symptome;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
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

public class RegistrationScreen implements Screen {
    private JPanel screenPanel;
    private RegisterQueryDB registerQueryDB;
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField zipField;
    private JDateChooser dobChooser;
    private JLabel notificationLabel;
    
    public RegistrationScreen() throws SQLException {
        screenPanel = setupScreenPanel();
        registerQueryDB = new RegisterQueryDB();
    }
    
    @Override
    public JPanel getPanel() {
        return screenPanel;
    }
    
    private JPanel setupScreenPanel() {
        screenPanel = new JPanel();        
        screenPanel.setBackground(Color.white);
        screenPanel.setLayout(null); 
        
        usernameField = new JTextField();
        usernameField.setBounds(675, 228, 320, 36);
        usernameField.setFont(new Font("SegoeUI", Font.PLAIN, 24));
        usernameField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        screenPanel.add(usernameField);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(675, 324, 320, 36);
        passwordField.setFont(new Font("SegoeUI", Font.PLAIN, 24));
        passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        screenPanel.add(passwordField);
        
        zipField = new JTextField();
        zipField.setBounds(675, 419, 320, 36);
        zipField.setFont(new Font("SegoeUI", Font.PLAIN, 24));
        zipField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        screenPanel.add(zipField);

        dobChooser = new JDateChooser();
        dobChooser.setBounds(675, 517, 320, 36);
        dobChooser.getJCalendar().setPreferredSize(new Dimension(320, 240));
        dobChooser.setFont(new Font("SegoeUI", Font.PLAIN, 24));
        dobChooser.getJCalendar().setFont(new Font("SegoeUI", Font.PLAIN, 12));
        dobChooser.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        screenPanel.add(dobChooser);
        
        notificationLabel = new JLabel("");
        notificationLabel.setForeground(Color.red);
        notificationLabel.setFont(new Font("SegoeUI", Font.BOLD, 16));
        notificationLabel.setBounds(660, 556, 300, 39);
        screenPanel.add(notificationLabel);
        
        JLabel background;
        try { 
            background = new JLabel(new ImageIcon(ImageIO.read(new File("SymptoMeSignup.png"))));
            background.setBounds(0, 0, 1280, 720);
            screenPanel.add(background); 
        } catch (IOException ex) { 
            System.out.println("Cannot load background for signup screen"); 
        }
        
        JButton confirmButton;
        try {
            confirmButton = new JButton(new ImageIcon(ImageIO.read(new File("SymptoMeSignupConfirmButton.png"))));  
            confirmButton.setBounds(660, 595, 126, 39);          
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { try {
                    handleConfirmButtonPressed();
                    } catch (SQLException ex) {
                        Logger.getLogger(RegistrationScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            screenPanel.add(confirmButton);
        } catch (IOException ex) {
            System.out.println("Cannot load confirm button for signup screen");
        }
        
        JButton cancelButton;
        try {
            cancelButton = new JButton(new ImageIcon(ImageIO.read(new File("SymptoMeSignupCancelButton.png"))));  
            cancelButton.setBounds(660, 641, 126, 39);
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { handleBackButtonPressed(); }
            });
            screenPanel.add(cancelButton);
        } catch (IOException ex) {
            System.out.println("Cannot load cancel button for signup screen");
        }
        
        return screenPanel;
    }
    
    private Screen handleBackButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.LOGIN));
    }
    
    private Screen handleConfirmButtonPressed() throws SQLException {
        if (usernameField.getText().equals("") || passwordField.getText().equals("") || zipField.getText().equals("") || dobChooser.getDate() == null) {
            notificationLabel.setText("Please fill out all fields.");
            return this;
        } else if ( !zipField.getText().matches("\\b\\d{5}\\b") ) {
            notificationLabel.setText("Please enter a valid zip code.");
            zipField.setText("");
            return this;
        } else {
            java.sql.Date dob = new java.sql.Date(dobChooser.getDate().getTime());
            if (registerQueryDB.registerUser(usernameField.getText(), passwordField.getText(), zipField.getText(), dob)){
                return (ApplicationWindow.Instance().setScreen(ScreenType.LOGIN));
            }
            else{
                notificationLabel.setText("Username already exists.");
                System.out.println("Username already exists."); 
                return (this);
            }
        }
    }
        
}
