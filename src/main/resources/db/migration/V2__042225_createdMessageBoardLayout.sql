CREATE TABLE messageboard_messages
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    message     VARCHAR(255),
    user_rating DOUBLE PRECISION,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_messageboard_messages PRIMARY KEY (id)
);

-- COME BACK TO LINK TO DB