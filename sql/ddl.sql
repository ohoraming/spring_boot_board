CREATE TABLE board (
		id BIGSERIAL NOT NULL PRIMARY KEY,
		title VARCHAR(50) NOT NULL,
		content TEXT NOT NULL
);