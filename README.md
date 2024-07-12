# microservices_k8s 

### Docker image build and run  container:
- mvn clean
- mvn spring-boot:build-image
- docker run -p 8080:8080 kalynchuk/bookmarker-api:0.0.1-SNAPSHOT
### Build image with JIB:
- docker login registry-1.docker.io
- docker login registry.hub.docker.com
- mvn compile jib:build
### Upload image from https://hub.docker.com:
- docker pull kalynchukihor/bookmarker-api-jib:0.0.1-SNAPSHOT
### Run container:
- docker run -d -p 8080:8080 kalynchukihor/bookmarker-api-jib:0.0.1-SNAPSHOT
### Run dev environment:
- docker-compose up
