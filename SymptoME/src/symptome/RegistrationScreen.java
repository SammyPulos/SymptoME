package symptome;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegistrationScreen implements Screen {
    private JPanel screenPanel;
    
    JTextField usernameField;
    JPasswordField passwordField;
    JTextField zipField;
    JDateChooser dobChooser;
    JLabel notificationLabel;
    
    public RegistrationScreen() {
        screenPanel = setupScreenPanel();
    }
    
    @Override
    public JPanel getPanel() {
        return screenPanel;
    }
    
    private JPanel setupScreenPanel() {
        screenPanel = new JPanel();        
        screenPanel.setLayout(new FlowLayout());                
        
        JLabel titleLabel = new JLabel("Register for SymptoME");
        
        JLabel usernameLabel = new JLabel("Username");
        usernameField = new JTextField(15);
        JLabel passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField(15);
        JLabel zipLabel = new JLabel("Zip code");
        zipField = new JTextField(5);
        JLabel dobLabel = new JLabel("Date of birth");
        dobChooser = new JDateChooser();
                
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleBackButtonPressed(); }
        });
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleConfirmButtonPressed(); }
        });
        
        notificationLabel = new JLabel("");
        notificationLabel.setForeground(Color.red);
        
        screenPanel.add(titleLabel);
        screenPanel.add(usernameLabel);
        screenPanel.add(usernameField);
        screenPanel.add(passwordLabel);
        screenPanel.add(passwordField);
        screenPanel.add(zipLabel);
        screenPanel.add(zipField);
        screenPanel.add(dobLabel);
        screenPanel.add(dobChooser);
        screenPanel.add(backButton);
        screenPanel.add(confirmButton);
        screenPanel.add(notificationLabel);
        
        return screenPanel;
    }
    
    private Screen handleBackButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.LOGIN));
    }
    
    private Screen handleConfirmButtonPressed() {
        if (usernameField.getText().equals("") || passwordField.getText().equals("") || zipField.getText().equals("") || dobChooser.getDate() == null) {
            notificationLabel.setText("Please fill out all fields.");
            return this;
        } else if ( !zipField.getText().matches("\\b\\d{5}\\b") ) {
            notificationLabel.setText("Please enter a valid zip code.");
            zipField.setText("");
            return this;
        } else {
            // TODO: register account to db after checking if it exists
            java.sql.Date dob = new java.sql.Date(dobChooser.getDate().getTime());
            System.out.println(dob); // this dob should work with the db (iirc)
            return (ApplicationWindow.Instance().setScreen(ScreenType.LOGIN));
        }
    }
        
}
