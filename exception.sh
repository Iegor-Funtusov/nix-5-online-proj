#!/bin/sh

cd exception/ || exit
mvn clean install
cd target/ || exit
java -jar exception.jar
