/**
 * 
 */
package org.training.spring.hibernate.repos;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Repository;
import org.training.spring.hibernate.entities.Address;
import org.training.spring.hibernate.entities.Friend;
import org.training.spring.hibernate.entities.manytomany.Employee;

/**
 * @author mohanavelp
 *
 */
@Repository
@Transactional
public class SocialMediaRepositoryService {

	@PersistenceContext
	private EntityManager em;
	
	public long addFriend(Friend friend) {
		em.persist(friend);
		Set<Friend> relatedFriends = friend.getFriends();
		
		if (relatedFriends != null && !relatedFriends.isEmpty()) {
			for (Friend relatedFriend : relatedFriends) {
				relatedFriend.getFriends().add(friend);
				em.persist(relatedFriend);
			}
		}
		
		return friend.getId();
	}

	public long addAddress(Address address) {
		em.persist(address);
		
		Set<Friend> friends = address.getTenants();
		if (friends != null && !friends.isEmpty()) {
			for (Friend tenant : friends) {
				tenant.setAddress(address);
				addFriend(tenant);
			}
		}
		
		return address.getId();
	}

	public Address fetchAddress(long id) {
		return em.getReference(Address.class, id);
	}
	
	public long updateAddress(Address address, long addressId) {
		
		em.merge(address);
		Address existingAddress = fetchAddress(addressId);
		try {
			BeanUtils.copyProperties(existingAddress, address);
			existingAddress.setId(addressId);
			em.merge(existingAddress);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return address.getId();
	}
}
