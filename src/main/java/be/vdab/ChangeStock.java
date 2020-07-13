package be.vdab;

import java.sql.*;

import static be.vdab.ConnectionUtils.*;

public class ChangeStock {
    public static void main(String[] args) {
        String sql = "UPDATE beers SET price = 4 WHERE name ='Fitt'";
        try (Connection con = DriverManager.getConnection (ADRESS, USER, PASSWORD);
             Statement statement = con.createStatement();
        ) {
            System.out.println("Connection OK");
            int result = statement.executeUpdate(sql);
            System.out.println(result);
        }
        catch (Exception ex) {
            System.out.println("Oops, something went wrong!");
            ex.printStackTrace(System.err);
        }
    }
}
