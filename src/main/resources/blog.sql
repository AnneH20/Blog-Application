CREATE TABLE IF NOT EXISTS users (
     id SERIAL PRIMARY KEY,
     username VARCHAR(60) NOT NULL,
     first_name VARCHAR(60) NOT NULL,
     last_name VARCHAR(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS articles (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    slug VARCHAR(255),
    added_at TIMESTAMP,
    author_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES users(id)
);