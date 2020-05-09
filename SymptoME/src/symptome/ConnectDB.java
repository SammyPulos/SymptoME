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
    
    private static Connection connection = null;
    
    private ConnectDB(){}
    
    // Connects to Docker's image of Oracle Database via JDBC
    public void connectDB() throws SQLException {
        
        // Load the Driver
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");   
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error loading driver: " + cnfe);
        }
        
        // Define the Connection URL
        String host = "localhost";
        String dbName = "XE";
        int port = 1521;
        String oracleURL = "jdbc:oracle:thin:@" + host +
                            ":" + port + ":" +dbName;
        String mysqlURL = "jdbc:mysql://" + host +
                           ":" + port + "/" + dbName;
        // Establish the Connection
        String username = "system";
        String password = "oracle";
        connection = DriverManager.getConnection(oracleURL, username, password);
        
        // Get info about the db system
        DatabaseMetaData dbMetaData = connection.getMetaData();
        String productName = dbMetaData.getDatabaseProductName();
        System.out.println("Database: " + productName);
        String productVersion = dbMetaData.getDatabaseProductVersion();
        System.out.println("Version: " + productVersion);
    } 
    
    protected ArrayList<String> runQuery(String sqlQuery){
        ArrayList<String> results = new ArrayList<>();
        try{
            ResultSet rs;
            try (Statement statement = connection.createStatement()) {
                rs = statement.executeQuery(sqlQuery); 
                if (!rs.next()){
                    return results;
                }
                results.add(rs.getString(1));
                int i = 2;
                while (rs.next()){
                    results.add(rs.getString(i));
                    i++;
                }
            }
            rs.close();
            return results;
   
        } catch(SQLException e){
            System.out.println("Error with SQL Query Execution." + e);
        }
        return null;
    } 
}
