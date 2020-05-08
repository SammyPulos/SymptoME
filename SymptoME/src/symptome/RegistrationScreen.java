package symptome;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegistrationScreen implements Screen {
    private JPanel registrationPanel;
    
    public RegistrationScreen() {
        registrationPanel = setupRegistrationPanel();
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
        
        registrationPanel.add(titleLabel);
        registrationPanel.add(usernameLabel);
        registrationPanel.add(usernameField);
        registrationPanel.add(passwordLabel);
        registrationPanel.add(passwordField);
        registrationPanel.add(backButton);
        registrationPanel.add(confirmButton);
        
        return registrationPanel;
    }
    
    private Screen handleBackButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.LOGIN));
    }
    
    private Screen handleConfirmButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.LOGIN));
    }
        
}
