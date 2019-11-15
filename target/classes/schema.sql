CREATE TABLE  person (
  id INTEGER  NOT NULL AUTO_INCREMENT,
  name VARCHAR(64) NOT NULL,
  age INTEGER NOT NULL,
  PRIMARY KEY (id)
);

insert into person(name,age)
 VALUES('name1',20), ('name2', 31), ('name3', 19);
