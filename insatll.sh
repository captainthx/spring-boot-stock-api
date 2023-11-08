mvn clean install

if [ $? -eq 0 ];then
  docker build -t server .
  docker start --name server-container -d -p 8080:8080 server
  docker ps
else
  echo "Build failed"
fi
