FROM openjdk:21-slim
ENV APP_FILE gateway-service-1.0.jar
ENV APP_HOME /usr/apps
EXPOSE 80
COPY target/$APP_FILE $APP_HOME/
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $APP_FILE"]
