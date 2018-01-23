/**
 * 
 */
package org.training.spring.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.training.spring.hibernate.entities.Address;
import org.training.spring.hibernate.entities.Friend;
import org.training.spring.services.EmployeeService;
import org.training.spring.services.SocialMediaService;

/**
 * @author mohanavelp
 *
 */
@RestController
public class AndroSocialRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AndroSocialRestController.class);
	
	@Autowired
	EmployeeService empService;
	
	@Autowired
	SocialMediaService socialMedia;
	
	@RequestMapping("/social/{user}")
	public String hello(@PathVariable("user") String user) {
		LOGGER.debug("User tried to ping: ", user);
		return "Hello "+user+" - Andro services are up and running fine";
	}
	
	@RequestMapping(path="/social/address", method=RequestMethod.POST)
	public String addAddress(@RequestBody Address address) {
		socialMedia.addAddress(address);
		return "Address "+address.getCity() +" - added with id " + address.getId();
	}
	
	@RequestMapping(path="/social/address/{addressId}", method=RequestMethod.PUT)
	public String updateAddress(@RequestBody Address address, @PathVariable("addressId") long addressId) {
		
		address.setId(addressId);
		
		socialMedia.updateAddress(address, addressId);
		return "Address "+address.getCity() +" - updated with id " + address.getId();
	}
	
	@RequestMapping(path="/social/friend", method=RequestMethod.POST)
	public String addFriend(@RequestBody Friend friend) {
		socialMedia.addFriend(friend);
		return "User "+friend.getName()+" - added as a friend " + friend.getId();
	}
	
}
