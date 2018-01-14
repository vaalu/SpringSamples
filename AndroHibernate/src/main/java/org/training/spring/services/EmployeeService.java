/**
 * 
 */
package org.training.spring.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.training.spring.hibernate.entities.manytomany.Employee;
import org.training.spring.hibernate.entities.manytomany.Project;
import org.training.spring.hibernate.repos.EmployeeRepositoryService;

/**
 * @author mohanavelp
 *
 */
@Component
public class EmployeeService {

	@Autowired
	EmployeeRepositoryService empRepo;
	
	public int addEmployee(String name, String role) {
		
		Employee emp = new Employee();
		emp.setName(name);
		emp.setRole(role);
		emp.setInsertTime(new Date());
		
		return empRepo.addEmployee(emp);
	}
	
	public int addEmployee(Employee emp) {
		return empRepo.addEmployee(emp);
	}

	public int addProject(Project project) {
		return empRepo.addProject(project);
	}
	
	public List<Employee> fetchAllEmployees() {
		return empRepo.fetchAllEmployees();
	}
	
	public List<Employee> fetchEmployeesByName(String name) {
		return empRepo.fetchEmployeesByName(name);
	}
	
}
