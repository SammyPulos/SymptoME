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
    
    // calculates percentage of users with same symptoms
    public double calcPercentSameSymptoms() throws SQLException{
        if (retrieveUsersWithSameSymptoms() == null)
            return -1;
        
        Integer numUsers = (numUsers()-1);
        if (numUsers == 0) { 
            return 0; 
        } else {
            return (100 * retrieveUsersWithSameSymptoms().size() / numUsers);
        }
    }
    
     // calculates percentage of users with same symptoms who've been tested
    public double calcPercentSameSmyptomsTested() throws SQLException{
        if (retrieveUsersWithSameSymptoms() == null || retrieveUserWithSameSymptomsWhoTested() == null)
            return 0;
        else {
            return (100 * retrieveUserWithSameSymptomsWhoTested().size() / retrieveUsersWithSameSymptoms().size());
        }
    }
    
    public int numUsers() throws SQLException{
        ArrayList<String[]> results = executeReadQuery("SELECT * FROM Users");
        return results.size(); // return all but current user
    } 
    
    public Set<String> retrieveUsersWithSameSymptoms() {
        String username = SessionData.instance().getUsername();
        Date todaysDate = new java.sql.Date((new Date()).getTime());
        
        // 1. Retrieve curr user's report
        ArrayList<String[]> currUserReport = executeReadQuery("SELECT * FROM Reports WHERE username = '" + username + "' AND reportDate = TO_DATE('" + todaysDate + "','YYYY-MM-DD')");
        if (currUserReport == null || currUserReport.isEmpty()){
            return null;
        }
        
        // 2. Retrieve all reports where username != curr user
        ArrayList<String[]> allUsersReports = executeReadQuery("SELECT * FROM Reports WHERE username != '" + username + "'");
      
        // 3.  If symptom fields match, add user to a set (will drop duplicates)
        Set<String> sameSympUsers = new HashSet<>(); 
        for (String[] row : allUsersReports){
            int sameCount = 0;
            while (row[sameCount+3].equals(currUserReport.get(0)[sameCount+3]) && (sameCount < 6)) {
                    ++sameCount;
            }
            if (sameCount == 6){ // if all symptoms are the same, add user to set
                sameSympUsers.add(row[1]);
            }
        }
        // 4. Return users with same symptoms
        return sameSympUsers;
    } 
    
    public ArrayList<String[]> retrieveUserWithSameSymptomsWhoTested() {
        Set<String> sameSympUsers = retrieveUsersWithSameSymptoms();
        if (sameSympUsers ==  null || sameSympUsers.isEmpty()){
            return null;
        }
        String usernames = "";
        
        for (String username : sameSympUsers){
            usernames += "'";
            usernames += username;
            usernames += "' OR username = ";
        }
        // Find all reports where users are in the sameSymptoms set and beenTested == 1
        ArrayList<String[]> testedUsers = executeReadQuery("SELECT DISTINCT username FROM Reports WHERE beenTested = 1 AND (username = " + usernames.substring(0, usernames.length()-15) + ")");
        
        return testedUsers;
    }
}
