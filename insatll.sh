mvn clean install
docker -build -t server .
docker stop server-container
docker rm-server-container
docker run --name server-container -d -p 8080:8080 server
