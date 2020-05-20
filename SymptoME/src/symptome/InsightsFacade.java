package symptome;

import java.sql.SQLException;

public class InsightsFacade {
    
    // insights concrete queryDB classes
    InsightsSameSympsQueryDB insightsSameSympsQueryDB;
    
    public InsightsFacade() throws SQLException {
        // TODO
        this.insightsSameSympsQueryDB = new InsightsSameSympsQueryDB();
    }
    
    public double getPercentSameSymptoms() throws SQLException {
        return(insightsSameSympsQueryDB.calcPercentSameSymptoms());
    }
    public double getPercentSameSymptomsTested() throws SQLException {
        return(insightsSameSympsQueryDB.calcPercentSameSmyptomsTested());
    }
    public double getPercentSameSymptomsPositive() {
        return 2;
    }
    public double getPercentSameSymptomsNegative() {
        return 3;
    }
    public double getPercentSameLocationTested() {
        return 4;
    }
    public double getPercentSameLocationPositive() {
        return 5;
    }
    public double getPercentSameLocationNegative() {
        return 6;
    }
}
