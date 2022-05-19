# Thermometer
Thermometer Using SpringBoot
This is a sample Java / Maven / Spring Boot application 

<h2>Building the project</h2>
You will need:

Java JDK 8 or higher <br/>
Maven 4.0.0 or higher<br/>
Git<br/>

<h2>UI</h2>
Thymeleaf<br/>
Jquery<br/>
Bootstrap<br/>

<h2>File List</h2>
bean<br/>
	Thermometer<br/>
controller<br/>
	ThermometerController<br/>
	ThermometerRestController<br/>
service<br/>
	ThermometerService<br/>
util<br/>
	ThermometerApplication<br/>
resources<br/>
	data<br/>
		mockDataIgnore.txt<br/>
		mockDataSummer.txt<br/>
		mockDataWinter.txt<br/>
	templates<br/>
		thermometer.html<br/>
	application.properties<br/>


<h2>Deploying</h2>

./mvnw clean install <br/>
./mvnw spring-boot:run


These are available on port 8080
ex)
localhost:8080/


![Settings Window](https://github.com/KJosephDev/Thermometer/blob/main/screen/screen1.png)
<h2>Description</h2>
If you click the test button, it runs scheduling every 1 second in JavaScript and gets temperature information from Spring boot.
<br/>
When the current temperature reaches a critical point, a notification is given to the user.
<br/>
As an option, decrease/increase/ignore repeat can be given.

Example)
![Settings Window](https://github.com/KJosephDev/Thermometer/blob/main/screen/screen2.png)

Displays the current temperature in Celsius/Fahrenheit.<br/>
For data, mock data of txt file was used.<br/>
The threshold is 2 degrees Celsius, and only decreasing has an optional value.<br/>
Winter testmode decreases from 3 degrees above zero to 3 degrees below zero.<br/>
When the threshold point of 2 degrees Celsius is reached, a message is displayed.<br/>



<h2>Questions and Comments: josephkwak.developer@gmail.com</h2>
