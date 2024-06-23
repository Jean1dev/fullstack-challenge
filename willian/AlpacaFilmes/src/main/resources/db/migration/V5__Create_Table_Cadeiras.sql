CREATE TABLE IF NOT EXISTS `cadeiras` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `numero` int DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `id_sala` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_sala`) REFERENCES `salas` (`id`)
)
