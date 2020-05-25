# Team3

Must have Tomcat v9.0 server in eclipse

# To run:
Right click TraineeControllerServlet.java -> Run as -> Run on server

# Create Database in MySql WorkBench:
CREATE USER 'scoreportal'@'localhost' IDENTIFIED BY 'scoreportal'
GRANT ALL PRIVILEGES ON * . * TO 'scoreportal'@'localhost'

create schema ilp_student_tracker

create table trainees(
	trainee_id Serial,
    batch_id bigint unsigned,
    name varchar(50),
    last_name varchar(50),
    score smallint,
    Primary Key (trainee_id),
    foreign key (batch_id) references batch(batch_id)
);

create table batch(
	batch_id serial,
	location varchar(50),
	join_date timestamp,	
	primary key (batch_id)
);
