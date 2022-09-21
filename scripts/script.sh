#!/bin/bash
echo "premier param : $1" 
clear
echo "		    * * * * * * * * * * * * * * "
echo "		    *            Menu         * "
echo "		* * * * * * * * * * * * * * * * * * *"
echo "		* [1] : Jouer contre l'ordinateur   *"
echo "		* [2] : Jeu aléatoire               *"
echo "		* * * * * * * * * * * * * * * * * * *"
read -p "Choisissez un nombre => " choix
if [ $choix -eq 1 ]
then
    cd ..
    javac -d build ./src/myPackage/*.java
    java -cp build jeu.Demo
else
    cd ..
    javac -d build ./src/myPackage/*.java
    java -cp build jeu.MainPersonnalise $1 $2 $3
fi
