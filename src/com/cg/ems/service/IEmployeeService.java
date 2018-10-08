package com.cg.ems.service;

import java.util.List;

import com.cg.ems.bean.Employee;
import com.cg.ems.exception.EMSException;

public interface IEmployeeService {

	public String addEmployee(Employee employee) throws EMSException;

	public List<Employee> viewEmployeeById(String empId) throws EMSException;

	public List<Employee> viewEmployeeByFname(String firstName) throws EMSException;

	public List<Employee> viewEmployeeByLname(String lastName) throws EMSException;

	public List<Employee> viewEmployeeByDeptName(String deptName)
			throws EMSException;

	public List<Employee> viewEmployeeByGrade(String empGrade)
			throws EMSException;

	public List<Employee> viewEmployeeByMaritalStatus(String maritalStatus)
			throws EMSException;

	public Employee updateEmployeeById(Employee employee) throws EMSException;

	public List<Employee> retriveAllDetails() throws EMSException;

	public List<Employee> viewEmployeeByWildCard(String searchString) throws EMSException;

	public Employee viewEmployeeById1(String employeeId) throws EMSException;
}
