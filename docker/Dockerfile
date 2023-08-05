FROM eclipse-temurin:17

# Add an unprivilged user that will be running our app
RUN groupadd -g 1000 app
RUN useradd -d /home/app -u 1000 -g 1000 -s /bin/bash app

COPY build/libs/thing-0.0.1-SNAPSHOT.jar /app.jar
COPY docker/run.sh /run.sh

USER app
ENTRYPOINT ["/run.sh"]
