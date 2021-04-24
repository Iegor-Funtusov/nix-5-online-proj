# nix-5-online-proj

1. Console compile:

javac -sourcepath .\src -d .\build -cp .\libs\commons-tex
t-1.1.1.8-jre15.jar;.\libs\commons-lang3-3.11.jar src\entity\User.java src\entity\Customer.java src\entity\OurCustomers.java

java -classpath .\build;.\libs\commons-text-1.1.1.8-jre15
.jar;.\libs\commons-lang3-3.11.jar;. entity.OurCustomers

2. Maven 

mvn clean install

mvn exec:java -D exec.mainClass=entity.OurCustomers