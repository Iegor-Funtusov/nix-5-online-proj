#!/bin/sh

cd collection/ || exit

mvn clean install

java -jar collection/target/collectiom.jar
