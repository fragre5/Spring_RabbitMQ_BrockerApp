# Cosine similarity

### Input data format:
The case with only two vectors

    {
    "vector1": [1, 2, 3],
    "vector2": [4, 5, 6]
    }

Array of vectors

    {
    "vectors": [
      {"vector1": [1, 2, 3], "vector2": [4, 5, 6]},
      {"vector1": [7, 8, 9], "vector2": [10, 11, 12]},
      {"vector1": [13, 14, 15], "vector2": [16, 17, 18]},
      ...
      ]
    }


The program presents an endpoint for loading and calculating the cosine similarity between vectors.
The found values are also stored in the database and can be obtained by another endpoint.

The application accepts the request and puts it in the RabbitMQ queue. 
The listener retrieves requests from the queue and processes them. 
Vector API is used for processing and finding cosine similarity. 
The key feature of the Vector API is the use of SIMD instructions to speed up computationy.


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