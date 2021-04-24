#! /bin/bash

source_dir="src/main/java"
target_dir="target"
package_name="com/nixcourse"
main_class="Main"

if [ ! -d $target_dir ]; then
  mkdir $target_dir
elif [ -d $target_dir/$package_name ]; then
  rm -rf $target_dir/*
fi

javac -sourcepath $source_dir -d $target_dir \
  -cp "$source_dir/util/ArrayListAlgorithms.java" \
  "$source_dir/$package_name/$main_class.java"

java -cp $target_dir "$(echo $package_name | tr -s '/' '.').$main_class"