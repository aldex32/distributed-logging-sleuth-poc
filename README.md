# Distributed logging POC with Sleuth and Zipkin

## Microservices flow
1. `Service1` will push a message to `topic1`
2. `Service2` will consume the message from `topic1`
3. `Service2` will send an HTTP request to `Service3`
4. `Service3` will send an HTTP response to `Service2`
5. `Service2` will push a message to `topic2`
6. `Service1` will consume the message from `topic2`

## Prerequisites
- Java 8
- Docker

## Container dependencies
- zookeeper
- kafka
- zipkin

## Build and running the containers (including the dependencies listed above)
`./run_containers.sh`

## Trigger the services
`curl http://localhost:8082`

## Check Zipkin and query by trace id
`http://localhost:9411/`

## Zipkin screenshots
### Tracing a flow
![alt text](images/zipkin-trace.png "Zipkin Trace")

### Services dependencies
![alt text](images/services_dependencies.png "Services dependencies")