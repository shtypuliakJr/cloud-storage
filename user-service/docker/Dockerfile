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
ENV JAVA_OPTS -Dspring.config.additional-location=user:/opt/user-service/application.properties
ENV JAVA_DEBUG -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:6001

COPY --from=builder /src/build/libs/user-service.jar /opt/user-service/user-service-$VERSION.jar
COPY ./docker/local-application.properties /opt/user-service/application.properties

WORKDIR /opt/user-service/
CMD java $JAVA_OPTS $JAVA_DEBUG -jar user-service.jar
