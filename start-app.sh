#! /bin/sh

docker-compose -f docker/ibeer-compose.yml up -d && \
./gradlew -S -i --parallel clean bootJar bootBuildImage && \
docker run -itd --name i-beer-api -p 8081:8081 --network docker_ibeer_network \
                --link ibeer-db -e JAVA_OPTS="--enable-preview" com.ipaixao.ibeer/i-beer:0.0.1-SNAPSHOT
