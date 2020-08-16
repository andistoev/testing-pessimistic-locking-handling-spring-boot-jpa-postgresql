#!/usr/bin/env bash

echo "Starting postgres-test-db ..."
docker build -t postgres-test-db .
docker-compose up -d
