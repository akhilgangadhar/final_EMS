
DROP TABLE user_master;
DROP TABLE Department;
DROP TABLE Employee cascade constraints;
DROP SEQUENCE user_master_id_sequence;
DROP SEQUENCE emp_id_sequence;

create table User_Master(
UserName VARCHAR2(15), 
UserPassword VARCHAR2(50), 
UserType VARCHAR2(10),
userID VARCHAR2(6) PRIMARY KEY
);

create sequence user_master_id_sequence
start with 100
increment by 1
nocache;

insert into User_Master values('Soumya','Soumya123','admin',user_master_id_sequence.NEXTVAL);
insert into User_Master values('Nilendu','Nilendu123','user',user_master_id_sequence.NEXTVAL);
insert into User_Master values('Akhil','Akhil123','admin',user_master_id_sequence.NEXTVAL);
insert into User_Master values('Rituraj','Rituraj123','user',user_master_id_sequence.NEXTVAL);
insert into User_Master values('Ankit','Rituraj123','user',user_master_id_sequence.NEXTVAL);
insert into User_Master values('Pranay','Pranay123','user',user_master_id_sequence.NEXTVAL);
 
create table Department(Dept_ID int PRIMARY KEY,Dept_Name VARCHAR2(50));
 
create table Employee(
Emp_ID int PRIMARY KEY, 
Emp_First_Name VARCHAR2(25), 
Emp_Last_Name VARCHAR2(25), 
Emp_Date_of_Birth DATE, 
Emp_Date_of_Joining DATE, 
Emp_Dept_ID int, 
Emp_Grade VARCHAR2(2), 
Emp_Designation VARCHAR2(50), 
Emp_Basic int, 
Emp_Gender VARCHAR2(11), 
Emp_Marital_Status VARCHAR2(11), 
Emp_Home_Address VARCHAR2(100), 
Emp_Contact_Num VARCHAR2(15)
);

create sequence emp_id_sequence
start with 100
increment by 1
nocache;

INSERT INTO employee values(emp_id_sequence.NEXTVAL,'Soumya','Jena','05-JUNE-1996','20-SEP-2017',102,'M5','Manager',90000,'F','U','Bangalore','9491043672');
INSERT INTO employee values(emp_id_sequence.NEXTVAL,'Nilendu','Das','28-AUG-1996','20-SEP-2017',100,'M2','Analyst',24000,'F','U','Bangalore','8714333996');
INSERT INTO employee values(emp_id_sequence.NEXTVAL,'Akhil','Gangadhar','12-JAN-1996','20-SEP-2017',101,'M4','Consultant',55000,'F','U','Kolkata','8136912198');
INSERT INTO employee values(emp_id_sequence.NEXTVAL,'Rituraj','Das','12-MAY-1996','25-SEP-2017',103,'M2','Analyst',24000,'F','U','Hyderabad','7736737369');
INSERT INTO employee values(emp_id_sequence.NEXTVAL,'Pranay','Bikash','21-SEP-1996','20-OCT-2017',100,'M2','Analyst',24000,'M','U','Mumbai','9567037017');
INSERT INTO employee values(emp_id_sequence.NEXTVAL,'Ankit','Bazarbaruah','10-MAY-1996','20-SEP-2017',102,'M2','Analyst',24000,'M','U','Bangalore','9491043672');

INSERT INTO Department VALUES(100,'IT');
INSERT INTO Department VALUES(101,'Sales');
INSERT INTO Department VALUES(102,'LD');
INSERT INTO Department VALUES(103,'HR');
INSERT INTO Department VALUES(104,'FS');


desc user_master;
desc employee;
desc department;