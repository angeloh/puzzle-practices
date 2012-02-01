package com.dao.hibernate;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.dao.DAO;

/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common methods that they might all use. Can be used for standard CRUD
 * operations.</p>
 *
 * @author <a href="mailto:angelokh@gmail.com">Angelo Huang</a> 
 */
public class BaseDAOHibernate extends HibernateDaoSupport implements DAO {   

    public void saveObject(Object o) {
        getHibernateTemplate().saveOrUpdate(o);
    }

    public Object getObject(Class<?> clazz, Serializable id) {
        Object o = getHibernateTemplate().get(clazz, id);

        if (o == null) {
            throw new ObjectRetrievalFailureException(clazz, id);
        }

        return o;
    }

    public List<?> getObjects(Class<?> clazz) {
        return getHibernateTemplate().loadAll(clazz);
    }

    public void removeObject(Class<?> clazz, Serializable id) {
        getHibernateTemplate().delete(getObject(clazz, id));
    }
}
