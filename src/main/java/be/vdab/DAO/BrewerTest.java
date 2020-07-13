package be.vdab.DAO;

import be.vdab.DAO.dao.BrewerDAO;
import be.vdab.DAO.dao.BrewerDAOImpl;
import be.vdab.DAO.model.Brewer;

import java.util.List;

public class BrewerTest {
    public static void main(String[] args) {
        BrewerDAO brewerDao = new BrewerDAOImpl();

        try {
            List<Brewer> brewerList = brewerDao.getBrewersByZipcode(1650);
            for (int i = 0; i < brewerList.size(); i++) {
                System.out.println(brewerList.get(i).toString());
            }
        } catch (BrewerExeption ex) {
            ex.printStackTrace();
        }
    }
}
