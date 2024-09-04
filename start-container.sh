#! /bin/sh

docker-compose -p beer-stack -f docker/ibeer-resources-compose.yml up -d && \
docker logs -f ibeer-service
