#!/bin/sh

cd maven/ || exit

mvn clean install
mvn exec:java -D exec.mainClass=ua.example2.Patient