package symptome;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

public class SurveyScreen implements Screen {
    private JPanel screenPanel;
    
    public SurveyScreen() {
        screenPanel = setupScreenPanel();
    }
    
    @Override
    public JPanel getPanel() {
        return screenPanel;
    }
    
    private JPanel setupScreenPanel() {
        screenPanel = new JPanel();        
        screenPanel.setLayout(new BoxLayout(screenPanel, BoxLayout.Y_AXIS));                
        
        JLabel titleLabel = new JLabel("Survey:");
        
        // TODO: check if they already had their survey for today
        JLabel feelingLabel = new JLabel("On a scale of 0-10 how good do you feel?");
        JSlider feelingSlider = new JSlider(0,10);
        feelingSlider.setMajorTickSpacing(1);
        feelingSlider.setPaintLabels(true);
        feelingSlider.setPaintTicks(true);
        feelingSlider.setSnapToTicks(true);
        
        JLabel symptomLabel = new JLabel("Please check those you are experiencing:");
        JCheckBox coughBox = new JCheckBox("Cough"); 
        JCheckBox diffBreathingBox = new JCheckBox("Difficulity Breathing"); 
        JCheckBox feaverBox = new JCheckBox("Feaver"); 
        JCheckBox painBox = new JCheckBox("Muscle pain"); 
        JCheckBox soreThroatBox = new JCheckBox("Sore throat"); 
        JCheckBox lossBox = new JCheckBox("Lost taste or smell"); 
        
        JLabel outsideLabel = new JLabel("Did you go outside today where there were other people?");
        JRadioButton outsideRBY = new JRadioButton("Yes");
        JRadioButton outsideRBN = new JRadioButton("No");
        ButtonGroup outsideBG = new ButtonGroup();
        outsideBG.add(outsideRBY);
        outsideBG.add(outsideRBN);
        
        JLabel testedLabel = new JLabel("Were you tested for COVID-19 today?");
        JRadioButton testedRBY = new JRadioButton("Yes");
        JRadioButton testedRBN = new JRadioButton("No");
        ButtonGroup testedBG = new ButtonGroup();
        testedBG.add(testedRBY);
        testedBG.add(testedRBN);
        
        JLabel resultLabel = new JLabel("If you recieved your test results today what were they?");
        JRadioButton resultRBY = new JRadioButton("Positive");
        JRadioButton resultRBN = new JRadioButton("Negative");
        JRadioButton resultRBNA = new JRadioButton("No results");
        ButtonGroup resultBG = new ButtonGroup();
        resultBG.add(resultRBY);
        resultBG.add(resultRBN);
        resultBG.add(resultRBNA);
        
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleSubmitButtonPressed(); }
        });       
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleHomeButtonPressed(); }
        });        
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleLogoutButtonPressed(); }
        });
        
        screenPanel.add(titleLabel);
        screenPanel.add(feelingLabel);
        screenPanel.add(feelingSlider);
        screenPanel.add(symptomLabel);
        screenPanel.add(coughBox);
        screenPanel.add(diffBreathingBox);
        screenPanel.add(feaverBox);
        screenPanel.add(painBox);
        screenPanel.add(soreThroatBox);
        screenPanel.add(lossBox);
        screenPanel.add(outsideLabel);
        screenPanel.add(outsideRBY);
        screenPanel.add(outsideRBN);
        screenPanel.add(testedLabel);
        screenPanel.add(testedRBY);
        screenPanel.add(testedRBN);
        screenPanel.add(resultLabel);
        screenPanel.add(resultRBY);
        screenPanel.add(resultRBN);
        screenPanel.add(resultRBNA);
        screenPanel.add(submitButton);
        screenPanel.add(homeButton);
        screenPanel.add(logoutButton);
        
        return screenPanel;
    }
    
    private Screen handleSubmitButtonPressed() {
        return this;
    }
    
    private Screen handleHomeButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.HOME));
    }
    
    private Screen handleLogoutButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.LOGIN));
    }      
}
