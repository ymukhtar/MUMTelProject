INSERT INTO `jobportal`.`User` (`userName`, `enabled`, `password`) VALUES ('yasir', true, 'yasir');
INSERT INTO `jobportal`.`User` (`userName`, `enabled`, `password`) VALUES ('awais', true, 'awais');
INSERT INTO `jobportal`.`User` (`userName`, `enabled`, `password`) VALUES ('admin', true, 'admin');

INSERT INTO `jobportal`.`Authorities` (`user_role_id`, `role`, `username`) VALUES ('1', 'ROLE_USER', 'yasir');
INSERT INTO `jobportal`.`Authorities` (`user_role_id`, `role`) VALUES ('2', 'ROLE_USER');
INSERT INTO `jobportal`.`Authorities` (`user_role_id`, `role`, `username`) VALUES ('3', 'ROLE_USER', 'admin');
INSERT INTO `jobportal`.`Authorities` (`user_role_id`, `role`, `username`) VALUES ('4', 'ROLE_ADMIN', 'admin');

INSERT INTO `jobportal`.`Category` (`categoryId`, `description`, `title`) VALUES ('1', 'JAVA', 'JAVA Details');
INSERT INTO `jobportal`.`Category` (`categoryId`, `description`, `title`) VALUES ('2', '.NET', 'Dot Net');
INSERT INTO `jobportal`.`Category` (`categoryId`, `description`,`title`) VALUES ('3', 'Python','Python Details');