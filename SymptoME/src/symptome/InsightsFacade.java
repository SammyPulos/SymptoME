package symptome;

import java.sql.SQLException;

public class InsightsFacade {
    // insights concrete queryDB classes
    InsightsSameSympsQueryDB insightsSameSympsQueryDB;
    
    public InsightsFacade() throws SQLException {
        // TODO
        this.insightsSameSympsQueryDB = new InsightsSameSympsQueryDB();
    }
    
    // TODO: actually figure out the percents
    public double getPercentSameSymptoms() throws SQLException {
        return (100 * insightsSameSympsQueryDB.numUsersWithSameSymptoms() / insightsSameSympsQueryDB.numUsers());
    }
    public double getPercentSameSymptomsTested() {
        return 1;
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
