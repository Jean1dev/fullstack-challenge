CREATE TABLE `combo_item` (
    `combo_id` BIGINT,
    `item_id` BIGINT,
    FOREIGN KEY (`combo_id`) REFERENCES `combo`(`id`),
    FOREIGN KEY (`item_id`) REFERENCES `item`(`id`)
);