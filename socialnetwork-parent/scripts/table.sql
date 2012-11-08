CREATE TABLE socialnetwork.USER (
   id INT,
   lastname VARCHAR(50),
   firstname VARCHAR(50),
   email VARCHAR(50),
   password VARCHAR(50),
  PRIMARY KEY (ID)
) ENGINE = InnoDB COMMENT = 'table of user' ROW_FORMAT = DEFAULT;
