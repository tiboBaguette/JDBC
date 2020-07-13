package be.vdab;

import java.sql.*;

import static be.vdab.ConnectionUtils.*;

public class ConnectDB {
    public static void main(String[] args) {
        String sql = "SELECT b.name, alcohol, price, category, br.name " +
                "FROM beers b LEFT OUTER JOIN categories c " +
                "ON b.CategoryId = c.Id LEFT OUTER JOIN brewers br " +
                "ON b.BrewerId = br.Id " +
                "WHERE Alcohol > 10 " +
                "ORDER BY Alcohol";
        try (Connection con = DriverManager.getConnection (ADRESS, USER, PASSWORD);
             Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = statement.executeQuery(sql);
             ) {
            System.out.println("Connection OK");

            rs.afterLast();
            while(rs.previous()) {
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
