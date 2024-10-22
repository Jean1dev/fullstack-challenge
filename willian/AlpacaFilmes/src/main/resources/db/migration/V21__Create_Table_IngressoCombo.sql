CREATE TABLE `ingresso_combo` (
    `id_ingresso` BIGINT,
    `id_combo` BIGINT,
    CONSTRAINT `fk_ingresso_combo_ingresso` FOREIGN KEY (`id_ingresso`) REFERENCES `ingressos`(`id`),
    CONSTRAINT `fk_ingresso_combo_combo` FOREIGN KEY (`id_combo`) REFERENCES `combo`(`id`)
);