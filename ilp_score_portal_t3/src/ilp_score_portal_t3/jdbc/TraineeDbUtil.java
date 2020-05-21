package ilp_score_portal_t3.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;
import javax.swing.JOptionPane;






public class TraineeDbUtil {
	private DataSource dataSource;
	
	public TraineeDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public ArrayList<Trainee> getTrainees() throws Exception{
		ArrayList<Trainee> trainees = new ArrayList<>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			//get a connection
			myConn = dataSource.getConnection();
			//create a sql statement
			String sql = "select * from trainees limit 10";
			myStmt = myConn.createStatement();
			//execute query
			myRs = myStmt.executeQuery(sql);
			//process result set
			while(myRs.next()) {
				//retrieve data from result set row
				int trainee_id = myRs.getInt("trainee_id");
				String batch_id = myRs.getString("batch_id");
				String firstName = myRs.getString("name");
				String lastName = myRs.getString("last_name");
				int score = myRs.getInt("score");
				//create new student object
				Trainee tempTrainee = new Trainee(trainee_id, batch_id, firstName, lastName, score);
				//add it to the list of trainees
				trainees.add(tempTrainee);
			}
						
			return trainees;
		}finally {
			//close jdbc objects
			close(myConn,myStmt,myRs);
		}
		
	}
	
	public ArrayList<Trainee> getLowTrainees(String theSearchBatch) throws Exception{
		 ArrayList<Trainee> trainees = new ArrayList<>();
	        
	        Connection myConn = null;
	        PreparedStatement myStmt = null;
	        ResultSet myRs = null;
	        
	        try {
	            
	            // get connection to database
	            myConn = dataSource.getConnection();

	            	String sql= "select trainee_id, batch.location as batch_id, name, last_name, score from trainees inner join batch where trainees.batch_id = batch.batch_id\r\n" + 
	                		"and trainees.score<60 and batch.location like ? order by trainee_id";
	            	// create prepared statement
	                myStmt = myConn.prepareStatement(sql);
	                // set params
	                String theSearchNameLike = "%" + theSearchBatch.toLowerCase() + "%";
	                myStmt.setString(1, theSearchNameLike);
	                
	                
	            // execute statement
	            myRs = myStmt.executeQuery();
	            
	            // retrieve data from result set row
	            while (myRs.next()) {
	                
	                // retrieve data from result set row
	                int trainee_id = myRs.getInt("trainee_id");
	                String batch_id = myRs.getString("batch_id");
	                String firstName = myRs.getString("name");
	                String lastName = myRs.getString("last_name");
	                int score = myRs.getInt("score");
	                
	                // create new student object
	                Trainee tempTrainee = new Trainee(trainee_id, batch_id, firstName, lastName, score);
	                
	                // add it to the list of trainees
	                trainees.add(tempTrainee);            
	            }
	            
	            return trainees;
	        }
	        finally {
	            // clean up JDBC objects
	            close(myConn, myStmt, myRs);
	        }
	}
	
	public ArrayList<Trainee> getHighTrainees(String theSearchBatch) throws Exception{
		 ArrayList<Trainee> trainees = new ArrayList<>();
	        
	        Connection myConn = null;
	        PreparedStatement myStmt = null;
	        ResultSet myRs = null;
	        
	        try {
	            
	            // get connection to database
	            myConn = dataSource.getConnection();

	            	String sql= "select trainee_id, batch.location as batch_id, name, last_name, score from trainees inner join batch where trainees.batch_id = batch.batch_id\r\n" + 
	                		"and trainees.score>80 and batch.location like ? order by trainee_id";
	            	// create prepared statement
	                myStmt = myConn.prepareStatement(sql);
	                // set params
	                String theSearchNameLike = "%" + theSearchBatch.toLowerCase() + "%";
	                myStmt.setString(1, theSearchNameLike);
	                
	                
	            // execute statement
	            myRs = myStmt.executeQuery();
	            
	            // retrieve data from result set row
	            while (myRs.next()) {
	                
	                // retrieve data from result set row
	                int trainee_id = myRs.getInt("trainee_id");
	                String batch_id = myRs.getString("batch_id");
	                String firstName = myRs.getString("name");
	                String lastName = myRs.getString("last_name");
	                int score = myRs.getInt("score");
	                
	                // create new student object
	                Trainee tempTrainee = new Trainee(trainee_id, batch_id, firstName, lastName, score);
	                
	                // add it to the list of trainees
	                trainees.add(tempTrainee);            
	            }
	            
	            return trainees;
	        }
	        finally {
	            // clean up JDBC objects
	            close(myConn, myStmt, myRs);
	        }
	}
	
	
	//Method to check if a value can be casted to integer
	public static boolean isNumeric(String str) { 
		  try {  
		    Integer.parseInt(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
	}
	
    public ArrayList<Trainee> searchTrainee(String theSearchID)  throws Exception {
        ArrayList<Trainee> trainees = new ArrayList<>();
        
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        
        try {
            
            // get connection to database
            myConn = dataSource.getConnection();
            //Search by id
            if(isNumeric(theSearchID) && theSearchID != null) {
            	//Convert theSearchID to integer
            	int search = Integer.parseInt(theSearchID);
            	 // create sql to search for trainees by id
                String sql = "select * from trainees where trainee_id = ? ";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
                // set params
                
                myStmt.setInt(1, search);
                
            }
            //
            // only search by name if theSearchName is not empty
            //
            else {
                // create sql to get all trainees
                String sql = "select * from trainees order by trainee_id limit 10";
                // create prepared statement
                myStmt = myConn.prepareStatement(sql);
            }
            
            // execute statement
            myRs = myStmt.executeQuery();
            
            // retrieve data from result set row
            while (myRs.next()) {
                
                // retrieve data from result set row
                int trainee_id = myRs.getInt("trainee_id");
                String batch_id = myRs.getString("batch_id");
                String firstName = myRs.getString("name");
                String lastName = myRs.getString("last_name");
                int score = myRs.getInt("score");
                
                // create new student object
                Trainee tempTrainee = new Trainee(trainee_id, batch_id, firstName, lastName, score);
                
                // add it to the list of trainees
                trainees.add(tempTrainee);            
            }
            
            return trainees;
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }
	
    public ArrayList<Trainee> searchBatch(String theSearchBatch)  throws Exception {
        ArrayList<Trainee> trainees = new ArrayList<>();
        
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        
        try {
            
            // get connection to database
            myConn = dataSource.getConnection();

            	String sql= "select trainee_id, batch.location as batch_id, name, last_name, score from trainees inner join batch where trainees.batch_id = batch.batch_id\r\n" + 
                		"and batch.location like ? order by trainee_id";
            	// create prepared statement
                myStmt = myConn.prepareStatement(sql);
                // set params
                String theSearchNameLike = "%" + theSearchBatch.toLowerCase() + "%";
                myStmt.setString(1, theSearchNameLike);
                
                
            // execute statement
            myRs = myStmt.executeQuery();
            
            // retrieve data from result set row
            while (myRs.next()) {
                
                // retrieve data from result set row
                int trainee_id = myRs.getInt("trainee_id");
                String batch_id = myRs.getString("batch_id");
                String firstName = myRs.getString("name");
                String lastName = myRs.getString("last_name");
                int score = myRs.getInt("score");
                
                // create new student object
                Trainee tempTrainee = new Trainee(trainee_id, batch_id, firstName, lastName, score);
                
                // add it to the list of trainees
                trainees.add(tempTrainee);            
            }
            
            return trainees;
        }
        finally {
            // clean up JDBC objects
            close(myConn, myStmt, myRs);
        }
    }
    
	public Trainee getTrainee(String theTraineeId) throws Exception {
		Trainee theTrainee = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int traineeId;
		
		try {
			//convert student id to int
			traineeId = Integer.parseInt(theTraineeId);
			//get connection to database
			myConn = dataSource.getConnection();
			//create a sql to get selected student
			String sql = "select * from trainees where trainee_id =?";
			//create prepared statement
			myStmt = myConn.prepareStatement(sql);
			//set params
			myStmt.setInt(1, traineeId);
			
			//execute statement
			myRs = myStmt.executeQuery();
			
			//retrieve data from result set row
			if(myRs.next()) {
				 int trainee_id = myRs.getInt("trainee_id");
				String firstName = myRs.getString("name");
				String lastName = myRs.getString("last_name");
				int score = myRs.getInt("score");
				//use the studentId during construction
				theTrainee = new Trainee(trainee_id,firstName,lastName,score);
			}else {
				throw new Exception("Could not find trainee id: "+ traineeId);
			}
			return theTrainee;
		}
		finally {
			//clean up JDBC object
			close(myConn, myStmt, myRs);
		}
		
	}
	
	public void updateTrainee(Trainee theTrainee) throws Exception {
		Connection myConn= null;
		PreparedStatement myStmt = null;
		//get db connection
		try {
			myConn = dataSource.getConnection();
			//create sql update statement
			int x = theTrainee.getScore();
			if(x>=0 && x<=100) {
				String sql = "update trainees "
						     + "set score=? "
						     + "where trainee_id=?";
				//prepare statement
				myStmt = myConn.prepareStatement(sql);
				//set params
				myStmt.setInt(1, theTrainee.getScore());
				myStmt.setInt(2, theTrainee.getTrainee_id());
				//execute sql statement
				myStmt.execute();
				JOptionPane.showMessageDialog(null, "Update Succesful");
			}else {
				 JOptionPane.showMessageDialog(null, "Score must be between 0 and 100");
			}
		}finally {
			close(myConn, myStmt, null);
		}

	}
    
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if(myRs!=null) {
				myRs.close();
			}
			if(myStmt!=null) {
				myStmt.close();
			}
			if(myConn!=null) {
				myConn.close();
			}
		}catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}
	
}
