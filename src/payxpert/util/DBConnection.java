package payxpert.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {


    public static Connection getConnection() throws SQLException{

        return DriverManager.getConnection(DBPropertyUtil.getPropertyString());
    }
}
