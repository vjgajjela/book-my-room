# About the application
This application is used to book available meeting rooms based on capacity provided.
<br/>
<br/>
# Building the application
Please go to the pom location and run below command
<br/>
<b> mvn clean install </b>
<br/>
<br/>
# Running the application
After successful build
<br/>
Please go to the pom location and run below command
<br/>
<b> mvn spring-boot:run </b>
<br/>
<br/>
# Using the API
After the successful deployment
<br/>
Please hit <a><b>http://localhost:8080/swagger-ui/index.html</b></a>
<br/>
<br/>

# DB details
This application uses in memory H2 Database.
<br/>
H2 URL : <a><b>http://localhost:8080/h2-ui/</b></a>
<br/>
Username : <b>sa</b>
<br/>
password : <b>password</b>
<br/>
<br/>
# Tables
Below are the table details
<br/>
<br/>
<b> maint_timings </b>
<br/>
This table stores maintenance timing details.
<br/>
<br/>
<b> meeting_rooms </b>
<br/>
This table stores meeting room details.
<br/>
<br/>
<b> bookings </b>
<br/>
This table stores booking details.
<br/>
<br/>


