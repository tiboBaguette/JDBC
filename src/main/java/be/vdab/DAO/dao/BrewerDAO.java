package be.vdab.DAO.dao;

import be.vdab.DAO.Exceptions.BrewerExeption;
import be.vdab.DAO.model.Brewer;

import java.util.List;

public interface BrewerDAO {
    // Brewer getbrewerById(int id) throws BrewerExeption;
    // void updateBrewer(Brewer brewer) throws BrewerExeption;

    List<Brewer> getBrewersByZipcode(int zipcode) throws BrewerExeption;
}
