#!/usr/bin/env bash
if [ -d classes ]; then
    rm -rf classes;
fi
mkdir classes

javac -cp C:\Program Files\Java\jdk1.8.0_291\lib\tools.jar com/myz/java/study/ast/anno/MySetter* -d classes/

javac -cp classes -d classes -processor com/myz/java/study/ast/MyAnnotationProcessor com/myz/java/study/ast/AstUser.java

javap -p classes com/myz/java/study/ast/AstUser.class

java -cp classes com/myz/java/study/ast/AstUser/AstUser