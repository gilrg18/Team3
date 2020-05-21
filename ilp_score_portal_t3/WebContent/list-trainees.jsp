<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>

<head>
	<title>ILP Score Portal</title>
	<link type ="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>ILP Score Portal</h2>
		</div>
	</div>
	<div id = "container">
		<div id="content">
		
		         <!--  add a search box -->
            <form action="TraineeControllerServlet" method="GET">
                <input type="hidden" name="command" value="SEARCH" />            
                Search trainee ID: <input type="text" name="theSearchID" />
                <input type="submit" value="Search" class="search-trainee-button" />
                
              
                           
            </form>
            
            <form action="TraineeControllerServlet" method="GET">         
                Show low performance trainees
                <input  type="submit" value="Go" class="search-trainee-button" onclick="show()" />
                               
            </form>

             <form style="display:none;" id= "location" action="TraineeControllerServlet" method="GET">
             	  <input type="hidden" name="command" value="LIST_LOW" />  
             	Search Batch: <select name="theSearchBatch" >
				    <option value="Guadalajara">GDL</option>
				    <option value="Queretaro">QRO</option>
				    <option value="MexicoCity">CDMX</option>
  				</select>
                
                <input type="submit" value="Search" class="search-trainee-button" />
              </form>
            
            <form action="TraineeControllerServlet" method="GET">         
                Show high performance trainees
                <input  type="submit" value="Go" class="search-trainee-button" onclick="show2()" />
                               
            </form>

             <form style="display:none;" id= "location2" action="TraineeControllerServlet" method="GET">
             	  <input type="hidden" name="command" value="LIST_HIGH" />  
             	Search Batch: <select name="theSearchBatch" >
				    <option value="Guadalajara">GDL</option>
				    <option value="Queretaro">QRO</option>
				    <option value="MexicoCity">CDMX</option>
  				</select>
                
                <input type="submit" value="Search" class="search-trainee-button" />
              </form>

			<table>
				<tr>
					<th>ID</th>
					<th>Batch</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Score</th>
					<th>Action</th>
				</tr>

				<c:forEach var="tempTrainee" items="${TRAINEE_LIST}">
				<!-- set up a link for each student -->
				<c:url var="tempLink" value="TraineeControllerServlet">
					<c:param name = "command" value="LOAD" />
					<c:param name = "traineeId" value="${tempTrainee.getTrainee_id()}" />
				</c:url>
				<!--  set up a link to delete a student -->
			
		  		<tr>
		  			<td> ${tempTrainee.getTrainee_id()} </td>
		  			<td> ${tempTrainee.getBatch_id()} </td>
					<td> ${tempTrainee.getFirstName()} </td>
					<td> ${tempTrainee.getLastName()} </td>
					<td> ${tempTrainee.getScore()} </td>
					<td> <a href="${tempLink}"
					 onclick="if(!(confirm('Update ID: ${tempTrainee.getTrainee_id()} score?'))) return false">Update Score</a>
						
					</td>		
				</tr>
				</c:forEach> 
			</table>
			           
		</div>
	</div>

<script>
function show() {
	  var x = document.getElementById("location");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	    event.preventDefault();
	  } else{
		  x.style.display = "none";  
		  event.preventDefault();
	  }
}

function show2() {
	  var x = document.getElementById("location2");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	    event.preventDefault();
	  } else{
		  x.style.display = "none";  
		  event.preventDefault();
	  }
}
</script>


</body>

</html>