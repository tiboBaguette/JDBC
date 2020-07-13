package be.vdab;

import java.sql.*;

import static be.vdab.ConnectionUtils.*;

public class AddBeer {
    public static void main(String[] args) {
        String sql = "INSERT INTO beers (name, alcohol, price, stock, categoryId, brewerId) VALUES ('MyBeer', 12, 3, 100, 2, 2) ";
        try (Connection con = DriverManager.getConnection (ADRESS, USER, PASSWORD);
             Statement statement = con.createStatement();
        ) {
            System.out.println("Connection OK");
            int result = statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS);
            System.out.println(result);

            try(ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Generated key: " + id);
                }
            }
        }
        catch (Exception ex) {
            System.out.println("Oops, something went wrong!");
            ex.printStackTrace(System.err);
        }
    }
}