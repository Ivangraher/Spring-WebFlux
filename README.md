# Microservicios de Usuarios y Categorías

Este proyecto consiste en dos microservicios independientes: **Usuarios** y **Categorías**, que se comunican entre sí de forma reactiva utilizando **Spring Boot** y **Spring WebFlux**. El microservicio de **Usuarios** maneja las operaciones CRUD para usuarios, mientras que el microservicio de **Categorías** gestiona la información de categorías. Ambos microservicios se comunican a través de **WebClient** de forma asincrónica y no bloqueante.

## Funcionalidades

- **Microservicio de Usuarios**:
  - Obtener un usuario por su ID (`GET /users/{id}`).
  - Obtener todos los usuarios de una categoría (`GET /users/category/{categoryId}`).
  - Obtener información detallada de un usuario, incluyendo su categoría asociada.

- **Microservicio de Categorías**:
  - Obtener el nombre de la categoría por su ID (`GET /categories/{categoryId}`).
  - Gestión de categorías con operaciones básicas (CRUD).

## Tecnologías Utilizadas

- **Spring Boot**: 
- **Spring WebFlux**: 
- **WebClient**: 
- **H2 Database**: 
- **Java 17**: 
- **Maven**: 

## Puertos de los Microservicios

- **Microservicio de Usuarios**:
  - **Puerto**: `8080`
  - **Rutas**:
    - `GET /users/{id}`: Obtiene un usuario por su ID, incluyendo la categoría a la que pertenece.
    - `GET /users/category/{categoryId}`: Obtiene todos los usuarios filtrados por el ID de la categoría.

- **Microservicio de Categorías**:
  - **Puerto**: `8082`
  - **Rutas**:
    - `GET /categories/{categoryId}`: Obtiene el nombre de la categoría por su ID.

## Instalación y Ejecución

### Requisitos

- **Java 17** o superior
- **Maven**

### Pasos para Ejecutar

1. **Clonar el repositorio**:

   ```bash
   git clone https://github.com/tu-repositorio/proyecto-microservicios.git
   cd proyecto-microservicios
