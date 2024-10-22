CREATE TABLE `programacao_horario` (
  `id_programacao` bigint NOT NULL,
  `id_horario` bigint NOT NULL,
  FOREIGN KEY (`id_programacao`) REFERENCES `programacao` (`id`),
  FOREIGN KEY (`id_horario`) REFERENCES `horarios` (`id`)
)