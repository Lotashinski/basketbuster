#!/usr/bin/env bash

root=$(pwd)
servicesRoot="$root/services"

echo
echo "-====== Scan $servicesRoot ======-"
echo

services=$(ls "$servicesRoot/")

for var in $services
do
    nodeName="$servicesRoot/$var"
    if ! [ -d "$nodeName" ]; then
      continue
    fi

    if ! [ -f  "$nodeName/pom.xml" ]; then
      continue
    fi

    cd "$nodeName" || continue

    echo "Run build $var"

    mvn package -DskipTests 1> /dev/null &
done

unset var
wait

echo
echo "Build complete"

if [[ "$*" == *"docker"* ]]; then
    echo
    echo "-====== Run Docker ======-"
    echo

    docker compose up --build --force-recreate -d
fi
