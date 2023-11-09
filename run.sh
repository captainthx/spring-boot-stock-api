#!/bin/bash
#/ network_name=""
container_name="server-container"
image_name="server"

if docker ps | grep -q "$container_name"; then
    echo "Container "$container_name" already exists. Stopping and removing it."
    docker stop $container_name
    docker rm $container_name
fi

# Build the Maven project
mvn clean install

# Check if the Maven build was successful or if there were any errors
if [ $? -eq 0 ]; then
  echo "Maven build succeeded."
  # Build a Docker image
  docker build -t $image_name .

  # Run a Docker container
  docker run --name $container_name -v ${PWD}/logs:/logs -p 8081:8080 -d $image_name

  # Display a list of running Docker containers
  docker ps
else
  echo "Maven build failed."
fi