package com.dao.hibernate;

import java.util.*;

import org.springframework.dao.DataRetrievalFailureException;

import com.dao.RestaurantDAO;
import com.model.Restaurant;



/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve Restaurant objects.
 * 
 *
 * @author <a href="mailto:angelokh@gmail.com">Angelo Huang</a>
 */
public class RestaurantDAOHibernate extends BaseDAOHibernate implements RestaurantDAO {
	
    public List<Restaurant> getRestaurants() {
        return getHibernateTemplate().find("from Restaurant");
    }
    
    public Restaurant getRestaurant(String restaurantName) {
    	return (Restaurant) getHibernateTemplate().find(new StringBuilder("from Restaurant r where r.name = ").append(restaurantName).toString());    	
    }

    public void saveRestaurant(Restaurant restaurant) {
        getHibernateTemplate().saveOrUpdate(restaurant);
    }

    public void removeRestaurant(Restaurant restaurant) {              
    	Restaurant getRestaurant = getRestaurant(restaurant.getName());
    	if(getRestaurant.getId().equals(restaurant.getId())){
    		getHibernateTemplate().delete(getRestaurant);
    	}
    	else{
    		throw new DataRetrievalFailureException("restaurant '" + restaurant.getName() + "' not found...");
    	}
    }

}
