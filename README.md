# Spring Boot + MongoDB + Docker

## Script overview
#### Remove docker containers

`docker stop springboot-mongo`

`docker rm springboot-mongo`

`docker stop mongo`

`docker rm mongo`

#### Build application jar

`./gradlew clean build -x test`

#### Run MongoDB instance

`docker run -d -p 27017:27017 --name mongo mongo`

#### Build custom application image, create a container based on image, and link this container to the mongo container
`docker build -t springboot-mongo .`

`docker run -d -p 8085:8085 --name springboot-mongo --link=mongo  springboot-mongo`