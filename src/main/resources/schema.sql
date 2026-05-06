-- ================================================
-- DROP EXISTING TABLES
-- ================================================
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- ================================================
-- CREATE USERS TABLE
-- ================================================
CREATE TABLE users (
                       id          BIGSERIAL PRIMARY KEY,
                       first_name  VARCHAR(50)  NOT NULL,
                       last_name   VARCHAR(50)  NOT NULL,
                       email       VARCHAR(100) NOT NULL UNIQUE,
                       phone       VARCHAR(20)  NOT NULL,
                       role        VARCHAR(20)  NOT NULL DEFAULT 'USER',
                       is_active   BOOLEAN      NOT NULL DEFAULT TRUE,
                       address     VARCHAR(200),
                       created_at  TIMESTAMP    NOT NULL DEFAULT NOW(),
                       updated_at  TIMESTAMP    NOT NULL DEFAULT NOW()
);

-- ================================================
-- CREATE PRODUCTS TABLE
-- ================================================
CREATE TABLE products (
                          id          BIGSERIAL        PRIMARY KEY,
                          name        VARCHAR(100)     NOT NULL,
                          description VARCHAR(500),
                          price       NUMERIC(12, 2)   NOT NULL,
                          stock       INTEGER          NOT NULL DEFAULT 0,
                          category    VARCHAR(50)      NOT NULL,
                          image_url   VARCHAR(255),
                          is_active   BOOLEAN          NOT NULL DEFAULT TRUE,
                          created_at  TIMESTAMP        NOT NULL DEFAULT NOW(),
                          updated_at  TIMESTAMP        NOT NULL DEFAULT NOW()
);

-- ================================================
-- INDEXES
-- ================================================
CREATE INDEX idx_products_category  ON products (category);
CREATE INDEX idx_products_is_active ON products (is_active);
CREATE INDEX idx_users_email        ON users (email);
CREATE INDEX idx_users_role         ON users (role);
CREATE INDEX idx_users_is_active    ON users (is_active);