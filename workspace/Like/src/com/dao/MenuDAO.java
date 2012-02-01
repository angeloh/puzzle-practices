
package com.dao;

import java.util.List;

import com.model.*;
import com.dao.DAO;

/**
 * @author <a href="mailto:angelokh@gmail.com">Angelo Huang</a>
 */
public interface MenuDAO extends DAO {
	public List<Menu> getMenus(Restaurant restaurant);
	public Menu getMenu(Restaurant restaurant, Integer dayID);
    public void saveMenu(Menu menu);
    public void removeMenu(Menu menu);
}
