package symptome;

import java.sql.SQLException;

public interface InsightsLocationQueryDB {
    public double calcPercentSimilarLocationTested() throws SQLException;
    public double calcPercentSimilarLocationPositive() throws SQLException;
    public double calcPercentSimilarLocationNegative() throws SQLException;
}
