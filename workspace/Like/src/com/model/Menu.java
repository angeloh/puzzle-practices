package com.model;

import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * This class is used to represent an Menu.</p> 
 * @author <a href="mailto:angelokh@gmail.com">Angelo Huang</a>
 *  
 */
@Entity
@Table(name = "menu")
@IdClass(MenuPk.class)
public class Menu implements Serializable {
	private static final long serialVersionUID = -3894361321024790045L;
	private static final int HASH_PRIME = 1000003;
	private Restaurant restaurant = new Restaurant();
	private Integer dayId;   
    private Set<Dish> dishSet = new HashSet<Dish>();

    public Menu() {
	}

	public Menu(Restaurant restaurant, Integer dayId) {
		this.restaurant = restaurant;
		this.dayId = dayId;
	}
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="restaurant_id", insertable=false, updatable=false, nullable=false)
	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	/**
	 * @param restaurant.
	 */
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	@Id
	@Column(name = "day_id", nullable = false)
	public Integer getDayId() {
		return dayId;
	}
	
	/**
     * Sets the dayId.
     * @param dayId
     */
	public void setDayId(Integer dayId) {
		this.dayId = dayId;
	}
	
	@Transient	
    public Set<Dish> getDishList() {
		return dishSet;
	}
	/**
	 * @param dishSet
	 */
	public void setDishList(Set<Dish> dishSet) {
		this.dishSet = dishSet;
	}
	
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu menu = (Menu) object;
        if (!menu.getRestaurant().getName().equals(this.restaurant.getName()))
        	return false;
        if (!menu.getDayId().equals(this.dayId))
        	return false;
        return true;
    }

    @Override
    public int hashCode() {
    	int result = 0;
		result = HASH_PRIME * result + this.restaurant.getName().hashCode();
		result = HASH_PRIME * result + this.dayId.hashCode();
		return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
        .append("restaurant:" + this.restaurant.getName())
        .append("dayId:" + this.dayId).toString();
    }
	
}
