# **Trabajo Práctico: Patrones Estructurales**

**Cátedra:** Programación Orientada a Objetos II.
**Tema:** Aplicación de Patrones de Diseño Estructurales.
**Lenguaje:** Java.

## **Integrantes**
- Pedernera Theisen Nahuel.
- Pergher Lucas Maurice.
- Gallo Guillermo Ariel.
## **Objetivos**
- Analizar problemas de diseño de software y reconocer escenarios de aplicación para patrones estructurales.
- Implementar soluciones robustas y flexibles aplicando los patrones Adapter, Decorator, Composite y Facade.
- Justificar formalmente la elección de cada patrón de diseño.
- Modelar la solución de software utilizando diagramas de clases (UML/Mermaid).

## **Metodología de Trabajo**
Para cada uno de los siguientes problemas, el grupo deberá:

- Analizar el Escenario: Identificar las clases principales, sus responsabilidades y los problemas de acoplamiento, rigidez o complejidad.
- Identificar el Patrón: Determinar qué patrón (o patrones) estructural(es) es el más adecuado para resolver el problema.
- Justificar la Elección: por qué se eligió ese patrón, relacionándolo con la intención del mismo.
- Implementar la Solución: Escribir el código Java funcional que resuelva el problema utilizando el patrón seleccionado.
- Modelar la Solución: Crear el diagrama de clases de la solución implementada (no del problema original).

# **Escenarios y Desarrollo**

## Escenario 1: *Sistema de Ensamblaje de Computadoras*

Se te pide diseñar el módulo de "Carrito de Compras" para una tienda de componentes de PC. El sistema debe permitir al usuario armar su propia computadora.

### Requisitos:

- El sistema debe manejar componentes individuales (hojas) como CPU (\$250), Memoria RAM (\$80), Disco SSD (\$100).
- El sistema debe manejar componentes "compuestos" (cajas o kits) que contienen otros componentes. Por ejemplo:
  - Un Gabinete (\$120) puede contener una Placa Madre.
  - Una Placa Madre (\$150) debe contener un CPU y varias Memorias RAM.
- El cliente final (el carrito de compras) debe poder tratar a un Disco SSD (individual) y a un Gabinete (compuesto, lleno de partes) de la misma manera, principalmente para calcular el precio total.
- Requisito Adicional: La tienda quiere ofrecer "extras" que se pueden añadir a cualquier componente, ya sea simple (como una RAM) o compuesto (como un Gabinete completo). o Garantía Extendida: Añade un 10% al costo total del componente al que se aplica. o Servicio de Instalación: Añade un costo fijo de \$50 al componente.
- Estos extras deben poder combinarse (ej: un Gabinete con Servicio de Instalación y Garantía Extendida).

---
## **Desarrollo**
### **Clases principales y sus responsabilidades:**

Las clases identificadas y sus responsabilidades se describirán utilizando el formato: "**NombreClase:** Responsabilidad":

- **Componente**: representa cualquier ítem del carrito (simple o compuesto). Define interfaz común (getPrecio() y getNombre()).
- **ComponenteSimple**: representa un producto individual (CPU, RAM, SSD). Implementa getPrecio() retornando su costo establecido.
- **ComponenteCompuesto**: representa un conjunto de componentes (como Gabinete y Placa Madre). Contiene una lista de componentes hijos y calcula el precio total sumando el precio individual de cada uno.
- **ExtraDecorator:** clase decorador base para agregar funcionalidades adicionales a cualquier componente (tanto simples como compuestos). Mantiene una referencia al componente decorado.
- **GarantiaExtendida:** clase extra del decorador que incrementa el costo total un 10% al aplicar garantía.
- **ServicioInstalacion:** clase extra del decorador que añade un costo fijo de \$50 sí se solicita instalación de componentes.
- **CarritoCompras:** contiene los componentes agregados por el usuario y calcula el total general correspondiente.

### **Problemas detectados antes de aplicar el patrón:**

- **_Mucho acoplamiento:_** dado que cada clase debe conocer la estructura interna de otras (por ejemplo, el carrito tendría que distinguir entre productos simples y kits).
- **_Rigidez ante escalabilidad:_** si se agregan nuevos tipos de extras, habría que modificar el código existente.
- **_Falta de extensibilidad:_** no se puede agregar una nueva regla de cálculo de precio sin modificar las clases base.
- **_Duplicación de lógica:_** el cálculo de precio y agregado de extras se realiza repetidamente.

### **Patrón que resuelve el problema:**

Se propone utilizar los patrones **Composite** y **Decorator**, dado que se necesita "juntar" y tratar a los componentes simples y los compuestos de la misma forma, lo cual es posible con el patrón de diseño composite. Mientras que el patrón decorator permite agregar extras dinámicos sin alterar las clases originales.

### **Código Java:**
*ver adjunto en sección "Código" del repositorio*

### **Diagrama UML:**
*ver adjunto en sección "Diagramas" del repositorio*

---

## Escenario 2: *Módulo de Generación de Reportes*

Estás trabajando en un sistema de contabilidad. El módulo más complejo es el de "Generación de Reportes Fiscales". Para generar un solo reporte en PDF, el sistema actualmente requiere que el programador (el cliente) realice una serie de pasos complejos:

- Instanciar un ConectorDB y conectarse a la base de datos de clientes.
- Usar un LectorDeDatos para obtener el CUIT del cliente.
- Instanciar un ServicioWebAFIP y autenticarse.
- Llamar al ServicioWebAFIP.obtenerDatosFiscales(cuit) para obtener un objeto DatosFiscales.
- Instanciar un ProcesadorDeImpuestos y pasarle los DatosFiscales para calcular los montos.
- Instanciar un RenderizadorPDF y pasarle los montos calculados para generar el archivo.

El cliente (la interfaz de usuario) se queja de que esto es demasiado complicado y está fuertemente acoplado a cuatro clases diferentes : (ConectorDB, ServicioWebAFIP, ProcesadorDeImpuestos, RenderizadorPDF).

---

## **Desarrollo**
### **Clases principales identificadas y sus responsabilidades:**

- **ConectorBD:** conectarse a la base de datos y obtener los datos del cliente.
- **LectorDeDatos:** leer el CUIT u otra información del cliente desde la base.
- **ServicioWebAFIP:** autenticarse y obtener datos fiscales del cliente desde la base de datos de AFIP (ARCA).
- **ProcesadorDeImpuestos:** calcular montos de impuestos y totales a partir de los datos fiscales.
- **RenderizadorPDF:** generar el PDF final con los resultados procesados.
- **GeneradorReportesFiscales:** clase nueva que busca proveer una interfaz simple y de alto nivel para generar un reporte en un solo paso.

### **Problemas detectados antes de aplicar el patrón:**

- **_Mucho acoplamiento:_** el cliente debe conocer y coordinar directamente cuatro o cinco clases distintas, gestionando dependencias y orden de ejecución.
- **_Dependencia a estructuras externas:_** si por ejemplo, cambia la API de AFIP, o el modo de conexión a la base de datos, habría que modificar todo el código cliente.
- **_Complejidad innecesaria:_** el flujo de creación y uso de objetos es lineal y repetitivo de manera compleja e innecesaria.
- **_Falta de encapsulamiento:_** los detalles técnicos (conexión, autenticación, renderización) deberían ocultarse al usuario del módulo.

### **Patrón que resuelve el problema:**

Para este escenario se opta por utilizar el patrón **Facade**, ya que permite unificar y simplificar el acceso a un conjunto complejo de subsistemas (Base de Datos, API AFIP, procesamiento, PDF), mostrando solamente una interfaz sencilla y de alto nivel.

### **Código Java:**
*ver adjunto en sección "Código" del repositorio*

### **Diagrama UML:**
*ver adjunto en sección "Diagramas" del repositorio*

---

# Escenario 3: Integración de API de Logística

## Patrón aplicado: **Adapter**

### Justificación del Patrón

En este escenario, el sistema de **E-Commerce** ya cuenta con una interfaz definida para interactuar con distintos servicios de envío (`IServicioEnvio`). Esta interfaz establece los métodos que el sistema espera utilizar para calcular costos, obtener tiempos estimados y despachar pedidos.

El nuevo proveedor de logística, **LogísticaVeloz**, proporciona una librería externa con una clase (`ApiLogisticaVeloz`) que no cumple con la interfaz establecida por el sistema. Existen diferencias tanto en los nombres de los métodos como en los tipos de datos utilizados, además de estructuras de objetos propias de la librería que el sistema no reconoce.

Modificar el código del sistema o de la librería no es una opción viable, ya que ambas partes están diseñadas de forma independiente y deben mantenerse desacopladas. Esta incompatibilidad entre interfaces impide integrar directamente el nuevo proveedor dentro del flujo actual.

El **patrón Adapter** resuelve este problema al actuar como un intermediario o traductor entre ambas interfaces. La clase adaptadora implementa la interfaz `IServicioEnvio` (esperada por el sistema) y, dentro de sus métodos, delega las operaciones hacia una instancia de `ApiLogisticaVeloz`, realizando las conversiones necesarias entre los tipos de datos.

De esta manera:

* Se mantiene el **principio de abierto/cerrado**, ya que el sistema se amplía para admitir un nuevo proveedor sin modificar el código existente.
* Se logra **bajo acoplamiento**, dado que el sistema no necesita conocer los detalles internos de la API externa.
* Se preserva la **consistencia de la interfaz interna**, lo que permite que el resto del sistema siga operando sin cambios.
* Se facilita la **sustitución o incorporación de nuevos servicios de envío** en el futuro, simplemente creando nuevos adaptadores.

---