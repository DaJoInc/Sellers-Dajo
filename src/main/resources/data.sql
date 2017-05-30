
create table APP_USER (
   id BIGINT NOT NULL AUTO_INCREMENT,
   ident_id INT(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   first_name VARCHAR(30) NOT NULL,
   last_name  VARCHAR(30) NOT NULL,
   email VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (ident_id)
);


create table USER_PROFILE(
   id BIGINT NOT NULL AUTO_INCREMENT,
   type VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (type)
);


CREATE TABLE APP_USER_USER_PROFILE (
    user_id BIGINT NOT NULL,
    user_profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, user_profile_id),
    CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES APP_USER (id),
    CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES USER_PROFILE (id)
);


INSERT INTO USER_PROFILE(type)
VALUES ('USER');

INSERT INTO USER_PROFILE(type)
VALUES ('ADMIN');

INSERT INTO USER_PROFILE(type)
VALUES ('DBA');


INSERT INTO APP_USER(ident_id, password, first_name, last_name, email)
VALUES ('1064','$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm', 'Lord','kotan','dajo@ue.edu.co');



INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT user.id, profile.id FROM app_user user, user_profile profile
  where user.ident_id='1064' and profile.type='ADMIN';

CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);
create table USER_PRODUCTS(
  id BIGINT NOT NULL AUTO_INCREMENT,
  code_product VARCHAR(30) NOT NULL,
  des_product  VARCHAR(30) NOT NULL,
  image_product VARCHAR(30) NOT NULL,
  pathImage_product  VARCHAR(30) NOT NULL,
  type VARCHAR(30) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (code_product)
);


CREATE TABLE APP_USER_USER_PRODUCTS (
  user_id BIGINT NOT NULL,
  user_products_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, user_products_id),
  CONSTRAINT FK_APP_USER_2 FOREIGN KEY (user_id) REFERENCES APP_USER (id),
  CONSTRAINT FK_USER_PRODUCTS FOREIGN KEY (user_products_id) REFERENCES USER_PRODUCTS (id)
);

INSERT INTO USER_PRODUCTS(code_product, des_product, image_product, pathImage_product,type)
VALUES ('1064','El chocolate rico', 'Lord','kotan','HOL');


INSERT INTO APP_USER_USER_PRODUCTS (user_id, user_products_id)
  SELECT user.id, products.id FROM `dajoSellers`.APP_USER user, `dajoSellers`.USER_PRODUCTS products
  where user.ident_id='1064' and products.code_product='1064';
