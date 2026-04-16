# Spring Boot Application

Esta es una aplicación robusta construida con **Java 17** y **Spring Boot 3.5.4**. La arquitectura está diseñada para un despliegue ágil y escalable mediante contenedores **Docker**.

## Requisitos

Antes de comenzar, asegúrate de tener instalado:

- **Java 17 (JDK)**
- **Maven 3.9+** o **Gradle**
- **Docker & Docker Compose** (V2 recomendado)

## Despliegue con Docker

La forma más rápida de poner en marcha la aplicación es utilizando Docker Compose. Este proceso compila el código, crea la imagen y levanta todos los servicios necesarios (App, Base de Datos, etc.).

### 1. Configuración de entorno

Puedes ajustar las variables de conexión, credenciales y puertos en el archivo `.env` ubicado en la raíz del proyecto.

| Variable | Descripción | Valor por Defecto |
| :--- | :--- | :--- |
| `PORT` | Puerto en el que corre la aplicación Spring Boot | `1234` |
| `LOGGING_LEVEL` | Nivel de detalle de los logs (debug, info, warn, error) | `info` |
| `MYSQL_HOST` | Dirección del servidor de base de datos (nombre del servicio en Docker) | `localhost` |
| `MYSQL_PORT` | Puerto de conexión a la base de datos MySQL | `3306` |
| `MYSQL_USER` | Nombre de usuario para la conexión a la base de datos | `root` |
| `MYSQL_DB` | Nombre de la base de datos (esquema) a utilizar | `institute` |
| `MYSQL_PASSWORD` | Contraseña del usuario de la base de datos | `0000` |
| `MYSQL_ROOT_PASSWORD` | Contraseña maestra (root) de la instancia de MySQL | `0000` |

### 2. Levantar la aplicación

Ejecuta el siguiente comando para construir las imágenes y levantar los contenedores en segundo plano (*detached mode*):

```bash
docker compose up --build -d
```

### 3. Detener el proyecto

Para detener y eliminar los contenedores, redes y volúmenes temporales:

``` bash
sudo docker compose down
```
