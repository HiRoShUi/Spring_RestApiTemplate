#!/bin/sh

set -e -x

cd source-code
  ./mvnw clean package
cd ..

cp source-code/org.makro.spring.restapi.general/target/restapi.jar  build-output/.
