package com.cg.ems.ui;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.ems.bean.Employee;
import com.cg.ems.exception.EMSException;
import com.cg.ems.service.EmployeeServiceImpl;
import com.cg.ems.service.IEmployeeService;


public class AdminConsole {

	// private DateTimeFormatter dtFormat;
	private Scanner scanner;
	private String user;
	private IEmployeeService employeeService = null;
	private EmployeeServiceImpl employeeServiceImpl = null;
	private Logger logger = Logger.getRootLogger();

	public AdminConsole(String user) {
		this.user = user;
	}

	public void start() throws ParseException, EMSException {

		PropertyConfigurator.configure("resources//log4j.properties");
		scanner = new Scanner(System.in);
		// dtFormat = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

		System.out.println("Welcome " + user);
		System.out.println("---------------");

		String choice = null;
		while (true) {
			System.out
					.println("1.Add Employee Details\n2.Modify Employee Details\n3."
							+ "Display all Employees\n4.Exit");
			System.out.println("--------------------------");
			System.out.print("Enter your choice:");
			choice = scanner.next();

			switch (choice) {
			case "1":
				addEmployee();
				break;
			case "2":
				modifyEmployee();
				break;
			case "3":
				viewAllEmployees();
				break;
			case "4":
				System.out.print("Thank you!!");
				System.exit(0);
				break;
			default:
				System.out.println("\nPlease enter a valid option[1-4]\n");
			}
		}
	}

	public void addEmployee() {
		Employee employee = null;
		String empId = null;
		while (employee == null) {
			try {
				employee = populateEmployee();
			} catch (ParseException e) {
				System.err.println(e.getMessage());
			}
		}

		try {
			employeeService = new EmployeeServiceImpl();
			empId = employeeService.addEmployee(employee);

			System.out
					.println("Employee details have been successfully registered! ");
			System.out.println("Employee ID is: " + empId);

		} catch (EMSException employeeException) {
			logger.error("Exception occured", employeeException);
			System.out.println("ERROR : " + employeeException.getMessage());
		} finally {
			empId = null;
			employeeService = null;
			employee = null;
		}
	}



	private void viewAllEmployees() {
		employeeService = new EmployeeServiceImpl();
		try {
			List<Employee> employeeList = new ArrayList<Employee>();
			employeeList = employeeService.retriveAllDetails();

			if (employeeList != null) {
				Iterator<Employee> i = employeeList.iterator();
				while (i.hasNext()) {

					displayEmployee(i.next());
				}
			} else {
				System.out.println("No Employees in the system.");
			}

		}

		catch (EMSException e) {

			System.out.println("Error  :" + e.getMessage());
		}
	}

	private void modifyEmployee() throws EMSException {
		employeeService = new EmployeeServiceImpl();
		Employee employee = null;
		System.out.println("Enter employee id:");
		String empId = scanner.next();

		employee = getEmployeeById(empId);

		if (employee != null) {

			System.out.println("First Name  :" + employee.getFirstName());
			employee.setFirstName(updateAction(employee.getFirstName()));

			System.out.println("Last Name  :" + employee.getLastName());
			employee.setLastName(updateAction(employee.getLastName()));

			System.out.println("Department Id  :" + employee.getDeptId());
			employee.setDeptId(updateActionInt(employee.getDeptId()));

			System.out.println("Employee Grade  :" + employee.getEmpGrade());
			employee.setEmpGrade(updateAction(employee.getEmpGrade()));

			System.out.println("Designation  :" + employee.getDesignation());
			employee.setDesignation(updateAction(employee.getDesignation()));

			System.out.println("Basic Salary  :" + employee.getBasicSalary());
			employee.setBasicSalary(updateActionDouble(employee
					.getBasicSalary()));

			System.out.println("Marital Status  :"
					+ employee.getMaritalStatus());
			employee.setMaritalStatus(updateAction(employee.getMaritalStatus()));

			System.out.println("Home Address  :" + employee.getHomeAddress());
			employee.setHomeAddress(updateAction(employee.getHomeAddress()));

			System.out.println("Contact Number  :" + employee.getContactNo());
			employee.setContactNo(updateAction(employee.getContactNo()));

			Employee emp = employeeService.updateEmployeeById(employee);
			System.out.println("Updated Employee Details");
			System.out.println("-----------------------------------------");
			displayEmployee(emp);

		} else {
			System.err
					.println("There are no employee details associated with employee id "
							+ empId);
		}
	}

	public Employee populateEmployee() throws ParseException {

		// Reading and setting the values for the Employee

		Employee employee = new Employee();

		System.out.println("\n Enter Details");
		System.out.println("Enter first name: ");
		employee.setFirstName(scanner.next());

		System.out.println("Enter last name: ");
		employee.setLastName(scanner.next());

		System.out.println("Enter Date of birth(dd-MM-YYYY): ");
		String dateString = scanner.next();
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		employee.setDateOfBirth(date);

		System.out.println("Enter Date of joining(dd-MM-YYYY): ");
		String dateString1 = scanner.next();
		DateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy");
		Date date1 = new Date();
		try {
			date1 = formatter1.parse(dateString1);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		employee.setDateOfJoining(date1);

		System.out.println("Enter department id: ");
		employee.setDeptId(scanner.nextInt());

		System.out.println("Enter Employee grade ");
		employee.setEmpGrade(scanner.next());

		scanner.nextLine();
		System.out.println("Enter designation: ");
		employee.setDesignation(scanner.nextLine());

		System.out.println("Enter Basic salary: ");
		employee.setBasicSalary(scanner.nextDouble());

		System.out.println("Enter gender(M/F): ");
		employee.setGender(scanner.next());

		System.out.println("Enter Marital Status(M/U): ");
		employee.setMaritalStatus(scanner.next());

		scanner.nextLine();
		System.out.println("Enter Home Address: ");
		employee.setHomeAddress(scanner.nextLine());

		System.out.println("Enter contact Number: ");
		employee.setContactNo(scanner.next());

		employeeServiceImpl = new EmployeeServiceImpl();

		try {
			employeeServiceImpl.validateEmployee(employee);
			return employee;
		} catch (EMSException employeeException) {
			logger.error("exception occured", employeeException);
			System.err.println("Invalid data:");
			System.err.println(employeeException.getMessage()
					+ " \n Try again..");
			//System.exit(0);

		}
		return employee;
	}

	public void displayEmployee(Employee employee) {

		System.out.println("Employee Id  :" + employee.getEmpId());
		System.out.println("First Name  :" + employee.getFirstName());
		System.out.println("Last Name  :" + employee.getLastName());
		System.out.println("Date of Birth :" + employee.getDateOfBirth());
		System.out.println("Date of Joining  :" + employee.getDateOfJoining());
		System.out.println("Department Id  :" + employee.getDeptId());
		System.out.println("Employee Grade  :" + employee.getEmpGrade());
		System.out.println("Designation  :" + employee.getDesignation());
		System.out.println("Basic Salary  :" + employee.getBasicSalary());
		System.out.println("Gender  :" + employee.getGender());
		System.out.println("Marital Status  :" + employee.getMaritalStatus());
		System.out.println("Home Address  :" + employee.getHomeAddress());
		System.out.println("Contact Number  :" + employee.getContactNo());
		System.out.println("-----------------------------------------");
	}

	public String updateAction(String initValue) {

		System.out.println("Is update required?(Y/N)");
		String option = scanner.next();
		String updatedValue = null;
		switch (option) {
		case "Y":
			System.out.println("Enter new value");
			updatedValue = scanner.next();
			break;
		case "N":
			updatedValue = initValue;
			break;
		default:
			System.out.println("Enter a valid option(Y/N)");
			updatedValue = updateAction(initValue);
		}
		return updatedValue;

	}

	public int updateActionInt(int initValue) {
		System.out.println("Is update required?(Y/N)");
		String option = scanner.next();
		int updatedValue = 0;
		switch (option) {
		case "Y":
			System.out.println("Enter new value");
			updatedValue = scanner.nextInt();
			break;
		case "N":
			updatedValue = initValue;
			break;
		default:
			System.out.println("Enter a valid option");
		}
		return updatedValue;
	}

	public double updateActionDouble(double initValue) {
		System.out.println("Is update required?(Y/N)");
		String option = scanner.next();
		double updatedValue = 0;
		switch (option){
		case "Y":
			System.out.println("Enter new value");
			updatedValue = scanner.nextDouble();
			break;
		case "N":
			updatedValue = initValue;
			break;
		default:
			System.out.println("Enter a valid option");
		}
		return updatedValue;
	}

	public Employee getEmployeeById(String employeeId){
		Employee employee = null;
		employeeService = new EmployeeServiceImpl();
		try {
			employee = employeeService.viewEmployeeById1(employeeId);
		} catch (EMSException employeeException) {
			logger.error("exception occured ", employeeException);
			System.out.println("ERROR : " + employeeException.getMessage());
		}
		return employee;
	}
}
