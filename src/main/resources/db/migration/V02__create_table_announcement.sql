CREATE TABLE announcement (
      id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
      title VARCHAR(255),
      description TEXT,
      type VARCHAR(255),
      created_at TIMESTAMP,
      active BOOLEAN,
      user_id BIGINT,
      CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);
