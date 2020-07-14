package be.vdab;

import be.vdab.DAO.Exceptions.BeerExeption;
import be.vdab.DAO.dao.BeerDAO;
import be.vdab.DAO.dao.BeerDAOImpl;
import be.vdab.DAO.model.Beer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BeerTest {

    @Test
    @DisplayName("Test if beer id 21 is updated")
    void testUpdateBeer() {
        // beer object id 21
        Beer beer = new Beer();
        beer.setId(21);
        beer.setName("test beer");
        beer.setStock(30000);
        beer.setAlcohol(50);
        beer.setPrice(200);

        try {
            // execute update
            BeerDAO beerDAO = new BeerDAOImpl();
            beerDAO.updateBeer(beer);
            beerDAO.getBeerById(beer.getId());
            System.out.println(beer.toString());

            // get beer by id
            Beer testBeer = beerDAO.getBeerById(beer.getId());

            // assert that result of getbeerbyid is same as original beer used to update

            // beer.setName(":)");
            assertEquals(beer, testBeer);

        } catch (BeerExeption e) {
            e.printStackTrace();
        }
    }
}
