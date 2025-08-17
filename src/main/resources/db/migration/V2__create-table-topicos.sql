  CREATE TABLE topicos (
      id BIGINT AUTO_INCREMENT PRIMARY KEY,
      titulo VARCHAR(255) NOT NULL,
      mensaje VARCHAR(1000) NOT NULL,
      autor VARCHAR(100) NOT NULL,
      curso VARCHAR(100) NOT NULL,
      fecha_creacion TIMESTAMP NOT NULL,
      status VARCHAR(20) NOT NULL,
      CONSTRAINT uk_titulo_mensaje UNIQUE (titulo(100), mensaje(200))
      );