    CREATE TABLE products(
         id SERIAL PRIMARY KEY,
         name VARCHAR ( 50 ) UNIQUE NOT NULL,
         price DOUBLE PRECISION NOT NULL,
		 date TIMESTAMP NOT NULL);
