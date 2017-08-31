#!/bin/bash

echo "Killing containers"
docker rm -f  $(docker ps -a | grep -E "(service-1|service-2|service-3|universalforwarder|splunk)" | awk '{ print $1 }')

echo "Removing dangling volumes"
docker volume rm $(docker volume ls -qf dangling=true)