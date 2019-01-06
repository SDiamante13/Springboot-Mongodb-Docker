#!/usr/bin/env bash

docker stop springboot-mongo
docker rm springboot-mongo

docker stop mongo
docker rm mongo

./gradlew clean build -x test

docker run -d -p 27017:27017 --name mongo mongo

docker build -t springboot-mongo .
docker run -d -p 8085:8085 --name springboot-mongo --link=mongo  springboot-mongo
