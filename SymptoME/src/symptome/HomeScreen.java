package symptome;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeScreen extends Screen{
    
    public HomeScreen() {
        screenPanel = setupScreenPanel();
    }
    
    private JPanel setupScreenPanel() {
        screenPanel = new JPanel();        
        screenPanel.setBackground(Color.white);
        screenPanel.setLayout(null);         
        
        JLabel background;
        try { 
            background = new JLabel(new ImageIcon(ImageIO.read(new File("SymptoMeHome.png"))));
            background.setBounds(0, 0, 1280, 720);
            screenPanel.add(background); 
        } catch (IOException ex) { 
            System.out.println("Cannot load background for home screen"); 
        }
        
        JButton profileButton;
        try {
            profileButton = new JButton(new ImageIcon(ImageIO.read(new File("SymptoMeHomeProfileButton.png"))));
            profileButton.setBounds(95, 273, 246, 246);
            profileButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { handleProfileButtonPressed(); }
            });
            screenPanel.add(profileButton);
        } catch (IOException ex) {
            System.out.println("Cannot load profile button for home screen"); 
        }
        
        JButton surveyButton;
        try {
            surveyButton = new JButton(new ImageIcon(ImageIO.read(new File("SymptoMeHomeSurveyButton.png"))));
            surveyButton.setBounds(379, 273, 246, 246);
            surveyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { handleSurveyButtonPressed(); }
            });
            screenPanel.add(surveyButton);
        } catch (IOException ex) {
            System.out.println("Cannot load profile button for home screen"); 
        }
        
        JButton insightsButton;
        try {
            insightsButton = new JButton(new ImageIcon(ImageIO.read(new File("SymptoMeHomeInsightsButton.png"))));
            insightsButton.setBounds(658, 273, 246, 246);
            insightsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { handleInsightsButtonPressed(); }
            });
            screenPanel.add(insightsButton);
        } catch (IOException ex) {
            System.out.println("Cannot load insights button for home screen"); 
        }  
        
        JButton historyButton;
        try {
            historyButton = new JButton(new ImageIcon(ImageIO.read(new File("SymptoMeHomeHistoryButton.png"))));
            historyButton.setBounds(942, 273, 246, 246);
            historyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { handleHistoryButtonPressed(); }
            });
            screenPanel.add(historyButton);
        } catch (IOException ex) {
            System.out.println("Cannot load history button for home screen"); 
        }
        
        JButton logoutButton;
        try {
            logoutButton = new JButton(new ImageIcon(ImageIO.read(new File("SymptoMeHomeLogoutButton.png"))));
            logoutButton.setBounds(555, 577, 184, 58);
            logoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) { handleLogoutButtonPressed(); }
            });
            screenPanel.add(logoutButton);
        } catch (IOException ex) {
            System.out.println("Cannot load logout button for home screen"); 
        }
        
        return screenPanel;
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
    private void handleHistoryButtonPressed() {
        this.toHistoryScreen();
    }
    private void handleLogoutButtonPressed() {
        this.toLoginScreen();
    }
}
