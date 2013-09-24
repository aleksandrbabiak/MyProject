package starthttpnettyserver.db;

import com.mysql.jdbc.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseExecutor {
         
   public static DatabaseConnectionHandler dbConnectionHandler = new DatabaseConnectionHandler();
   
    public static int carryOutInsert(String sql)  {
        Connection connection;
        connection = dbConnectionHandler.openConnection();
        int result = 0;
        try {
            result = connection.createStatement().executeUpdate(sql);        
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        dbConnectionHandler.closeConnection(connection);
        return result;
    }
    public static ResultSet carryOutSelect(String sql)  {     
        Connection connection;
        connection = dbConnectionHandler.openConnection();        
        ResultSet result = null;
        try {
            result = connection.createStatement().executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return result;
    }

}    
    

