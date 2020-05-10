package symptome;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public final class ConnectDB {
    private static final ConnectDB instance_ = new ConnectDB();
    static public ConnectDB instance(){
        return instance_;
    }
    
    private Connection connection = null;
    private ConnectDB(){ 
        try {
            connectDB();
        } catch (SQLException ex) {
            System.err.println("Could not connect to database due to exception:\n " + ex.getMessage());
        }
    }
    
    // Connects to Docker's image of Oracle Database via JDBC
    public void connectDB() throws SQLException {
        
        // Load the Driver
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");   
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error loading driver: " + cnfe);
        }
        
        // Establish the Connection
        connection = DriverManager.getConnection(getOracleURL(), getUsername(), getPassword());
        
        // Get info about the db system
        DatabaseMetaData dbMetaData = connection.getMetaData();
        String productName = dbMetaData.getDatabaseProductName();
        System.out.println("Database: " + productName);
        String productVersion = dbMetaData.getDatabaseProductVersion();
        System.out.println("Version: " + productVersion);
    } 
    
    protected ArrayList<String> runReadQuery(String sqlQuery){
        ArrayList<String> results = new ArrayList<>();
        try{
            ResultSet rs;
            try (Statement statement = connection.createStatement()) {
                rs = statement.executeQuery(sqlQuery); 
                if (!rs.next()){  // no results returned
                    return results;
                }
                else{
                    results.add(rs.getString(1));
                    int i = 2;  // TODO: consider do while
                    while (rs.next()){
                        results.add(rs.getString(i));
                        i++;
                    }
                }
            }
            rs.close();
            return results;
   
        } catch(SQLException e){
            System.out.println("Error with SQL Query Execution." + e);
        }
        return null;
    } 
    
    protected void runUpdateQuery(String sqlQuery){
        try (Statement statement = connection.createStatement()) {
            statement.executeQuery(sqlQuery); 
        
        } catch(SQLException e){
            System.out.println("Error with SQL Query Execution." + e);
        }
    } 
    
    
    private String getOracleURL() {
        String host = "localhost";
        String dbName = "XE";
        int port = 1521;
        return ("jdbc:oracle:thin:@" + host + ":" + port + ":" +dbName);
    }
    private String getUsername() {
        return "system";
    }
    private String getPassword() {
        return "oracle";
    }
}
