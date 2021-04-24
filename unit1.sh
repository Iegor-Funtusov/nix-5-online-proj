#!/bin/sh
echo "start console build"
. ./console.sh;
echo "finish console build"
echo "start ant build"
cd ..
. ./ant.sh
echo "finish ant build"
echo "start maven build"
cd ..
. ./maven.sh
echo "finish maven build"
