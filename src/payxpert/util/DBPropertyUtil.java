package payxpert.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBPropertyUtil {
    public static String getPropertyString() {

        Properties prop = new Properties();

        try {
            FileInputStream fs = new FileInputStream("db.properties");
            prop.load(fs);

            fs.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        String hostname = prop.getProperty("hostname");
        String dbname = prop.getProperty("dbname");
        String username = prop.getProperty("username");
        String password = prop.getProperty("password");
        String port = prop.getProperty("port");

        String connectionString = "jdbc:mysql://" + hostname + ":" + port + "/" + dbname +
                "?user=" + username + "&password=" + password;

        return connectionString;


    }
}
