package payxpert.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static final String URL = "jdbc:mysql://localhost:3306/payxpert";
    public static final String User = "root";
    public static final String Password = "Shardul@297";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(URL, User, Password);
    }
}
