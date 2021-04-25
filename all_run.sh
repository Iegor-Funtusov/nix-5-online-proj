
#!/bin/sh
echo "start console build"
. ./console_run.sh;
echo "finish console build"
echo "start ant build"
cd ..
. ./ant_run.sh
echo "finish ant build"
echo "start maven build"
cd ..
. ./maven_run.sh
echo "finish maven build"