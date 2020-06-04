package symptome;

import java.sql.SQLException;

public interface InsightsSymptomsQueryDB {
    public double calcPercentSimilarSymptoms() throws SQLException;
    public double calcPercentSimilarSmyptomsTested() throws SQLException;
    public double calcPercentSimilarSmyptomsTestedNegative() throws SQLException;
    public double calcPercentSimilarSmyptomsTestedPositive() throws SQLException;
}
