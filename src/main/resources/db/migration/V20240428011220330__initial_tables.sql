CREATE TABLE IF NOT EXISTS client (
	id uuid NOT NULL,
	name VARCHAR(250) NOT NULL,
	phone VARCHAR(50) NOT NULL,
	cpf VARCHAR(50) NOT NULL,
	address VARCHAR(250) NOT NULL,
	number VARCHAR(250) NOT NULL,
	complement VARCHAR(250) NOT NULL,
	zip_code VARCHAR(250) NOT NULL,
	neighborhood VARCHAR(250),
	city VARCHAR(250),
	state VARCHAR(250),
	created_date TIMESTAMP,
	last_modified_date TIMESTAMP);
