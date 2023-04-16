# ShoppingList
Projekt systemu informatycznego na zaliczenie laboratorium został wykonany w oparciu o aplikacje napisane w Java SpringBoot z Thymeleaf oraz Android SDK w części aplikacji mobilej na telefony z systemem Android.  

Instrukcja uruchomienia Aplikacji ShoppingList  

1) sudo apt-get update  
2) sudo apt-get install -y openjdk-17-jre-headless maven git
3) rm -Rf ShoppingList  
4) git clone https://github.com/annadrobek/ShoppingList.git  
5) cd ShoppingList  
6) mvn compile  
7) mvn pacakge  
8) docker build -t annadrobek/shoppinglistdemo .  
9) docker login -u annadrobek --password-stdin  
10) docker push annadrobek/shoppinglistdemo  
11) docker run -itd -p 8080:10000 --name shoppinglist annadrobek/shoppinglistdemo:latest    

W celu optymlizacji procesu została zdefiniowana akcja GitHub wykonująca powyższe kroki za każdym razem jak będą wprowadzane zmiany w branchu "main". Wówczas proces uruchomienia aplikacji ogranicza się do  
1) docker run -itd -p 8080:10000 --name shoppinglist annadrobek/shoppinglistdemo:latest
