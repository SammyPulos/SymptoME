
package symptome;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoginQueryDB extends QueryDB{
 
    public LoginQueryDB() throws SQLException{
        super();
    }
    
    private String confirmPasswordFor(String username){
        return ("SELECT passhash FROM Users WHERE username='" + username + "'");  
    }
    
    public boolean validateUser(String username, String password) {
        ArrayList<String[]> results = executeReadQuery(confirmPasswordFor(username));
        if (results == null || results.isEmpty()){
            return false;
        }
        else{
            return (password.equals(results.get(0)[0]));
        }
    }        
}
