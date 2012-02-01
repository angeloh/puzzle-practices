package com.model;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.util.HibernateUtil;

public class Main {

	public static void main(String[] args) {
		Main obj = new Main();
		obj.saveRestaurant("Macdonals");
		obj.saveRestaurant("KFC");
		obj.saveRestaurant("JackInTheBox");
		
	}
	
	public void saveRestaurant(String restaurantName)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			Restaurant rest = new Restaurant();
			rest.setName(restaurantName);
			session.save(rest);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}		
	}

}
