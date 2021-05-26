FROM openjdk:11
USER root
ENV IMA_HOME /opt/ima
RUN groupadd -r ima -g 617
RUN useradd -u 617 -r -g ima -m -d /home/ima -s /sbin/nologin -c "ima user" ima
USER ima:ima
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} ${IMA_HOME}/immunizationServer.jar
WORKDIR /opt/ima
EXPOSE 7090
ENTRYPOINT ["java","-jar","immunizationServer.jar"]