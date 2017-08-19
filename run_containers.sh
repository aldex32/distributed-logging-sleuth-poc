#!/bin/bash

echo "Killing containers"
docker rm -f  $(docker ps -a | grep -E "(service-1|service-2|service-3|kafka|zookeeper|zipkin|splunk)" | awk '{ print $1 }')

echo "Build project"
mvn clean install -DskipTests

echo "Building containers"
docker-compose -f docker-compose.yml build
docker-compose -f docker-compose.yml up -d
