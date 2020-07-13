package be.vdab.DAO.dao;

import be.vdab.DAO.BeerExeption;
import be.vdab.DAO.model.Beer;

public interface BeerDAO {
    Beer getBeerById(int id) throws BeerExeption;
    void updateBeer(Beer beer) throws BeerExeption;
}
