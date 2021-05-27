@echo off

docker network inspect imadomain > NUL || docker network create --driver bridge imadomain
docker run -p 7090:7090 --net imadomain --name imaserver richardsonjh/immunization-server:latest
