#!/bin/bash

root=$(
  cd $(dirname $(readlink $0 || echo $0))/..
  /bin/pwd
)

${root}/bin/server.sh stop