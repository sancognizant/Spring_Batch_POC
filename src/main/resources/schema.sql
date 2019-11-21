CREATE TABLE  product (
  id INTEGER  NOT NULL AUTO_INCREMENT,
  productName VARCHAR(64),
  quantity Integer,
  PRIMARY KEY (id)
);

CREATE TABLE  responseDiscount (
   id INTEGER  NOT NULL AUTO_INCREMENT,
   discount float,
   message VARCHAR(150),
  PRIMARY KEY (id)
);

insert into product(
  productName,
  quantity
 )

 VALUES(
   'phone',
      5
 ),
 (
   'phone',
      10
 ),
  (
   'macbook',
      10
 ),
  (
   'macbook',
      55
 ),
  (
   'macbook',
      105
 );



