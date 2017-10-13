#!/bin/bash
echo "Deleting all containers"
docker rm -f $(docker ps -a -q)

echo "Deleting all images"
docker rmi -f $(docker images -q)

echo "Removing dangling volumes"
docker volume rm $(docker volume ls -qf dangling=true)