USE social_network;

CREATE TABLE IF NOT EXISTS articles (
    id VARCHAR(36) NOT NULL PRIMARY KEY,
    author_id VARCHAR(36) NOT NULL, -- 사용자 ID 개념만 사용
    content TEXT NOT NULL,
    created_at BIGINT NOT NULL,
    updated_at BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS article_likes (
    article_id VARCHAR(36) NOT NULL,
    user_id VARCHAR(36) NOT NULL, -- 사용자 ID 개념만 사용
    PRIMARY KEY (article_id, user_id),
    FOREIGN KEY (article_id) REFERENCES articles(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS user_relations (
    user_id VARCHAR(36) NOT NULL,      -- 사용자 ID 개념만 사용
    following_id VARCHAR(36) NOT NULL, -- 팔로우 대상도 개념적으로만 유지
    created_at DATETIME NOT NULL DEFAULT NOW(),
    updated_at DATETIME NOT NULL DEFAULT NOW(),
    PRIMARY KEY (user_id, following_id)
);
