
package symptome;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoginQueryDB extends QueryDB{
 
    public LoginQueryDB() throws SQLException{
        super();
    }
    
    private String buildQuery(String username){
        return ("SELECT passhash FROM Users WHERE username='" + username + "'");  
    }
    
    public boolean validateUser(String username, String password) throws SQLException{
        ArrayList<String> results = executeQuery(buildQuery(username));
        if (results.isEmpty())
            return false;
        else
            return (password.equals(results.get(0)));
    }        
}
