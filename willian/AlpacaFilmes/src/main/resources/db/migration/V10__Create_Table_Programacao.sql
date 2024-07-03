CREATE TABLE `programacao` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    id_filme bigint,
    id_sala bigint,
    id_horario bigint,
    dataCadastro bigint,
    FOREIGN KEY (id_filme) REFERENCES filmes(id),
    FOREIGN KEY (id_sala) REFERENCES Salas(id),
    FOREIGN KEY (id_horario) REFERENCES Horarios(id),
    PRIMARY KEY (`id`)
);