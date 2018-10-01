#!/bin/sh

set -e -x

cd source-code/org.makro.spring.restapi.general
	./mvnw clean package
cd ..
cd ..

cp source-code/org.makro.spring.restapi.general/target/restapi-1.0.0.jar  build-output/.
