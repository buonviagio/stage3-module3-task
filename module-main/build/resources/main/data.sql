-- 1. First drop tables if they exist (clean start)
DROP TABLE IF EXISTS news_tag;
DROP TABLE IF EXISTS news;
DROP TABLE IF EXISTS author;
DROP TABLE IF EXISTS tag;

-- Create tables
CREATE TABLE IF NOT EXISTS author (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(15) NOT NULL,
    create_date TIMESTAMP NOT NULL,
    last_update_date TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS tag (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(15) NOT NULL UNIQUE,
    create_date TIMESTAMP NOT NULL,
    last_update_date TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS news (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(30) NOT NULL,
    content VARCHAR(255) NOT NULL,
    create_date TIMESTAMP NOT NULL,
    last_update_date TIMESTAMP NOT NULL,
    author_id BIGINT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author(id)
);

CREATE TABLE IF NOT EXISTS news_tag (
    news_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    PRIMARY KEY (news_id, tag_id),
    FOREIGN KEY (news_id) REFERENCES news(id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tag(id) ON DELETE CASCADE
);

-- Insert initial data
INSERT INTO author (name, create_date, last_update_date) VALUES
('John Doe', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Jane Smith', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tag (name, create_date, last_update_date) VALUES
('technology', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sports', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO news (title, content, create_date, last_update_date, author_id) VALUES
('Tech News', 'Latest in technology...', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
('Sports Update', 'Recent sports events...', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);

INSERT INTO news_tag (news_id, tag_id) VALUES
(1, 1),
(2, 2);