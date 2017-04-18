#!/bin/sh

# current running script path
scriptpath=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd -P)

. "$scriptpath/config.sh"

# use server in this path (extract it here if does not exist)
sql_server_root="$(dirname "$scriptpath")/$server_root_name"

# select mariadb version
if $cygwin || $mingw || $msys; then
  # use windows version
  if $x64; then
    sql_server_name='mariadb-10.1.17-winx64'
  elif $x86; then
    sql_server_name='mariadb-10.1.17-win32'
  else
    log 'Unsupported Windows architecture! Please stop the database server manually.\n'
    exit 1
  fi
  sql_server_admin_name='mysqladmin.exe'
elif $linux; then
  # use linux version
  if $x64; then
    sql_server_name='mariadb-10.1.17-linux-x86_64'
  elif $x86; then
    sql_server_name='mariadb-10.1.17-linux-i686'
  else
    log 'Unsupported Linux architecture! Please stop the database server manually.\n'
    exit 1
  fi
  sql_server_admin_name='mysqladmin'
else
  log 'Unsupported OS! Please stop the database server manually.\n'
  exit 1
fi
sql_server_dir="$sql_server_root/$sql_server_name"
sql_server_bin_dir="$sql_server_dir/bin"
sql_server_admin="$sql_server_bin_dir/$sql_server_admin_name"

"$sql_server_admin" -u root shutdown