package be.vdab;

import java.sql.*;

import static be.vdab.ConnectionUtils.*;

public class ChangeStockTransaction {
    public static void main(String[] args) {
        String sql1 = "UPDATE beers SET stock = ? WHERE name = ?";
        String sql2 = "UPDATE beers SET stock = ? WHERE name = ?";
        try (Connection con = DriverManager.getConnection (
                ADRESS,
                USER,
                PASSWORD
        );)
        {
            System.out.println("Connection OK");

            try (PreparedStatement statement1 = con.prepareStatement(sql1);
                 PreparedStatement statement2 = con.prepareStatement(sql2)) {

                con.setAutoCommit(false);

                statement1.setInt(1, 300);
                statement1.setString(2, "Fitt");
                int result1 = statement1.executeUpdate();
                System.out.println(result1);

                statement2.setInt(1, 350);
                statement2.setString(2, statement2.enquoteLiteral("Brigand"));
                int result2 = statement1.executeUpdate();
                System.out.println(result2);

                con.commit();

            } catch (SQLException e) {
                con.rollback();
            }
        }
        catch (Exception ex) {
            System.out.println("Oops, something went wrong!");
            ex.printStackTrace(System.err);
        }
    }
}
