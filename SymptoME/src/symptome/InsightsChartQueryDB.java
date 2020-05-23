package symptome;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InsightsChartQueryDB extends QueryDB {
    
    public InsightsChartQueryDB() throws SQLException{
        super();
    }
    
    public ArrayList<String[]> retrieveFeelingRatings(){
        
        String username = SessionData.instance().getUsername();
        Date todaysDate = new java.sql.Date((new Date()).getTime());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(todaysDate);
        
        // selet time period from (curr month day 1 - curr month curr day)
        String strFirstDate = strDate.substring(0,8) + "01"; //curr yyyy-mm-01
        java.sql.Date firstDate = java.sql.Date.valueOf(strFirstDate);
        
        // get curr user's feeling ratings from 1st of Month to Today
        ArrayList<String[]> feelingRatings = executeReadQuery("SELECT reportDate, feelingRating FROM Reports WHERE username = '" + username + "' AND reportDate <= TO_DATE('" + todaysDate + "','YYYY-MM-DD') AND reportDate >= TO_DATE('" + firstDate + "','YYYY-MM-DD')");
        if (feelingRatings == null || feelingRatings.isEmpty()){
            return null;
        }
        return feelingRatings;
    }
    
}
