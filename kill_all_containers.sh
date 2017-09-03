#!/bin/bash

echo "Killing containers"
docker rm -f  $(docker ps -a | grep -E "(service-1|service-2|service-3|service-4|kafka|zookeeper|zipkin|splunk)" | awk '{ print $1 }')