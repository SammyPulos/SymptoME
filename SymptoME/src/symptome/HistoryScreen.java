package symptome;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

public class HistoryScreen extends Screen{
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
    
    public HistoryScreen() throws SQLException {
        historyScreenQueryDB = new HistoryScreenQueryDB();
        screenPanel = setupScreenPanel();
    }
    
    private JPanel setupScreenPanel() {
        screenPanel = new JPanel();        
        screenPanel.setBackground(Color.white);
        screenPanel.setLayout(null); 

        dateChooser = new JDateChooser(new Date());
        dateChooser.setBounds(555, 195, 320, 36);
        dateChooser.getJCalendar().setPreferredSize(new Dimension(320, 240));
        dateChooser.setFont(new Font("SegoeUI", Font.PLAIN, 24));
        dateChooser.getJCalendar().setFont(new Font("SegoeUI", Font.PLAIN, 12));
        dateChooser.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        dateChooser.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("date".equals(evt.getPropertyName())) {
                    updateSurveyFields();
                }
            }
        });
        screenPanel.add(dateChooser);
        
        notificationLabel = new JLabel("");
        notificationLabel.setForeground(Color.red);
        notificationLabel.setFont(new Font("SegoeUI", Font.BOLD, 16));
        notificationLabel.setBounds(555, 235, 340, 39);
        screenPanel.add(notificationLabel);
        
        JLabel feelingLabel = new JLabel("• On a scale of 0-10 how good do you feel?");
        feelingLabel.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        feelingLabel.setBounds(460, 280, 360, 32);
        screenPanel.add(feelingLabel);
        
        feelingSlider = new JSlider(0,10);
        feelingSlider.setBackground(Color.white);
        feelingSlider.setFont(new Font("SegoeUI", Font.PLAIN, 12));
        feelingSlider.setBounds(460, 310, 500, 50);
        feelingSlider.setMajorTickSpacing(1);
        feelingSlider.setPaintLabels(true);
        feelingSlider.setPaintTicks(true);
        feelingSlider.setSnapToTicks(true);
        feelingSlider.setEnabled(false);
        feelingSlider.setValue(0);
        screenPanel.add(feelingSlider);
        
        JLabel symptomLabel = new JLabel("• Please check the symptoms you are experiencing:");
        symptomLabel.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        symptomLabel.setBounds(460, 365, 500, 32);
        screenPanel.add(symptomLabel);
        
        coughBox = new JCheckBox("Cough");
        coughBox.setBackground(Color.white);
        coughBox.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        coughBox.setBounds(480, 395, 200, 20);
        coughBox.setEnabled(false);
        screenPanel.add(coughBox);
        
        diffBreathingBox = new JCheckBox("Difficulty Breathing");
        diffBreathingBox.setBackground(Color.white);
        diffBreathingBox.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        diffBreathingBox.setBounds(680, 395, 200, 20);
        diffBreathingBox.setEnabled(false);
        screenPanel.add(diffBreathingBox);
         
        feverBox = new JCheckBox("Fever"); 
        feverBox.setBackground(Color.white);
        feverBox.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        feverBox.setBounds(480, 415, 200, 20);
        feverBox.setEnabled(false);
        screenPanel.add(feverBox);
        
        painBox = new JCheckBox("Muscle pain"); 
        painBox.setBackground(Color.white);
        painBox.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        painBox.setBounds(680, 415, 200, 20);
        painBox.setEnabled(false);
        screenPanel.add(painBox);
        
        soreThroatBox = new JCheckBox("Sore throat"); 
        soreThroatBox.setBackground(Color.white);
        soreThroatBox.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        soreThroatBox.setBounds(480, 435, 200, 20);
        soreThroatBox.setEnabled(false);
        screenPanel.add(soreThroatBox);
        
        lossBox = new JCheckBox("Loss of taste or smell"); 
        lossBox.setBackground(Color.white);
        lossBox.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        lossBox.setBounds(680, 435, 200, 20);
        lossBox.setEnabled(false);
        screenPanel.add(lossBox);
        
        JLabel outsideLabel = new JLabel("• Did you go outside where there were other people today?");
        outsideLabel.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        outsideLabel.setBounds(460, 470, 500, 32);
        screenPanel.add(outsideLabel);
        
        outsideRBY = new JRadioButton("Yes");
        outsideRBY.setBackground(Color.white);
        outsideRBY.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        outsideRBY.setBounds(480, 500, 200, 20);
        outsideRBY.setEnabled(false);
        screenPanel.add(outsideRBY);
        
        outsideRBN = new JRadioButton("No");
        outsideRBN.setBackground(Color.white);
        outsideRBN.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        outsideRBN.setBounds(680, 500, 200, 20);
        outsideRBN.setEnabled(false);
        screenPanel.add(outsideRBN);
        
        outsideBG = new ButtonGroup();
        outsideBG.add(outsideRBY);
        outsideBG.add(outsideRBN);
        
        JLabel testedLabel = new JLabel("• Were you tested for COVID-19 today?");
        testedLabel.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        testedLabel.setBounds(460, 535, 500, 32);
        screenPanel.add(testedLabel);
        
        testedRBY = new JRadioButton("Yes");
        testedRBY.setBackground(Color.white);
        testedRBY.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        testedRBY.setBounds(480, 565, 200, 20);
        testedRBY.setEnabled(false);
        screenPanel.add(testedRBY);
        
        testedRBN = new JRadioButton("No");
        testedRBN.setBackground(Color.white);
        testedRBN.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        testedRBN.setBounds(680, 565, 200, 20);
        testedRBN.setEnabled(false);
        screenPanel.add(testedRBN);
        
        testedBG = new ButtonGroup();
        testedBG.add(testedRBY);
        testedBG.add(testedRBN);
        
        JLabel resultLabel = new JLabel("• If you recieved your test results today what were they?");
        resultLabel.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        resultLabel.setBounds(460, 600, 500, 32);
        screenPanel.add(resultLabel);
        
        resultRBY = new JRadioButton("Positive");
        resultRBY.setBackground(Color.white);
        resultRBY.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        resultRBY.setBounds(480, 630, 100, 20);
        resultRBY.setEnabled(false);
        screenPanel.add(resultRBY);
        
        resultRBN = new JRadioButton("Negative");
        resultRBN.setBackground(Color.white);
        resultRBN.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        resultRBN.setBounds(680, 630, 100, 20);
        resultRBN.setEnabled(false);
        screenPanel.add(resultRBN);
        
        resultRBNA = new JRadioButton("No results");
        resultRBNA.setBackground(Color.white);
        resultRBNA.setFont(new Font("SegoeUI", Font.PLAIN, 16));
        resultRBNA.setBounds(880, 630, 100, 20);
        resultRBNA.setEnabled(false);
        screenPanel.add(resultRBNA);
        
        resultBG = new ButtonGroup();
        resultBG.add(resultRBY);
        resultBG.add(resultRBN);
        resultBG.add(resultRBNA);
        
        JLabel background;
        try { 
            background = new JLabel(new ImageIcon(ImageIO.read(new File("SymptoMeHistory.png"))));
            background.setBounds(0, 0, 1280, 720);
            screenPanel.add(background); 
        } catch (IOException ex) { 
            System.out.println("Cannot load background for history screen"); 
        }
        
        JButton forwardButton;
        try { 
            forwardButton = new JButton(new ImageIcon(ImageIO.read(new File("SymptoMeHistoryForwardButton.png"))));
            forwardButton.setBounds(929, 186, 43, 43);
            forwardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { handleForwardButtonPressed(); }
            });
            screenPanel.add(forwardButton); 
        } catch (IOException ex) { 
            System.out.println("Cannot load forward button for history screen"); 
        }
        
        JButton backwardButton;
        try { 
            backwardButton = new JButton(new ImageIcon(ImageIO.read(new File("SymptoMeHistoryBackwardButton.png"))));
            backwardButton.setBounds(448, 186, 43, 43);
            backwardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { handleBackwardButtonPressed(); }
            });
            screenPanel.add(backwardButton); 
        } catch (IOException ex) { 
            System.out.println("Cannot load backward button for history screen"); 
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
            System.out.println("Cannot load home button for history screen"); 
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
            System.out.println("Cannot load profile button for history screen"); 
        }

        JButton surveyButton;
        try { 
            surveyButton = new JButton(new ImageIcon(ImageIO.read(new File("SymptoMeSidebarSurveyButton.png"))));
            surveyButton.setBounds(6, 265, 171, 115);
            surveyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { handleSurveyButtonPressed(); }
            });
            screenPanel.add(surveyButton); 
        } catch (IOException ex) { 
            System.out.println("Cannot load survey button for history screen"); 
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
            System.out.println("Cannot load insights button for history screen"); 
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
            System.out.println("Cannot load logout button for history screen"); 
        }

        updateSurveyFields();
        
        return screenPanel;
    }
    
    private Screen handleForwardButtonPressed() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateChooser.getDate());
        cal.add(Calendar.DATE, 1);
        dateChooser.setDate(cal.getTime());
        return this;
    }
    
    private Screen handleBackwardButtonPressed() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateChooser.getDate());
        cal.add(Calendar.DATE, -1);
        dateChooser.setDate(cal.getTime());
        return this;
    }
    
    private void updateSurveyFields() {
        java.sql.Date targetDate = new java.sql.Date(dateChooser.getDate().getTime());
        ArrayList<String[]> results = historyScreenQueryDB.retrieveReport(SessionData.instance().getUsername(), targetDate);
        if ((results == null) || (results.isEmpty())){  // if no survey taken on that day
            populateEmptySurvey();
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
    
    private void handleHomeButtonPressed() {
        this.toHomeScreen();
    }
    private void handleProfileButtonPressed() {
        this.toProfileScreen();
    }
    private void handleSurveyButtonPressed() {
        this.toSurveyScreen();
    }
    private void handleInsightsButtonPressed() {
        this.toInsightsScreen();
    }
    private void handleLogoutButtonPressed() {
        this.toLoginScreen();
    }    
    
}
