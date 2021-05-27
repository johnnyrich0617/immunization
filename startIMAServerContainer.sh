#!/bin/sh

docker network inspect imadomain >/dev/null 2>&1 || docker network create --driver bridge imadomain
docker run -p 7090:7090 --net imadomain --name imaserver richardsonjh/immunization-server:latest
