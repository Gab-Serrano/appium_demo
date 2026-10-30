# Automatización móvil - Sauce Labs

Este proyecto contiene pruebas automatizadas para la aplicación móvil Sauce Labs Sample App. Las pruebas están desarrolladas en Java y utilizan Appium, Selenium y TestNG para validar distintos escenarios, como el inicio de sesión, la visualización y ordenamiento de productos y el flujo de compra.

## Requisitos previos

- **Java JDK 14 o superior**
- **Maven**
- **Node.js y npm**
- **Appium 2**
- **Appium inspector**
- **Android Studio y Android SDK**
- **Emulador Android**
- **controlador UiAutomator2 de Appium**. 

## Configuración del proyecto

### 1. Clonar el repositorio

Abrir una terminal en la carpeta donde se quiera guardar el proyecto y ejecutar:

```bash
git clone <URL_DEL_REPOSITORIO>
```

Luego, ingresar a la carpeta del proyecto:

```bash
cd swag-labs-appium
```

### 2. Configurar el archivo de propiedades

Abrir el archivo `src/main/resources/config.properties`. Allí se encuentran los parámetros utilizados para iniciar la sesión de Appium y configurar las esperas de los tests.

Revisa especialmente la propiedad `appium.app`. Se debe reemplazar su valor por la ruta absoluta del APK incluido en el proyecto:

```properties
appium.app=C:/ruta/al/proyecto/swag-labs-appium/apps/Android.SauceLabs.Mobile.Sample.app.apk
```

Verificar que el resto de los parámetros coincida con el entorno. La configuración inicial utiliza los siguientes valores:

```properties
appium.url=http://127.0.0.1:4723
appium.platform.name=Android
appium.platform.version=13.0
appium.automation.name=UiAutomator2
appium.device.name=Medium Phone
appium.app.wait.initial.activity=com.swaglabsmobileapp.MainActivity
explicit.wait=20
```

### 3. Iniciar el emulador

Abrir Android Studio, ingresar al **Device Manager** e iniciar el emulador que utilizarás para las pruebas.

Comprobar que Android lo reconozca ejecutando:

```bash
adb devices
```

### 4. Iniciar el servidor Appium

Abre otra terminal y ejecuta:

```bash
appium --use-plugins=inspector
```

Por defecto, Appium inicia el servidor en `http://127.0.0.1:4723`, que es la dirección configurada en el proyecto. Mantén esta terminal abierta mientras se ejecutan las pruebas.

### 5. Descargar las dependencias

Desde la carpeta raíz del proyecto, ejecuta:

```bash
mvn clean install
```

Maven descargará las dependencias definidas en el archivo `pom.xml` y compilará el proyecto.

## Ejecución de las pruebas

Con el emulador iniciado y el servidor Appium en ejecución, abre una terminal en la carpeta raíz del proyecto y ejecuta:

```bash
mvn clean test
```

## Estructura general

```text
swag-labs-appium/
├── apps/                         # APK de Sauce Labs Sample App
├── src/
│   ├── main/
│   │   ├── java/                 # Configuración, driver, páginas, modelos y utilidades
│   │   └── resources/            # config.properties y datos JSON de prueba
│   └── test/
│       └── java/                 # Tests, configuración base y proveedores de datos
├── pom.xml                       # Dependencias y configuración de Maven
└── README.md                     # Instrucciones del proyecto
``
