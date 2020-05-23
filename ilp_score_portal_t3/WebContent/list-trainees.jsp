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
			<h2> ILP Score Portal</h2>
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
                <input  type="submit" value="Go" class="search-trainee-button" onclick="showLocation()" />
                               
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
                <input  type="submit" value="Go" class="search-trainee-button" onclick="showLocation2()" />
                               
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
				<!-- set up a link for each trainee -->
				<c:url var="tempLink" value="TraineeControllerServlet">
					<c:param name = "command" value="LOAD" />
					<c:param name = "traineeId" value="${tempTrainee.getTrainee_id()}" />
				</c:url>
				<!--  set up a link to delete a trainee -->
			
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
			
			
			<!-- Handle "First" and "Prev" links -->
			<div id="pagination">
			<c:choose>
				<c:when test="${currentPage == 1}">
					<a href="#" class="disabled">First</a>
					<a href="#" class="disabled">Prev</a>
				</c:when>
				<c:otherwise>
					<c:url var="firstPageLink" value="/TraineeControllerServlet">
						<c:param name="pageNumber" value="1" />
					</c:url>		
					<a href="${firstPageLink}">First</a>

					<c:url var="prevPageLink" value="/TraineeControllerServlet">
						<c:param name="pageNumber" value="${currentPage - 1}" />
					</c:url>
					<a href="${prevPageLink}">Prev</a>
				</c:otherwise>
			</c:choose>
			
			<!-- Handle Page Number links -->
			<c:forEach begin="1" end="${totalTraineeCount}" step="10" varStatus="loop">
				<c:choose>
				
					<c:when test="${loop.count > (currentPage-5) && loop.count <= (currentPage+5) || (currentPage <= 5 && loop.count <= 10) || totalPages <=10}">

						<c:url var="pageLink" value="/TraineeControllerServlet">
							<c:param name="pageNumber" value="${loop.count}" />
						</c:url>

						<c:choose>
							<c:when test="${loop.count == currentPage}">
								<a href="${pageLink}" class="selected-page">${loop.count}</a>
							</c:when>
							<c:otherwise>
								<a href="${pageLink}">${loop.count}</a>
							</c:otherwise>
						</c:choose>

					</c:when>
				
				</c:choose>
			</c:forEach>
			
			<!-- Handle "..." if there are more pages -->
			<c:choose>
				<c:when test="${currentPage < (totalPages-5) && (totalPages > 10)}">
					...
				</c:when>
			</c:choose>
			
			<!-- Handle "Next" and "Prev" links -->
			<c:choose>
			
				<c:when test="${currentPage == totalPages}">
					<a href="#" class="disabled">Next</a>
					<a href="#" class="disabled">Last</a>
				</c:when>
				
				<c:otherwise>
					<c:url var="nextPageLink" value="/TraineeControllerServlet">
						<c:param name="pageNumber" value="${currentPage + 1}" />
					</c:url>
					<a href="${nextPageLink}">Next</a>

					<c:url var="lastPageLink" value="/TraineeControllerServlet">
						<c:param name="pageNumber" value="${totalPages}" />
					</c:url>		
					<a href="${lastPageLink}">Last</a>
				</c:otherwise>
				
			</c:choose>		
			</div>
			           
		</div>
	</div>

<script>
function showLocation() {
	  var x = document.getElementById("location");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	    event.preventDefault();
	  } else{
		  x.style.display = "none";  
		  event.preventDefault();
	  }
}

function showLocation2() {
	  var x = document.getElementById("location2");
	  if (x.style.display === "none") {
	    x.style.display = "block";
	    event.preventDefault();
	  } else{
		  x.style.display = "none";  
		  event.preventDefault();
	  }
}

function showPagination() {
	  var x = document.getElementById("pagination");
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