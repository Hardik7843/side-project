*Show Databases*
```
Show Databases;
```



*Creating database*
```
CREATE DATABASE IF NOT EXISTS jdbc_demo;
```



*For Creating User Table*
```
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
    balance int 
);
```


**for altering table**
```
ALTER TABLE users
MODIFY COLUMN balance INT DEFAULT(10000);
```
