/*
 * MySQL script.
 * Load the database with reference data and unit test data.
 */


INSERT INTO configuration (id, conf_key, conf_value) VALUES (1, "trains-cents-per-kilometre", "1");

-- password is 'admin'
INSERT INTO account (reference_id, username, password, enabled, credentialsexpired, expired, locked, card_number, card_date, version, created_by, created_at, updated_by, updated_at) VALUES ('a07bd221-3ecd-4893-a0f0-78d7c0fbf94e', 'user', '$2a$10$LaCUwxyZEFiyrEwwwOtaPe.FUv8zAHxCDsWAz1yBcMkQ6zPOP2YzK', true, false, false, false, NULL, NOW(), 0, 'user', NOW(), NULL, NULL);
-- password is 'operations'
INSERT INTO account (reference_id, username, password, enabled, credentialsexpired, expired, locked, card_number, card_date, version, created_by, created_at, updated_by, updated_at) VALUES ('7bd137c8-ab64-4a45-bf2d-d9bae3574622', 'operations', '$2a$10$CoMVfutnv1qZ.fNlHY1Na.rteiJhsDF0jB1o.76qXcfdWN6As27Zm', true, false, false, false, NULL, NOW(), 0, 'user', NOW(), NULL, NULL);
INSERT INTO account (reference_id, username, password, enabled, credentialsexpired, expired, locked, card_number, card_date, version, created_by, created_at, updated_by, updated_at) VALUES ('7bd137c8-ab64-4a45-bf2d-d9bae3574623', 'up2011765544', '$2a$10$LaCUwxyZEFiyrEwwwOtaPe.FUv8zAHxCDsWAz1yBcMkQ6zPOP2YzK', true, false, false, false, NULL, NOW(), 0, 'user', NOW(), NULL, NULL);
INSERT INTO account (reference_id, username, password, enabled, credentialsexpired, expired, locked, card_number, card_date, version, created_by, created_at, updated_by, updated_at) VALUES ('7bd137c8-ab64-4a45-bf2d-d9bae3574333', 'up2011765533', '$2a$10$LaCUwxyZEFiyrEwwwOtaPe.FUv8zAHxCDsWAz1yBcMkQ6zPOP2YzK', true, false, false, false, NULL, NOW(), 0, 'user', NOW(), NULL, NULL);

INSERT INTO role (id, code, label, ordinal, effective_at, expires_at, created_at) VALUES (1, 'ROLE_USER', 'User', 0, '2015-01-01 00:00:00', NULL, NOW());
INSERT INTO role (id, code, label, ordinal, effective_at, expires_at, created_at) VALUES (2, 'ROLE_ADMIN', 'Admin', 1, '2015-01-01 00:00:00', NULL, NOW());
INSERT INTO role (id, code, label, ordinal, effective_at, expires_at, created_at) VALUES (3, 'ROLE_SYSADMIN', 'System Admin', 2, '2015-01-01 00:00:00', NULL, NOW());
INSERT INTO role (id, code, label, ordinal, effective_at, expires_at, created_at) VALUES (4, 'ROLE_INSPECTOR', 'Inspector', 3, '2015-01-01 00:00:00', NULL, NOW());


INSERT INTO account_role (account_id, role_id) SELECT a.id, r.id FROM Account a, Role r WHERE a.username = 'user' and r.id = 1;
INSERT INTO account_role (account_id, role_id) SELECT a.id, r.id FROM Account a, Role r WHERE a.username = 'operations' and r.id = 4;
INSERT INTO account_role (account_id, role_id) SELECT a.id, r.id FROM Account a, Role r WHERE a.username = 'up2011765544' and r.id = 1;
INSERT INTO account_role (account_id, role_id) SELECT a.id, r.id FROM Account a, Role r WHERE a.username = 'up2011765533' and r.id = 1;

insert into `trains`.`line` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`) values ('A', 'Train Line A', '1', '2015-01-01 00:00:00', NULL, NOW());

insert into `trains`.`train` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `capacity`) values ('TR1', 'Train 1', '1', '2015-01-01 00:00:00', NULL, NOW(), '250');
insert into `trains`.`train` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `capacity`) values ('TR2', 'Train 2', '2', '2015-01-01 00:00:00', NULL, NOW(), '300');
insert into `trains`.`train` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `capacity`) values ('TR3', 'Train 3', '3', '2015-01-01 00:00:00', NULL, NOW(), '100');
insert into `trains`.`train` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `capacity`) values ('TR4', 'Train 4', '4', '2015-01-01 00:00:00', NULL, NOW(), '200');
insert into `trains`.`train` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `capacity`) values ('TR5', 'Train 5', '5', '2015-01-01 00:00:00', NULL, NOW(), '250');
insert into `trains`.`train` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `capacity`) values ('TR6', 'Train 6', '6', '2015-01-01 00:00:00', NULL, NOW(), '300');
insert into `trains`.`train` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `capacity`) values ('TR7', 'Train 7', '7', '2015-01-01 00:00:00', NULL, NOW(), '100');
insert into `trains`.`train` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `capacity`) values ('TR8', 'Train 8', '8', '2015-01-01 00:00:00', NULL, NOW(), '200');

insert into `trains`.`station` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`) values ('PORTO-CMP', 'Porto-Campanha', '1', '2015-01-01 00:00:00', NULL, NOW());
insert into `trains`.`station` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`) values ('GAIA-D', 'Vila Nova de Gaia-Devesas', '2', '2015-01-01 00:00:00', NULL, NOW());
insert into `trains`.`station` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`) values ('AVR', 'Aveiro', '3', '2015-01-01 00:00:00', NULL, NOW());
insert into `trains`.`station` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`) values ('CMBR-B', 'Coimbra-B', '4', '2015-01-01 00:00:00', NULL, NOW());
insert into `trains`.`station` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`) values ('LISB-ORNT', 'Lisboa-Oriente', '5', '2015-01-01 00:00:00', NULL, NOW());
insert into `trains`.`station` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`) values ('LISB-STAP', 'Lisboa-Santa Apolonia', '6', '2015-01-01 00:00:00', NULL, NOW());

insert into `trains`.`trip` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `from`, `to`, `line`, `duration`, `distance`) values ('TRIP1', 'Trip 1', '1', '2015-01-01 00:00:00', NULL, NOW(), '1', '2', '1', '300', '4000');
insert into `trains`.`trip` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `from`, `to`, `line`, `duration`, `distance`) values ('TRIP2', 'Trip 2', '2', '2015-01-01 00:00:00', NULL, NOW(), '2', '3', '1', '1740', '60000');
insert into `trains`.`trip` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `from`, `to`, `line`, `duration`, `distance`) values ('TRIP3', 'Trip 3', '3', '2015-01-01 00:00:00', NULL, NOW(), '3', '4', '1', '1500', '57000');
insert into `trains`.`trip` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `from`, `to`, `line`, `duration`, `distance`) values ('TRIP4', 'Trip 4', '4', '2015-01-01 00:00:00', NULL, NOW(), '4', '5', '1', '5820', '175000');
insert into `trains`.`trip` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `from`, `to`, `line`, `duration`, `distance`) values ('TRIP5', 'Trip 5', '5', '2015-01-01 00:00:00', NULL, NOW(), '5', '6', '1', '420', '7000');
insert into `trains`.`trip` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `from`, `to`, `line`, `duration`, `distance`) values ('TRIP6', 'Trip 6', '6', '2015-01-01 00:00:00', NULL, NOW(), '6', '5', '1', '420', '7000');
insert into `trains`.`trip` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `from`, `to`, `line`, `duration`, `distance`) values ('TRIP7', 'Trip 7', '7', '2015-01-01 00:00:00', NULL, NOW(), '5', '4', '1', '5820', '175000');
insert into `trains`.`trip` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `from`, `to`, `line`, `duration`, `distance`) values ('TRIP8', 'Trip 8', '8', '2015-01-01 00:00:00', NULL, NOW(), '4', '3', '1', '1500', '57000');
insert into `trains`.`trip` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `from`, `to`, `line`, `duration`, `distance`) values ('TRIP9', 'Trip 9', '9', '2015-01-01 00:00:00', NULL, NOW(), '3', '2', '1', '1740', '60000');
insert into `trains`.`trip` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `from`, `to`, `line`, `duration`, `distance`) values ('TRIP10', 'Trip 10', '10', '2015-01-01 00:00:00', NULL, NOW(), '2', '1', '1', '300', '4000');

insert into `trains`.`departure` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `from`, `time`, `line`, `train`) values ('DPT1', 'Daily departure', '1', '2015-01-01 00:00:00', NULL, NOW(), '1', '0001-01-01 09:00:00', '1', '1');
insert into `trains`.`departure` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `from`, `time`, `line`, `train`) values ('DPT2', 'Daily departure', '2', '2015-01-01 00:00:00', NULL, NOW(), '1', '0001-01-01 11:00:00', '1', '2');
insert into `trains`.`departure` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `from`, `time`, `line`, `train`) values ('DPT3', 'Daily departure', '3', '2015-01-01 00:00:00', NULL, NOW(), '1', '0001-01-01 15:00:00', '1', '3');
insert into `trains`.`departure` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `from`, `time`, `line`, `train`) values ('DPT4', 'Daily departure', '4', '2015-01-01 00:00:00', NULL, NOW(), '1', '0001-01-01 17:00:00', '1', '4');
insert into `trains`.`departure` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `from`, `time`, `line`, `train`) values ('DPT5', 'Daily departure', '5', '2015-01-01 00:00:00', NULL, NOW(), '6', '0001-01-01 09:00:00', '1', '5');
insert into `trains`.`departure` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at` ,`from`, `time`, `line`, `train`) values ('DPT6', 'Daily departure', '6', '2015-01-01 00:00:00', NULL, NOW(), '6', '0001-01-01 11:00:00', '1', '6');
insert into `trains`.`departure` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `from`, `time`, `line`, `train`) values ('DPT7', 'Daily departure', '7', '2015-01-01 00:00:00', NULL, NOW(), '6', '0001-01-01 15:00:00', '1', '7');
insert into `trains`.`departure` (`code`, `label`, `ordinal`, `effective_at`, `expires_at`, `created_at`, `from`, `time`, `line`, `train`) values ('DPT8', 'Daily departure', '8', '2015-01-01 00:00:00', NULL, NOW(), '6', '0001-01-01 17:00:00', '1', '8');
