# Project Title

This is a Spring Boot application that interacts with a RabbitMQ message broker and a PostgreSQL database. 
The application provides an endpoint for uploading and sending data to a RabbitMQ topic. 

### The data is structured like this:

    {
    "datetime": "15.11.2023 15:00:25.001", 
    "title": "Very fun book", 
    "text": "sometext"
    }

### The outdated file structure:

    [{
    "datetime": "15.11.2023 15:00:25.001", 
    "title": "Very fun book", 
    "x_avg_count_in_line": 0.012
    }, 

    {
    "datetime": "18.01.2023 12:00:25.001", 
    "title": "Other very fun book", 
    "x_avg_count_in_line": 0.032
    }]


The application then retrieves the data from the topic, calculates the number of "x" occurrences in the "text" field, and stores the results in the database.
The application also provides an endpoint to retrieve the results from the database.


### **Run application:**

* **mvn clean install**
* **mvn spring-boot:run** to start the application
* ~~java -jar ./target/brockerapp-1.0.0.jar~~ - not yet
* You need Docker to run RabbitMQ, so you have to use Docker on your machine anyway. 
  So use the provided **Dockerfile-rabbitmq** and **docker-compose.yml** to run it. 

### RabbitMQ Configuration

The RabbitMQ configurations are defined in the `definitions.json` file.
This file specifies the virtual host, queue, exchanges, and bindings. 
The `rabbitmq.conf` file is used to set the `vm memory watermark` for RabbitMQ.