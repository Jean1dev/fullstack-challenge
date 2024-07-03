CREATE TABLE `programacao` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `id_filme` bigint,
    `id_sala` bigint,
    `data_cadastro` datetime(6),
    FOREIGN KEY (id_filme) REFERENCES `filmes`(`id`),
    FOREIGN KEY (id_sala) REFERENCES `salas`(`id`),
    PRIMARY KEY (`id`)
);