package com.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;
import com.promount.model.Employee;

public class TableCreationCRUD {
	private String jdbcURL = "jdbc:mysql://localhost:3306/adv_java";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";
    
    private static final String INSERT_DEPARTMENT_SQL = "INSERT INTO department" + "(department_name) VALUES " +
            " (?);";
    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employees" + "(fullName, email, phone, department_id, profileUrl, password) VALUES " +
            " (?, ?, ?, ?, ?, ?);";   
    
    private static final String FIND_BY_ID_DEPT_SQL = "SELECT id FROM department WHERE department_name=?";
    
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
           
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
	
	private static final String createTableEmployeeSQL = "create table employees (\r\n" + "  id  int(3) not null auto_increment primary key,\r\n" +
			"  fullName varchar(40),\r\n" + "  email varchar(30),\r\n" + "  phone decimal,\r\n" +
			"  department_id int \r\n, "  +  "  FOREIGN KEY (department_id) REFERENCES department(id), \r\n" +
			"  profileUrl varchar(100), \r\n" +
			"  password varchar(20)\r\n" + ");";
	
	private static final String createTableDepartmentSQL = "create table department (\r\n" + " id int(3) not null auto_increment primary key, \r\n"		
			+ " department_name varchar(20)\r\n" + ");";

	public void createTable() {
		try (Connection connection = getConnection()){
			PreparedStatement departmentSQL = connection.prepareStatement(createTableDepartmentSQL);
			PreparedStatement employeeSQL = connection.prepareStatement(createTableEmployeeSQL);
		
				departmentSQL.execute();
				employeeSQL.execute();
			
			
		 //connection.prepareStatement(createTableDepartmentSQL);
	//connection.prepareStatement(createTableEmployeeSQL);
		}catch(SQLException e){
			 printSQLException(e);
		}
		
	}
	
	public void insertData(Employee emp) {
		int deptId;
		ResultSet rs; 
		try (Connection connection = getConnection()) {
			PreparedStatement employeeStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL);
			PreparedStatement findDeptIdStatement = connection.prepareStatement(FIND_BY_ID_DEPT_SQL);
			
			findDeptIdStatement.setString(1, emp.getTech().toLowerCase());
			rs = findDeptIdStatement.executeQuery();
//			if(rs.next()) {
//                deptId = rs.getInt("id");
//            }else {
			
			if(!rs.next()) {
            	PreparedStatement departmentStatement = connection.prepareStatement(INSERT_DEPARTMENT_SQL);
//				departmentStatement.setInt(1, deptId);
				departmentStatement.setString(1, emp.getTech());
				departmentStatement.executeUpdate();
			}
				rs = findDeptIdStatement.executeQuery();
				
				employeeStatement.setString(1, emp.getFullName());
				employeeStatement.setString(2, emp.getUserName());
				employeeStatement.setLong(3, emp.getPhone());
				if(rs.next()) {
					deptId = rs.getInt("id");
					employeeStatement.setInt(4, deptId);
				}
				employeeStatement.setString(5, emp.getProfilePhotoUrl());
				employeeStatement.setString(6, emp.getPassword());
				employeeStatement.executeUpdate();
				/*
				 * ResultSet generatedKeys = departmentStatement.getGeneratedKeys(); if
				 * (generatedKeys.next()) { deptId = generatedKeys.getInt(1); }
				 */
				
//				employeeStatement.setInt(1, empId);
				
//		}
	
			
        } catch (SQLException e) {
            printSQLException(e);
        }
	}
	
	private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }     
	}
	
	
	
}