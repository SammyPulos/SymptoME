package symptome;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeScreen implements Screen{
    private JPanel screenPanel;
    
    public HomeScreen() {
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
    /*
        screenPanel = new JPanel();        
        screenPanel.setLayout(new BoxLayout(screenPanel, BoxLayout.Y_AXIS));                        
        
        JButton profileButton = new JButton("Profile");
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleProfileButtonPressed(); }
        });
        JButton surveyButton = new JButton("Survey");
        surveyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleSurveyButtonPressed(); }
        });
        JButton insightsButton = new JButton("Insights");
        insightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleInsightsButtonPressed(); }
        });
        JButton historyButton = new JButton("History");
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleHistoryButtonPressed(); }
        });
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { handleLogoutButtonPressed(); }
        });

        screenPanel.add(titleLabel);
        screenPanel.add(helloLabel);
        screenPanel.add(profileButton);
        screenPanel.add(surveyButton);
        screenPanel.add(insightsButton);
        screenPanel.add(historyButton);
        screenPanel.add(logoutButton);
        */
        
        return screenPanel;
    }
    
    private Screen handleProfileButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.PROFILE));
    }
    private Screen handleSurveyButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.SURVEY));
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
    
}
