CREATE TABLE `ingressos` (
`id` BIGINT NOT NULL AUTO_INCREMENT,
`nome` VARCHAR(100) NOT NULL,
`documento` VARCHAR(11) NOT NULL,
`status` VARCHAR(8) DEFAULT 'ATIVO',
`data_cadastro` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
`data_atualizacao` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
`id_filme` BIGINT,
`id_sala` BIGINT,
`id_horario` BIGINT,
`id_tipo_ingresso` BIGINT,
`id_cadeira` BIGINT,
PRIMARY KEY (`id`),
CONSTRAINT `fk_filme` FOREIGN KEY (`id_filme`) REFERENCES `filmes`(`id`),
CONSTRAINT `fk_sala` FOREIGN KEY (`id_sala`) REFERENCES `salas`(`id`),
CONSTRAINT `fk_horario` FOREIGN KEY (`id_horario`) REFERENCES `horarios`(`id`),
CONSTRAINT `fk_tipo_ingresso` FOREIGN KEY (`id_tipo_ingresso`) REFERENCES `tipo_ingresso`(`id`),
CONSTRAINT `fk_cadeira` FOREIGN KEY (`id_cadeira`) REFERENCES `cadeiras`(`id`)
);