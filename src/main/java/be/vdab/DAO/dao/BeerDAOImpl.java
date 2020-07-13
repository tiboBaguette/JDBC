package be.vdab.DAO.dao;

import be.vdab.DAO.BeerExeption;
import be.vdab.DAO.model.Beer;

import java.sql.*;

import static be.vdab.ConnectionUtils.*;

public class BeerDAOImpl implements BeerDAO {
    @Override
    public Beer getBeerById(int id) throws BeerExeption {
        try (Connection con = DriverManager.getConnection(ADRESS, USER, PASSWORD);
             PreparedStatement prepstat = con.prepareStatement(
                     "SELECT * FROM beers WHERE id = ?"
             );
        ) {
            prepstat.setInt(1, id);
            ResultSet rs = prepstat.executeQuery();

            if (rs.next()) {
                Beer beer = new Beer();
                beer.setId(id);
                beer.setName(rs.getString("Name"));
                beer.setPrice(rs.getFloat("Price"));
                beer.setAlcohol(rs.getFloat("Alcohol"));
                beer.setStock(rs.getInt("Stock"));
                return beer;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.out.println("Oops, something went wrong!");
            ex.printStackTrace(System.err);
            throw new BeerExeption(ex);
        }
    }

    @Override
    public void updateBeer(Beer beer) throws BeerExeption {
        String sql = "UPDATE beers SET name = ?, price = ?, stock = ?, alcohol = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection (ADRESS, USER, PASSWORD);
             PreparedStatement prepstat = con.prepareStatement(sql);
        ) {
            System.out.println("Connection OK");

            prepstat.setString(1, beer.getName());
            prepstat.setFloat(2, beer.getPrice());
            prepstat.setInt(3, beer.getStock());
            prepstat.setFloat(4, beer.getAlcohol());
            prepstat.setInt(5, beer.getId());
            int result = prepstat.executeUpdate();
            System.out.println("update: " + result);

            if (result == 0) {
                String sql2 = "INSERT INTO beers (name, price, stock, alcohol) VALUES (?, ?, ?, ?) ";

                PreparedStatement prepstat2 = con.prepareStatement(sql2);
                prepstat2.setString(1, beer.getName());
                prepstat2.setFloat(2, beer.getPrice());
                prepstat2.setInt(3, beer.getStock());
                prepstat2.setFloat(4, beer.getAlcohol());

                result = prepstat2.executeUpdate();
                System.out.println("insert: " + result);
            }
        }
        catch (Exception ex) {
            System.out.println("Oops, something went wrong!");
            ex.printStackTrace(System.err);
            throw new BeerExeption(ex);
        }
    }
}
