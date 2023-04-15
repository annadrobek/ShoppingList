# ShoppingList
Projekt systemu informatycznego na zaliczenie laboratorium został wykonany w oparciu o aplikacje napisane w Java SpringBoot. 
Wykonany system informatyczny zbudowany będzie w oparciu o framework thymeleaf.

Instrukcja uruchomienia Aplikacji ShoppingList

Budowanie i osadzanie środowiska
Budowanie ręczne obrazu Doker
Do budowania ręcznego konieczne jest posiadanie środowiska docker. 
Aby zbudować obraz dockerowy należy uruchomić polecenie
docker build . --file Dockerfile --tag annadrobek/ShoppingList:latest
a następnie
docker login -u $DOCKER_USER -p $DOCKER_PASSWORD
aby finalnie móc wykonać polecenie
docker push annadrobek/ShoppingList:latest
