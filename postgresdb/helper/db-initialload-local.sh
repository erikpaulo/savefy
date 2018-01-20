#!/usr/bin/env bash


#COPY USER_GROUP FROM '/Users/eriklacerda/Dev-Projects/ipocket/src/main/resources/config/postgres/load-data/db-postgres-load-savefy-group.csv' DELIMITER ',' CSV;
#COPY USER_ACCOUNT FROM '/Users/eriklacerda/Dev-Projects/ipocket/src/main/resources/config/postgres/load-data/db-postgres-load-savefy-user_account.csv' DELIMITER ',' CSV;
#COPY USER_ROLE FROM '/Users/eriklacerda/Dev-Projects/ipocket/src/main/resources/config/postgres/load-data/db-postgres-load-savefy-roles.csv' DELIMITER ',' CSV;
#copy CATEGORY from '/Users/eriklacerda/Dev-Projects/ipocket/heroku-db/CATEGORY.csv' WITH (DELIMITER ',', NULL '');
#copy SUBCATEGORY from '/Users/eriklacerda/Dev-Projects/ipocket/heroku-db/SUBCATEGORY.csv' WITH (DELIMITER ',', NULL '');
#copy ACCOUNT from '/Users/eriklacerda/Dev-Projects/ipocket/heroku-db/ACCOUNT.csv' WITH (DELIMITER ',', NULL '');
#copy ACCOUNT_ENTRY from '/Users/eriklacerda/Dev-Projects/ipocket/heroku-db/ACCOUNT_ENTRY.csv' WITH (DELIMITER ',', NULL '');
#copy BILL from '/Users/eriklacerda/Dev-Projects/ipocket/heroku-db/BILL.csv' WITH (DELIMITER ',', NULL '');
#copy BUDGET from '/Users/eriklacerda/Dev-Projects/ipocket/heroku-db/BUDGET.csv' WITH (DELIMITER ',', NULL '');
#copy BUDGET_ENTRY from '/Users/eriklacerda/Dev-Projects/ipocket/heroku-db/BUDGET_ENTRY.csv' WITH (DELIMITER ',', NULL '');
#copy INVESTMENT from '/Users/eriklacerda/Dev-Projects/ipocket/heroku-db/INVESTMENT.csv' WITH (DELIMITER ',', NULL '');
#copy INVESTMENT_ENTRY from '/Users/eriklacerda/Dev-Projects/ipocket/heroku-db/INVESTMENT_ENTRY.csv' WITH (DELIMITER ',', NULL '');
#copy INDEX from '/Users/eriklacerda/Dev-Projects/ipocket/heroku-db/INDEX.csv' WITH (DELIMITER ',', NULL '');

COPY INSTITUTION FROM '/Users/eriklacerda/Dev-Projects/savefy/postgresdb/data/system/db-postgres-load-institution.csv' DELIMITER ',' CSV;
COPY SANITIZE_PATTERN FROM '/Users/eriklacerda/Dev-Projects/savefy/postgresdb/data/system/db-postgres-load-sanitize-pattern.csv' DELIMITER ',' CSV;