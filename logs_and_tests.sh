#!/bin/sh

cd logs_and_tests/ || exit

mvn clean install

java -jar app/target/logs_and_tests.jar
