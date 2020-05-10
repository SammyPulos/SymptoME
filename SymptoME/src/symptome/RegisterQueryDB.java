
package symptome;

import java.sql.SQLException;
import java.util.ArrayList;


public class RegisterQueryDB extends QueryDB{
    
    public RegisterQueryDB() throws SQLException{
        super();
    }
    
    private String searchUsername(String username){
        return ("SELECT * FROM Users WHERE username='" + username + "'");  
    }
    
    private String addUser(String username, String password, String zipCode, java.sql.Date dob){
        return ("INSERT INTO Users (username, passhash, zipCode, dob) " +
                "VALUES ('" + username + "', '" + password + "', " + zipCode + ", " + 
                "TO_DATE('" + dob + "','YYYY-MM-DD'))");
    }
   
    public boolean registerUser(String username, String password, String zipCode, java.sql.Date dob) throws SQLException{
        ArrayList<String> results = executeReadQuery(searchUsername(username));
        if (results == null || results.isEmpty()){
            executeUpdateQuery(addUser(username, password, zipCode, dob));
            return true;
        }
        else
            return false; // username already exists
    }  
    
}
