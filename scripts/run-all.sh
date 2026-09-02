#!/bin/bash
# Compile and run every OOP assignment program.
set -e
cd "$(dirname "$0")/.."
mkdir -p out
javac -d out $(find src -name '*.java')
echo "Compiled all programs."
echo

run() {
  echo "========== $1 =========="
  java -cp out "$1"
  echo
}

run partA.q01.Q01_Calculator
run partA.q02.Q02_Area
run partA.q03.Q03_Maximum
run partA.q04.Q04_StudentDisplay
run partA.q05.Q05_Printer
run partA.q06.Q06_NumberMultiply
run partB.q07.Q07_StudentConstructors
run partB.q08.Q08_EmployeeConstructors
run partB.q09.Q09_RectangleConstructors
run partB.q10.Q10_BoxConstructors
run partB.q11.Q11_BookConstructors
run partB.q12.Q12_StudentChaining
