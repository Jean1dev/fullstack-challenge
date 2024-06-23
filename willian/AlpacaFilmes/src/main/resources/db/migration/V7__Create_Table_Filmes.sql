CREATE TABLE `filmes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(300) NULL,
  `original_title` VARCHAR(300) NULL,
  `release_date` VARCHAR(300) NULL,
  `poster_path` VARCHAR(100) NULL,
  `overview` VARCHAR(1000) NULL,
  `data_cadastro` DATE NULL,
  PRIMARY KEY (`id`));