package symptome;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class InsightsFacade {
    
    // insights concrete queryDB classes
    InsightsSameSympsQueryDB insightsSameSympsQueryDB;
    InsightsSameZipQueryDB insightsSameZipQueryDB;
    InsightsChartQueryDB insightsChartQueryDB;
    
    public InsightsFacade() throws SQLException {
        // TODO
        this.insightsSameSympsQueryDB = new InsightsSameSympsQueryDB();
        this.insightsSameZipQueryDB = new InsightsSameZipQueryDB();
        this.insightsChartQueryDB = new InsightsChartQueryDB();
    }
    
    public double getPercentSameSymptoms() throws SQLException {
        return(insightsSameSympsQueryDB.calcPercentSameSymptoms());
    }
    public double getPercentSameSymptomsTested() throws SQLException {
        return(insightsSameSympsQueryDB.calcPercentSameSmyptomsTested());
    }
    public double getPercentSameSymptomsPositive() throws SQLException {
        return(insightsSameSympsQueryDB.calcPercentSameSmyptomsTestedPositive());
    }
    public double getPercentSameSymptomsNegative() throws SQLException {
        return(insightsSameSympsQueryDB.calcPercentSameSmyptomsTestedNegative());
    }
    public double getPercentSameLocationTested() throws SQLException {
        return(insightsSameZipQueryDB.calcPercentSameLocationTested());
    }
    public double getPercentSameLocationPositive() throws SQLException {
        return(insightsSameZipQueryDB.calcPercentSameLocationPositive());
    }
    public double getPercentSameLocationNegative() throws SQLException {
        return(insightsSameZipQueryDB.calcPercentSameLocationNegative());
    }
    public ArrayList<String[]> getChartPoints() {
        return(insightsChartQueryDB.retrieveFeelingRatings());
    }
}
