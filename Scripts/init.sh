#!/bin/sh

# parameters
quiet=false
force=false
debug=false
while getopts 'hqfwd' flag; do
  case "$flag" in
  h)
    cat <<-'EOF'
			./init.sh [options]

			options:
			-h	show this help
			-q	quiet, non-interactive mode
			-f	force, skip checks
			-d 	debug mode
			EOF
    exit 0
    ;;
  q) quiet=true ;;
  f) force=true ;;
  d) debug=true ;;
  esac
done

# current running script path
scriptpath=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd -P)

. "$scriptpath/config.sh"
. "$scriptpath/functions.sh"

# jboss cli init file
init_cli="$scriptpath/$init_cli_name"

# use server in this path (extract it here if does not exist)
server_root="$(dirname "$scriptpath")/$server_root_name"
server_dir="$server_root/$server_name"

# wildfly, server cli tools path
server_bin_dir="$server_dir/bin"

# log directory path
server_log_dir="$server_dir/standalone/log"

# wildfly command line tools
jboss_cli="$server_bin_dir/$jboss_cli_name"
add_user="$server_bin_dir/$add_user_name"
standalone="$server_bin_dir/$standalone_name"

# if no server found, look for this archive
server_zip="$server_root/$server_zip_name"

# pid file used by the server
server_pidfile="$server_root/$server_pidfile_name"

# mariadb jdbc driver
jdbc_driver="$server_root/$jdbc_driver_name"

# project directory
project_core="$(dirname "$scriptpath")/$project_core_relative_path"
project_core_deploy="$project_core/$project_core_deploy_relative_path"

if ! $force; then
  # do checks before launching wildfly
  check_commands
  pidfile_check "$server_pidfile"
  ports_require "$sql_server_ports"
  ports_check "$server_ports"
fi


# check if server is ready to run
if [ ! -d "$server_dir" ]; then
  mkdir -p "$server_root"
  
  # check if already downloaded
  if [ ! -f "$server_zip" ]; then
    download_file "$server_zip" "$server_download_url" "$server_name"
  fi

  log 'Extracting server...\n'
  unzip -q "$server_zip" -d "$server_root"
fi

mkdir -p "$server_log_dir"

# check if jdbc driver is ready to deploy
if [ ! -f "$jdbc_driver" ]; then
  download_file "$jdbc_driver" "$jdbc_driver_download_url" "$jdbc_driver_name"
fi

# check if project is ready to deploy
if [ -d "$project_core" ]; then
  cd "$project_core"
  log 'Building project...\n'
  mvn -q clean install
  cd - >/dev/null 2>&1
else
  log 'WARNING: Project core directory not found!\n%s\n' "$project_core"
  confirm
fi

# fix mingw in cli utils
if ! $darwin; then
  if ! grep -q 'MINGW' "$add_user"; then
    sed -i "s/-i CYGWIN/-i 'CYGWIN\\\\|MINGW\\\\|Msys'/" "$add_user"
  fi

  if ! grep -q 'MINGW' "$jboss_cli"; then
    sed -i 's/cygwin=true/&\n        ;;\n    MINGW*)\n        cygwin=true\n        ;;\n    Msys*)\n        cygwin=true/' "$jboss_cli"
  fi

  if ! grep -q 'MINGW' "$standalone"; then
    sed -i 's/cygwin=true/&\n        ;;\n    MINGW*)\n        cygwin=true\n        ;;\n    Msys*)\n        cygwin=true/' "$standalone"
  fi
fi

# default management user: admin:admin
add_management_user 'admin' 'admin'

# start wildfly server
log 'Starting Wildfly server...\n'
export LAUNCH_JBOSS_IN_BACKGROUND=1
export JBOSS_PIDFILE="$server_pidfile"
if $debug; then
  log 'DEBUG MODE\n'
  nohup "$standalone" --debug -c "$server_config_xml" </dev/null >"$server_log_dir/console.log" 2>&1 &
else
  nohup "$standalone" -c "$server_config_xml" </dev/null >"$server_log_dir/console.log" 2>&1 &
fi

while [ ! -f "$server_pidfile" ]; do
  # wait for pid file to be created
  sleep 1
done
sleep 3

# configure server
cd "$server_root"

log 'Configuring server using JBOSS-CLI...\n'
jboss_cli_setup=false
while ! $jboss_cli_setup; do
  jboss_out=$("$jboss_cli" --file="$init_cli" 2>&1)
  log 'JBOSS-CLI output:\n%s\n' "$jboss_out"

  if ! printf '%s' "$jboss_out" | grep -q 'CONFIG END'; then
    # wait for server to get ready to accept connections
    log 'Retrying JBOSS-CLI connection...\n'
    sleep 1
  else
    jboss_cli_setup=true
    log 'Configuration OK\n'
  fi
done

cd - >/dev/null 2>&1

# display server information
log 'To watch log:\ntail -f "%s"\n' "$server_log_dir/console.log"
log 'To watch server log:\ntail -f "%s"\n' "$server_log_dir/server.log"
log 'Server started, pid: %s\n' "$(cat "$server_pidfile")"
