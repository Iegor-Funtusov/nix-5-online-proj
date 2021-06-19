#!/bin/sh

cd collection/ || exit

mvn clean install

cd target/ || exit

java -jar collection.jar
