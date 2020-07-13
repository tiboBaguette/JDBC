package be.vdab.DAO.dao;

import be.vdab.DAO.CategoryExeption;
import be.vdab.DAO.model.Category;

public interface CategoryDAO {
    Category getCategoryById(int id) throws CategoryExeption;
    void updateCategory(Category category) throws CategoryExeption;
}
