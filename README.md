PRODUCT DETAILS
---------------
Product Details application offers RESTful APIs to retrieve a product information based  on product id and update the price information of a product based on product id.
This can be set up and deployed easily to be used across client platforms. 


Build and Deployment steps:
---------------------------
* Pre-requisites:
	- JDK 1.8
	- Cassandra
	- Spring Tool Suite

* Unzip MyRetail.gzip and extract the contents
* In IDE, Import --> Maven --> Existing Maven project

* Open Cassandra browser OR cqlsh terminal
* Run the DDL and DML scripts. Those are included under resources folder

* Run the command mvn clean install on the project (myretail --> Run as --> Maven build... --> clean install
* Run the project as Spring Boot App 

* Hit the API requests in REST client tool

* To view the test coverage report please run mvn covertura:cobertura


Sample JSON Requests and Responses:
----------------------------------

* Get Product Details for product ID

Request : 
GET http://localhost:8080/v1/products/13860428

Response: 
Status: 200 ok

Response body:
	{
  		"productId": 13860428,
 		"upc": "025192110306",
  		"productDisplayName": "The Big Lebowski (Blu-ray)",
  		"productPrice": {
    		"regularPrice": 79.24,
    		"clearancePrice": 70.12,
    		"priceType": "R",
    		"currencyType": "IND"
  		}
	}


* Update Product price for product ID

Request: 
URL : PUT http://localhost:8080/v1/products/13860428
	
 Request body
  		"productId": 13860428,
 		"upc": "025192110306",
  		"productDisplayName": "The Big Lebowski (Blu-ray)",
  		"productPrice": {
    		"regularPrice": 79.24,
    		"clearancePrice": 70.12,
    		"priceType": "R",
    		"currencyType": "IND"
  		}
	}

Response: 
Status: 200 ok

Response Body:
No Response body
