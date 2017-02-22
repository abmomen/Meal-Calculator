
package testproject10;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    private static String url = "jdbc:mysql://localhost:3306/testdb";    
    private static String driverName = "com.mysql.jdbc.Driver";   
    private static String username = "root";   
    private static String password = "    ";
    private static Connection con;
    
   public static Connection createConnection() throws SQLException{
  
           try {
            Class.forName(driverName);
            con = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found."); 
        }
        return con;
    }
    
}
