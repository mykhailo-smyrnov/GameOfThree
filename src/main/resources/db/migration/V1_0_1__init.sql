DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(30) NOT NULL UNIQUE,
    `created_date` TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS `player_type`;
CREATE TABLE IF NOT EXISTS `player_type` (
    `id` INT(1) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(8) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
INSERT INTO `player_type` (name) VALUES ("COMPUTER");
INSERT INTO `player_type` (name) VALUES ("HUMAN");

DROP TABLE IF EXISTS `game_status`;
CREATE TABLE IF NOT EXISTS `game_status` (
    `id` INT(1) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(8) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
INSERT INTO `game_status` (name) VALUES ("STARTED");
INSERT INTO `game_status` (name) VALUES ("FINISHED");

DROP TABLE IF EXISTS `game`;
CREATE TABLE IF NOT EXISTS `game` (
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `account_id` INT(11) NOT NULL,
    `started_by_id` INT(1) NOT NULL,
    `status_id` INT(1) NOT NULL,
    `winner_id` INT(1),
    `init_number` INT(5) NOT NULL,
    `current_number` INT(5) NOT NULL,
    `start_date` TIMESTAMP NOT NULL,
    `finish_date` TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS `game_stats`;
CREATE TABLE IF NOT EXISTS `game_stats` (
    `id` INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `game_id` INT(11) NOT NULL,
    `move_by` INT(1) NOT NULL,
    `move_number` INT(2) NOT NULL,
    `move_date` TIMESTAMP NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;