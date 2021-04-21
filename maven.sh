#!/bin/sh

cd maven/ || exit

mvn clean install
mvn exec:java -D exec.mainClass=ua.com.nkrasnovoronka.unit1.Main
