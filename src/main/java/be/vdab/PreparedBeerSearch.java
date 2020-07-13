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
                PASSWORD);
             PreparedStatement statement = con.prepareStatement(sql)
        ) {
            System.out.println("Connection OK");

            System.out.println("Bier met 5% of hoger: ");
            statement.setFloat(1, 5F);
            ResultSet rs1 = statement.executeQuery();
            while(rs1.next()) {
                String beerName = rs1.getString("b.name");
                double alcohol = rs1.getDouble("alcohol");
                double price = rs1.getDouble("price");
                String category = rs1.getString("category");
                String brewer = rs1.getString("br.name");
                System.out.println(beerName + " | " + alcohol + "% | " + price + "$ | " + category + " | " + brewer);
            }

            System.out.println("Bier met 10% of hoger: ");
            statement.setFloat(1, 10F);
            ResultSet rs2 = statement.executeQuery();
            while(rs2.next()) {
                String beerName = rs2.getString("b.name");
                double alcohol = rs2.getDouble("alcohol");
                double price = rs2.getDouble("price");
                String category = rs2.getString("category");
                String brewer = rs2.getString("br.name");
                System.out.println(beerName + " | " + alcohol + "% | " + price + "$ | " + category + " | " + brewer);
            }
        }
        catch (Exception ex) {
            System.out.println("Oops, something went wrong!");
            ex.printStackTrace(System.err);
        }
    }
}
