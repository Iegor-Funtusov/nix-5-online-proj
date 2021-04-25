# nix-5-online-proj

For the compiling by the command line you should to open "CommandLineCompile.ps1" using PowerShall
or enter to command line next commands:

javac -d ./Hw1/classes ./Hw1/src/*

java -classpath ./Hw1/classes Main


For the compiling by the Maven you should to open "MavenCompile.ps1" using PowerShall
or enter to command line next commands:

mvn clean

mvn compile

java -classpath ./Hw1/classes Main


For the compiling by the Ant you should to open "AntCompile.ps1" using PowerShall
or enter to command line next commands:

ant clean

ant compile

ant run
