package com.model;

import java.io.Serializable;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * This class is used to represent an restaurant.</p> 
 * @author <a href="mailto:angelokh@gmail.com">Angelo Huang</a> 
 */
@Entity
@Table(name = "restaurant")
public class Restaurant implements Serializable {
	private static final long serialVersionUID = 3617859655330969141L;
	private Integer id;
	private String name;
	private Set<Menu> menuSet = new HashSet<Menu>();

	public Restaurant() {
	}

	public Restaurant(String name, Set<Menu> menuSet) {
		this.name = name;
		this.menuSet = menuSet;
	}
	
	@Id
	@GeneratedValue
	@Column(name = "restaurant_id")
	public Integer getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(Integer id) {
		this.id = id;
	}
   
	@Column(name = "restaurant_name", nullable = false, length = 255)
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
    
	@OneToMany(mappedBy="restaurant")
	@Column(name="restaurant_id")    // inverse=true
    public Set<Menu> getMenuSet() {
		return menuSet;
	}
	/**
	 * @param menuSet
	 */
	public void setMenuSet(Set<Menu> menuSet) {
		this.menuSet = menuSet;
	}
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Restaurant)) {
            return false;
        }

        Restaurant ret = (Restaurant) object;

        return ret.getName().equals(this.name);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public String toString() {
        return new StringBuilder()
        .append("id:" + this.id)
        .append("name:" + this.name)
        .append("menuSet:" + menuSet.toString()).toString();
    }
}
