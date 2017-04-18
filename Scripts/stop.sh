#!/bin/sh

# current running script path
scriptpath=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd -P)

# stop wildfly
"$scriptpath/wildfly-stop.sh"

# stop mariadb
"$scriptpath/sql-stop.sh"