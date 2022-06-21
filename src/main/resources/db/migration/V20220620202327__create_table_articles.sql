CREATE TABLE articles
(
    id    INTEGER PRIMARY KEY AUTO_INCREMENT,
    text  VARCHAR,
    color VARCHAR,
    user_id INTEGER,
    FOREIGN KEY (user_id) REFERENCES users(id)
)