package be.vdab.DAO.dao;

import be.vdab.DAO.BrewerExeption;
import be.vdab.DAO.model.Brewer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static be.vdab.ConnectionUtils.*;

public class BrewerDAOImpl implements BrewerDAO {
    @Override
    public List<Brewer> getBrewersByZipcode(int zipcode) throws BrewerExeption {
        String sql = "SELECT * FROM brewers WHERE zipcode = ?";
        try (Connection con = DriverManager.getConnection(ADRESS, USER, PASSWORD);
             PreparedStatement prepstat = con.prepareStatement(sql);
        ) {
            prepstat.setInt(1, zipcode);
            ResultSet rs = prepstat.executeQuery();

            List<Brewer> brewerList = new ArrayList<Brewer>();
            while(rs.next()) {
                Brewer brewer = new Brewer();
                brewer.setId(rs.getInt("Id"));
                brewer.setName(rs.getString("Name"));
                brewer.setAddress(rs.getString("Address"));
                brewer.setZipcode(rs.getInt("Zipcode"));
                brewer.setCity(rs.getString("City"));
                brewer.setTurnover(rs.getInt("Turnover"));

                brewerList.add(brewer);
            }
            return brewerList;

        } catch (SQLException ex) {
            System.out.println("Oops, something went wrong!");
            ex.printStackTrace(System.err);
            throw new BrewerExeption(ex);
        }
    }
}
