package com.dao.hibernate;

import java.util.*;

import org.springframework.dao.DataRetrievalFailureException;

import com.dao.MenuDAO;
import com.model.Menu;
import com.model.Restaurant;



/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve Menu objects.
 * 
 *
 * @author <a href="mailto:angelokh@gmail.com">Angelo Huang</a>
 */
public class MenuDAOHibernate extends BaseDAOHibernate implements MenuDAO {
	public List<Menu> getMenus(Restaurant restaurant) {
		List<Menu>  l = getHibernateTemplate().find("from Menu m where m.restaurant=?",new Object[]{restaurant});
		return l;
	}
    
    public Menu getMenu(Restaurant restaurant, Integer dayID) {
    	return (Menu) getHibernateTemplate().find("from Menu m where m.restaurant=? and m.dayId=?",new Object[]{restaurant, dayID});   	
    	
    }
    
    public void saveMenu(Menu menu) {
    	getHibernateTemplate().saveOrUpdate(menu);
    }
    
    public void removeMenu(Menu menu) {   	
    	
    	Menu getMenu = getMenu(menu.getRestaurant(), menu.getDayId());
     	if(getMenu != null){
     		getHibernateTemplate().delete(getMenu);
     	}
     	else{
     		throw new DataRetrievalFailureException("menu not found...");
     	}
    }
     
}
