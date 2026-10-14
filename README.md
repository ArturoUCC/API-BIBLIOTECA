[Uploading README_BIBLIOTECA.md…]()
# API Biblioteca

API REST para gestionar **libros, clientes y préstamos** de una biblioteca. Proyecto académico de Arquitectura Orientada a Servicios (SOA).

**Repositorio:** https://github.com/ArturoUCC/API-BIBLIOTECA.git

## Tecnologías

- Java 21 y Spring Boot 4.1.1
- Spring Web MVC y Spring Data JPA / Hibernate
- MySQL (MySQL Connector/J)
- Thymeleaf para las interfaces HTML
- Maven Wrapper (`mvnw.cmd`)
- Postman para pruebas de los endpoints

## Requisitos

- Java compatible con la versión configurada en `pom.xml` (Java 21)
- Servidor MySQL iniciado
- Visual Studio Code con extensiones de Java, o un IDE compatible

## Instalación y ejecución

1. Clona el repositorio:

   ```bash
   git clone https://github.com/ArturoUCC/API-BIBLIOTECA.git
   cd API-BIBLIOTECA
   ```

2. Crea una base de datos en MySQL llamada `libreria`, o verifica que exista:

   ```sql
   CREATE DATABASE IF NOT EXISTS libreria;
   ```

3. Configura `src/main/resources/application.properties` de forma **local**, sin publicar contraseñas. Configuración recomendada:

   ```properties
   spring.application.name=libreria
   server.port=1500
   spring.datasource.url=jdbc:mysql://localhost:3306/libreria?useSSL=false&serverTimezone=UTC
   spring.datasource.username=${DB_USERNAME:root}
   spring.datasource.password=${DB_PASSWORD}
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

   **Atención:** La configuración original del proyecto utiliza credenciales escritas directamente; sustitúyelas por variables antes de subir el código a GitHub. No basta con modificar este ejemplo de README.

4. Define las variables de entorno en PowerShell (en la misma terminal desde la que vas a ejecutar Maven):

   ```powershell
   $env:DB_USERNAME = "root"
   $env:DB_PASSWORD = Read-Host "Contraseña de MySQL"
   ```

5. Arranca la aplicación desde la carpeta que contiene `pom.xml`:

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

6. Comprueba el endpoint: `http://localhost:1500/api/libros`.

> El puerto 1500 debe estar libre; detén otras aplicaciones que lo utilicen. Si ejecutas desde el botón **Run** de VS Code, asegúrate de que las variables de entorno estén disponibles para ese proceso.

## Arquitectura

- `models`: entidades JPA (`LibroModel`, `ClienteModel`, `PrestamoModel`).
- `repositories`: acceso a datos mediante `JpaRepository`.
- `services`: interfaces e implementaciones de la lógica de negocio.
- `controllers`: controladores REST e interfaces HTML.
- `resources/templates`: plantillas Thymeleaf.

## Endpoints REST verificados en el código compartido

### Libros

| Método | Ruta | Descripción |
|---|---|---|
| GET | `/api/libros` | Obtener todos los libros |
| GET | `/api/libros/{isbn}` | Obtener libro por identificador |
| POST | `/api/libros` | Registrar libro |
| PUT | `/api/libros/{isbn}` | Actualizar libro existente |
| DELETE | `/api/libros/{isbn}` | Eliminar libro |

### Clientes y préstamos

El proyecto contiene `ClienteController.java` y `PrestamoController.java`, así como controladores para vistas HTML. **Antes de publicar el README definitivo, agrega aquí las rutas exactas de esos dos controladores**; no se dispone de su código completo en la información revisada para preparar este documento.

## Ejemplos de peticiones y respuestas

### Consultar libros

```http
GET http://localhost:1500/api/libros
```

Respuesta esperada si no hay registros:

```json
[]
```

### Registrar libro

```http
POST http://localhost:1500/api/libros
Content-Type: application/json
```

```json
{
  "titulo": "El principito",
  "autor": "Antoine de Saint-Exupéry",
  "editorial": "Editorial de ejemplo",
  "añopublicacion": "1943",
  "numejemplares": 5
}
```

Ejemplo ilustrativo de respuesta `200 OK` (el identificador real lo genera la base de datos):

```json
{
  "isbn": 1,
  "titulo": "El principito",
  "autor": "Antoine de Saint-Exupéry",
  "editorial": "Editorial de ejemplo",
  "añopublicacion": "1943",
  "numejemplares": 5
}
```

### Modificar libro

```http
PUT http://localhost:1500/api/libros/1
Content-Type: application/json
```

```json
{
  "titulo": "El principito",
  "autor": "Antoine de Saint-Exupéry",
  "editorial": "Editorial de ejemplo",
  "añopublicacion": "1943",
  "numejemplares": 8
}
```

### Eliminar libro

```http
DELETE http://localhost:1500/api/libros/1
```

Si el registro existe, el controlador responde `200 OK` sin contenido; si no existe, devuelve `404 Not Found`.

## Seguridad y archivos que no deben publicarse

- No subir contraseñas reales, tokens, archivos `.env` ni copias de bases de datos con datos privados.
- No subir `target/`, `.class`, archivos temporales ni configuraciones privadas del IDE.
- Revisa `application.properties` y **el historial de commits** antes de publicar. Si una contraseña ya fue publicada, cámbiala en MySQL: eliminarla en un commit posterior no la borra del historial.
- Configura `.gitignore` apropiadamente.

## Evidencias

El documento de entrega incluye capturas de peticiones realizadas con Postman. Las respuestas de ejemplo de este README son ilustrativas y deben contrastarse con las pruebas efectivamente ejecutadas.
