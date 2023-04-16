[![Docker Image CI & CD](https://github.com/annadrobek/ShoppingList/actions/workflows/main.yml/badge.svg)](https://github.com/annadrobek/ShoppingList/actions/workflows/main.yml)

# ShoppingList
Projekt systemu informatycznego na zaliczenie laboratorium został wykonany w oparciu o aplikacje napisane w Java SpringBoot z Thymeleaf oraz Android SDK w części aplikacji mobilej na telefony z systemem Android.  

**Założenia wstępne tożsame dla uruchomienia aplikacji w dockerze
**Port lokalny 10000 jest wolny
**Docker jest zainstalowany lokalnie

```diff
@@ **Instrukcja uruchomienia aplikacji ShoppingList w kontenerze Docker
```
1) docker build -t shoppinglistdemo .  
2) docker run -itd -p 10000:8080 --name shoppinglist shoppinglistdemo:latest  
3) otwórz przeglądarkę na adresie http://localhost:10000

```diff
+W celu optymlizacji procesu została zdefiniowana akcja GitHub tworząca obrazy dockerowe.
+Akcja będzie się uruchamiać po każdym wprowadzaniu zmiany w branchu "main".
+Wówczas proces uruchomienia aplikacji ogranicza się do
```
1) docker run -itd -p 10000:8080 --name shoppinglist annadrobek/shoppinglistdemo:latest
2) otwórz przeglądarkę na adresie http://localhost:10000

```diff
-**Instrukcja kompilacji aplikacji ShoppingList w środowisku Linux (Debian/Ubuntu)**  
```
1) sudo apt-get update  
2) sudo apt-get install -y openjdk-17-jre-headless maven git
3) rm -Rf ShoppingList  
4) git clone https://github.com/annadrobek/ShoppingList.git  
5) cd ShoppingList  
6) mvn compile  
7) mvn package   

```diff
-**Instrukcja kompilacji aplikacji ShoppingList w środowisku Windows**  
```
1) Pobierz i zainstaluj https://aka.ms/download-jdk/microsoft-jdk-17.0.6-windows-x64.msi  
2) W celu instalacji maven wykonaj kroki z https://phoenixnap.com/kb/install-maven-windows  
3) Pobierz i zainstaluj https://github.com/git-for-windows/git/releases/download/v2.40.0.windows.1/Git-2.40.0-64-bit.exe
4) Preferowany restart systemu operacyjnego
5) Wciśnij klawisz z logiem Windows na klawiaturze i wpisz cmd
6) rmdir ShoppingList
7) git clone https://github.com/annadrobek/ShoppingList.git  
8) cd ShoppingList  
9) mvn compile  
10) mvn package  
