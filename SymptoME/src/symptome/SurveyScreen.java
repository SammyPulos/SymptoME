package symptome;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
    private final SurveyScreenQueryDB surveyScreenQueryDB;
    
    private java.sql.Date todaysDate;
    private JSlider feelingSlider;
    
    private JCheckBox coughBox;
    private JCheckBox diffBreathingBox;
    private JCheckBox feverBox;
    private JCheckBox painBox;
    private JCheckBox soreThroatBox;
    private JCheckBox lossBox;
    
    private JRadioButton outsideRBY;
    private JRadioButton outsideRBN;
    private JRadioButton testedRBY;
    private JRadioButton testedRBN;
    private JRadioButton resultRBY;
    private JRadioButton resultRBN;
    private JRadioButton resultRBNA;
    
    private JLabel notificationLabel;
    
    // Builder Screen: Director
    public SurveyScreen() throws SQLException {
        surveyScreenQueryDB = new SurveyScreenQueryDB();
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
        todaysDate = new java.sql.Date((new Date()).getTime());
        JLabel feelingLabel = new JLabel("On a scale of 0-10 how good do you feel?");
        feelingSlider = new JSlider(0,10);
        feelingSlider.setMajorTickSpacing(1);
        feelingSlider.setPaintLabels(true);
        feelingSlider.setPaintTicks(true);
        feelingSlider.setSnapToTicks(true);
        feelingSlider.setValue(0);
        
        JLabel symptomLabel = new JLabel("Please check the symptoms you are experiencing:");
        coughBox = new JCheckBox("Cough"); 
        diffBreathingBox = new JCheckBox("Difficulty Breathing"); 
        feverBox = new JCheckBox("Fever"); 
        painBox = new JCheckBox("Muscle pain"); 
        soreThroatBox = new JCheckBox("Sore throat"); 
        lossBox = new JCheckBox("Loss of taste or smell"); 
        
        JLabel outsideLabel = new JLabel("Did you go outside where there were other people today?");
        outsideRBY = new JRadioButton("Yes");
        outsideRBN = new JRadioButton("No");
        ButtonGroup outsideBG = new ButtonGroup();
        outsideBG.add(outsideRBY);
        outsideBG.add(outsideRBN);
        
        JLabel testedLabel = new JLabel("Were you tested for COVID-19 today?");
        testedRBY = new JRadioButton("Yes");
        testedRBN = new JRadioButton("No");
        ButtonGroup testedBG = new ButtonGroup();
        testedBG.add(testedRBY);
        testedBG.add(testedRBN);
        
        JLabel resultLabel = new JLabel("If you recieved your test results today what were they?");
        resultRBY = new JRadioButton("Positive");
        resultRBN = new JRadioButton("Negative");
        resultRBNA = new JRadioButton("No results");
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
        
        notificationLabel = new JLabel("");
        notificationLabel.setForeground(Color.red);
        
        screenPanel.add(titleLabel);
        screenPanel.add(feelingLabel);
        screenPanel.add(feelingSlider);
        screenPanel.add(symptomLabel);
        screenPanel.add(coughBox);
        screenPanel.add(diffBreathingBox);
        screenPanel.add(feverBox);
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
        screenPanel.add(notificationLabel);
        
        updateSurveyFields();
        
        return screenPanel;
    }
    
     public boolean checkCompletedSurvey(){
        // checking "Did you go outside today where there were other people?" Y/N
        if (!outsideRBY.isSelected() && !outsideRBN.isSelected()){
            return false;
        }
        // checking "Were you tested for COVID-19 today?" Y/N
        else if (!testedRBY.isSelected() && !testedRBN.isSelected()){
            return false;
        }
        // checking "If you received your test results today, what were they?" P/N/NA
        else if (!resultRBY.isSelected() && !resultRBN.isSelected() && !resultRBNA.isSelected()){
            return false;
        }
        return true;
    }
    
    private Screen handleSubmitButtonPressed() {     
        
        // make sure fields are actually filled
        if (!checkCompletedSurvey()){
            notificationLabel.setText("Please complete all fields before submitting.");
            return this;
        }
        // push results to database
        else{
            Report report = new Report.ReportBuilder().withUsername(SessionData.instance().getUsername()).withDate(todaysDate).withFeeling(feelingSlider).withCough(coughBox).withDiffBreathing(diffBreathingBox).withFever(feverBox).withPain(painBox).withSoreThroat(soreThroatBox).withLoss(lossBox).withOutsideRBY(outsideRBY).withOutsideRBN(outsideRBN).withTestedRBY(testedRBY).withTestedRBN(testedRBN).withResultRBY(resultRBY).withResultRBN(resultRBN).withResultRBNA(resultRBNA).build();
            surveyScreenQueryDB.addReport(report);
            return (ApplicationWindow.Instance().setScreen(ScreenType.HOME));
        }
    }
    
    private Screen handleHomeButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.HOME));
    }
    
    private Screen handleLogoutButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.LOGIN));
    } 
    
    private void updateSurveyFields() {
        JDateChooser dateChooser = new JDateChooser(new Date());
        java.sql.Date targetDate = new java.sql.Date(dateChooser.getDate().getTime());
        ArrayList<String> results = surveyScreenQueryDB.retrieveReport(SessionData.instance().getUsername(), targetDate);
        if ((results == null) || (results.isEmpty())){  // if no survey taken on that day
        }
        else{
            notificationLabel.setText(""); //should be empty string to show nothing
            feelingSlider.setValue(Integer.parseInt(results.get(2)));
            coughBox.setSelected(Integer.parseInt(results.get(3)) == 1);
            diffBreathingBox.setSelected(Integer.parseInt(results.get(4)) == 1);
            feverBox.setSelected(Integer.parseInt(results.get(5)) == 1); 
            painBox.setSelected(Integer.parseInt(results.get(6)) == 1); 
            soreThroatBox.setSelected(Integer.parseInt(results.get(7)) == 1); 
            lossBox.setSelected(Integer.parseInt(results.get(8)) == 1);
            if (Integer.parseInt(results.get(9)) == 1)
                outsideRBY.setSelected(true);
            else
                outsideRBN.setSelected(true);
            if (Integer.parseInt(results.get(10)) == 1)
                testedRBY.setSelected(true);
            else
                testedRBN.setSelected(true);
            if (Integer.parseInt(results.get(11)) == 1)
                resultRBY.setSelected(true);
            else if (Integer.parseInt(results.get(11)) == 2)
                resultRBNA.setSelected(true);
            else
                resultRBN.setSelected(true);
        }
    }
}
