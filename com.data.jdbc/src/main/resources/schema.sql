CREATE TABLE IF NOT EXISTS books
(
    id                  BIGINT          PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    title               VARCHAR(255)    NOT NULL,
    author              VARCHAR(128)    NOT NULL,
    publication_year    INT
);