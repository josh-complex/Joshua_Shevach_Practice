# U2-M10-Summative-Challenge
  
## Capstone modifications
Please view [**this directory**](https://github.com/BentAllenDesign/Joshua_Shevach_JavaS1/tree/main/JoshuaShevachU1Capstone) in the S1 repository to see the changes done to the Capstone project in order to fit this summative's grading criteria.  
The project was updated to use JPA repositories, register with a Eureka server instance, import configurations from a config server, and handle user authentication for secured paths, all while deing deployed to a docker container, which runs on an Amazon Linux 2 Ec2 instance.  
  
### Some details about this project:
* The DNS address for this project is **http://ec2-18-191-254-218.us-east-2.compute.amazonaws.com**
* The default port 80 is running the Eureka server, and is what you'll see if you open the DNS address without specifying a port
* The game store edge service is running on port 8080 and its configuration [**can be found here**](https://github.com/BentAllenDesign/U2-M10-Challenge-Properties/blob/main/game-store-service.yaml).
	* Authentications:
		* Staff authentication:
			* Username: **staffUser**
			* Password: **staff**
		* Manager authentication:
			* Username: **managerUser**
			* Password: **manager**
		* Admin authentication:
			* Username: **adminUser**
			* Password: **admin**
	* Endpoints for games:
		* `POST /games MANAGER` 
		* `GET /games` 
		* `GET /games/{id}` 
		* `GET /games/t/{title}` 
		* `GET /games/s/{studio}`
		* `GET /games/r/{rating}`
		* `PUT /games STAFF`
		* `DELETE /games/{id} ADMIN`
	* Endpoints for consoles:
		* `POST /consoles MANAGER` 
		* `GET /consoles` 
		* `GET /consoles/{id}` 
		* `GET /consoles/m/{manufacturer}` 
		* `PUT /consoles STAFF`
		* `DELETE /consoles/{id} ADMIN`
	* Endpoints for t-shirts:
		* `POST /t-shirts MANAGER` 
		* `GET /t-shirts` 
		* `GET /t-shirts/{id}` 
		* `GET /t-shirts/c/{color}` 
		* `GET /t-shirts/s/{size}`
		* `PUT /t-shirts STAFF`
		* `DELETE /t-shirts/{id} ADMIN`
	* Endpoints for invoices:
		* `POST /invoices`
		* `GET /invoices ADMIN`
		* `GET /invoices/{id} MANAGER`
