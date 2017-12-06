package application;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Wrapper class for saving list of films to XML.
 * 
 * @author josephcourtley
 *
 */
@XmlRootElement(name = "employees")
public class EmployeeWrapper {
	
	private List<Employee> employees;
	
	@XmlElement(name = "employee")
	public List<Employee> getEmployees() {
		return employees;
	}
	
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
}