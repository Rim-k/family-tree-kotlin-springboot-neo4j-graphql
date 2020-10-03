#!/bin/sh

./gradlew bootJar

cp -rf src/main/docker build/

cp -f build/libs/demo-*.jar build/docker/backend-api/

docker-compose -f build/docker/docker-compose.yml up