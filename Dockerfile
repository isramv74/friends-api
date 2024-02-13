FROM eclipse-temurin:11
MAINTAINER Israel Martinez "Jose.Israel.Martinez.Vazquez@gmail.com"

EXPOSE 8080
RUN mkdir /opt/app
COPY build/libs/friends-api-0.1.war /opt/app
CMD ["java", "-Dgrails.env=prod -server -Xmx1024M", "-jar", "/opt/app/friends-api-0.1.war"]