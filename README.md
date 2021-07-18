# Api REST Earthquake

Proyecto REST para el ramo Computación Paralela y Distribuída

## Objetivos
- Comprender el funcionamiento del protocolo HTTP (sus verbos y estados).
- Comprender el funcionamiento de aplicaciones stateless, mecanismos asíncronos y funcionamiento REST).

## Especificaciones

Desarrollar una aplicación REST que sea capaz de conectarse al Centro Sismológico Nacional (dependiente de la Universidad de Chile).

Utilizar mecanismos de Autenticación, Modelo de datos, Scraping (para la obtención 
de datos), Soporte Cors, Dockers y su correspondiente salida ya sea exitosa 
o errónea.

## Implementación

Instalación nativa desde el repositorio en Ubuntu 20.04 LTS de 64 bits.
Configurado en el puerto localhost:8080
Base de datos con PostgreSQL

## Ejecución

Al clonar el repositorio ubicarse en la carpeta /App-REST-Earthquake/
Posteriormente ingresar el siguiente comando para la ejecución del Servicio
```sh
mvn -f cpyd/ clean install && java -jar cpyd/target/cpyd.war
```

## Consumo
Una vez levantado el servicio, para comprobar su funcionamiento y consumir la Api REST ingresamos a: 
http://localhost:8080/cpyd/swagger-ui/
