# Account Management System

Account Management System is a simple Java Spring Boot Application for managing operations related to accounts.

## Features

The various features of this project are as below :
- Create Customer and Account
- Credit and Debit Amount to Account
- View Customers and Accounts
- Delete Customer and Account

## Technology Stack

Technology | Description
----------- | ----------
Core Framework | Spring Boot
Security Framework | Spring Security
Persistent Layer Framework | Spring Data JPA
Database | MySQL
JDK | Version 17
Maven | Dependency Management
Git | Version Control Management
Postman | API Development Environment for Testing

## Prerequisites

- MySQL needs to be installed on your machine and server should be up and running.
- Create a database and update the credentials in application.properties file.
- PostMan needs to be installed in order to test the application

## Installation

1. Download the zip or clone the Git repository.
2. Unzip the zip file (if you downloaded one)
3. Open Command Prompt and Change directory (cd) to folder containing pom.xml
4. Open Eclipse
	1. File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
	2. Select the project
	3. Choose the Spring Boot Application file (search for @SpringBootApplication)
	4. Right Click on the file and Run as Java Application
	
## Security

Basic Spring Authentication is used. The credentials in order to invoke the Rest APIs are configured in 
application.properties file.

## Testing APIs

Use Postman in order to test the APIs. 

Request Type | URL
GET | http://localhost:8080/ams/customers
POST | http://localhost:8080/ams/customer/create
PUST | http://localhost:8080/ams/customer/update
DELETE | http://localhost:8080/ams/account/delete/5

## Contributing
Any contributions you make are greatly appreciated. Please find the process below :

1. Click Fork after you open the Git repository
2. Clone your forked repository on your local, replacing USERNAME with your Github username.
	git clone https://github.com/{USERNAME}/account.git
3. Create a branch:
	git checkout -b {branchName}
4. Add and commit the changes
	git add .
	git commit -m "changes"
5. Push changes to remote.
	git push origin {branchName}
6. Create and submit a pull request.

## Contact
Pujitha - pujitha.neelisetti@outlook.com
Project Link - https://github.com/pujithaneelisetti/account
	
