package ilp_score_portal_t3.jdbc;

public class Trainee {
	
	private int trainee_id;
	private String batch_id;
	private String firstName;
	private String lastName;
	private int score;
	
		
	public Trainee(int trainee_id, String firstName, String lastName, int score) {
		this.trainee_id = trainee_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.score = score;
	}

	public Trainee(int trainee_id, String batch_id, String firstName, String lastName, int score) {
		this.trainee_id = trainee_id;
		this.batch_id = batch_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.score = score;
	}

	public int getTrainee_id() {
		return trainee_id;
	}
	
	public void setTrainee_id(int trainee_id) {
		this.trainee_id = trainee_id;
	}
	
	public String getBatch_id() {
		return batch_id;
	}
	
	public void setBatch_id(String batch_id) {
		this.batch_id = batch_id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firsName) {
		this.firstName = firsName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Trainee [trainee_id=" + trainee_id + ", batch_id=" + batch_id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", score=" + score + "]";
	}

	
}
