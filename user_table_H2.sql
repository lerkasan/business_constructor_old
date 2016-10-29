CREATE TABLE USERS
(
  'id' INT NOT NULL AUTO_INCREMENT,
  'name' VARCHAR(100) NOT NULL ,
  'password' VARCHAR(100) NOT NULL ,
  'role' INT NOT NULL ,
  FOREIGN KEY (role) REFERENCES ROLE (id)
);

CREATE TABLE ROLE
(
  'id' INT NOT NULL AUTO_INCREMENT,
  'role' VARCHAR(100) NOT NULL 
)
