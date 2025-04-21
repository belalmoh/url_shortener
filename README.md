# URL Shortener Project

This is a project where it basically shortens the url and redirects to the original url.

## Tech Stack

- Spring Boot
- MongoDB

## Features

- Create Short URL
- Redirect to Original URL
- Delete Short URL
- Get All Short URLs

## Swagger UI

### Get All Endpoints with Swagger UI
- <host>:<port>/api-docs.html -> Example: http://localhost:8080/api-docs.html


## Steps to run the project

1. Clone the repository
2. Rename sample.env to .env and change the environment variables accordingly
3. Run `make build-img` to build the docker image
4. Run `make run-api` to run the project
5. (Optional) _Open `http://localhost:9091` to see the grafana dashboard_
6. (Optional) _Open `http://localhost:9090` to see the prometheus dashboard_

