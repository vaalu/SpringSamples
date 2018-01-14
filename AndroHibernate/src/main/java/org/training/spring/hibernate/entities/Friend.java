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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author mohanavelp
 *
 */
@Entity
@Table(name="SOCIAL_FRIEND", uniqueConstraints= {@UniqueConstraint(columnNames= {"SOCIAL_FRIEND_ID"})})
public class Friend {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SOCIAL_FRIEND_ID", nullable=false, unique=true, length=11)
	private long id;
	
	@Column(name="NAME", nullable=false, length=100)
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "id", insertable=false, updatable=false)
	private Set<Friend> friends = new HashSet<>();
	
	
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

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the friends
	 */
	public Set<Friend> getFriends() {
		return friends;
	}

	/**
	 * @param friends the friends to set
	 */
	public void setFriends(Set<Friend> friends) {
		this.friends = friends;
	}
}
