#!/bin/sh
cd console/ || exit
javac -sourcepath ./src/ -d build/classes/ -cp ./libs/jsoup-1.13.1.jar:./libs/commons-lang3-3.11.jar
src/ua/example1/PatientCommunication.java src/ua/example1/PatientInformation.java src/ua/example1/Patient.java

java -cp build/classes/:./libs/commons-lang3-3.11.jar:. ua.example1.Patient