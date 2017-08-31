#!/bin/bash

echo "Killing containers"
#docker rm -f  $(docker ps -a | grep -E "(service-1|service-2|service-3)" | awk '{ print $1 }')
docker-compose down --volumes
docker volume rm $(docker volume ls -qf dangling=true)

echo "Build project"
mvn clean install -DskipTests

echo "Building containers"
docker-compose -f docker-compose.yml build
docker-compose -f docker-compose.yml up -d
