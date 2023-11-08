mvn clean install
docker stop server-container
docker rm server-container
docker build -t server .
docker run --name server-container -d -p 8080:8080 server
docker ps