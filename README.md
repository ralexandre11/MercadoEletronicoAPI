<p align="left">
  <a href="https://www.me.com.br/">
    <img src="src/main/resources/me.svg" alt="Mercado Eletrônico" width="300">
  </a>
</p>

# REST services with Spring Boot

Developed by [![Linkedin: ricardoalexandreribeiro](https://img.shields.io/badge/-Ricardo%20Ribeiro-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/ricardoalexandreribeiro/)](https://www.linkedin.com/in/ricardoalexandreribeiro/)

If you want, read the reference documentation about [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)

## About the application

This repository contains an example REST Services with Spring Boot.

The purpose of the application is:
* Access and manipulate information in a H2 Database (memory) from a set of methods to create (POST), read (GET), change (PUT) and delete (DELETE), by HTTP messages.
* This application run embedded inside the docker container.

## Developed With

* **Spring Boot** Java framework used to create a micro Service.
* Database **H2** to store the data, only in memory.
* **Docker** to package and run applications inside a container.
* **Maven** as Dependency Management System, and **Lombok Java library** to improve productivity.
* **STS** as IDE.

## Architecture

* The following illustration shows the application architecture.

<p>
  <img src="src/main/resources/architecture.jpg" alt="Architecture">
</p>

## Database

* The H2 database was used, which is a memory instance generated when the application is started.
* Some data is generated for testing when the application is started.

## How to run application using Docker
* If you don't have Docker, please install Docker: [Docker Desktop](https://docs.docker.com/get-docker/).
* Run on the terminal:

		`sudo docker pull ricardoribeirodev/ricardorepository:0.0.1-SNAPSHOT`

		`docker run -p 8080:8080 ricardoribeirodev/ricardorepository:0.0.1-SNAPSHOT`

## How to test the endpoint "/api/pedido/"

* GET (List all orders)
		
		`http://localhost:8080/api/pedido`

* GET (List a specific order)
		
		`http://localhost:8080/api/pedido/222222`

* POST (Insert a new order)
		
		`http://localhost:8080/api/pedido`
		
		(Body)
		{
		  "pedido": "444444",
		  "itens": [
		    {
		      "descricao": "ITEM A",
		      "precoUnitario": 300.00,
		      "qtd": 3
		    },
		    {
		      "descricao": "ITEM B",
		      "precoUnitario": 450.00,
		      "qtd": 2
		    }
		  ]
		}`
		
* PUT (Update an existing order)
		
		`http://localhost:8080/api/pedido/444444`
		
		(Body)
		{
		  "pedido": "444444",
		  "itens": [
		    {
		      "descricao": "ITEM A",
		      "precoUnitario": 500.00,
		      "qtd": 3
		    },
		    {
		      "descricao": "ITEM B",
		      "precoUnitario": 700.00,
		      "qtd": 2
		    }
		  ]
		}`

* DELETE (Delete an existing order)
		
		`http://localhost:8080/api/pedido/333333`
		
## How to test the endpoint "/api/status/"


* POST (Check status Order)
		
		`http://localhost:8080/api/status`
		
		(Body)
		{
			"status":"APROVADO",
			"itensAprovados": 5,
			"valorAprovado": 540,
			"pedido":"222222"
		}`

	
		
		

## How to develop
* You will need a Windows or Linux with Java/OpenJDK.
* Application is using Maven, so all required libraries should be downloaded automatically.
* Clone the git repository using the URL on the Github home page:

		`$ https://github.com/ralexandre11/MercadoEletronicoAPI`

		`$ cd MercadoEletronicoAPI`

* To buid the image Docker, use the command:

		`$ mvn package`

* use the docker command to run in the topic above.

