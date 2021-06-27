#!/bin/sh

cd ionio/ || exit

mvn clean install

echo csv is in target/classes/csvdb/
java -jar target/ionio.jar

