@echo off

rem Afficher le répertoire actuel
echo Répertoire actuel : %cd%

rem Changer de répertoire vers le dossier 'Jeu'
cd Jeu

rem Afficher le répertoire actuel
echo Répertoire actuel : %cd%

rem Compiler les fichiers Java en spécifiant le répertoire de destination pour les fichiers .class
javac @compile.list

rem Exécuter le programme Java
java Controleur

rem Mettre en pause pour voir les résultats
pause
