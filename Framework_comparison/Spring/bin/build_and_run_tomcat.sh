#!/bin/bash
set -e
PORT="${1:-8080}"
docker build . -t jakartaee
docker run -p $PORT:8080 --name jakartaee_tomcat jakartaee
