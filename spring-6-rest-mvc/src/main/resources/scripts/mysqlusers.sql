DROP DATABASE IF EXISTS spring_6_rest_mvc;

DROP USER IF EXISTS `spring_admin`@`%`;
DROP USER IF EXISTS `spring_user`@`%`;

CREATE DATABASE IF NOT EXISTS spring_6_rest_mvc CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS `spring_admin`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
    CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `spring_6_rest_mvc`.* TO `spring_admin`@`%`;

CREATE USER IF NOT EXISTS `spring_user`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, SHOW VIEW ON `spring_6_rest_mvc`.* TO `spring_admin`@`%`;

FLUSH PRIVILEGES;