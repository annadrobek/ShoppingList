FROM maven:latest as builder
RUN git clone https://github.com/annadrobek/ShoppingList.git
WORKDIR /ShoppingList
RUN mvn compile
RUN mvn pacakge
FROM tomcat
COPY --from=builder target/demo-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
