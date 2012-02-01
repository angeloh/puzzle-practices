package com.dao.hibernate;

import java.util.*;

import org.springframework.dao.DataRetrievalFailureException;

import com.dao.DishDAO;
import com.model.Dish;



/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve Dish objects.
 * 
 *
 * @author <a href="mailto:angelokh@gmail.com">Angelo Huang</a>
 */
public class DishDAOHibernate extends BaseDAOHibernate implements DishDAO {
    
    public List<Dish> getDishes() {
        return getHibernateTemplate().find("from Dish");
    }
    
    public Dish getDish(String dishName) {
    	return (Dish) getHibernateTemplate().find(new StringBuilder("from Dish d where d.name = ").append(dishName).toString());    	
    }

    public void saveDish(Dish dish) {
        getHibernateTemplate().saveOrUpdate(dish);
    }

    public void removeDish(Dish dish) {              
        Dish getDish = getDish(dish.getName());
    	if(getDish.getId().equals(dish.getId())){
    		getHibernateTemplate().delete(getDish);
    	}
    	else{
    		throw new DataRetrievalFailureException("dish '" + dish.getName() + "' not found...");
    	}
    }

}
