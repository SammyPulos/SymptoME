package symptome;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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
        todaysDate = new java.sql.Date((new Date()).getTime());
        surveyScreenQueryDB = new SurveyScreenQueryDB();
        screenPanel = setupScreenPanel();
    }
    
    @Override
    public JPanel getPanel() {
        return screenPanel;
    }
    
    private JPanel setupScreenPanel() {
        screenPanel = new JPanel();        
        screenPanel.setBackground(Color.white);
        screenPanel.setLayout(null); 
        
        JLabel feelingLabel = new JLabel("• On a scale of 0-10 how good do you feel?");
        feelingLabel.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        feelingLabel.setBounds(460, 160, 360, 32);
        screenPanel.add(feelingLabel);
        
        feelingSlider = new JSlider(0,10);
        feelingSlider.setBackground(Color.white);
        feelingSlider.setFont(new Font("SegoeUI", Font.PLAIN, 12));
        feelingSlider.setBounds(460, 190, 500, 50);
        feelingSlider.setMajorTickSpacing(1);
        feelingSlider.setPaintLabels(true);
        feelingSlider.setPaintTicks(true);
        feelingSlider.setSnapToTicks(true);
        feelingSlider.setValue(0);
        screenPanel.add(feelingSlider);
        
        JLabel symptomLabel = new JLabel("• Please check the symptoms you are experiencing:");
        symptomLabel.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        symptomLabel.setBounds(460, 245, 500, 32);
        screenPanel.add(symptomLabel);
        
        coughBox = new JCheckBox("Cough");
        coughBox.setBackground(Color.white);
        coughBox.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        coughBox.setBounds(480, 275, 200, 20);
        screenPanel.add(coughBox);
        
        diffBreathingBox = new JCheckBox("Difficulty Breathing");
        diffBreathingBox.setBackground(Color.white);
        diffBreathingBox.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        diffBreathingBox.setBounds(680, 275, 200, 20);
        screenPanel.add(diffBreathingBox);
         
        feverBox = new JCheckBox("Fever"); 
        feverBox.setBackground(Color.white);
        feverBox.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        feverBox.setBounds(480, 295, 200, 20);
        screenPanel.add(feverBox);
        
        painBox = new JCheckBox("Muscle pain"); 
        painBox.setBackground(Color.white);
        painBox.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        painBox.setBounds(680, 295, 200, 20);
        screenPanel.add(painBox);
        
        soreThroatBox = new JCheckBox("Sore throat"); 
        soreThroatBox.setBackground(Color.white);
        soreThroatBox.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        soreThroatBox.setBounds(480, 315, 200, 20);
        screenPanel.add(soreThroatBox);
        
        lossBox = new JCheckBox("Loss of taste or smell"); 
        lossBox.setBackground(Color.white);
        lossBox.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        lossBox.setBounds(680, 315, 200, 20);
        screenPanel.add(lossBox);
        
        JLabel outsideLabel = new JLabel("• Did you go outside where there were other people today?");
        outsideLabel.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        outsideLabel.setBounds(460, 355, 500, 32);
        screenPanel.add(outsideLabel);
        
        outsideRBY = new JRadioButton("Yes");
        outsideRBY.setBackground(Color.white);
        outsideRBY.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        outsideRBY.setBounds(480, 385, 200, 20);
        screenPanel.add(outsideRBY);
        
        outsideRBN = new JRadioButton("No");
        outsideRBN.setBackground(Color.white);
        outsideRBN.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        outsideRBN.setBounds(680, 385, 200, 20);
        screenPanel.add(outsideRBN);
        
        ButtonGroup outsideBG = new ButtonGroup();
        outsideBG.add(outsideRBY);
        outsideBG.add(outsideRBN);
        
        JLabel testedLabel = new JLabel("• Were you tested for COVID-19 today?");
        testedLabel.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        testedLabel.setBounds(460, 425, 500, 32);
        screenPanel.add(testedLabel);
        
        testedRBY = new JRadioButton("Yes");
        testedRBY.setBackground(Color.white);
        testedRBY.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        testedRBY.setBounds(480, 455, 200, 20);
        screenPanel.add(testedRBY);
        
        testedRBN = new JRadioButton("No");
        testedRBN.setBackground(Color.white);
        testedRBN.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        testedRBN.setBounds(680, 455, 200, 20);
        screenPanel.add(testedRBN);
        
        ButtonGroup testedBG = new ButtonGroup();
        testedBG.add(testedRBY);
        testedBG.add(testedRBN);
        
        JLabel resultLabel = new JLabel("• If you recieved your test results today what were they?");
        resultLabel.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        resultLabel.setBounds(460, 495, 500, 32);
        screenPanel.add(resultLabel);
        
        resultRBY = new JRadioButton("Positive");
        resultRBY.setBackground(Color.white);
        resultRBY.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        resultRBY.setBounds(480, 525, 100, 20);
        screenPanel.add(resultRBY);
        
        resultRBN = new JRadioButton("Negative");
        resultRBN.setBackground(Color.white);
        resultRBN.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        resultRBN.setBounds(680, 525, 100, 20);
        screenPanel.add(resultRBN);
        
        resultRBNA = new JRadioButton("No results");
        resultRBNA.setBackground(Color.white);
        resultRBNA.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        resultRBNA.setBounds(880, 525, 100, 20);
        screenPanel.add(resultRBNA);
        
        ButtonGroup resultBG = new ButtonGroup();
        resultBG.add(resultRBY);
        resultBG.add(resultRBN);
        resultBG.add(resultRBNA);
        
        notificationLabel = new JLabel("");
        notificationLabel.setForeground(Color.red);
        notificationLabel.setFont(new Font("SegoeUI", Font.BOLD, 16));
        notificationLabel.setBounds(546, 580, 340, 39);
        screenPanel.add(notificationLabel);
        
        JLabel background;
        try { 
            background = new JLabel(new ImageIcon(ImageIO.read(new File("SymptoMeSurvey.png"))));
            background.setBounds(0, 0, 1280, 720);
            screenPanel.add(background); 
        } catch (IOException ex) { 
            System.out.println("Cannot load background for survey screen"); 
        }
            
        JButton submitButton;
        try { 
            submitButton = new JButton(new ImageIcon(ImageIO.read(new File("SymptoMeSurveySubmitButton.png"))));
            submitButton.setBounds(610, 624, 197, 47);
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { handleSubmitButtonPressed(); }
            });
            screenPanel.add(submitButton); 
        } catch (IOException ex) { 
            System.out.println("Cannot load submit button for survey screen"); 
        }
        
        JButton homeButton;
        try { 
            homeButton = new JButton(new ImageIcon(ImageIO.read(new File("SymptoMeSidebarHomeButton.png"))));
            homeButton.setBounds(6, 6, 171, 124);
            homeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { handleHomeButtonPressed(); }
            });
            screenPanel.add(homeButton); 
        } catch (IOException ex) { 
            System.out.println("Cannot load home button for survey screen"); 
        }

        JButton profileButton;
        try { 
            profileButton = new JButton(new ImageIcon(ImageIO.read(new File("SymptoMeSidebarProfileButton.png"))));
            profileButton.setBounds(6, 138, 171, 119);
            profileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { handleProfileButtonPressed(); }
            });
            screenPanel.add(profileButton); 
        } catch (IOException ex) { 
            System.out.println("Cannot load profile button for survey screen"); 
        }
        
        JButton insightsButton;
        try { 
            insightsButton = new JButton(new ImageIcon(ImageIO.read(new File("SymptoMeSidebarInsightsButton.png"))));
            insightsButton.setBounds(6, 388, 171, 109);
            insightsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { handleInsightsButtonPressed(); }
            });
            screenPanel.add(insightsButton); 
        } catch (IOException ex) { 
            System.out.println("Cannot load insights button for survey screen"); 
        }
        
        JButton historyButton;
        try { 
            historyButton = new JButton(new ImageIcon(ImageIO.read(new File("SymptoMeSidebarHistoryButton.png"))));
            historyButton.setBounds(6, 505, 171, 107);
            historyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { handleHistoryButtonPressed(); }
            });
            screenPanel.add(historyButton); 
        } catch (IOException ex) { 
            System.out.println("Cannot load history button for survey screen"); 
        }
        
        JButton logoutButton;
        try { 
            logoutButton = new JButton(new ImageIcon(ImageIO.read(new File("SymptoMeSidebarLogoutButton.png"))));
            logoutButton.setBounds(6, 620, 171, 59);
            logoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { handleLogoutButtonPressed(); }
            });
            screenPanel.add(logoutButton); 
        } catch (IOException ex) { 
            System.out.println("Cannot load logout button for survey screen"); 
        }
        
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
    
    private Screen handleProfileButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.PROFILE));
    }
    
    private Screen handleInsightsButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.INSIGHTS));
    }   
    
    private Screen handleHistoryButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.HISTORY));
    }   
    
    private Screen handleLogoutButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.LOGIN));
    }    
    
    private void updateSurveyFields() {
        JDateChooser dateChooser = new JDateChooser(new Date());
        java.sql.Date targetDate = new java.sql.Date(dateChooser.getDate().getTime());
        ArrayList<String[]> results = surveyScreenQueryDB.retrieveReport(SessionData.instance().getUsername(), targetDate);
        if ((results == null) || (results.isEmpty())){  // if no survey taken on that day
        }
        else{
            notificationLabel.setText(""); //should be empty string to show nothing
            feelingSlider.setValue(Integer.parseInt(results.get(0)[2]));
            coughBox.setSelected(Integer.parseInt(results.get(0)[3]) == 1);
            diffBreathingBox.setSelected(Integer.parseInt(results.get(0)[4]) == 1);
            feverBox.setSelected(Integer.parseInt(results.get(0)[5]) == 1); 
            painBox.setSelected(Integer.parseInt(results.get(0)[6]) == 1); 
            soreThroatBox.setSelected(Integer.parseInt(results.get(0)[7]) == 1); 
            lossBox.setSelected(Integer.parseInt(results.get(0)[8]) == 1);
            if (Integer.parseInt(results.get(0)[9]) == 1)
                outsideRBY.setSelected(true);
            else
                outsideRBN.setSelected(true);
            if (Integer.parseInt(results.get(0)[10]) == 1)
                testedRBY.setSelected(true);
            else
                testedRBN.setSelected(true);
            if (Integer.parseInt(results.get(0)[11]) == 1)
                resultRBY.setSelected(true);
            else if (Integer.parseInt(results.get(0)[11]) == 2)
                resultRBNA.setSelected(true);
            else
                resultRBN.setSelected(true);
        }
    }
}
