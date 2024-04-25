# Account Management and Money Transfer System 

## Project Overview ##
    This project is a simple Spring Boot application that provides APIs for Creating User Accounts and performing Money Transfers between accounts. 
    Here we have adopted Contract First Approach for much better and seamless development.


## Technologies Used
	Spring Boot
	Maven
	Java17
	H2 Database (in-memory for demo purposes, can be replaced with other databases)
	RESTful API
	
	
##Prerequisites
	JDK 8 or higher installed
	Maven installed  
	IDE (optional, but recommended)


## Local Setup
     1.Clone the Repository
         TODO: Add the github repo here
         
     2. mvn clean install
         This will generate the api contracts as per the OpenAPI specs listed in yaml file.
         
     3. Run as Spring Boot Application
     
     4. Run the application and swagger on below urls:
         http://localhost:8080
         http://localhost:8080/swagger-ui.html

## Usage and Main Operations
    The application provides the following RESTful endpoints:

      . POST /api/v1/accounts - Create a new account
      . GET  /api/v1/accounts/{id} - Get account details by ID
      . GET  /api/v1/accounts - Get all accounts
      . POST /api/transactions - Perform a money transfer between accounts



## Documentation ##

    [Swagger Link](http://localhost:8080/swagger-ui.html)                                                                                                                 |


## Main Operations Performed by this API ##

    |  These services can perform                      |
    |--------------------------------------------------|
    |    1. Account Creation                           |
    |    2. Fetch Account(s) Information               | 
    |    3. Transaction - Payment Transfer operations  |
    |--------------------------------------------------|
    

## Version
	0.0.1-SNAPSHOT


### Enhancements ###
     * Security 
     * Authentication

### Contributors and Owners ###
     
     Repo Admin\Owner
      Ashwani Tiwari (tiwariashwanik@gmail.com)