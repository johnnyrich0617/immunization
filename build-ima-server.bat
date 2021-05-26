@echo off

call mvn clean install

call  docker build -t richardsonjh/immunization-server .