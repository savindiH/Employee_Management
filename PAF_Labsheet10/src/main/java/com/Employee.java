package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class Employee
{ //A common method to connect to the DB
		private Connection connect(){ 
			
						Connection con = null; 
						
						try{ 
								Class.forName("com.mysql.jdbc.Driver"); 
 
								//Provide the correct details: DBServer/DBName, username, password 
								con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", ""); 
						} 
						catch (Exception e) {
							e.printStackTrace();
							} 
						
						return con; 
			} 
		
		
		public String insertEmployee(String code, String name, String contact, String position, String email){ 
			
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						
						if (con == null) 
						{
							return "Error while connecting to the database for inserting."; 
							
						} 
						// create a prepared statement
						
						String query = " insert into employeess (`employeeID`,`employeeCode`,`employeeName`,`employeeContact`,`employeePosition`,`employeeEmail`)"+" values (?, ?, ?, ?, ?)"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, 0); 
						preparedStmt.setString(1, code); 
						preparedStmt.setString(2, name); 
						preparedStmt.setString(3, contact); 
						preparedStmt.setString(4, position); 
						preparedStmt.setString(5, email); 
						// execute the statement
 
						preparedStmt.execute(); 
						con.close(); 
						
						String newEmployees = readEmployees(); 
						output = "{\"status\":\"success\",\"data\":\""+newEmployees+"\"}"; 
					} 
					
					catch (Exception e) 
					{ 
						output = "{\"status\":\"error\", \"data\":\"Error while inserting the employee.\"}"; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
		
		
		
		public String readEmployees() 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for reading."; 
		 } 
		 // Prepare the html table to be displayed
		 output = "<table border=\"1\" class=\"table\"><tr><th>Employee Code</th>"
		 		+ "<th>Employee Name</th><th>Employee Contact</th>"
		 		+ "<th>Employee Position</th>"
		 		+ "<th>Update</th>"
		 		+ "<th>Remove</th></tr>"; 
		
		 String query = "select * from employees"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String employeeID = Integer.toString(rs.getInt("employeeID")); 
		 String employeeCode = rs.getString("employeeCode"); 
		 String employeeName = rs.getString("employeeName"); 
		 String employeeContact = rs.getString("employeeContact"); 
		 String employeePosition = rs.getString("employeePosition"); 
		 String employeeEmail = rs.getString("employeeEmail"); 
		 // Add into the html table
		 output += "<tr><td><input id='hidEmployeeIDUpdate' name='hidEmployeeIDUpdate' type='hidden' value='"+employeeID+"'>"+employeeName+"</td>"; 
		 output += "<td>" + employeeContact + "</td>"; 
		 output += "<td>" + employeePosition + "</td>"; 
		 output += "<td>" + employeeEmail + "</td>"; 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' "
				 + "class='btnUpdate btn btn-secondary' data-employeeid='" + employeeID + "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' "
				 + "class='btnRemove btn btn-danger' data-itemid='" + employeeID + "'></td></tr>"; 
		 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 
		catch (Exception e) 
		 { 
		 output = "Error while reading the items."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}

			
			
			public String updateEmployee(String Code, String name, String contact, String position, String email){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								return "Error while connecting to the database for updating.";
								} 
							// create a prepared statement
							String query = "UPDATE employees SET employeeCode=?, employeeEmail=?,employeeName=?,employeeContact=?,employeePosition=? WHERE employeeID=?"; 
							PreparedStatement preparedStmt = con.prepareStatement(query); 
							// binding values
							preparedStmt.setString(1, email); 
							preparedStmt.setString(2, name); 
							preparedStmt.setString(3, position); 
							preparedStmt.setString(4, contact);
							preparedStmt.setString(6, Code);
							preparedStmt.setInt(5, Integer.parseInt(Code)); 
							// execute the statement
							preparedStmt.execute(); 
							con.close(); 
							String newEmployees = readEmployees(); 
							output = "{\"status\":\"success\",\"data\":\""+newEmployees+"\"}"; 

					} 
					
					catch (Exception e){ 
						
						output = "{\"status\":\"error\",\"data\":\"Error while updating the employee.\"}"; 

						System.err.println(e.getMessage()); 
						
					} 
					
					return output; 
			} 
			
			
			public String deleteEmployee(String employeeID){ 
				
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						String query = "delete from items where employeeID=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(employeeID)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						String newEmployees = readEmployees(); 
						 output = "{\"status\":\"success\",\"data\":\""+newEmployees+"\"}"; 

					} 
					
					catch (Exception e){ 
						output = "{\"status\":\"error\",\"data\":\"Error while deleting the employee.\"}";
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
			
			
			
			
} 
