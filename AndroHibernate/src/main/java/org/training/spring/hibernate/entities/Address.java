/**
 * 
 */
package org.training.spring.hibernate.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author mohanavelp
 *
 */
@Entity
@Table(name="ADDRESS", uniqueConstraints= {@UniqueConstraint(columnNames= {"ID"})})
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID", nullable=false, unique=true, length=11)
	private long id;
	
	@Column(name="ADDRESS_LINE_1", nullable=true, length=100)
	private String addressLine1;
	
	@Column(name="ADDRESS_LINE_2", nullable=true, length=100)
	private String addressLine2;
	
	@Column(name="CITY", nullable=false, length=100)
	private String city;
	
	@Column(name="STATE", nullable=false, length=100)
	private String state;
	
	@Column(name="COUNTRY", nullable=false, length=100)
	private String country;
	
	@Column(name="ADDRESS_TYPE", nullable=false, length=100)
	private String type;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="address")
	private Set<Friend> tenants = new HashSet<>();

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
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the tenants
	 */
	public Set<Friend> getTenants() {
		return tenants;
	}

	/**
	 * @param tenants the tenants to set
	 */
	public void setTenants(Set<Friend> tenants) {
		this.tenants = tenants;
	}
	
}
