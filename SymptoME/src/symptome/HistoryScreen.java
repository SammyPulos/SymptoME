package symptome;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

public class HistoryScreen implements Screen{
    private JPanel screenPanel;
    private HistoryScreenQueryDB historyScreenQueryDB;
    
    private JDateChooser dateChooser;
    private JLabel notificationLabel;
    private JSlider feelingSlider;
    private JCheckBox coughBox;
    private JCheckBox diffBreathingBox;
    private JCheckBox feverBox; 
    private JCheckBox painBox; 
    private JCheckBox soreThroatBox; 
    private JCheckBox lossBox; 
    private JRadioButton outsideRBY;
    private JRadioButton outsideRBN;
    private ButtonGroup outsideBG;
    private JRadioButton testedRBY;
    private JRadioButton testedRBN;
    private ButtonGroup testedBG;
    private JRadioButton resultRBY;
    private JRadioButton resultRBN;
    private JRadioButton resultRBNA;
    private ButtonGroup resultBG;
    private Object Interger;
    
    public HistoryScreen() throws SQLException {
        historyScreenQueryDB = new HistoryScreenQueryDB();
        screenPanel = setupScreenPanel();
    }
    
    @Override
    public JPanel getPanel() {
        return screenPanel;
    }
    
    private JPanel setupScreenPanel() {
        screenPanel = new JPanel();        
        screenPanel.setLayout(new BoxLayout(screenPanel, BoxLayout.Y_AXIS));                
        
        JLabel titleLabel = new JLabel("History:");
        
        dateChooser = new JDateChooser(new Date());
        
        JButton forwardButton = new JButton("Next day");
        forwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleForwardButtonPressed(); }
        });
        JButton backwardButton = new JButton("Previous day");
        backwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleBackwardButtonPressed(); }
        });
        
        (notificationLabel = new JLabel("")).setForeground(Color.red);
        
        JLabel feelingLabel = new JLabel("On a scale of 0-10 how good did you feel?");
        feelingSlider = new JSlider(0,10);
        feelingSlider.setEnabled(false);
        feelingSlider.setMajorTickSpacing(1);
        feelingSlider.setPaintLabels(true);
        feelingSlider.setPaintTicks(true);
        feelingSlider.setSnapToTicks(true);
        
        JLabel symptomLabel = new JLabel("Experienced Symptoms:");
        (coughBox = new JCheckBox("Cough")).setEnabled(false);
        (diffBreathingBox = new JCheckBox("Difficulity Breathing")).setEnabled(false); 
        (feverBox = new JCheckBox("Fever")).setEnabled(false); 
        (painBox = new JCheckBox("Muscle pain")).setEnabled(false); 
        (soreThroatBox = new JCheckBox("Sore throat")).setEnabled(false); 
        (lossBox = new JCheckBox("Loss of taste or smell")).setEnabled(false); 
        
        JLabel outsideLabel = new JLabel("Did you go outside where there were other people today?");
        (outsideRBY = new JRadioButton("Yes")).setEnabled(false);
        (outsideRBN = new JRadioButton("No")).setEnabled(false);
        outsideBG = new ButtonGroup();
        outsideBG.add(outsideRBY);
        outsideBG.add(outsideRBN);
        
        JLabel testedLabel = new JLabel("Were you tested for COVID-19?");
        (testedRBY = new JRadioButton("Yes")).setEnabled(false);
        (testedRBN = new JRadioButton("No")).setEnabled(false);
        testedBG = new ButtonGroup();
        testedBG.add(testedRBY);
        testedBG.add(testedRBN);
        
        JLabel resultLabel = new JLabel("If you recieved your test results what were they?");
        (resultRBY = new JRadioButton("Positive")).setEnabled(false);
        (resultRBN = new JRadioButton("Negative")).setEnabled(false);
        (resultRBNA = new JRadioButton("No results")).setEnabled(false);
        resultBG = new ButtonGroup();
        resultBG.add(resultRBY);
        resultBG.add(resultRBN);
        resultBG.add(resultRBNA);

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
        screenPanel.add(dateChooser);
        screenPanel.add(forwardButton);
        screenPanel.add(backwardButton);
        screenPanel.add(notificationLabel);
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
        screenPanel.add(homeButton);
        screenPanel.add(logoutButton);
        
        updateSurveyFields();
        
        return screenPanel;
    }
    
    private Screen handleForwardButtonPressed() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateChooser.getDate());
        cal.add(Calendar.DATE, 1);
        dateChooser.setDate(cal.getTime());
        updateSurveyFields();
        return this;
    }
    
    private Screen handleBackwardButtonPressed() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateChooser.getDate());
        cal.add(Calendar.DATE, -1);
        dateChooser.setDate(cal.getTime());
        updateSurveyFields();
        return this;
    }
    
    private void updateSurveyFields() {
        java.sql.Date targetDate = new java.sql.Date(dateChooser.getDate().getTime());
        ArrayList<String> results = historyScreenQueryDB.retrieveReport(SessionData.instance().getUsername(), targetDate);
        if ((results == null) || (results.isEmpty())){  // if no survey taken on that day
            populateEmptySurvey();
        }
        else{
            notificationLabel.setText(""); //should be empty string to show nothing
            feelingSlider.setValue(Integer.parseInt(results.get(2)));
            if (Integer.parseInt(results.get(3)) == 1)
                coughBox.setSelected(true);
            if (Integer.parseInt(results.get(4)) == 1)
                diffBreathingBox.setSelected(true);
            if (Integer.parseInt(results.get(5)) == 1)
                feverBox.setSelected(true); 
            if (Integer.parseInt(results.get(6)) == 1)
                painBox.setSelected(true); 
            if (Integer.parseInt(results.get(7)) == 1)
                soreThroatBox.setSelected(true); 
            if (Integer.parseInt(results.get(8)) == 1)
                lossBox.setSelected(true);
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
    
    private void populateEmptySurvey() {    
        notificationLabel.setText("No survey recorded for selected date");
        feelingSlider.setValue(0);
        coughBox.setSelected(false);
        diffBreathingBox.setSelected(false);
        feverBox.setSelected(false); 
        painBox.setSelected(false); 
        soreThroatBox.setSelected(false); 
        lossBox.setSelected(false); 
        outsideBG.clearSelection();
        testedBG.clearSelection();
        resultBG.clearSelection();
    }
    
    private Screen handleHomeButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.HOME));
    }
    
    private Screen handleLogoutButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.LOGIN));
    }    
    
}
