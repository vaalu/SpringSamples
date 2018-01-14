/**
 * 
 */
package org.training.spring.hibernate.repos;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.training.spring.hibernate.entities.manytomany.Employee;
import org.training.spring.hibernate.entities.manytomany.Project;

/**
 * @author mohanavelp
 *
 */
@Repository
@Transactional
public class EmployeeRepositoryService {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private EntityManagerFactory emFactory;
	
	public int addEmployee(Employee employee) {
		employee.setInsertTime(new Date());
		em.persist(employee);
		return employee.getId();
	}
	
	public int addProject(Project project) {
		project.setInsertTime(new Date());
		
		
		em.persist(project);
		return project.getId();
	}
	
	public List<Employee> fetchAllEmployees() {
		Query empQry = em.createQuery("FROM Employee");
		List<Employee> employees = empQry.getResultList();
		return employees;
	}
	
	public List<Employee> fetchEmployeesByName(String name) {
		
		CriteriaBuilder criteriaBuilder = emFactory.getCriteriaBuilder();
		
		CriteriaQuery<Employee> empCriteriaQry = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> empRoot = empCriteriaQry.from(Employee.class);
		
		empCriteriaQry.select(empRoot);
		empCriteriaQry.where(criteriaBuilder.equal(empRoot.get("name"),name));
		
		Query empQry = em.createQuery(empCriteriaQry);
		List<Employee> employees = empQry.getResultList();
		
		return employees;
	}
	
	
}
