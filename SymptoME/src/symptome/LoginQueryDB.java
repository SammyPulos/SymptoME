
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
    
    public boolean validateUser(String username, String password) throws SQLException{
        ArrayList<String[]> results = executeReadQuery(confirmPasswordFor(username));
        if (results == null || results.isEmpty()){
            System.out.println("not returning anything");
            return false;
        }
        else{
            System.out.println(results.get(0)[0]);
            return (password.equals(results.get(0)[0]));
        }
    }        
}
