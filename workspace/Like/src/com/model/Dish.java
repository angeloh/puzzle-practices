package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is used to represent an dish.</p> 
 * @author <a href="mailto:angelokh@gmail.com">Angelo Huang</a> *
 * 
 */
@Entity
@Table(name = "dish")
public class Dish implements Serializable {
	private static final long serialVersionUID = 6690399975176348L;
	private Integer id;
	private String name;
	
	public Dish() {
	}

	public Dish(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	@Id
	@GeneratedValue
	@Column(name = "dish_id")
	public Integer getId() {
		return id;
	}
	/**
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

    @Column(name = "dish_name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    } 
    
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Dish)) {
            return false;
        }

        Dish dish = (Dish) object;

        return dish.getName().equals(this.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public String toString() {
        return new StringBuilder()
        .append("id:" + this.id)
        .append("name:" + this.name).toString();        
    }
}
