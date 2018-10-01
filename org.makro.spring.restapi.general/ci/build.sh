#!/bin/sh

set -e -x

cd source-code
  ./mvnw clean package
cd ..

cp source-code/target/restapi.jar  build-output/.
