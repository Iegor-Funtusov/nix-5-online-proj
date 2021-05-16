### unit_1_compile

1. console-build:
    - run cli_compile.sh

2. ant_build:
    - in the folder ant-build/ant run '. ./setantenv.sh' (linux), cd ..;
    - use 'ant clean/compile/jar/run jar/run' command in cli

example: ant compile

3. mvn-build:
    - use mvn clean/compile/install/package in cli or from IDE
    - use java -jar target/mvn-build*.jar to run