CREATE TABLE `user` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(255) NOT NULL,
	`password` VARCHAR(255) NOT NULL,
	`full_name` VARCHAR(255) NOT NULL,
	`created_at` DATETIME NOT NULL,
	`updated_at` DATETIME NOT NULL,
	PRIMARY KEY (`id`) USING BTREE,
	UNIQUE INDEX `UK_username` (`username`) USING BTREE
)
COLLATE=utf8mb4_unicode_ci
CHARSET=utf8mb4
ENGINE=InnoDB
;
