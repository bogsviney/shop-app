         CREATE TABLE users(
         id SERIAL PRIMARY KEY,
         email VARCHAR UNIQUE NOT NULL,
         password VARCHAR NOT NULL);