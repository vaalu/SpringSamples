/**
 * 
 */
package org.training.spring.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.training.spring.hibernate.entities.manytomany.Employee;
import org.training.spring.hibernate.entities.manytomany.Project;
import org.training.spring.services.EmployeeService;

/**
 * @author mohanavelp
 *
 */
@RestController
public class AndroEmployeeRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AndroEmployeeRestController.class);
	
	@Autowired
	EmployeeService empService;
	
	@RequestMapping("/hello/{user}")
	public String hello(@PathVariable("user") String user) {
		LOGGER.debug("User tried to ping: ", user);
		return "Hello "+user+" - Andro services are up and running fine";
	}
	
	@RequestMapping(value= "/employee/{user}/{role}", method= RequestMethod.POST)
	public String addNew(@PathVariable("user") String user, @PathVariable("role") String role) {
		int empId = empService.addEmployee(user, role);
		return "Added "+user+" - as an employee with role: " + role + " employee id: " + empId;
	}
	
	@RequestMapping(path="/employee", method=RequestMethod.POST)
	public String addEmployee(@RequestBody Employee emp) {
		int empId = empService.addEmployee(emp);
		return "Employee "+emp.getName()+" - added with id " + empId;
	}
	
	@RequestMapping(path="/employee", method=RequestMethod.GET)
	@ResponseBody
	public List<Employee> fetchAllEmployees() {
		List<Employee> employees = empService.fetchAllEmployees();
		return employees;
	}
	

	@RequestMapping(path="/employee/{name}", method=RequestMethod.GET)
	@ResponseBody
	public List<Employee> fetchEmployeesByName(@PathVariable("name") String name) {
		List<Employee> employees = empService.fetchEmployeesByName(name);
		return employees;
	}
	
	@RequestMapping(path="/project", method=RequestMethod.POST)
	public String addProject(@RequestBody Project project) {
		int empId = empService.addProject(project);
		return "Project "+project.getName()+" - added with id " + empId;
	}
}
