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
run partC.q13.Q13_StudentCompare
run partC.q14.Q14_EmployeeCompareSalary
run partC.q15.Q15_DistanceAdd
run partC.q16.Q16_NumberAdd
run partC.q17.Q17_TimeAdd
run partC.q18.Q18_ComplexAdd
run partD.q19.Q19_BoxCompare
run partD.q20.Q20_CalculatorNumbers
run partD.q21.Q21_BankAccountTransfer
run partD.q22.Q22_ProductComparePrice
run partD.q23.Q23_RectangleCompareArea
run partD.q24.Q24_LibraryBookExample
run inheritance.partA.q01.Q01_EmployeeManager
run inheritance.partA.q02.Q02_VehicleCarBike
run inheritance.partA.q03.Q03_BankAccounts
run inheritance.partA.q04.Q04_PersonStudentResult
run inheritance.partA.q05.Q05_Shapes
run inheritance.partB.q06.Q06_AnimalSounds
run inheritance.partB.q07.Q07_BankInterest
run inheritance.partB.q08.Q08_EmployeeSalary
run inheritance.partB.q09.Q09_Payments
run inheritance.partB.q10.Q10_TransportFare
run inheritance.partC.q11.Q11_StaticHiding
run inheritance.partC.q12.Q12_OverrideVsHide
run inheritance.partC.q13.Q13_ThreeLevelHiding
run inheritance.partC.q14.Q14_EmployeeManagerHide
run inheritance.partC.q15.Q15_ShoppingSystem
