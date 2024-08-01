FROM amazoncorretto:17

WORKDIR .

COPY build/libs/caching-0.0.1-SNAPSHOT.jar .

ENTRYPOINT ["java", "-jar", "caching-0.0.1-SNAPSHOT.jar"]