Considere a branch main

## Pre requisitos:
 - linux
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


### Caso queira rodar com graal-native
antes acompanhe este [doc sobre graal](https://docs.spring.io/spring-boot/docs/current/reference/html/native-image.html#native-image.introducing-graalvm-native-images.key-differences-with-jvm-deployments)
eh importante entender o funcionamento dos [agents](https://www.graalvm.org/22.0/reference-manual/native-image/Agent/)

gere o jar

``` bash
mvn package
```

execute o agent para identificar as chamadas com reflection e gerar os arquivos de configuracao
copie e cole os arquivos gerados para o diretorio resources/META-INF/native-image

``` bash
java -agentlib:native-image-agent=config-output-dir=/your-path/ -jar target/bycoders-0.0.1-SNAPSHOT.jar
```

execute o comando para gerar a imagem docker com o binario nativo

``` bash
mvn -Pnative spring-boot:build-image
```

execute o docker container

``` bash
docker run --rm -p 8080:8080 docker.io/library/bycoders:0.0.1-SNAPSHOT
```




