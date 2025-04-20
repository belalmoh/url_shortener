.PHONY: build-api run-api clean-api

build-api:
	cd shortener_service && ./mvnw clean install

run-api:
	cd shortener_service && ./mvnw spring-boot:run

clean-api:
	cd shortener_service && ./mvnw clean

