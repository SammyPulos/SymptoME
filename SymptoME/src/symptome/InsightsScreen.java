package symptome;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class InsightsScreen implements Screen{
    private JPanel screenPanel;
    
    private JLabel notificationLabel;
    
    public InsightsScreen() throws SQLException {
        screenPanel = setupScreenPanel();
    }
    
    @Override
    public JPanel getPanel() {
        return screenPanel;
    }
    
    private JPanel setupScreenPanel() throws SQLException {
        screenPanel = new JPanel();        
        screenPanel.setLayout(new BoxLayout(screenPanel, BoxLayout.Y_AXIS));                
        
        JLabel titleLabel = new JLabel("Insights:");

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
        
        SetupInsights();
        
        screenPanel.add(homeButton);
        screenPanel.add(logoutButton);
        
        return screenPanel;
    }
    
    private Screen SetupInsights() throws SQLException {
        if (screenPanel == null) { return this; }
        
        InsightsFacade insightsFacade = new InsightsFacade(); // TODO: need to actually get/generate this
        
        if (insightsFacade.getPercentSameSymptoms() < 0){ // check that user has taken daily survey
            notificationLabel = new JLabel("Please submit your daily survey in order to receive the latest insights.");
            notificationLabel.setForeground(Color.red);
            screenPanel.add(notificationLabel);
        }
        JLabel percentSame = new JLabel("<html><br/>Overall " + insightsFacade.getPercentSameSymptoms() + "% users have recorded the same symptoms as yours at some point</html>");
        JLabel percentSameSymptomsTested = new JLabel("<html><br/>Out of people with symptoms matching your most recent report " + insightsFacade.getPercentSameSymptomsTested() + "% have been tested for COVID-19</html>");
        JLabel percentSameSymptomsResults = new JLabel("    Out of these " + insightsFacade.getPercentSameSymptomsPositive() + "% have tested positive and " + insightsFacade.getPercentSameSymptomsNegative() + "% have tested negative");
        JLabel percentSameLocationTested = new JLabel("<html><br/>In your area " + insightsFacade.getPercentSameLocationTested() + "% of users have been tested for COVID-19</html>");
        JLabel percentSameLocationResults = new JLabel("    Out of these " + insightsFacade.getPercentSameLocationPositive() + "% have tested positive and " + insightsFacade.getPercentSameLocationNegative() + "% have tested negative");
        JLabel spacingLabel = new JLabel("<html><br/></html>");
        
        ChartPanel feelingChartPanel = setupChartPanel();
        
        screenPanel.add(percentSame);
        screenPanel.add(percentSameSymptomsTested);
        screenPanel.add(percentSameSymptomsResults);
        screenPanel.add(percentSameLocationTested);
        screenPanel.add(percentSameLocationResults);
        screenPanel.add(spacingLabel);
        screenPanel.add(feelingChartPanel);
        
        return this;
    }
    
    private ChartPanel setupChartPanel() {
        XYSeries series = new XYSeries("May");
        for (int i = 0; i < 31; ++i) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 10 + 1);
            series.add(i+1, randomNum);
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
                new Font("Serif", java.awt.Font.BOLD, 18)
            )
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setMaximumSize(new Dimension(10000, 400));
        chartPanel.setDomainZoomable(false);
        chartPanel.setRangeZoomable(false);
        chartPanel.setPopupMenu(null);
        
        return chartPanel;
    }
    
    private Screen handleHomeButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.HOME));
    }
    
    private Screen handleLogoutButtonPressed() {
        return (ApplicationWindow.Instance().setScreen(ScreenType.LOGIN));
    }    
    
}
