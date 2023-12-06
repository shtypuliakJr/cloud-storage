ARG GRADLE_IMG=gradle:7.4.2-jdk17
ARG JAVA_IMG=amazoncorretto:17

FROM $GRADLE_IMG AS builder
WORKDIR /src
COPY . /src/

ENV GRADLE_OPTS -Dorg.gradle.daemon=false
ARG VERSION
RUN gradle clean assemble -Pversion=$VERSION

FROM $JAVA_IMG

ARG VERSION
ENV JAVA_OPTS -Dspring.config.additional-location=file:/opt/notification-service/application.properties
ENV JAVA_DEBUG -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:6001

COPY --from=builder /src/build/libs/notification-service-$VERSION.jar /opt/notification-service/notification-service.jar
COPY ./docker/local-application.properties /opt/notification-service/application.properties

WORKDIR /opt/notification-service/
CMD java $JAVA_OPTS $JAVA_DEBUG -jar notification-service.jar
