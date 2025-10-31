# Trabajo Práctico: Patrones de Diseño Creacionales

**Cátedra:** Programación Orientada a Objetos II  
**Tema:** Aplicación de Patrones de Diseño Creacionales  
**Lenguaje:** Java

---

##  Integrantes del Equipo

| Nombre                          | Rol       |
|---------------------------------|-----------|
| Guillermo Ariel Gallo           | Estudiante |
| Pedernera Theisen Nahuel Thomas | Estudiante |
| Pergher Lucas Maurice           | Estudiante |

---
##  Escenario del Problema: Sistema de Configuración y Renderizado de Reportes

Se debe diseñar el núcleo de un **sistema de generación de reportes** para una aplicación de análisis de datos. Este sistema será utilizado por múltiples módulos de la aplicación (ej. módulo de finanzas, marketing, RRHH).

El sistema presenta **tres grandes desafíos de diseño** que deben ser resueltos mediante patrones creacionales.

###  Requerimiento 1: Motor de Renderizado

**Problema:** El sistema debe exportar reportes en diferentes formatos (PDF, Excel, CSV).

**Desafíos:**
- Cada tipo de reporte tiene una lógica de renderizado completamente diferente
- El código cliente NO debe acoplarse a las clases concretas
- El sistema debe ser extensible para nuevos formatos sin modificar código existente

**Formatos requeridos:**
- `ReportePDF` → `RenderizadorPDF`
- `ReporteExcel` → `RenderizadorExcel`
- `ReporteCSV` → `RenderizadorCSV`

###  Requerimiento 2: Construcción de Reportes

**Problema:** Un objeto `Reporte` es una entidad compleja con múltiples parámetros opcionales.

**Datos Obligatorios:**
- `titulo` (String)
- `cuerpoPrincipal` (String)

**Datos Opcionales:**
- `encabezado` (String)
- `pieDePagina` (String)
- `fecha` (LocalDate)
- `autor` (String)
- `orientacion` (Enum: VERTICAL, HORIZONTAL)

**Restricciones:**
- Se debe evitar el "constructor telescópico" (múltiples constructores sobrecargados)
- No se debe usar un constructor con 7 parámetros obligando al cliente a pasar `null`
- El código debe ser limpio y legible

###  Requerimiento 3: Gestor de Configuración Global

**Problema:** Toda la aplicación necesita acceder a configuraciones globales.

**Configuraciones requeridas:**
- `urlBd` (String) - URL de la base de datos
- `userBd` (String) - Nombre de usuario de la BD
- `pathReportes` (String) - Directorio de salida para reportes

**Restricciones:**
- Debe existir un **único punto de acceso** a esta configuración
- Solo debe existir **una y solo una instancia** del objeto `GestorConfiguracion`
- Crear múltiples instancias sería ineficiente y causaría inconsistencias

---

##  Desarrollo

### Análisis y Justificación de Patrones

#### Requerimiento 1: Motor de Renderizado

##### Patrón Seleccionado

El patrón **Factory Method** propone definir una interfaz para crear un objeto, pero dejar que las subclases decidan qué clase concreta instanciar. Permitiendo que reportes con distinta lógica de renderizado y mejorando la ampliación del código.

##### Justificación (Problemas identificados)

El principal problema presente es el código repetido que debe implementarse si se quiere hacer una lógica de creación por cada tipo de reporte, complicando además a la hora de implementar nuevos formatos de exportación, dificultando el mantenimiento del Software.

##### ¿Por qué este patrón es la solución adecuada?

Factory Method es la solución adecuada ya que permite crear objetos de distintos tipos (renderizadores de reportes en este caso), sin que el código dependa de sus clases concretas, facilitando además el agregar nuevos formatos, sin la necesidad de modificar el código existente.

##### ¿Qué problemas evita?

- Evita el acoplamiento fuerte entre el cliente y las clases concretas de renderizado.
- Evita duplicar la misma lógica de creación de objetos con variaciones en distintas partes del código.
- Respeta el principio Abierto/Cerrado (abiertas para extensión, pero cerradas a la modificación), ya que permite agregar nuevos formatos aprovechando el constructor "genérico" para crear una nueva clase para el nuevo formato.
- Evita errores de mantenimiento, porque la lógica de creación queda centralizada y polimórfica.

##### Alternativas Consideradas

Además del patrón Factory, se tuvieron en cuenta otros patrones creacionales similares, como su variante **Abstract Factory**, descartado porque debe crearse un solo tipo de objeto (el render), resultando innecesaria su elección. Otro patrón considerado fue **Strategy**, el cual encapsula algoritmos intercambiables para un mismo proceso, pero tampoco resultó el más oportuno ya que Strategy ayudaría a variar la estrategia de renderizado y no el instanciar distintos tipos de clases según el formato escogido.

---

#### Requerimiento 2: Construcción de Reportes

##### Patrón Seleccionado

Considerando que el objeto **Reporte** es una entidad con muchos posibles argumentos, otros incluso opcionales, se evidencia el problema de constructores largos e inentendibles para cada instancia de dicho objeto, necesitando una manera de hacerlo más legible, problema el cual es tratado por el patrón **Builder**.

##### Justificación (Problemas identificados)

El problema más recurrente es la existencia de un constructor telescópico (múltiples constructores sobrecargados), surgido a causa de tener muchos parámetros a la hora de crear un objeto. Sumado a esto se encuentra el problema de que no se entiende qué parámetro corresponde a cada valor, perdiendo legibilidad y facilitando la confusión.

##### ¿Por qué este patrón es la solución adecuada?

- Evita constructores largos y sobrecargados.
- Mejora la legibilidad y seguridad del código (al evitar equivocarse en el orden de los argumentos).
- Cumple el principio de separación de responsabilidades: el Builder construye, el objeto mantiene su lógica interna.
- Facilita la extensibilidad sin romper compatibilidad.

##### ¿Qué problemas específicos del "constructor" resuelve?

- Reemplaza el constructor telescópico por un único Builder flexible.
- Se eliminan los parámetros largos, cada propiedad se configura con un método nombrado e identificable.
- Solo se configuran los parámetros deseados, los demás mantienen valores por defecto (para los atributos opcionales).

---

#### Requerimiento 3: Gestor de Configuración Global

##### Patrón Seleccionado

Dado que el sistema necesita acceder a configuraciones comunes y si se opta por un enfoque convencional dónde cada clase crea su propio objeto de configuración, se podría presenciar inconsistencias en las configuraciones, incluso ineficiencias en el rendimiento. Esto evidencia la necesidad de crear y mantener una sola instancia, situación la cual es abordada por el patrón **Singleton**.

##### Justificación (Problemas identificados)

Si cada clase o módulo crea su propio objeto de configuración, podrían existir múltiples versiones de la configuración en memoria, con posibles distintas configuraciones. Además, cargar distintos archivos de configuración, o realizar múltiples intentos de conexión a la base de datos, resulta en dificultades innecesarias del rendimiento.

##### ¿Por qué este patrón es la solución adecuada?

- Garantiza que haya una única instancia de configuración existente, accesible por todos los componentes y compartida por todos sus módulos.
- Si la configuración cambia, el cambio se propagará globalmente sin mayores complicaciones.
- Ahorra recursos al cargar los datos una sola vez.

##### ¿Cómo se garantiza la unicidad de la instancia?

Singleton es sencillo de implementar ya que se basa en tener un constructor privado, pero que es llamado por un método interno `getInstance()` que comprueba previamente la existencia de una instancia antes de crearla.

Para casos de entornos concurrentes, debe considerarse que varios hilos pueden intentar crear la instancia al mismo tiempo, siendo necesario implementar sincronización o inicialización segura. Esto es conocido como **Thread-Safety**.

##### Implementación

Un enfoque aplicable a este caso, considerando Thread-Safety, puede ser la **Inicialización Temprana**, que consiste en crear la instancia automáticamente al crear la clase (aunque no sea llamada o utilizada), alternativa la cual aborda el problema de múltiples hilos y es muy sencilla de implementar.

---

*Documento elaborado con fines académicos para la cátedra de Programación Orientada a Objetos II*
