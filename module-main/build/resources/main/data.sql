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
('Sidney Sheldon', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Jackie Collins', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Nora Roberts', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Jane Smith', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tag (name, create_date, last_update_date) VALUES
('technology', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('art', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('finance', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('innovation', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('sports', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO news (title, content, create_date, last_update_date, author_id) VALUES
('Tech News', 'Latest in technology...', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
('Sports Update', 'Recent sports events...', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
('GENERAL PROVISIONS','A Nigerian boy solves a 30-year math equation, is recognized by a Japanese university.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2),
('EXECUTIVE', 'Monsanto will close three of its facilities. We are making a difference!', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
('EXECUTIVE', 'The 18-year old grandson taking his grandma to prom, and other feel-goods.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 3),
('LEGISLATURE', 'A woman with rare, autoimmune disease, transverse myelitis, gets her bathroom redone so it is wheelchair accessible, all services and materials are donated by a local contractor company.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4),
('AERONAUTICS', 'The worst flood in India in 100 years brings Twitter to the rescue.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 4),
('BANKS AND FINANCE INSTITUTIONS', 'Washington High School students afforded discretion as their food and hygiene needs are met.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5),
('CONSERVATION', 'The church that tipped a pizza guy who is in recovery $700. Warning, the tears will flow.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 5);

INSERT INTO news_tag (news_id, tag_id) VALUES
(1, 1),
(1, 4),
(1, 3),
(2, 1),
(2, 2),
(3, 3),
(3, 4);
