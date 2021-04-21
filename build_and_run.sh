#! /bin/bash

source_dir="src"
target_dir="out"
lib_dir="lib"
package_name="com/nixcourse"
main_class="Main"

if [ ! -d $target_dir ]; then
  mkdir $target_dir
else [ -d $target_dir/$package_name ]
  rm -rf out/*
fi

javac -sourcepath $source_dir -d $target_dir \
  -cp $lib_dir/junit-4.13.2.jar \
  $source_dir/$package_name/Main.java

java -cp $target_dir/:$lib_dir/junit-4.13.2.jar:. com.nixcourse.Main