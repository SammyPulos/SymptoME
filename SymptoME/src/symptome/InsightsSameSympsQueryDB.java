package symptome;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class InsightsSameSympsQueryDB extends QueryDB {
    
    public InsightsSameSympsQueryDB() throws SQLException{
        super();
    }
    
    public int numUsers() throws SQLException{
        ArrayList<String[]> results = executeReadQuery("SELECT * FROM Users");
        System.out.println(results.size());
        return results.size();
    } 
   
    public int numUsersWithSameSymptoms() {
        String username = SessionData.instance().getUsername();
        Date todaysDate = new java.sql.Date((new Date()).getTime());
        
        // 1. Retrieve curr user's report
        ArrayList<String[]> currUserReport = executeReadQuery("SELECT * FROM Reports WHERE username = '" + username + "' AND reportDate = TO_DATE('" + todaysDate + "','YYYY-MM-DD')");
        
        // 2. Retrieve all reports where username != curr user
        ArrayList<String[]> allUsersReports = executeReadQuery("SELECT * FROM Reports WHERE username != '" + username + "'");
      
        // 3.  If symptom fields match, add user to a set (will drop duplicates)
        Set<String> sameSympUsers = new HashSet<String>(); 
        for (String[] row : allUsersReports){
            int sameCount = 0;
            for (int i = 3; i < 9; i++){ // only comparing symptoms
                if (row[i].equals(currUserReport.get(0)[i])){
                    sameCount++;
                }
                else {
                    break;
                }
            }
            if (sameCount == 6){ // if all symptoms are the same, add user to set
                sameSympUsers.add(row[1]);
            }
        }
        // 4. Return number of users with same symptoms
        return (sameSympUsers.size());
    } 
}
