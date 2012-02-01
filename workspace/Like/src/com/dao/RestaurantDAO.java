
package com.dao;

import java.util.List;

import com.model.*;
import com.dao.DAO;

/**
 * @author <a href="mailto:angelokh@gmail.com">Angelo Huang</a>
 */
public interface RestaurantDAO extends DAO {
	public List<Restaurant> getRestaurants();
    public Restaurant getRestaurant(String restaurantName);
    public void saveRestaurant(Restaurant restaurant);
    public void removeRestaurant(Restaurant restaurant);
}
