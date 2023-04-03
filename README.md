# Sistema de fidelización de clientes.
## Trabajo Grupal de la materia Backend de IIN, Facultad Politécnica de la UNA.

### Colaboradores
* Junior Gutierrez [@jg2kpy](https://github.com/jg2kpy)
* Alejandro Cabral [@alcabvaldo](https://github.com/alcabvaldo)
* Jessica Alarcon [@JessiProgram](https://github.com/JessiProgram)
* Guille Paiva [@guillepaivag1605](https://github.com/guillepaivag1605)

## Requerimientos
 * JDK (Java Development Kit) 11.0.2
 * Maven 3
 * WildFly 18.0.1
 * PostgreSQL 13

### Debian-based OS (Debian, Ubuntu, etc)
   Actualizar repositorio
   
```sh
sudo apt update
```

   JDK 11
```sh
sudo apt install default-jdk
```

   Maven
```sh
sudo apt install maven
```

   PostgreSQL
```sh
sudo apt install postgresql
```

   WildFly
```sh
wget https://download.jboss.org/wildfly/18.0.1.Final/wildfly-18.0.1.Final.zip
```

Si no queremos instalar PostgreSQL entonces podemos usar docker
```sh
docker-compose up -d
```

### WindowsNT-based OS (Windows 10, Windows 11, etc)
Usar los instaladores

JDK 11 por Oracle: https://download.java.net/java/GA/jdk11/9/GPL/openjdk-11.0.2_windows-x64_bin.zip

Maven por Apache: https://dlcdn.apache.org/maven/maven-3/3.9.1/binaries/apache-maven-3.9.1-bin.zip

PostgreSQL: https://www.enterprisedb.com/downloads/postgres-postgresql-downloads

WildFly: https://download.jboss.org/wildfly/18.0.1.Final/wildfly-18.0.1.Final.zip

### Variables de entorno
Debemos poner en variables de entorno una direccion de email y su contraseña para poder usar el servicio de envio de correo electronico
```sh
alias user=*usuario*
alias pass=*contraseña*
```
