#!/bin/sh

cd antrun/ant/ || exit
. ./setantenv.sh

cd ..
ant compile
ant jar
ant run
ant clean

