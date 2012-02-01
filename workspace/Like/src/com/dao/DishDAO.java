
package com.dao;

import java.util.List;

import com.model.Dish;
import com.dao.DAO;

/**
 * @author <a href="mailto:angelokh@gmail.com">Angelo Huang</a>
 */
public interface DishDAO extends DAO {
	public List<Dish> getDishes();
	public Dish getDish(String dishName);
    public void saveDish(Dish dish);
    public void removeDish(Dish dish);
}
