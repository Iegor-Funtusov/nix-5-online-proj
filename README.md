# unit_1_compile
1. Запуск с консоли:
rm -rf target
javac -sourcepath src/ -d target/classes/actions -cp lib/lombok-1.18.20.jar:lib/junit-4.12.jar src/main/java/test/ContactTest.java src/main/java/domain/Contact.java src/main/java/business/ContactManager.java src/main/java/dao/*.java
java -cp target/classes/actions/:lib/junit-4.12.jar:lib/lombok-1.18.20.jar: test.ContactTest

2. Запуск через ant:
ant clean
ant compile
ant jar
ant run

3. Запуск через maven:
mvn clean install
mvn exe:java -D exec.mainClass=test.ContactTest
