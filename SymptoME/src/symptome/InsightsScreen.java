package symptome;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class InsightsScreen extends Screen{
    
    private JLabel notificationLabel;
    private JLabel percentSame;
    private JLabel percentSameSymptomsTested;
    private JLabel percentSameSymptomsResults;
    private JLabel percentSameLocationTested;
    private JLabel percentSameLocationResults;
    private ChartPanel feelingChartPanel;
    InsightsFacade insightsFacade = new InsightsFacade();
                
    public InsightsScreen() throws SQLException {
        this.insightsFacade = new InsightsFacade();
        screenPanel = setupScreenPanel();
    }
    
    private JPanel setupScreenPanel() throws SQLException {
        screenPanel = new JPanel();        
        screenPanel.setBackground(Color.white);
        screenPanel.setLayout(null);                         
        
        notificationLabel = new JLabel("Please submit your daily survey in order to receive the latest insights.");
        notificationLabel.setForeground(Color.red);
        notificationLabel.setFont(new Font("SegoeUI", Font.BOLD, 16));
        notificationLabel.setBounds(460, 145, 600, 20);
        screenPanel.add(notificationLabel);
        
        percentSame = new JLabel("");
        percentSame.setFont(new Font("SegoeUI", Font.PLAIN, 20));
        percentSame.setBounds(250, 185, 1000, 25);
        screenPanel.add(percentSame);
        
        percentSameSymptomsTested = new JLabel("");
        percentSameSymptomsTested.setFont(new Font("SegoeUI", Font.PLAIN, 20));
        percentSameSymptomsTested.setBounds(250, 225, 1000, 25);
        screenPanel.add(percentSameSymptomsTested);
        
        percentSameSymptomsResults = new JLabel("");
        percentSameSymptomsResults.setFont(new Font("SegoeUI", Font.PLAIN, 20));
        percentSameSymptomsResults.setBounds(250, 255, 1000, 25);        
        screenPanel.add(percentSameSymptomsResults);
        
        percentSameLocationTested = new JLabel("");
        percentSameLocationTested.setFont(new Font("SegoeUI", Font.PLAIN, 20));
        percentSameLocationTested.setBounds(250, 295, 1000, 25);     
        screenPanel.add(percentSameLocationTested);
        
        percentSameLocationResults = new JLabel("");
        percentSameLocationResults.setFont(new Font("SegoeUI", Font.PLAIN, 20));
        percentSameLocationResults.setBounds(250, 325, 1000, 25);  
        screenPanel.add(percentSameLocationResults);
        
        ChartPanel feelingChartPanel = setupChartPanel();
        feelingChartPanel.setBounds(250, 375, 950, 300);
        screenPanel.add(feelingChartPanel);
        
        JLabel background;
        try { 
            background = new JLabel(new ImageIcon(ImageIO.read(new File("SymptoMeInsights.png"))));
            background.setBounds(0, 0, 1280, 720);
            screenPanel.add(background); 
        } catch (IOException ex) { 
            System.out.println("Cannot load background for insights screen"); 
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
            System.out.println("Cannot load home button for insights screen"); 
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
            System.out.println("Cannot load profile button for insights screen"); 
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
            System.out.println("Cannot load survey button for insights screen"); 
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
            System.out.println("Cannot load history button for insights screen"); 
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
            System.out.println("Cannot load logout button for insights screen"); 
        }
        
        SetupInsights();
        
        return screenPanel;
    }
    
    private Screen SetupInsights() throws SQLException {
        if (screenPanel == null) { return this; }
        
        if (insightsFacade.getPercentSimilarSymptoms() < 0) // check that user has taken daily survey
            notificationLabel.setText("Please submit your daily survey in order to receive the latest insights.");
        else {
            notificationLabel.setText("");
            percentSame.setText("• Overall " + insightsFacade.getPercentSimilarSymptoms() + "% users have recorded the same symptoms as yours at some point");
            percentSameSymptomsTested.setText("• Out of people with symptoms matching your most recent report " + insightsFacade.getPercentSimilarSymptomsTested() + "% have been tested for COVID-19");
            percentSameSymptomsResults.setText("    • Out of these " + insightsFacade.getPercentSimilarSymptomsPositive() + "% have tested positive and " + insightsFacade.getPercentSimilarSymptomsNegative() + "% have tested negative");
            percentSameLocationTested.setText("• In your area " + insightsFacade.getPercentSimilarLocationTested() + "% of users have been tested for COVID-19");
            percentSameLocationResults.setText("    • Out of these " + insightsFacade.getPercentSimilarLocationPositive() + "% have tested positive and " + insightsFacade.getPercentSimilarLocationNegative() + "% have tested negative");        
        }
        
        return this;
    }
    
    private ChartPanel setupChartPanel() {
        XYSeries series = new XYSeries(new SimpleDateFormat("MMMM").format(new Date()));
        ArrayList<String[]> chartTuples = insightsFacade.getChartPoints();
        if (chartTuples != null) {
            for (int i = 1; i < 31; ++i) {
                for (String[] tuple: chartTuples){
                    if (Integer.parseInt(tuple[0].substring(8,10)) == i)
                        series.add(i, Integer.parseInt(tuple[1]));
                }
            }
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Feeling Trend",
                "Day of the month",
                "Goodness rating",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        ValueAxis domainAxis = plot.getDomainAxis();
        ValueAxis rangeAxis = plot.getRangeAxis();
        domainAxis.setRange(1, 31);
        rangeAxis.setRange(-0.5, 10.5);
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);
        
        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Feeling Trend",
                new Font("SegoeUI", Font.PLAIN, 20)
            )
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMaximumSize(new Dimension(10000, 400));
        chartPanel.setDomainZoomable(false);
        chartPanel.setRangeZoomable(false);
        chartPanel.setPopupMenu(null);
        
        return chartPanel;
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
    private void handleHistoryButtonPressed() {
        this.toHistoryScreen();
    }
    private void handleLogoutButtonPressed() {
        this.toLoginScreen();
    }    
}
