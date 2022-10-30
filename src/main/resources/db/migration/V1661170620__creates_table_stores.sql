CREATE TABLE IF NOT EXISTS customer
(
    id   int    NOT NULL PRIMARY KEY,
    firstName   VARCHAR(200) NOT NULL,
    lastName    VARCHAR(200) NOT NULL,
    phone        VARCHAR(20) NOT NULL
);
