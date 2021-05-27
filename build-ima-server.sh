#!/bin/sh

mvn clean install

docker build -t richardsonjh/immunization-server .