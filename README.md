# springboot-user-service
This is a User Service Spring Boot App

## creation of mysql database

```shell
docker run -d \
  --name mysql_container \
  -e MYSQL_ROOT_PASSWORD=your_root_password \
  -p 3306:3306 \
  mysql:latest
```

Login to the docker instance
```shell
docker exec -it mysql_container sh
mysql -u root -p <root password>
```

Run
```sqlite-psql
CREATE USER 'app_user'@'%' IDENTIFIED BY 'helloworld';
GRANT ALL PRIVILEGES ON user_service.* TO 'app_user'@'%' WITH GRANT OPTION;
```