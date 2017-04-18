#!/bin/sh

# parameters
quiet=false
force=false
while getopts 'hqf' flag; do
  case "$flag" in
  h)
    cat <<-'EOF'
			./sql-init.sh [options]

			options:
			-h	show this help
			-q	quiet, non-interactive mode
			-f	force, skip checks
			EOF
    exit 0
    ;;
  q) quiet=true ;;
  f) force=true ;;
  esac
done

# current running script path
scriptpath=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd -P)

. "$scriptpath/config.sh"
. "$scriptpath/functions.sh"

# use server in this path (extract it here if does not exist)
sql_server_root="$(dirname "$scriptpath")/$server_root_name"

# pid file used by the sql server
sql_server_pidfile="$sql_server_root/$sql_server_pidfile_name"

init_sql="$scriptpath/$init_sql_name"

# select mariadb version
if $cygwin || $mingw || $msys; then
  # use windows version
  if $x64; then
    sql_server_name='mariadb-10.1.17-winx64'
    sql_server_zip_name="$sql_server_name.zip"
    #sql_server_download_url="https://downloads.mariadb.org/f/mariadb-10.1.17/winx64-packages/$sql_server_zip_name?serve"
    sql_server_download_url="https://dl.dropboxusercontent.com/u/36518295/mariadb/$sql_server_zip_name"
  elif $x86; then
    sql_server_name='mariadb-10.1.17-win32'
    sql_server_zip_name="$sql_server_name.zip"
    #sql_server_download_url="https://downloads.mariadb.org/f/mariadb-10.1.17/win32-packages/$sql_server_zip_name?serve"
    sql_server_download_url="https://dl.dropboxusercontent.com/u/36518295/mariadb/$sql_server_zip_name"
  else
    log 'Unsupported Windows architecture! Please set up the database manually.\n'
    exit 1
  fi
  sql_server_monitor_name='mysql.exe'
  sql_server_daemon_name='mysqld.exe'
  sql_server_daemon_extra_args=''
  sql_server_installer_name='bin/mysql_install_db.exe'
  sql_server_installer_extra_args=''
  unzip_command='extract_zip'
elif $linux; then
  # use linux version
  if $x64; then
    sql_server_name='mariadb-10.1.17-linux-x86_64'
    sql_server_zip_name="$sql_server_name.tar.gz"
    #sql_server_download_url="https://downloads.mariadb.org/f/mariadb-10.1.17/bintar-linux-x86_64/$sql_server_zip_name?serve"
    sql_server_download_url="https://dl.dropboxusercontent.com/u/36518295/mariadb/$sql_server_zip_name"
  elif $x86; then
    sql_server_name='mariadb-10.1.17-linux-i686'
    sql_server_zip_name="$sql_server_name.tar.gz"
    #sql_server_download_url="https://downloads.mariadb.org/f/mariadb-10.1.17/bintar-linux-x86/$sql_server_zip_name?serve"
    sql_server_download_url="https://dl.dropboxusercontent.com/u/36518295/mariadb/$sql_server_zip_name"
  else
    log 'Unsupported Linux architecture! Please set up the database manually.\n'
    exit 1
  fi
  sql_server_monitor_name='mysql'
  sql_server_daemon_name='mysqld_safe'
    sql_server_daemon_extra_args="--ledir='$sql_server_root/$sql_server_name/bin'"
  sql_server_installer_name='scripts/mysql_install_db'
  sql_server_installer_extra_args='--no-defaults'
  unzip_command='extract_targz'
else
  log 'Unsupported OS! Please set up the database manually.\n'
  exit 1
fi

# sql server variables
sql_server_zip="$sql_server_root/$sql_server_zip_name"
sql_server_dir="$sql_server_root/$sql_server_name"
sql_server_bin_dir="$sql_server_dir/bin"
sql_server_monitor="$sql_server_bin_dir/$sql_server_monitor_name"
sql_server_daemon="$sql_server_bin_dir/$sql_server_daemon_name"
sql_server_installer="$sql_server_dir/$sql_server_installer_name"

if ! $force; then
  # do checks before launching sql server
  check_commands
  pidfile_check "$sql_server_pidfile"
  ports_check "$sql_server_ports"
fi

# check if sql server is ready to run
if [ ! -d "$sql_server_dir" ]; then
  mkdir -p "$sql_server_root"

  # check if already downloaded
  if [ ! -f "$sql_server_zip" ]; then
    download_file "$sql_server_zip" "$sql_server_download_url" "$sql_server_name"
  fi

  log 'Extracting SQL server...\n'
  "$unzip_command" "$sql_server_zip" "$sql_server_root"
fi

# default config on windows
_win_copied=false
if [ -f "$sql_server_dir/my-small.ini" ] && [ ! -f "$sql_server_dir/my.ini" ]; then
  _win_copied=true
  cp "$sql_server_dir/my-small.ini" "$sql_server_dir/my.ini"
fi

# detect if data directory needs to be initialized
if { $linux && [ ! -d "$sql_server_dir/data/mysql" ]; } || $_win_copied; then
  log 'Initializing data directory...\n'
  "$sql_server_installer" $sql_server_installer_extra_args --basedir="$sql_server_dir" --datadir="$sql_server_dir/data" >/dev/null 2>&1
fi

log 'Starting SQL server...\n'
eval nohup "'$sql_server_daemon'" "$sql_server_daemon_extra_args" --basedir="'$sql_server_dir'" --datadir="'$sql_server_dir/data'" --pid-file="'$sql_server_pidfile'" >/dev/null 2>&1 &

while [ ! -f "$sql_server_pidfile" ]; do
  # wait for pid file to be created
  sleep 1
done
sleep 3

log 'Configuring SQL server...\n'
sql_setup=false
while ! $sql_setup; do
  sql_setup_out=$("$sql_server_monitor" --user root <"$init_sql" 2>&1)
  log 'MYSQL output:\n%s\n' "$sql_setup_out"

  if ! printf '%s' "$sql_setup_out" | grep -q "CONFIG END"; then
    # wait for sql server to get ready to accept connections
    log 'Retrying MYSQL connection...\n'
    sleep 1
  else
    sql_setup=true
    log 'Configuration OK\n'
  fi
done

# display server information
log 'SQL server started, pid: %s\n' "$(cat "$sql_server_pidfile")"
