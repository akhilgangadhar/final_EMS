package com.cg.ems.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.cg.ems.bean.Employee;
import com.cg.ems.exception.EMSException;
import com.cg.ems.service.EmployeeServiceImpl;
import com.cg.ems.service.IEmployeeService;


public class UserConsole {

	// private DateTimeFormatter dtFormat;
	private Scanner scanner;
	private String user;
	private IEmployeeService employeeService = null;
	private Logger logger = Logger.getRootLogger();
	private String userId;
	public static boolean flag=true;

	public UserConsole(String user, String userId) {
		this.user = user;
		this.userId = userId;
	}

	public void start() throws EMSException {
		PropertyConfigurator.configure("resources//log4j.properties");
		scanner = new Scanner(System.in);
		
		System.out.println("Welcome " + user);
		System.out.println("----------------");

		String choice = null;
		while (true) {
			System.out
					.println("1.Search Employee Details\n2.Exit\n");
			System.out.println("----------------------");
			System.out.print("Enter your choice :");
			choice = scanner.next();
			System.out.println("-----------------------");

			switch (choice) {

			case "1":
				searchEmployee();
			case "2":
				System.out.print("Thank you!!");
				System.exit(0);
				break;
			default:
				System.out.println("Please enter a valid option[1-2]");
			}
		}
	}

	

	private void searchEmployee() {
		while (true) {
			System.out.println("[1].View Employee by Id");
			System.out.println("[2].View Employee by First Name");
			System.out.println("[3].View Employee by Last Name");
			System.out.println("[4].View Employee by Department Name");
			System.out.println("[5].View Employee by Grade");
			System.out.println("[6].View Employee by Marital Status");
			System.out.println("[7].Exit");
			System.out.println("----------------------------------");
			System.out.println("Enter your choice :");

			try {
				String value;
				value = scanner.next();

				switch (value) {

				case "1":
					searchById();
					break;
				case "2":
					searchByFName();
					break;

				case "3":
					searchByLName();
					break;
					
				case "4":
					searchByDeptName();
					break;
					
				case "5":
					searchByGrade();
					break;

				case "6":
					searchByMaritalStatus();
					break;
				case "7":
					System.out.print("Thank you!!");
					System.exit(0);
					break;
				default:
					System.out.println("Enter a valid option[1-7]");
				}
			} catch (InputMismatchException e) {
				scanner.nextLine();
				System.err.println("Please enter a numeric value, try again");
			}
		}
	}

	private void searchByMaritalStatus(){
		String maritalStatus = null;
		List<Employee> emplList = new ArrayList<Employee>();
		scanner.nextLine();
		System.out.println("Enter Marital Status(U/M):");
		maritalStatus = scanner.nextLine();
		maritalStatus = maritalStatus.replace(' ','|');
		emplList = getEmployeeByMaritalStatus(maritalStatus);

		if (emplList != null) {
			Iterator<Employee> i = emplList.iterator();
			while (i.hasNext()) {
				displayEmployee(i.next());
			}
		} else {
			System.err
					.println("There are no employee details associated with given marital status "
							+ maritalStatus);
		}
	}

	private void searchByGrade() {
		String empGrade = null;
		List<Employee> emplList = new ArrayList<Employee>();
		scanner.nextLine();
		System.out.println("Enter Employee Grade:");
		empGrade = scanner.nextLine();
		empGrade = empGrade.replace(' ','|');
		emplList = getEmployeeByGrade(empGrade);

		if (emplList != null) {
			Iterator<Employee> i = emplList.iterator();
			while (i.hasNext()) {
				displayEmployee(i.next());
			}
		} else {
			System.err
					.println("There are no employee details associated with given employee grade "
							+ empGrade);
		}
	}

	private void searchByDeptName() {
		String deptName = null;
		List<Employee> emplList = new ArrayList<Employee>();
		scanner.nextLine();
		System.out.println("Enter Department Name:");
		deptName = scanner.nextLine();
		deptName = deptName.replace(' ','|');
		System.out.println(deptName);
		emplList = getEmployeeByDeptName(deptName);

		if (emplList != null) {
			Iterator<Employee> i = emplList.iterator();
			while (i.hasNext()) {
				displayEmployee(i.next());
			}
		} else {
			System.err.println("There are no employee details associated with given department name "
							+ deptName);
		}
	}

	private void searchByLName() {
		String empLName = null;
		List<Employee> emplList = new ArrayList<Employee>();

		System.out.println("Enter Employee last name:");
		empLName = scanner.next();
		emplList = getEmployeeByLName(empLName);

		if (emplList != null) {
			Iterator<Employee> i = emplList.iterator();
			if(emplList.get(0).getEmpId()!=9999999)
			while (i.hasNext()) {
				displayEmployee(i.next());
			}
			else{
				//flag=false;
				System.err.println("Invalid Last Name.");
			}
		} else {
			System.err.println("There are no employee details associated with given employee last name "
							+ empLName);
		}
	}

	private void searchByFName() {
		String empFName = null;
		List<Employee> emplList = new ArrayList<Employee>();

		System.out.println("Enter Employee first name:");
		empFName = scanner.next();
		emplList = getEmployeeByFName(empFName);

		if (emplList != null) {
			Iterator<Employee> i = emplList.iterator();
			if(emplList.get(0).getEmpId()!=9999999){
			while (i.hasNext()) {
				displayEmployee(i.next());
			}
			}
			else{
				//flag=false;
				System.err.println("Invalid First Name.");
			}
		} else {
			System.err.println("There are no employee details associated with given employee first name "
							+ empFName);
		}

	}

	private void searchById() {
		String empId = null;
		List<Employee> emplList = new ArrayList<Employee>();

		System.out.println("Enter Emp Id:");
		empId = scanner.next();
		emplList = getEmployeeById(empId);

		if (emplList != null) {
			Iterator<Employee> i = emplList.iterator();
			if(emplList.get(0).getEmpId()!=9999999)
			while (i.hasNext()) {
				displayEmployee(i.next());
			}
			else{
				//flag=false;
				System.err.println("Invalid employee ID.");
			}
				
		} else {
			
		System.err.println("There are no employee details associated with given employee id "
							+ empId);
		}
	}

	public List<Employee> getEmployeeById(String employeeId) {
		List<Employee> employee = null;
		employeeService = new EmployeeServiceImpl();

		try {
			employee = employeeService.viewEmployeeById(employeeId);
			
		} catch (EMSException employeeException) {
			logger.error("exception occured ", employeeException);
			System.out.println("ERROR : " + employeeException.getMessage());
		}

		/* employeeService = null; */
		return employee;
	}

	public List<Employee> getEmployeeByFName(String fName) {
		List<Employee> employeeBean = null;
		employeeService = new EmployeeServiceImpl();

		try {
			employeeBean = employeeService.viewEmployeeByFname(fName);
		} catch (EMSException employeeException) {
			logger.error("exception occured ", employeeException);
			System.out.println("ERROR : " + employeeException.getMessage());
		}

		employeeService = null;
		return employeeBean;
	}

	public List<Employee> getEmployeeByLName(String lName) {
		List<Employee> employeeBean = null;
		employeeService = new EmployeeServiceImpl();

		try {
			employeeBean = employeeService.viewEmployeeByLname(lName);
		} catch (EMSException employeeException) {
			logger.error("exception occured ", employeeException);
			System.out.println("ERROR : " + employeeException.getMessage());
		}

		employeeService = null;
		return employeeBean;
	}

	public List<Employee> getEmployeeByDeptName(String deptName) {
		List<Employee> employeeBean = null;
		employeeService = new EmployeeServiceImpl();

		try {
			employeeBean = employeeService.viewEmployeeByDeptName(deptName);
		} catch (EMSException employeeException) {
			logger.error("exception occured ", employeeException);
			System.out.println("ERROR : " + employeeException.getMessage());
		}

		employeeService = null;
		return employeeBean;
	}

	public List<Employee> getEmployeeByGrade(String empGrade) {
		List<Employee> employeeBean = null;
		employeeService = new EmployeeServiceImpl();

		try {
			employeeBean = employeeService.viewEmployeeByGrade(empGrade);
		} catch (EMSException employeeException) {
			logger.error("exception occured ", employeeException);
			System.out.println("ERROR : " + employeeException.getMessage());
		}

		employeeService = null;
		return employeeBean;
	}

	public List<Employee> getEmployeeByMaritalStatus(String maritalStatus) {
		List<Employee> employeeBean = null;
		employeeService = new EmployeeServiceImpl();

		try {
			employeeBean = employeeService
					.viewEmployeeByMaritalStatus(maritalStatus);
		} catch (EMSException employeeException) {
			logger.error("exception occured ", employeeException);
			System.out.println("ERROR : " + employeeException.getMessage());
		}

		employeeService = null;
		return employeeBean;
	}

	public void displayEmployee(Employee employee) {

	
		System.out.println("------------Employee Details---------- ");

		System.out.println("Employee Id : " + employee.getEmpId());
		System.out.println("First Name : " + employee.getFirstName());
		System.out.println("Last Name : " + employee.getLastName());
		System.out.println("Date of Birth : " + employee.getDateOfBirth());
		System.out.println("Date of Joining : " + employee.getDateOfJoining());
		System.out.println("Department Id : " + employee.getDeptId());
		System.out.println("Employee Grade : " + employee.getEmpGrade());
		System.out.println("Designation : " + employee.getDesignation());
		System.out.println("Basic Salary : " + employee.getBasicSalary());
		System.out.println("Gender : " + employee.getGender());
		System.out.println("Marital Status : " + employee.getMaritalStatus());
		System.out.println("Home Address : " + employee.getHomeAddress());
		System.out.println("Contact Number : " + employee.getContactNo());
		System.out.println("-------------------------------------");
		
		
	}
}
