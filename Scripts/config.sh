#!/bin/sh

server_root_name='Server'
server_name='wildfly-10.0.0.Final'
server_config_xml='standalone-full.xml'

# cli tools in bin directory
jboss_cli_name='jboss-cli.sh'
add_user_name='add-user.sh'
standalone_name='standalone.sh'

# jboss cli init file
init_cli_name='init.cli'

# pid file used by the server
server_pidfile_name='wildfly.pid'

# ports used by wildfly
server_ports='9990 9993 8009 8787 8080 8443 3528 3529 4712 4713'

# if no server found, look for this archive
server_zip_name="$server_name.zip"

# url to download server archive if not exists
server_download_url="http://download.jboss.org/wildfly/10.0.0.Final/$server_zip_name"

# mariadb jdbc driver
jdbc_driver_name='mysql-connector-java-5.1.9.jar'
jdbc_driver_download_url="https://dl.dropboxusercontent.com/u/36518295/mariadb/$jdbc_driver_name"

# mariadb

# ports used by sql server
sql_server_ports='3306'

sql_server_pidfile_name='mysql.pid'

init_sql_name='sql-init-test.sql'

# project

project_core_relative_path='Project-and-Task Handler/Project-and-Task-Handler-core'
project_core_deploy_relative_path='Project-and-Task-Handler-core-ear/target/Project-and-Task-Handler-core-ear-1.0-SNAPSHOT.ear'

# OS
x64=false;
x86=false;
if [ $(getconf LONG_BIT) = "64" ]
then
  x64=true
else
  x86=true
fi

cygwin=false;
mingw=false;
msys=false;
linux=false;
darwin=false;
case "$(uname)" in
  CYGWIN*) cygwin=true ;;
  MINGW*) mingw=true ;;
  Msys*) msys=true ;;
  Linux) linux=true;;
  Darwin*) darwin=true ;;
esac

netstat='netstat -tlpn 2>/dev/null'

if $mingw || $msys || $cygwin; then
  netstat='netstat -ano -p tcp'
fi

if $darwin; then
  netstat='netstat -anv -p tcp'
fi