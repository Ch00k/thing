CREATE TABLE
  thing (
    id BIGINT NOT NULL auto_increment,
    name VARCHAR(255),
    created_at datetime (6),
    updated_at datetime (6),
    PRIMARY key (id)
  ) engine = InnoDB;
