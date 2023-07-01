#! /bin/sh

docker-compose -f docker/ibeer-compose.yml up -d && \
docker logs -f ibeer-service
