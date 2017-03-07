/*
 * MySQL script.
 * Load the database with reference data and unit test data.
 */

-- password is 'admin'
INSERT INTO Account (referenceId, username, password, enabled, credentialsexpired, expired, locked, version) VALUES ('a07bd221-3ecd-4893-a0f0-78d7c0fbf94e', 'user', '$2a$10$LaCUwxyZEFiyrEwwwOtaPe.FUv8zAHxCDsWAz1yBcMkQ6zPOP2YzK', true, false, false, false, 0);
-- password is 'operations'
INSERT INTO Account (referenceId, username, password, enabled, credentialsexpired, expired, locked, version) VALUES ('7bd137c8-ab64-4a45-bf2d-d9bae3574622', 'operations', '$2a$10$CoMVfutnv1qZ.fNlHY1Na.rteiJhsDF0jB1o.76qXcfdWN6As27Zm', true, false, false, false, 0);
INSERT INTO Account (referenceId, username, password, enabled, credentialsexpired, expired, locked, version) VALUES ('7bd137c8-ab64-4a45-bf2d-d9bae3574623', 'up2011765544', '$2a$10$LaCUwxyZEFiyrEwwwOtaPe.FUv8zAHxCDsWAz1yBcMkQ6zPOP2YzK', true, false, false, false, 0);
INSERT INTO Account (referenceId, username, password, enabled, credentialsexpired, expired, locked, version) VALUES ('7bd137c8-ab64-4a45-bf2d-d9bae3574333', 'up2011765533', '$2a$10$LaCUwxyZEFiyrEwwwOtaPe.FUv8zAHxCDsWAz1yBcMkQ6zPOP2YzK', true, false, false, false, 0);

INSERT INTO Role (id, code, label, ordinal) VALUES (1, 'ROLE_USER', 'User', 0);
INSERT INTO Role (id, code, label, ordinal) VALUES (2, 'ROLE_ADMIN', 'Admin', 1);
INSERT INTO Role (id, code, label, ordinal) VALUES (3, 'ROLE_SYSADMIN', 'System Admin', 2);

INSERT INTO account_role (account_id, role_id) SELECT a.id, r.id FROM Account a, Role r WHERE a.username = 'user' and r.id = 1;
INSERT INTO account_role (account_id, role_id) SELECT a.id, r.id FROM Account a, Role r WHERE a.username = 'operations' and r.id = 3;
INSERT INTO account_role (account_id, role_id) SELECT a.id, r.id FROM Account a, Role r WHERE a.username = 'up2011765544' and r.id = 1;
INSERT INTO account_role (account_id, role_id) SELECT a.id, r.id FROM Account a, Role r WHERE a.username = 'up2011765533' and r.id = 1;

