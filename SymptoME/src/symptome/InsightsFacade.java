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
    public double getPercentSameSymptomsPositive() throws SQLException {
        return(insightsSameSympsQueryDB.calcPercentSameSmyptomsTestedPositive());
    }
    public double getPercentSameSymptomsNegative() throws SQLException {
        return(insightsSameSympsQueryDB.calcPercentSameSmyptomsTestedNegative());
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
