if [ "$0" == "./cli_compile.sh" ]; then
  if [ -d ./build ]; then
    rm -rf ./build
  fi

  echo -n "use javac? (y/n):"
  read item
  case $item in
  y | Y)
    javac -classpath . -d ./build/classes Main.java
    ;;
  n | N)
    exit 0
    ;;
  esac

  echo -n "use java? (y/n):"
  read item
  case $item in
  y | Y)
    java -cp ./build/classes Main
    ;;
  n | N)
    exit 0
    ;;
  esac

  echo -n "make jar? (y/n):"
  read item
  case $item in
  y | Y)
    echo -e "main-class: Main\nclass-path: ../build/classes/" >./manifest.mf

    jar -cmf manifest.mf ./build/todolist.jar ./build/classes/*
    ;;
  n | N)
    exit 0
    ;;
  esac

  echo -n "run jar? (y/n):"
  read item
  case $item in
  y | Y)
    java -jar ./build/todolist.jar
    ;;
  n | N)
    exit 0
    ;;
  esac

else
  "please change yours location to threadedCode/unit_1_compile/folder_with_cli_compile_folder"
fi
