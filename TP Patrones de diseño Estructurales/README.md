
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
