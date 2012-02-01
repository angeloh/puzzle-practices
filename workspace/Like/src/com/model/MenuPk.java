package com.model;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class MenuPk implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final int HASH_PRIME = 1000003;
	private Restaurant restaurant = new Restaurant();
	private Integer dayId;
	
	public Restaurant getRestaurant() {
		return restaurant;
	}
	
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	public Integer getDayId() {
		return dayId;
	}
	
	public void setDayId(Integer dayId) {
		this.dayId = dayId;
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
}