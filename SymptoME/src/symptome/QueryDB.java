
package symptome;

import java.sql.SQLException;
import java.util.ArrayList;

abstract class QueryDB {
    
    private final ConnectDB db;
    
    protected QueryDB() throws SQLException{
        this.db = ConnectDB.instance();
    }
    
    protected ArrayList<String> executeReadQuery(String query){
       return (db.runReadQuery(query));
    };
    
    protected void executeUpdateQuery(String query){
        db.runUpdateQuery(query);
    }   
}
