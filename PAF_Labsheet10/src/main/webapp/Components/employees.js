$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateItemForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "EmployeessAPI", 
 type : type, 
 data : $("#formEmployee").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onEmployeeSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onEmployeeSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
$("#hidItemIDSave").val(""); 
$("#formItem")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidEmployeeIDSave").val($(this).data("itemid")); 
		 $("#employeeName").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#employeeContact").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#employeePosition").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#employeeEmail").val($(this).closest("tr").find('td:eq(3)').text()); 
		});




$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "EmployeesAPI", 
		 type : "DELETE", 
		 data : "employeeID=" + $(this).data("employeeid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onEmployeeDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
		
function onEmployeeDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}


// CLIENT-MODEL================================================================
function validateItemForm()
{
	// CODE
	if ($("#employeeId").val().trim() == "")
	{
	return "Insert employee ID.";
	}
	// NAME
	if ($("#employeeName").val().trim() == "")
	{
	return "Insert Employee Name.";
}


		

// DESCRIPTION------------------------
if ($("#employeePosition").val().trim() == ""){
	
	return "Insert Employee Position.";
}
	return true;
}