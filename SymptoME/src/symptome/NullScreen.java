package symptome;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NullScreen extends Screen {
    
    public NullScreen() {
        screenPanel = setupScreenPanel();
    }
    
    protected JPanel setupScreenPanel() {
        screenPanel = new JPanel();        
        screenPanel.setBackground(Color.white);
        screenPanel.setLayout(null); 
        
        JLabel warningLabel = new JLabel("<html>Something went wrong<br>This is the null screen<br>Please return to login</html>");
        warningLabel.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        warningLabel.setForeground(Color.red);
        warningLabel.setBounds(580, 100, 360, 100);
        screenPanel.add(warningLabel);
            
        JButton loginButton = new JButton("Back to login");
        loginButton.setBounds(595, 200, 120, 32);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleLoginButtonPressed(); }
        });
        screenPanel.add(loginButton); 
        
        return screenPanel;
    }
    
    private void handleLoginButtonPressed() {
        this.toLoginScreen();
    }
}
