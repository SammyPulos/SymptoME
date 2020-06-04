package symptome;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class InsightsFacade {
    
    // insights concrete queryDB classes
    InsightsSymptomsQueryDB insightsSymptomsQueryDB;
    InsightsLocationQueryDB insightsLocationQueryDB;
    InsightsChartQueryDB insightsChartQueryDB;
    
    public InsightsFacade() throws SQLException {
        this.insightsSymptomsQueryDB = new InsightsSameSympsQueryDB();
        this.insightsLocationQueryDB = new InsightsSameZipQueryDB();
        this.insightsChartQueryDB = new InsightsChartQueryDB();
    }
    
    public double getPercentSimilarSymptoms() throws SQLException {
        return(insightsSymptomsQueryDB.calcPercentSimilarSymptoms());
    }
    public double getPercentSimilarSymptomsTested() throws SQLException {
        return(insightsSymptomsQueryDB.calcPercentSimilarSmyptomsTested());
    }
    public double getPercentSimilarSymptomsPositive() throws SQLException {
        return(insightsSymptomsQueryDB.calcPercentSimilarSmyptomsTestedPositive());
    }
    public double getPercentSimilarSymptomsNegative() throws SQLException {
        return(insightsSymptomsQueryDB.calcPercentSimilarSmyptomsTestedNegative());
    }
    public double getPercentSimilarLocationTested() throws SQLException {
        return(insightsLocationQueryDB.calcPercentSimilarLocationTested());
    }
    public double getPercentSimilarLocationPositive() throws SQLException {
        return(insightsLocationQueryDB.calcPercentSimilarLocationPositive());
    }
    public double getPercentSimilarLocationNegative() throws SQLException {
        return(insightsLocationQueryDB.calcPercentSimilarLocationNegative());
    }
    public ArrayList<String[]> getChartPoints() {
        return(insightsChartQueryDB.retrieveFeelingRatings());
    }
}
