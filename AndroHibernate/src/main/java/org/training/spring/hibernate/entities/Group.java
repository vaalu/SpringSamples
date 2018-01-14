/**
 * 
 */
package org.training.spring.hibernate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author mohanavelp
 *
 */
@Entity
@Table(name="SOCIAL_GROUP", uniqueConstraints= {@UniqueConstraint(columnNames= {"SOCIAL_GROUP_ID"})})
public class Group {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SOCIAL_GROUP_ID", nullable=false, unique=true, length=11)
	private long id;
	
	@Column(name="NAME", nullable=false, length=100)
	private String name;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
