Student Application
1)	It’s a student application which will perform the basic operations like create,read,delete for a student and it’s a Maven project

2)	Below are the pre-requisites of the application
a)	Oracle Database 10g and above
b)	Tomcat

3)	In oracle database we need to execute below scripts in which schema user you want, I have created the below inside the “system” user
a)	Student Table
CREATE TABLE STUDENT 
(
  ID NUMBER NOT NULL 
, FIRSTNAME VARCHAR2(200) NOT NULL 
, LASTNAME VARCHAR2(200) NOT NULL 
, CONSTRAINT STUDENT_PK PRIMARY KEY 
  (
    ID 
  )
  ENABLE 
);

b)	Student Sequence
CREATE SEQUENCE STUDENT_SEQ INCREMENT BY 1 START WITH 1;

c)	Insert scripts for the Student Table if required,
Insert into STUDENT (ID,FIRSTNAME,LASTNAME) values (STUDENT_SEQ.nextval,'John','Rock');
Insert into STUDENT (ID,FIRSTNAME,LASTNAME) values (STUDENT_SEQ.nextval,'Kul','Deep');
Commit;

4)	We need to set the oracle database url, username and password inside the application.properties, currently the details are as per my local machine

jdbc.url=jdbc:oracle:thin:@127.0.0.1:1521:xe
jdbc.username=system
jdbc.password=system

5)	 When we build and deploy the application in Tomcat below are the Rest API which has been exposed
a)	Getting all the student details
URL: http://localhost:8090/studentservice/v1/student/all
HTTP Method: GET
Sample Response:
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Rock"
  },
  {
    "id": 2,
    "firstName": "Kul",
    "lastName": "Deep"
  }
]

b)	Getting a student detail using id
URL: http://localhost:8090/studentservice/v1/student/1
HTTP Method: GET
Sample Response:
{
  "id": 1,
  "firstName": "John",
  "lastName": "Rock"
}

c)	Delete Student on the basis of id
URL: http://localhost:8090/studentservice/v1/student/delete/1
HTTP Method: DELETE
Response: HTTP Status 200

d)	Create Student
URL: http://localhost:8090/studentservice/v1/student/create
HTTP Method: POST
Input Type: JSON
Sample Request: 
{
    "id":0,
    "firstName":"Nilesh",
    "lastName":"Bamne"
}

Response: HTTP Status 200

