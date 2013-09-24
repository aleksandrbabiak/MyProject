package starthttpnettyserver.db;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionHandler {
    public static final String USER_NAME = "root";
    public static final String USER_PASSWORD = "1111";
    public static final String DATABASE_NAME = "http_server_db";
    public static final String DATABASE_HOST = "localhost:3306";   
 
    public Connection openConnection()
    {
        Connection connection = null;
        try {
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://"+DATABASE_HOST+"/"+DATABASE_NAME, USER_NAME, USER_PASSWORD);
        } catch(SQLException e) {
        e.printStackTrace();
        }
        return connection;
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
