package symptome;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class HomeScreen implements Screen{
    private JPanel homePanel;
    
    public HomeScreen() {
        homePanel = setupRegistrationPanel();
        setupLogic();
    }
    
    @Override
    public JPanel getPanel() {
        return homePanel;
    }
    
    private JPanel setupRegistrationPanel() {
        homePanel = new JPanel();        
        homePanel.setLayout(new FlowLayout());                
        
        JLabel titleLabel = new JLabel("Home");
        
        homePanel.add(titleLabel);
        
        return homePanel;
    }
    
    private HomeScreen setupLogic() {
        return this;
    }
        
    
}
