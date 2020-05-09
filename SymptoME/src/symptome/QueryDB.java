
package symptome;

import java.sql.SQLException;
import java.util.ArrayList;

abstract class QueryDB {
    
    private final ConnectDB db;
    
    protected QueryDB() throws SQLException{
        this.db = ConnectDB.instance();
    }
    
    // delegates query to singleton
    protected ArrayList<String> executeQuery(String query){
       return (db.runQuery(query));
    };
        
}
