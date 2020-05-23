package ilp_score_portal_t3.jdbc;


import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;







@WebServlet("/TraineeControllerServlet")
public class TraineeControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private TraineeDbUtil traineeDbUtil;
	
	@Resource(name="ilp_score_portal_t3")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		//create our trainee db util ... and pass in the conn pool/datasource
		try {
			traineeDbUtil = new TraineeDbUtil(dataSource);
		}catch(Exception exc) {
			throw new ServletException(exc);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//read the "command" parameter
			String theCommand = request.getParameter("command");
			if(theCommand == null) {
				theCommand = "LIST";
			}	
			// route to the appropriate method
			switch(theCommand) {
			case "LIST":
				listTrainees(request, response);
				break;
			case "LIST_LOW":
				listLowTrainees(request, response);
				break;
			case "LIST_HIGH":
				listHighTrainees(request, response);
				break;
			case "LOAD":
				loadTrainee(request, response);
				break;
			case "UPDATE":
				updateTrainee(request, response);
				break;
			case "SEARCH":
                searchTrainees(request, response);
                break;
			case "SEARCH_BATCH":
                searchBatch(request, response);
                break;
			default:
				//listTrainees(request,response);
			}
			

		}catch(Exception exc) {
			throw new ServletException(exc);
		}
		
		
	}
	
	private void listTrainees(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		int pageNumber = getPageNumber(request);

		// computer pagination values
		long totalTraineeCount = traineeDbUtil.getTraineesTotalCount();

		int totalPages = (int) Math.floor(totalTraineeCount / WebConstants.PAGE_SIZE);
		
		if ( (totalTraineeCount % WebConstants.PAGE_SIZE) > 0) {
			totalPages++;
		}
		
		// add data for pagination support
		request.setAttribute("totalTraineeCount", totalTraineeCount);
		request.setAttribute("currentPage", pageNumber);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("pageSize", WebConstants.PAGE_SIZE);
		
		//get trainees from db util
		ArrayList<Trainee> trainees = traineeDbUtil.getTrainees(pageNumber);
		//add trainees to the request
		request.setAttribute("TRAINEE_LIST", trainees);
		//send to JSP page(view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-trainees.jsp");
		dispatcher.forward(request, response);
		
	}
//<PAGINATION	
	private int getPageNumber(HttpServletRequest request) {
		
		String pageNumberStr = request.getParameter("pageNumber");
		
		int pageNumber;
		
		if (pageNumberStr != null) {
			pageNumber = Integer.parseInt(pageNumberStr);
			
			if (pageNumber < 1) {
				pageNumber = 1;
			}
		}
		else {
			pageNumber = 1;
		}
		return pageNumber;
	}
//PAGINATION>

	private void listLowTrainees(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		String theSearchBatch = request.getParameter("theSearchBatch");
		int pageNumber = getPageNumber(request);

		// computer pagination values
		long totalTraineeCount = traineeDbUtil.getBatchTotalCount(theSearchBatch);

		int totalPages = (int) Math.floor(totalTraineeCount / WebConstants.PAGE_SIZE);
		
		if ( (totalTraineeCount % WebConstants.PAGE_SIZE) > 0) {
			totalPages++;
		}
		
		// add data for pagination support
		request.setAttribute("totalTraineeCount", totalTraineeCount);
		request.setAttribute("currentPage", pageNumber);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("pageSize", WebConstants.PAGE_SIZE);
		
		
		//get trainees from db util
		ArrayList<Trainee> trainees = traineeDbUtil.getLowTrainees(theSearchBatch, pageNumber);
		//add trainees to the request
		request.setAttribute("TRAINEE_LIST", trainees);
		//send to JSP page(view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-trainees.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void listHighTrainees(HttpServletRequest request, HttpServletResponse response) 
	throws Exception{
		String theSearchBatch = request.getParameter("theSearchBatch");
		//get trainees from db util
		ArrayList<Trainee> trainees = traineeDbUtil.getHighTrainees(theSearchBatch);
		//add trainees to the request
		request.setAttribute("TRAINEE_LIST", trainees);
		//send to JSP page(view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-trainees.jsp");
		dispatcher.forward(request, response);
		
	}
	
	
	private void loadTrainee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//read student id from form data
		String theTraineeId = request.getParameter("traineeId");
		// get student from database(db util)
		Trainee theTrainee = traineeDbUtil.getTrainee(theTraineeId);
		//place student in the request attribute
		request.setAttribute("THE_TRAINEE", theTrainee);
		//send to jsp page: update-student-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-score-form.jsp");
		dispatcher.forward(request,response);
	}
	
	private void updateTrainee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//read student info from form data
		int id = Integer.parseInt(request.getParameter("traineeId"));
		String firstName= request.getParameter("firstName");
		String lastName= request.getParameter(".lastName");
		//System.out.println(firstName +" "+ lastName);
		int score= Integer.parseInt(request.getParameter("score"));
		//create a new student object
		
		Trainee theTrainee = new Trainee(id, firstName, lastName, score);
		// perform update on database
		traineeDbUtil.updateTrainee(theTrainee);
		//send them back to the "list students" page
		listTrainees(request,response);
	}
	
	
    private void searchTrainees(HttpServletRequest request, HttpServletResponse response) throws Exception {
     
    	
    	// read search name from form data
        String theSearchID = request.getParameter("theSearchID");
        int pageNumber = getPageNumber(request);
	     
        long totalTraineeCount = traineeDbUtil.getTraineesTotalCount();
	
		int totalPages = (int) Math.floor(totalTraineeCount / WebConstants.PAGE_SIZE);
			
		if ( (totalTraineeCount % WebConstants.PAGE_SIZE) > 0) {
			totalPages++;
		}
		request.setAttribute("totalTraineeCount", totalTraineeCount);
		request.setAttribute("currentPage", pageNumber);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("pageSize", WebConstants.PAGE_SIZE);
        
        // search trainees from db util
        ArrayList<Trainee> trainees = traineeDbUtil.searchTrainee(theSearchID, pageNumber);
        
        // add trainees to the request
        request.setAttribute("TRAINEE_LIST", trainees);
                
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-trainees.jsp");
        dispatcher.forward(request, response);
 
    }
    
    private void searchBatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // read search name from form data
        String theSearchBatch = request.getParameter("theSearchBatch");
        
        // search trainees from db util
        ArrayList<Trainee> trainees = traineeDbUtil.searchBatch(theSearchBatch);
        
        // add trainees to the request
        request.setAttribute("TRAINEE_LIST", trainees);
                
        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-trainees.jsp");
        dispatcher.forward(request, response);
    }
    

	
}
