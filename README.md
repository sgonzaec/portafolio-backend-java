# Backend de Envío de Correos con AWS SES (JAVA)

Este proyecto es un backend desarrollado en Java usando Maven para enviar correos electrónicos utilizando Amazon Simple Email Service (AWS SES). Está diseñado para ser desplegado en Docker y alojado en DockerHub. A continuación se detalla el flujo de la arquitectura y cómo configurar, ejecutar y desplegar el proyecto.

## Arquitectura del Proyecto

### Flujo de la Arquitectura

1.	Cliente de Frontend: La aplicación frontend realiza una solicitud HTTP al backend para enviar un correo electrónico. Esta solicitud incluye los datos del correo como el destinatario, el remitente, el asunto y el mensaje.

2.	Controlador de Spring Boot: El backend, construido con Spring Boot, recibe la solicitud HTTP en un controlador. Este controlador procesa los datos recibidos y llama al servicio de envío de correos.
3.	Servicio de Envío de Correos: El servicio de envío de correos, EmailService, se encarga de interactuar con Amazon SES para enviar el correo electrónico. Utiliza las credenciales y configuraciones proporcionadas para autenticar y enviar el correo.
4.	Amazon SES: AWS SES recibe la solicitud de envío de correo del backend y maneja la entrega del correo electrónico al destinatario.
5.	Cliente de Frontend: Una vez que el correo ha sido enviado, el backend responde al cliente con una confirmación de éxito.

## Configuración del Proyecto

### Requisitos

- Java 11 o superior
- Maven 3.6 o superior
- Docker
- Una cuenta de AWS con acceso a Amazon SES

### Configuración de Amazon SES

1.	Credenciales de AWS: Crea un usuario IAM en la consola de AWS con permisos para utilizar Amazon SES. Guarda las credenciales (Access Key ID y Secret Access Key).

2.	Verificación de Correo: Verifica tu dirección de correo electrónico o dominio en Amazon SES.

### Configuración del Proyecto

**1.	Clona el Repositorio**

```
git clone https://github.com/tu-usuario/tu-repo.git
cd tu-repo
```

**2.	Configura las Credenciales**

Añade las credenciales de AWS en el archivo application.properties de tu proyecto:
```
aws.accessKeyId=TU_ACCESS_KEY_ID
aws.secretKey=TU_SECRET_ACCESS_KEY
```
***Nota**: Considera utilizar variables de entorno o un servicio de gestión de secretos para manejar las credenciales de manera más segura.*

**3.	Instala las Dependencias**

Ejecuta el siguiente comando para instalar las dependencias del proyecto:
```
mvn clean install
```

**4.	Crea la Imagen Docker**

Crea un archivo Dockerfile en la raíz del proyecto con el siguiente contenido:
```
FROM openjdk:11-jre-slim
COPY target/tu-jar.jar /app/tu-jar.jar
WORKDIR /app
CMD ["java", "-jar", "tu-jar.jar"]
```

Luego, construye la imagen Docker:
```
docker build -t tu-usuario/tu-repo:latest .
```

**5.	Sube la Imagen a DockerHub**

Inicia sesión en DockerHub:
```
docker login
```

Luego, sube la imagen:
```
docker push tu-usuario/tu-repo:latest
```

**6.	Ejecuta el Contenedor**

Para ejecutar el contenedor, utiliza el siguiente comando:
```
docker run -d -p 8080:8080 tu-usuario/tu-repo:latest
```

## Ejemplo de Uso
Para enviar un correo electrónico, realiza una solicitud POST a /send-email con el siguiente cuerpo JSON:

```
{
  "from": "remitente@ejemplo.com",
  "to": "destinatario@ejemplo.com",
  "subject": "Asunto del Correo",
  "message": "Cuerpo del mensaje"
}
```

## Respuesta del Backend
El backend responderá con un código de estado HTTP 204 en caso de éxito:

```
HTTP/1.1 204 No Content
```

## Contribuciones
Las contribuciones son bienvenidas. Por favor, abre un issue o un pull request en el repositorio para proponer cambios.

## Licencia
Este proyecto está bajo la Licencia MIT

Asegúrate de adaptar las rutas, nombres de archivo y detalles específicos a tu configuración. ¡Buena suerte con tu proyecto!
