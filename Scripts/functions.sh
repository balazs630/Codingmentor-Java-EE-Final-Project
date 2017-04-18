#!/bin/sh

log () {
  if ! $quiet; then
    printf "$@"
  fi
}

confirm () {
  log 'Press ENTER to continue anyway\n'
  
  if ! $quiet; then
    local line
    read line
  fi
}

check_commands () {
  if ! command -v mvn >/dev/null 2>&1; then
    log 'WARNING: The command "mvn" not found.\nProject building will fail.\n'
    confirm
  fi
  
  if ! command -v curl >/dev/null 2>&1; then
    log 'WARNING: The command "curl" not found.\nScript will fail unless the required files are downloaded manually.\n'
    confirm
  fi
  
  if ! command -v netstat >/dev/null 2>&1; then
    log 'WARNING: The command "netstat" not found.\nPort checking will fail.\n'
    confirm
  fi
  
  if ! command -v unzip >/dev/null 2>&1; then
    log 'WARNING: The command "unzip" not found.\nScript will fail unless the .zip archives are extracted manually.\n'
    confirm
  fi
  
  if ! command -v tar >/dev/null 2>&1; then
    log 'WARNING: The command "tar" not found.\nScript will fail unless the .tar archives are extracted manually.\n'
    confirm
  fi
}

# downloads a file from url, displaying given description
download_file () {
  if $quiet; then
    curl -sS -o "$1" "$2"
  else
    log 'Downloading %s...\n' "$3"
    curl -# -o "$1" "$2"
  fi
}

# add a management user
add_management_user () {
  "$add_user" --silent "$1" "$2"
  log 'Added wildfly management user: %s\n' "$1"
}

# check if PIDFILE exists
pidfile_check () {
  local pidfile="$1"
  local flags='-p'
  if $mingw || $msys || $cygwin; then
    # show windows processes
    flags='-W -p'
  fi
  if [ -f "$pidfile" ]; then
    if ! $quiet; then
      log 'PIDFIlE found:\n'
      log '%s\n' "$pidfile"
      if ps $flags $(cat "$pidfile"); then
        log 'Process already running.\n'
      else
        log 'Process not found, it may have crashed. Delete the pid file manually and try again.\n'
      fi
    fi
    exit 1
  fi
}

port_check () {
  local port="$1"
  shift
  eval "$netstat" | grep -E "$@" "[.:]$port(\$|\s)"
  return $?
}

ports_require () {
  local ports="$1"
  for port in $(printf '%s' "$ports"); do
    if ! port_check "$port" -q; then
      if $quiet; then
        exit 1
      fi
      log 'WARNING: No process found listening on port %s\n' "$port"
      confirm
    fi
  done
}

# check if ports are in use
ports_check () {
  local ports="$1"
  for port in $(printf '%s' "$ports"); do
    if port_check "$port"; then
      if $quiet; then
        exit 1
      fi
      log 'WARNING: Port %s may be in use!\n' "$port"
      confirm
    fi
  done
}

extract_zip () {
  unzip -q "$1" -d "$2"
}

extract_targz () {
  tar -xzf "$1" -C "$2"
}
