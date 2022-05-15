<%@page import="com.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employees Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/employees.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Employees Management V10.1</h1>
<form id="formEmployee" name="formEmployee" method="post" action="employees.jsp">
 Employee Code: 
 <input id="employeeCode" name="employeeCode" type="text" 
 class="form-control form-control-sm">
 <br> Employee name: 
 <input id="employeeName" name="employeeName" type="text" 
 class="form-control form-control-sm">
 <br> employee Contact: 
 <input id="employeeContact" name="employeeContact" type="text" 
 class="form-control form-control-sm">
 <br> Employee Position: 
 <input id="employeePosition" name="employeePosition" type="text" 
 class="form-control form-control-sm">
  <br> Employee Email: 
 <input id="employeeEmail" name="employeeEmail" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidEmployeeIDSave" 
 name="hidEmployeeIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divEmployeesGrid">
 <%
 Employee employeeObj = new Employee(); 
 out.print(employeeObj.readEmployees()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>