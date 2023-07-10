Considere a branch main

## Pre requisitos:
 - maven
 - docker
 - docker compose


## Para executar o projeto

O banco, back e front estao no mesmo docker compose, para executar o projeto basta executar os comandos abaixo:

``` bash
docker compose build --no-cache
docker compose up
```

## Para acessar o front
[http://localhost:3000](http://localhost:3000)


## Para acessar o swagger
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

``` bash