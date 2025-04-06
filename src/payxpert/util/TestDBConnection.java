package payxpert.util;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDBConnection {
    public static void main(String[] args) {
        try{
            Connection con = DBConnection.getConnection();
            System.out.println("Connected Successfully");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
