/**
 * 
 */
package org.training.spring.hibernate.entities.manytomany;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * @author mohanavelp
 *
 */
@Entity
@Table(name="PROJECT", uniqueConstraints= {@UniqueConstraint(columnNames= {"ID"})})
public class Project {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true, length=11)
	private int id;
	
	@Column(name="name", nullable=true, length=20)
	private String name;
	
	//If you want to store simple date (java sql date, which ignores timestamp, then use @Temporal annotation
	
	@Temporal(TemporalType.DATE)
	@Column(name="insert_time", nullable=true)
	private Date insertTime;
	
	@ManyToMany(mappedBy = "projects", cascade = {CascadeType.MERGE})
    private Set<Employee> employees = new HashSet<>();
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
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
	/**
	 * @return the insertTime
	 */
	public Date getInsertTime() {
		return insertTime;
	}
	/**
	 * @param insertTime the insertTime to set
	 */
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
}
