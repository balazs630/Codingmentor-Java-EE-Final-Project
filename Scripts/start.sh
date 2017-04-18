#!/bin/sh

# current running script path
scriptpath=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd -P)

# initialize sql server, create database, user
"$scriptpath/sql-init.sh"
result=$?

if [ "$result" -ne 0 ]; then
  printf 'Failed to start new SQL Server process, exiting.\n'
  exit $result
fi

# initialize wildfly, deploy project
"$scriptpath/init.sh"
result=$?

if [ "$result" -ne 0 ]; then
  printf 'Failed to start new Wildfly process, exiting.\n'
  exit $result
fi
