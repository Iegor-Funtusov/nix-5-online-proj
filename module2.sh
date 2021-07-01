#!/bin/bash

cd module2/ || exit
mvn clean install
mvn exec:java
