package symptome;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProfileScreen implements Screen {
    private JPanel screenPanel;
    private ProfileQueryDB profileQueryDB;
    
    public ProfileScreen() throws SQLException {
        profileQueryDB = new ProfileQueryDB();
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
                        
        JLabel usernameLabel = new JLabel(SessionData.instance().getUsername());
        usernameLabel.setFont(new Font("SegoeUI", Font.PLAIN, 24));
        usernameLabel.setBounds(554, 274, 320, 39);
        screenPanel.add(usernameLabel);
        
        JLabel zipCodeLabel = new JLabel(profileQueryDB.retrieveZipCode(SessionData.instance().getUsername()));
        zipCodeLabel.setFont(new Font("SegoeUI", Font.PLAIN, 24));
        zipCodeLabel.setBounds(554, 371, 320, 39);
        screenPanel.add(zipCodeLabel);
        
        JLabel dobLabel = new JLabel(profileQueryDB.retrieveDOB(SessionData.instance().getUsername()));
        dobLabel.setFont(new Font("SegoeUI", Font.PLAIN, 24));
        dobLabel.setBounds(554, 467, 320, 39);
        screenPanel.add(dobLabel);
        
        JLabel background;
        try { 
            background = new JLabel(new ImageIcon(ImageIO.read(new File("SymptoMeProfile.png"))));
            background.setBounds(0, 0, 1280, 720);
            screenPanel.add(background); 
        } catch (IOException ex) { 
            System.out.println("Cannot load background for profile screen"); 
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
            System.out.println("Cannot load home button for profile screen"); 
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
            System.out.println("Cannot load survey button for profile screen"); 
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
            System.out.println("Cannot load insights button for profile screen"); 
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
            System.out.println("Cannot load history button for profile screen"); 
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
            System.out.println("Cannot load logout button for profile screen"); 
        }
        
        return screenPanel;
    }
    
    private Screen handleHomeButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.HOME));
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
