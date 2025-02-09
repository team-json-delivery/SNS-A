USE social_network;

-- 샘플 게시글 데이터
INSERT INTO articles (id, author_id, content, created_at, updated_at) VALUES
('article-1', 'user-1', 'Hello, this is Alice\'s post!', UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
('article-2', 'user-2', 'Bob is writing something interesting.', UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000);

-- 샘플 좋아요 데이터
INSERT INTO article_likes (article_id, user_id) VALUES
('article-1', 'user-2'),
('article-2', 'user-1'),
('article-2', 'user-3');

-- 샘플 팔로우 관계 데이터
INSERT INTO user_relations (user_id, following_id, created_at, updated_at) VALUES
('user-1', 'user-2', NOW(), NOW()),
('user-2', 'user-3', NOW(), NOW());
