#!/bin/sh

# current running script path
scriptpath=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd -P)

. "$scriptpath/config.sh"

server_root="$(dirname "$scriptpath")/$server_root_name"
server_dir="$server_root/$server_name"
server_bin_dir="$server_dir/bin"
jboss_cli="$server_bin_dir/$jboss_cli_name"

"$jboss_cli" --connect command=:shutdown
