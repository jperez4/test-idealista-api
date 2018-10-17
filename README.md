## Test Idealista API

### Descripción y contexto
---
Prueba de código para puesto de backend developer junior en Idealista. Se trata de un servidor Spring Boot cuyo esqueleto ha sido generado mediante su definición funcional en la herramienta [Swagger Editor](https://editor.swagger.io/). Este servidor proporciona acceso a una API de forma local que contiene las siguientes funcionalides.

- Asignar una puntuación entre 0 y 100 a una serie de anuncios concretos basándose en un conjunto de reglas que definen la puntuacion a otorgar.
- Obtener una serie de anuncios irrelevantes para el usuario, aquellos con una puntuación inferior a 40, que son importantes para el responsable de calidad.
- Obtener una serie de anuncios ordenados por orden de relevancia para el usuario.

### Guía de usuario
---
Como hemos visto anteriormente, esta API dispone de tres funcionalidades y cada una de ellas se sirve a partir de un endpoint o punto de acceso distinto. A tener en cuenta que al tratarse de un ejercicio de prueba, no se ha incorporado una base de datos de donde extraer los anuncios y las fotos así como almacenar las puntuaciones. Por ello los datos a los que se necesita acceder, tales como las propiedades de las fotos en función de su identificación, se encuentran almacenadas por defecto en el servidor [(*vease SimulatedDB.java)*](https://github.com/jperez4/test-idealista-api/blob/master/src/main/java/io/swagger/model/SimulatedDB.java). Los endpoints que cubren estas funcionalidades son:

### /assignScore

Corresponde con la primera funcionalidad descrita y se trata de una petición de tipo POST cuyo parámetro de entrada veremos posteriormente. El conjunto de reglas que se utilizan para calcular este valor de puntuación, son las siguientes:

- Si el anuncio no tiene foto, la puntuación se decrementa en 10.
- Si tiene fotos, la puntuación se incrementa en 20 si su calidad es HD o en 10 si es SD por cada una.
- Si el anuncio tiene descripción, la puntuación se incrementa en 5.
- Si el anuncio es de tipología chalet y la descripción tiene más de 50 palabras, la puntuación se incrementa en 20.
- Si el anuncio es de tipología piso y la descripción tiene más de 50 palabras, la puntuación se incrementa en 30. Si no tiene más de 50 pero tiene más de 20, la puntuación se incrementa en 20.
- Si la descripción del anuncio contiene alguna de las siguientes palabras: luminoso, céntrico, reformado, ático o nuevo; la puntuación se incrementa en 5 puntos por cada una de ellas.
- Si el anuncio dispone de descripción, al menos una foto, su tipología es piso y dispose de tamaño de casa, la puntuación se incrementa en 40. 
- Si el anuncio dispone de descripción, al menos una foto, su tipología es chalet y dispose de tamaño de casa y tamaño de jardín, la puntuación se incrementa en 40. 
 
Un ejemplo de párametros de entrada en el cuerpo de la petición correpondiente al modelo de datos anuncio, se muestra a continuación para poder ser ejecutado a modo de prueba:

```javascript
[
 {
  "id": 1,
  "description": "Este piso es una ganga, compra, compra, COMPRA!!!!!",
  "typology": "CHALET",
  "houseSize": 300,
  "pictures": []
 },
 {
  "id": 2,
  "description": "Nuevo ático céntrico recién reformado. Adquiera este ático de lujo",
  "typology": "FLAT",
  "pictures": [4]
 },
 {
  "id": 3,
  "description": "",
  "typology": "CHALET",
  "houseSize": 210,
  "gardenSize": 25,
  "pictures": [2]
 },
 {
  "id": 4,
  "description": "Ático céntrico muy luminoso y recién reformado, parece nuevo",
  "typology": "FLAT",
  "houseSize": 130,
  "pictures": [5]
 },
 {
  "id": 5,
  "description": "Pisazo",
  "typology": "FLAT",
  "pictures": [3, 4]
 },
 {
  "id": 6,
  "description": "",
  "typology": "GARAGE",
  "pictures": [6]
 },
 {
  "id": 7,
  "description": "Garaje en el centro de Albacete",
  "typology": "GARAGE",
  "pictures": []
 },
 {
  "id": 8,
  "description": "Maravilloso chalet situado en als afueras de un pequeño pueblo rural.",
  "typology": "CHALET",
  "houseSize": 150,
  "gardenSize": 20,
  "pictures": [1, 7]
 }
]
```
Si procedemos a la ejecución de dicho ejemplo, obtendremos lo siguiente:

**Response Body**

```javascript
[
  {
    "id": "1",
    "score": 15
  },
  {
    "id": "2",
    "score": 35
  },
  {
    "id": "3",
    "score": 20
  },
  {
    "id": "4",
    "score": 30
  },
  {
    "id": "5",
    "score": 35
  },
  {
    "id": "6",
    "score": 10
  },
  {
    "id": "7",
    "score": -5
  },
  {
    "id": "8",
    "score": 85
  }
]
```
**Responde Code**

```javascript
200
```

**Response Headers**

```javascript
{
  "date": "Wed, 17 Oct 2018 09:21:21 GMT",
  "transfer-encoding": "chunked",
  "content-type": "application/json;charset=UTF-8"
}
```

### /listQMAds

Corresponde con la segunda funcionalidad descrita y se trata de una petición GET. Esta devuelve la lista de anuncios irrelevantes para el usuario (puntuación inferior a 40) pero que son de importancia para el responsable de calidad. Si previamente se ha realizado una llamada al endpoint anterior, esta petición se realizará en base a esos datos, si no, se tomarán una serie de datos de prueba por defecto. 

Si realizamos una petición a dicho endpoint obtendremos lo siguiente:

**Responde Body**

```javascript
[
  {
    "id": 1,
    "description": "Este piso es una ganga, compra, compra, COMPRA!!!!!",
    "typology": "CHALET",
    "houseSize": 300,
    "gardenSize": null,
    "pictures": []
  },
  {
    "id": 2,
    "description": "Nuevo ático céntrico recién reformado. Adquiera este ático de lujo",
    "typology": "FLAT",
    "houseSize": null,
    "gardenSize": null,
    "pictures": [
      4
    ]
  },
  {
    "id": 3,
    "description": "",
    "typology": "CHALET",
    "houseSize": 210,
    "gardenSize": 25,
    "pictures": [
      2
    ]
  },
  {
    "id": 4,
    "description": "Ático céntrico muy luminoso y recién reformado, parece nuevo",
    "typology": "FLAT",
    "houseSize": 130,
    "gardenSize": null,
    "pictures": [
      5
    ]
  },
  {
    "id": 5,
    "description": "Pisazo",
    "typology": "FLAT",
    "houseSize": null,
    "gardenSize": null,
    "pictures": [
      3,
      4
    ]
  },
  {
    "id": 6,
    "description": "",
    "typology": "GARAGE",
    "houseSize": null,
    "gardenSize": null,
    "pictures": [
      6
    ]
  },
  {
    "id": 7,
    "description": "Garaje en el centro de Albacete",
    "typology": "GARAGE",
    "houseSize": null,
    "gardenSize": null,
    "pictures": []
  }
]
```
**Response Code**

```javascript
200
```

**Response Headers**

```javascript
{
  "date": "Wed, 17 Oct 2018 09:39:07 GMT",
  "transfer-encoding": "chunked",
  "content-type": "application/json;charset=UTF-8"
}
```


Explica los pasos básicos sobre cómo usar la herramienta digital. Es una buena sección para mostrar capturas de pantalla o gifs que ayuden a entender la herramienta digital.

Start your server as an simple java application  

You can view the api documentation in swagger-ui by pointing to  
http://localhost:8080/  

Change default port value in application.properties
 	
### Guía de instalación
---
Paso a paso de cómo instalar la herramienta digital. En esta sección es recomendable explicar la arquitectura de carpetas y módulos que componen el sistema.

Según el tipo de herramienta digital, el nivel de complejidad puede variar. En algunas ocasiones puede ser necesario instalar componentes que tienen dependencia con la herramienta digital. Si este es el caso, añade también la siguiente sección.

La guía de instalación debe contener de manera específica:
- Los requisitos del sistema operativo para la compilación (versiones específicas de librerías, software de gestión de paquetes y dependencias, SDKs y compiladores, etc.).
- Las dependencias propias del proyecto, tanto externas como internas (orden de compilación de sub-módulos, configuración de ubicación de librerías dinámicas, etc.).
- Pasos específicos para la compilación del código fuente y ejecución de tests unitarios en caso de que el proyecto disponga de ellos.

#### Dependencias
Descripción de los recursos externos que generan una dependencia para la reutilización de la herramienta digital (librerías, frameworks, acceso a bases de datos y licencias de cada recurso). Es una buena práctica describir las últimas versiones en las que ha sido probada la herramienta digital. 

    Puedes usar este estilo de letra diferenciar los comandos de instalación.

### Cómo contribuir
---
Esta sección explica a desarrolladores cuáles son las maneras habituales de enviar una solicitud de adhesión de nuevo código (“pull requests”), cómo declarar fallos en la herramienta y qué guías de estilo se deben usar al escribir más líneas de código. También puedes hacer un listado de puntos que se pueden mejorar de tu código para crear ideas de mejora.

### Código de conducta 
---
El código de conducta establece las normas sociales, reglas y responsabilidades que los individuos y organizaciones deben seguir al interactuar de alguna manera con la herramienta digital o su comunidad. Es una buena práctica para crear un ambiente de respeto e inclusión en las contribuciones al proyecto. 

La plataforma Github premia y ayuda a los repositorios dispongan de este archivo. Al crear CODE_OF_CONDUCT.md puedes empezar desde una plantilla sugerida por ellos. Puedes leer más sobre cómo crear un archivo de Código de Conducta (aquí)[https://help.github.com/articles/adding-a-code-of-conduct-to-your-project/]

### Autor/es
---
Nombra a el/los autor/es original/es. Consulta con ellos antes de publicar un email o un nombre personal. Una manera muy común es dirigirlos a sus cuentas de redes sociales.

### Información adicional
--- 
This server was generated by the [swagger-codegen](https://github.com/swagger-api/swagger-codegen) project.  
By using the [OpenAPI-Spec](https://github.com/swagger-api/swagger-core), you can easily generate a server stub.  
This is an example of building a swagger-enabled server in Java using the SpringBoot framework.  

The underlying library integrating swagger to SpringBoot is [springfox](https://github.com/springfox/springfox)  

### Licencia 
---
[LICENCIA](https://github.com/EL-BID/Plantilla-de-repositorio/blob/master/LICENSE.md)

La licencia especifica los permisos y las condiciones de uso que el desarrollador otorga a otros desarrolladores que usen y/o modifiquen la herramienta digital.

Incluye en esta sección una note con el tipo de licencia otorgado a esta herramienta digital. El texto de la licencia debe estar incluído en un archivo *LICENSE.md* o *LICENSE.txt* en la carpeta raíz.

Si desconoces qué tipos de licencias existen y cuál es la mejor para cada caso, te recomendamos visitar la página https://choosealicense.com/.
