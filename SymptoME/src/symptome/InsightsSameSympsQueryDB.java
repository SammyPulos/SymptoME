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
        Set<String> sameSympUsers = retrieveUsersWithSameSymptoms();
        if (sameSympUsers == null)
            return -1;
        
        Integer numUsers = (numUsers()-1);
        if (numUsers == 0) { 
            return 0; 
        } else {
            return (100 * sameSympUsers.size() / numUsers);
        }
    }
    
     // calculates percentage of users with same symptoms who've been tested
    public double calcPercentSameSmyptomsTested() throws SQLException{
        Set<String> sameSympUsers = retrieveUsersWithSameSymptoms();
        ArrayList<String[]> sameSympUsersWhoTested = retrieveUserWithSameSymptomsWhoTested();
        
        if (sameSympUsers == null || sameSympUsersWhoTested == null)
            return 0;
        else 
            return (100 * sameSympUsersWhoTested.size() / sameSympUsers.size());
    }
    
      // calculates percentage of users with same symptoms who've tested negative
    public double calcPercentSameSmyptomsTestedNegative() throws SQLException{
        ArrayList<String[]> negTestedUsers = retrieveUsersWithSameSymptomsWithTestResults(0);
        ArrayList<String[]> posTestedUsers = retrieveUsersWithSameSymptomsWithTestResults(1);
        ArrayList<String[]> sameSympUsersWhoTested = retrieveUserWithSameSymptomsWhoTested();
        
        if (negTestedUsers == null || sameSympUsersWhoTested == null)
            return 0;
        // eliminate users that tested pos from the neg set. 
        Set<String> overlappingUsers = new HashSet<>();
        for (String[] neg : negTestedUsers){
            overlappingUsers.add(neg[0]);
        }
        for (String[] pos : posTestedUsers){
            overlappingUsers.add(pos[0]);
        }
        int negUserCount = overlappingUsers.size() - posTestedUsers.size();
        
        return (100 * negUserCount / sameSympUsersWhoTested.size());
    }
    
      // calculates percentage of users with same symptoms who've tested positive
    public double calcPercentSameSmyptomsTestedPositive() throws SQLException{
        ArrayList<String[]> posTestedUsers = retrieveUsersWithSameSymptomsWithTestResults(1);
        ArrayList<String[]> sameSympUsersWhoTested = retrieveUserWithSameSymptomsWhoTested();
        
        if (posTestedUsers == null || sameSympUsersWhoTested == null)
            return 0;
        else 
            return (100 * posTestedUsers.size() / sameSympUsersWhoTested.size());
    }
    
    public int numUsers() throws SQLException{
        ArrayList<String[]> results = executeReadQuery("SELECT * FROM Users");
        return results.size(); // return all but current user
    } 
    
    public Set<String> retrieveUsersWithSameSymptoms() {
        String username = SessionData.instance().getUsername();
        Date todaysDate = new java.sql.Date((new Date()).getTime());
        
        // retrieve curr user's report
        ArrayList<String[]> currUserReport = executeReadQuery("SELECT * FROM Reports WHERE username = '" + username + "' AND reportDate = TO_DATE('" + todaysDate + "','YYYY-MM-DD')");
        if (currUserReport == null || currUserReport.isEmpty()){
            return null;
        }
        
        // retrieve all reports where username != curr user (drop duplicates)
        ArrayList<String[]> allUsersReports = executeReadQuery("SELECT DISTINCT * FROM Reports WHERE username != '" + username + "'");
      
        // if symptom fields match, add user to a set
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
        // return users with same symptoms
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
        // find all reports where users are in the sameSymptoms set and beenTested == 1
        ArrayList<String[]> testedUsers = executeReadQuery("SELECT DISTINCT username FROM Reports WHERE beenTested = 1 AND (username = " + usernames.substring(0, usernames.length()-15) + ")");
        return testedUsers;
    }
    
    public ArrayList<String[]> retrieveUsersWithSameSymptomsWithTestResults(int result){
        ArrayList<String[]> testedUsers = retrieveUserWithSameSymptomsWhoTested();
        if (testedUsers ==  null || testedUsers.isEmpty()){
            return null;
        }
        String usernames = "";
        
        for (String[] username : testedUsers){
            usernames += "'";
            usernames += username[0];
            usernames += "' OR username = ";
        }
        if (result == 0){ // negative test results
            ArrayList<String[]> negTestedUsers = executeReadQuery("SELECT DISTINCT username FROM Reports WHERE beenTested = 1 AND testResult = 0 AND (username = " + usernames.substring(0, usernames.length()-15) + ")");
            return negTestedUsers;
        } else if (result == 1){ //positive test results
            ArrayList<String[]> posTestedUsers = executeReadQuery("SELECT DISTINCT username FROM Reports WHERE beenTested = 1 AND testResult = 1 AND (username = " + usernames.substring(0, usernames.length()-15) + ")");
            return posTestedUsers;
        }
        return null;
    }
}
