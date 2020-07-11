package be.vdab;

import java.sql.*;

import static be.vdab.ConnectionUtils.*;

public class PreparedBeerSearch {
    public static void main(String[] args) {
        String sql = "SELECT b.name, alcohol, price, category, br.name " +
                "FROM beers b LEFT OUTER JOIN categories c " +
                "ON b.CategoryId = c.Id LEFT OUTER JOIN brewers br " +
                "ON b.BrewerId = br.Id " +
                "WHERE Alcohol > ? " +
                "ORDER BY Alcohol desc";
        try (Connection con = DriverManager.getConnection (
                ADRESS,
                USER,
                PASSWORD
        );
             PreparedStatement statement = con.prepareStatement(sql))
        {
            System.out.println("Connection OK");

            statement.setInt(1, 10);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String beerName = rs.getString("b.name");
                double alcohol = rs.getDouble("alcohol");
                double price = rs.getDouble("price");
                String category = rs.getString("category");
                String brewer = rs.getString("br.name");
                System.out.println(beerName + " | " + alcohol + "% | " + price + "$ | " + category + " | " + brewer);
            }
        }
        catch (Exception ex) {
            System.out.println("Oops, something went wrong!");
            ex.printStackTrace(System.err);
        }
    }
}
