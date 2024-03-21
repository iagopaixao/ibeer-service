#! /bin/sh

docker-compose -p beer-stack -f docker/ibeer-compose.yml up -d && \
docker logs -f ibeer-service
