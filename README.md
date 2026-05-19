## ecommerce\_4

--Equipo 4:--

Adel Mendez Lizo 00000252770

Saul Isaac Apodaca Baldenegro 00000252020

Camila Zubia Higuera 00000244825

Abraham Coronel Bringas 00000252233

Janeth Cristina  Galván Quiñonez 00000252449


---

## Requisitos Previos e Instalación

Se debe de contar con las siguientes herramientas instaladas y configuradas:

1. **Java Development Kit (JDK):** Versión 11 o superior.
2. **Apache Maven:** Versión 3.8+.
3. **Servidor de Aplicaciones:** Apache Tomcat (Versión 10+) o GlassFish/Payara compatible con Jakarta EE 10/11.
4. **Sistema Gestor de Base de Datos:** MySQL Server 8.0 o superior.

---

## Configuración del Entorno y Base de Datos

A. Preparación de la Base de Datos
1. Inicie su servidor de MySQL.
2. Cree una base de datos limpia ejecutando la siguiente sentencia en su cliente SQL Workbench o CLI:
   ```sql
   CREATE DATABASE ecommerce_softfriends;

B. Configuración de Credenciales del Proyecto
Vaya al archivo de configuración de persistencia ubicado en:
---->src/main/resources/META-INF/persistence.xml

Modifique las siguientes propiedades para que coincidan con las credenciales de su entorno MySQL local:
<property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3606/ecommerce_softfriends?useSSL=false&amp;allowPublicKeyRetrieval=true&amp;serverTimezone=UTC"/>
<property name="jakarta.persistence.jdbc.user" value="root"/>
<property name="jakarta.persistence.jdbc.password" value="TU_CONTRASENIA_AQUÍ"/>

---

## Construcción y Despliegue del Sistema
El ciclo de empaquetado del proyecto está estandarizado mediante Maven. Siga estos pasos en la consola de comandos dentro de la raíz de la carpeta E-commerce_equipo4:
---->mvn clean
---->mvn package (Esto generará el artefacto ejecutable E-commerce_equipo4.war dentro del directorio target/).

1. Despliegue:
Copie el archivo .war generado y péguelo dentro de la carpeta webapps/ de su servidor Apache Tomcat local, o utilice el panel de administración de su IDE (NetBeans/IntelliJ) para realizar el despliegue directo.
Inicie el servidor. La aplicación estará disponible en la ruta raíz por defecto: http://localhost:8080/E-commerce_equipo4/

---

##Credenciales de Prueba para Evaluación
Para facilitar el proceso de evaluación de los flujos interactivos, puede utilizar los registros mock que se encuentran en el archivo BaseDatos_Mock.sql que está dentro del repositorio. 

---

##Arquitectura y Tecnologías Utilizadas
El sistema se diseñó siguiendo un patrón arquitectónico por capas bien delimitado, aplicando principios de alta cohesión y bajo acoplamiento:

* Backend: Servlets de Jakarta EE configurados de manera RESTful para procesar e intercambiar datos puros en formato JSON.
* Capa de Negocio y Persistencia: Uso de patrones DTO y capas Mapper para aislar el modelo de datos de la red.
* Frontend: Arquitectura asíncrona basada en Web Components dinámicos, manipulación avanzada del DOM/BOM y comunicación hacia la API de Java por medio de funciones async/await nativas de JavaScript (Fetch API).

---
