package symptome;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class InsightsSameZipQueryDB extends QueryDB{
    
    public InsightsSameZipQueryDB() throws SQLException{
        super();
    }
    
    // calculates percentage of users who've been tested in same zipCode
    public double calcPercentSameLocationTested() throws SQLException{
       ArrayList<String[]> nearbyUsers = retrieveNearbyUsers();
       ArrayList<String[]> nearbyTestedUsers = retrieveNearbyUsersWhoTested();
       
       if (nearbyUsers.isEmpty()) { return 0; } // TODO: added pls check
       return (100 * nearbyTestedUsers.size() / nearbyUsers.size());
    }
    // calculates percentage of users who've tested positive in same zipCode
    public double calcPercentSameLocationPositive() throws SQLException{
       ArrayList<String[]> nearbyTestedUsers = retrieveNearbyUsersWhoTested();
       ArrayList<String[]> nearbyPositiveUsers = retrieveNearbyUsersWithTestResults(1);
       
       if (nearbyTestedUsers == null || nearbyPositiveUsers == null)
           return 0;
       else if (nearbyTestedUsers.isEmpty()) // TODO: added pls check
           return 0;
       else
           return (100 * nearbyPositiveUsers.size() / nearbyTestedUsers.size());
    }
    // calculates percentage of users who've tested negative in same zipCode
    public double calcPercentSameLocationNegative() throws SQLException{
        ArrayList<String[]> nearbyTestedUsers = retrieveNearbyUsersWhoTested();
        ArrayList<String[]> nearbyNegativeUsers = retrieveNearbyUsersWithTestResults(0);
        ArrayList<String[]> nearbyPositiveUsers = retrieveNearbyUsersWithTestResults(1);
       
        if (nearbyNegativeUsers == null || nearbyTestedUsers == null)
            return 0;
        // eliminate users that tested pos from the neg set. 
        Set<String> overlappingUsers = new HashSet<>();
        for (String[] neg : nearbyNegativeUsers){
            overlappingUsers.add(neg[0]);
        }
        for (String[] pos : nearbyPositiveUsers){
            overlappingUsers.add(pos[0]);
        }
        int negUserCount = overlappingUsers.size() - nearbyPositiveUsers.size();

        if (nearbyTestedUsers == null || nearbyTestedUsers.isEmpty()) { return 0; } // TODO: added pls check
        return (100 * negUserCount / nearbyTestedUsers.size());  
    }
  
    public ArrayList<String[]> retrieveNearbyUsers(){
        String username = SessionData.instance().getUsername();
        
        // retrieve current user's zipCode
        ArrayList<String[]> usersZip = executeReadQuery("SELECT zipCode FROM Users WHERE username = '" + username + "'");
        String zipCode = usersZip.get(0)[0];
        
        // retrieve all users where zipCode = current user's zipCode
        ArrayList<String[]> nearbyUsers = executeReadQuery("SELECT username FROM Users WHERE zipCode = " + zipCode + " AND username != '" + username + "'");
      
        return nearbyUsers;
    }
   
    public ArrayList<String[]> retrieveNearbyUsersWhoTested() {
        ArrayList<String[]> nearbyUsers = retrieveNearbyUsers();
        if (nearbyUsers ==  null || nearbyUsers.isEmpty()){
            return null;
        }
        String usernames = "";
        
        for (String[] username : nearbyUsers){
            usernames += "'";
            usernames += username[0];
            usernames += "' OR username = ";
        }
        // find all nearby users who have been tested
        ArrayList<String[]> nearbyTestedUsers = executeReadQuery("SELECT DISTINCT username FROM Reports WHERE beenTested = 1 AND (username = " + usernames.substring(0, usernames.length()-15) + ")");
        
        return nearbyTestedUsers;
    }
    
    public ArrayList<String[]> retrieveNearbyUsersWithTestResults(int result) {
        ArrayList<String[]> nearbyTestedUsers = retrieveNearbyUsersWhoTested();
        if (nearbyTestedUsers ==  null || nearbyTestedUsers.isEmpty()){
            return null;
        }
        String usernames = "";
        
        for (String[] username : nearbyTestedUsers){
            usernames += "'";
            usernames += username[0];
            usernames += "' OR username = ";
        }
        if(result == 0) { // return nearby users that tested negative
            ArrayList<String[]> positiveNearbyUsers = executeReadQuery("SELECT DISTINCT username FROM Reports WHERE beenTested = 1 AND testResult = 0 AND (username = " + usernames.substring(0, usernames.length()-15) + ")");
            return positiveNearbyUsers;
        }
        else if (result == 1){ // return nearby users that tested positive
            ArrayList<String[]> negativeNearbyUsers = executeReadQuery("SELECT DISTINCT username FROM Reports WHERE beenTested = 1 AND testResult = 1 AND (username = " + usernames.substring(0, usernames.length()-15) + ")");
            return negativeNearbyUsers;
        }
        return null;   
    }
}
