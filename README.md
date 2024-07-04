# Customer API

Este proyecto es una API para manejar clientes usando Spring Boot. Incluye métodos para agregar, actualizar, buscar y eliminar clientes (CRUD).

## Requisitos

- Java 22
- MySQL - XAMPP
- Maven
- Git
- MySQL Workbench (Opcional)
- Postman (Opcional)
- IntelliJ IDEA (Opcional)

## Clonación del Proyecto

Para clonar el proyecto, usa el siguiente comando:

```
git clone https://github.com/Neider-Urbano/customer-api.git
```

## Configuración de la Base de Datos

1. Configura tu base de datos MySQL y crea una base de datos llamada `customer_db`.
2. Asegúrate de que las siguientes propiedades están configuradas en `src/main/resources/application.properties`

```
spring.application.name=customer-api
spring.datasource.url=jdbc:mysql://localhost:3306/customer_db
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

spring.datasource.initialization-mode=always
spring.datasource.schema=classpath:schema.sql
spring.datasource.data=classpath:data.sql
```

## Scripts de Base de Datos
Los siguientes scripts SQL se ejecutarán automáticamente al iniciar la aplicación:

```
CREATE TABLE IF NOT EXISTS customer_db.customer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(15)
);
```

```
INSERT INTO customer_db.customer (name, email, phone_number)
SELECT * FROM (
    SELECT 'Neider Urbano' AS name, 'neider@example.com' AS email, '3204524545' AS phone_number
    UNION ALL
    SELECT 'Julian Bastilla', 'julian@example.com', '3202343800'
) AS new_customers
WHERE NOT EXISTS (
    SELECT 1 FROM customer WHERE customer.email = new_customers.email
);
```

Este script permitira crear dos Customers, validando que no existan en base de datos los emails correspondientes.

## API Endpoints

- Puerto 8080: Es el puerto en el que se ejecuta tu aplicación Spring Boot.
- Puerto 3306: Es el puerto predeterminado para el servidor MySQL.

### POST /api/customers
Agrega un nuevo cliente. 

Ejemplo de solicitud en Postman:

- URL: http://localhost:8080/api/customers
- Método: `POST`
- Cuerpo (raw JSON):

```
{
"name": "Daniel Nieves",
"email": "nieves@example.com",
"phoneNumber": "3202343800"
}
```

### GET /api/customers
Obtiene todos los clientes.

Ejemplo de solicitud en Postman:

- URL: http://localhost:8080/api/customers
- Método: `GET`


### GET /api/customers/{id}
Obtiene un cliente por su ID.

Ejemplo de solicitud en Postman:

- URL: http://localhost:8080/api/customers/1
- Método: `GET`


### PUT /api/customers/{id}
Actualiza un cliente por su ID.

Ejemplo de solicitud en Postman:

- URL: http://localhost:8080/api/customers/1
- Método: `PUT`
- Cuerpo (raw JSON):

```
{
    "name": "Nombre Apellido",
    "email": "nombre@example.com",
    "phoneNumber": "3204524545"
}
```

### DELETE /api/customers/{id}
Elimina un cliente por su ID.

Ejemplo de solicitud en Postman:

- URL: http://localhost:8080/api/customers/1
- Método: `DELETE`


## Manejo de Errores

La API maneja varios tipos de errores y devuelve respuestas adecuadas con mensajes descriptivos.

### Ejemplos de Respuestas de Error 
1. Cliente no encontrado: Si intentas obtener o eliminar un cliente que no existe, recibirás una respuesta con el siguiente mensaje y un estado HTTP 404:

```
"Customer with id 1 not found"
```

## Pruebas Unitarias
El proyecto incluye pruebas unitarias para los métodos del servicio de clientes.

## Demo
[![Customer API](https://img.youtube.com/vi/dONLBOuxCcU/0.jpg)](https://youtu.be/dONLBOuxCcU)
