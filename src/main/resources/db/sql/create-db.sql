CREATE TABLE users (
  id       INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(30) NOT NULL,
  last_name  VARCHAR(30),
  gender     VARCHAR(30),
  email      VARCHAR(50) NOT NULL,
  password   VARCHAR(20) NOT NULL
);