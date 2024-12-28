# 📚 LibreriaOracleAula

Bienvenido a **LibreriaOracleAula**, una aplicación desarrollada en Java con Spring Boot que permite gestionar información sobre libros y autores utilizando un cliente externo para obtener datos en tiempo real. La aplicación incluye opciones para buscar, listar y explorar información detallada sobre libros y autores.

## 🚀 Funcionalidades

1. **Buscar libro por título**: Encuentra libros ingresando su título exacto.
2. **Buscar autor por nombre**: Consulta detalles y libros asociados a un autor específico.
3. **Listar libros registrados**: Muestra una lista de libros disponibles en la base de datos.
4. **Listar autores registrados**: Muestra una lista de autores registrados con sus detalles.
5. **Cliente externo**: Integración con Gutendex para obtener información adicional de libros.

## 🛠️ Tecnologías utilizadas

- **Java 17**: Lenguaje de programación principal.
- **Spring Boot**: Framework para desarrollo rápido de aplicaciones Java.
- **Spring Data JPA**: Gestión de datos con repositorios.
- **GutendexClient**: Cliente personalizado para interactuar con la API externa.
- **H2 Database**: Base de datos en memoria para pruebas y desarrollo.

## 📂 Estructura del proyecto

- **`model`**: Contiene las clases de entidad `Book` y `Author`.
- **`repository`**: Define los repositorios para gestionar `Book` y `Author`.
- **`client`**: Implementa el cliente para interactuar con la API de Gutendex.
- **`Main`**: Punto de entrada de la aplicación, con menú interactivo para realizar las acciones.

## ⚡ Ejecución de la aplicación

1. Clona este repositorio:
   ```bash
   git clone https://github.com/tu-usuario/LiteraturaApp.git
