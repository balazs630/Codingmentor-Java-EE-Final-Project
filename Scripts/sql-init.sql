SELECT 'CONFIG BEGIN' AS '';

CREATE DATABASE IF NOT EXISTS ProjectHandlerDB;
GRANT ALL PRIVILEGES ON ProjectHandlerDB.* To 'ProjectHandlerUser'@'localhost' IDENTIFIED BY 'ProjectHandlerPassword';

SELECT 'CONFIG END' AS '';