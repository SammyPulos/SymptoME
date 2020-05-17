package symptome;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProfileQueryDB extends QueryDB {
    
    public ProfileQueryDB() throws SQLException{
        super();
    }
     
    public String retrieveZipCode(String username){
        ArrayList<String[]> results = executeReadQuery("SELECT zipCode FROM Users WHERE username='" + username + "'"); 
        return results.get(0)[0];
    }
      
    public String retrieveDOB(String username){
        ArrayList<String[]> results = executeReadQuery("SELECT dob FROM Users WHERE username='" + username + "'"); 
        return results.get(0)[0].substring(0,10);
    }
}
