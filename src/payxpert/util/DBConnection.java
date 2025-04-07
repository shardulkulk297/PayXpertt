package payxpert.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
//    public static final String URL = "jdbc:mysql://localhost:3306/payxpert";
//    public static final String User = "root";
//    public static final String Password = "Shardul@297";



    public static Connection getConnection() throws SQLException{
        Properties prop = new Properties();

        try{
            FileInputStream fs = new FileInputStream("db.properties");
            prop.load(fs);


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String URL = prop.getProperty("URL");
        String USER = prop.getProperty("User");
        String Password = prop.getProperty("Password");

        return DriverManager.getConnection(URL, USER, Password);
    }
}
