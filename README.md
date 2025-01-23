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

## Tags and Description

### Command
```shell
git tag -a v1.0.0 -m "Tagging version 1.0.0"
git push origin v1.0.0
```

### Description

git tag -a v1.0.0 -m "Created Authorization Server for User Service. Can oauth using Auth Z Code"
git push origin v1.0.0

git tag -a v1.1.0 -m "Working Authentication, with Auth Z and client credentials"
git push origin v1.1.0


## References
[How to implement core services using JPA](https://docs.spring.io/spring-authorization-server/reference/guides/how-to-jpa.html)
