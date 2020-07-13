package be.vdab.DAO;

import be.vdab.DAO.dao.BeerDAO;
import be.vdab.DAO.dao.BeerDAOImpl;
import be.vdab.DAO.model.Beer;

public class BeerTest {
    public static void main(String[] args) {
        Beer beer = new Beer();
        beer.setId(1600);
        beer.setName("test");
        beer.setStock(30000);
        beer.setAlcohol(50);
        beer.setPrice(260);

        BeerDAO beerDAO = new BeerDAOImpl();

        try {
            beerDAO.updateBeer(beer);
            beerDAO.getBeerById(beer.getId());
            System.out.println(beer.toString());
        } catch (BeerExeption e) {
            e.printStackTrace();
        }
    }
}
