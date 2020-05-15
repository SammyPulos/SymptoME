package symptome;

import java.sql.SQLException;
import java.util.ArrayList;

public class HistoryScreenQueryDB extends QueryDB{
    
    public HistoryScreenQueryDB() throws SQLException{
        super();
    }
    
    // queries Results table for report based on provided username and targetDate
    public ArrayList<String> retrieveReport(String username, java.sql.Date targetDate){
        ArrayList<String> results = executeReadQuery("SELECT * FROM Reports WHERE reportDate = TO_DATE('" + targetDate + "','YYYY-MM-DD') AND username ='" + username + "'");   
        return results;
    }
    
}
