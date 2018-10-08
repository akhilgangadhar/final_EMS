package com.cg.ems.dao;

public interface IQueryMapper {

	public static final String GET_USER = "SELECT * FROM User_Master WHERE username=?";

	public static final String ADD_EMPLOYEE = "INSERT INTO Employee VALUES(emp_id_sequence.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String RETRIEVE_ALL_DETAILS = "SELECT * FROM Employee";
	public static final String SELECT_BY_ID = "SELECT * FROM Employee WHERE emp_id LIKE ? ";
	public static final String SELECT_BY_FNAME = "SELECT * FROM Employee WHERE emp_first_name LIKE ? ";
	public static final String SELECT_BY_LNAME = "SELECT * FROM Employee WHERE emp_last_name LIKE ? ";
	public static final String SELECT_BY_DEPT_NAME = "SELECT * FROM Employee WHERE Emp_Dept_ID IN (SELECT Dept_ID FROM Department WHERE regexp_like (Dept_NAME,?))";
	public static final String SELECT_BY_GRADE = "SELECT * FROM Employee WHERE regexp_like (Emp_Grade,?)";
	public static final String SELECT_BY_MARITAL_STATUS = "SELECT * FROM Employee WHERE regexp_like (Emp_Marital_Status,?)";

	public static final String UPDATE_EMPLOYEE_BY_ID = "UPDATE Employee SET Emp_First_Name=?, Emp_Last_Name=?,Emp_Dept_ID=?,Emp_Grade=?,Emp_Designation=?,Emp_Basic=?,Emp_Marital_Status=?,Emp_Home_Address=?,Emp_Contact_Num=? WHERE Emp_ID=?";

	public static final String ADD_USER_DETAILS = "INSERT INTO USER_MASTER VALUES (?,?,'user',emp_id_sequence.CURRVAL)";

	public static final String GET_SEQUENCE_ID = "SELECT emp_id_sequence.CURRVAL FROM dual";

	public static final String SELECT_BY_SEARCH_STRING = "SELECT * FROM Employee WHERE emp_id LIKE '%?%' ";
}
