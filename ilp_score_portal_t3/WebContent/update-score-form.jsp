<!DOCTYPE html>
<html>
<head>
	<title>Update Trainee Score</title>
	<link type="text/css" rel="stylesheet" href = "css/style.css">
	<link type="text/css" rel="stylesheet" href = "css/add-trainee-style.css">
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>ILP Score Portal</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Update Score</h3>
		<form action="TraineeControllerServlet" method="GET">
			<input type="hidden" name="command" value="UPDATE" />
			<input type="hidden" name="traineeId" value="${THE_TRAINEE.getTrainee_id()}" />
			<table>
				<tbody>
					<tr>
						<td><label>First name:</label></td>
						<td>${THE_TRAINEE.getFirstName()} </td>
					</tr>
					<tr >
						<td><label>Last name:</label></td>
						<td class="lastName" name="lastName">${THE_TRAINEE.getLastName()}</td>
					</tr>
					<tr>
						<td><label>New Score:</label></td>
						<td><input id ="tscore" type="text" name= "score" 
						           value="${THE_TRAINEE.getScore()}" /> </td>
					</tr>
					<tr>
						<td><label></label></td>
						<td><input type="submit" value= "Save" class ="save"  /> </td>
					</tr>
				</tbody>
			</table>
		</form>
		
		<div style="clear:both;"></div>
		
		<p>
			<a href="TraineeControllerServlet">Back to List</a>
		</p>
	</div>
	
	

</body>

</html>