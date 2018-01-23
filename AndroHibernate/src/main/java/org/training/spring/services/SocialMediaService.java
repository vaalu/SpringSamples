/**
 * 
 */
package org.training.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.training.spring.hibernate.entities.Address;
import org.training.spring.hibernate.entities.Friend;
import org.training.spring.hibernate.repos.SocialMediaRepositoryService;

/**
 * @author mohanavelp
 *
 */
@Component
public class SocialMediaService {

	@Autowired
	SocialMediaRepositoryService socialRepo;
	
	public long addFriend(Friend friend) {
		return socialRepo.addFriend(friend);
	}

	public long addAddress(Address address) {
		socialRepo.addAddress(address);
		return address.getId();
	}

	public long updateAddress(Address address, long addressId) {
		socialRepo.updateAddress(address, addressId);
		return address.getId();
	}
	
}
