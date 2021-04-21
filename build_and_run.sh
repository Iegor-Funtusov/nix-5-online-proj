#! /bin/bash

source_dir="src"
target_dir="out"
lib_dir="lib"
package_name="com/nixcourse"
main_class="TestApp"

dependencies=($( ls lib/* ))
dependencies_list=""

for i in $dependencies; do
  dependencies_list+=":$i"
done

if [ ! -d $target_dir ]; then
  mkdir $target_dir
elif [ -d $target_dir/$package_name ]; then
  rm -rf out/*
fi

javac -sourcepath $source_dir -d $target_dir \
  -cp $dependencies_list \
  "$source_dir/main/java/$package_name/User.java" \
  "$source_dir/test/java/$package_name/$main_class.java"

java -cp "out/$dependencies_list" \
  org.junit.runner.JUnitCore \
  "$(echo $package_name | tr -s '/' '.').$main_class"
