# Historias de Usuarios

## Rol: Cliente

---

### HU-C01: Registro de nuevo usuario

**Como** visitante, **quiero** registrarme en la tienda con mi correo y contraseña, **para** poder realizar compras y guardar mi información en la plataforma.

#### Criterios de Aceptación:
- El sistema debe validar que el correo ingresado tenga un formato válido. Por ejemplo: `usuario@dominio.com`.
- No se debe permitir el registro si ya existe una cuenta con el mismo correo.
- La contraseña debe cumplir con los siguientes requisitos de seguridad:
    - Mínimo 8 caracteres.
    - Al menos una letra mayúscula (A-Z).
    - Al menos una letra minúscula (a-z).
    - Al menos un número (0-9).
- Al completarse el registro, el sistema debe mostrar un mensaje de confirmación.
- Si ocurre un error (correo duplicado, contraseña inválida), el sistema debe mostrar un mensaje claro indicando el motivo.

---

### HU-C02: Inicio de sesión de cliente

**Como** cliente registrado, **quiero** iniciar sesión con mi correo y contraseña, **para** acceder a mi cuenta y mi historial de pedidos.

#### Criterios de Aceptación:
- El sistema debe validar que las credenciales ingresadas sean correctas.
- Al iniciar sesión de forma exitosa, el sistema debe redirigir al usuario a su panel de cuenta o a la página principal.
- Si el usuario no recuerda su contraseña, debe haber un enlace visible a un mecanismo de "olvidé mi contraseña".
- Si un usuario excede los 5 intentos de inicio de sesión fallidos consecutivos, el sistema debe bloquear la cuenta durante 30 minutos por seguridad.

---

### HU-C03: Cierre de sesión de cliente

**Como** cliente registrado, **quiero** cerrar mi sesión, **para** proteger la privacidad de mi cuenta en un dispositivo compartido.

#### Criterios de Aceptación:
- El sistema debe mostrar un botón o enlace visible para "Cerrar Sesión".
- Al confirmar el cierre de sesión, el sistema debe finalizar la sesión activa del cliente.
- El sistema debe redirigir al cliente a la página de inicio o de login después de cerrar sesión.
- Una vez cerrada la sesión, el cliente no debe poder acceder a páginas privadas (ej: "Mi Cuenta") usando el botón de retroceso del navegador.

---
## Búsqueda y Visualización de Productos
---

### HU-C04: Búsqueda de productos por nombre

**Como** cliente, **quiero** buscar productos por su nombre, **para** encontrar rápidamente lo que me interesa comprar.

#### Criterios de Aceptación:
- El sistema debe proveer un campo de búsqueda visible.
- Los resultados deben mostrar únicamente productos cuyo nombre coincida total o parcialmente con el texto ingresado.
- La búsqueda debe ser insensible a mayúsculas y minúsculas (ej: `Cerveza` y `cerveza` deben dar el mismo resultado).
- Si no se encuentran productos, el sistema debe mostrar un mensaje claro como: “No se encontraron productos para tu búsqueda”.
- Los resultados deben cargarse en un tiempo razonable (ej: menos de 3 segundos).
- Al seleccionar un producto de los resultados, se debe redirigir a la página de detalle de ese producto.

---

### HU-C05: Visualización del catálogo de productos

**Como** cliente, **quiero** ver un listado de todos los productos disponibles, **para** explorar el catálogo de la tienda.

#### Criterios de Aceptación:
- El sistema debe listar como “Disponibles” todos los productos con `stock > 0`.
- Cada producto en el listado debe mostrar: nombre, imagen principal, y precio.
- Hacer clic en un producto del listado debe redirigir a su página de detalle.
- Los productos sin stock (`stock = 0`) deben mostrarse con una etiqueta de “Agotado” y, preferiblemente, al final del listado.
- El sistema debe permitir filtrar los productos por categoría y rango de precios.

---

### HU-C06: Visualización del detalle de un producto

**Como** cliente, **quiero** ver la página de detalle de un producto, **para** conocer mejor sus características, descripción y cantidades restantes.

#### Criterios de Aceptación:
- La página de detalle debe mostrar la siguiente información del producto:
    - Nombre.
    - Descripción completa.
    - Imagen principal (y galería de imágenes secundarias si existen).
    - Cantidades restantes (stock).
    - Precio actual.
    - Estimador de costos de envío según código postal.
    - Sección de preguntas frecuentes y reseñas.
- Si el producto no existe (URL inválida), se debe mostrar una página de error `404`.
- Si el producto tiene `stock = 0`, debe mostrar el estado “No disponible por el momento” y deshabilitar el botón de compra.

---
## Carrito de Compras
---

### HU-C07: Añadir producto al carrito

**Como** cliente, **quiero** añadir un producto a mi carrito de compras desde su página de detalle, **para** poder comprarlo más tarde.

#### Criterios de Aceptación:
- El botón "Añadir al carrito" solo debe estar habilitado para productos con stock disponible.
- El sistema debe permitir al cliente elegir la cantidad de unidades que desea agregar.
- No se pueden agregar más unidades que el stock disponible.
- El ícono del carrito en la interfaz debe actualizarse inmediatamente para reflejar la cantidad de ítems.
- El producto añadido debe aparecer en el carrito con su nombre, cantidad, precio unitario y precio total.

---

### HU-C08: Visualización del carrito de compras

**Como** cliente, **quiero** ver el contenido de mi carrito de compras, **para** revisar los productos que he seleccionado y el total a pagar.

#### Criterios de Aceptación:
- El carrito de compras debe ser accesible desde un ícono o enlace visible en todas las páginas.
- El carrito debe mostrar una lista detallada de los productos: imagen, nombre, cantidad y precio por unidad.
- El sistema debe calcular y mostrar el subtotal por cada producto y el monto total a pagar de la compra.

---

### HU-C09: Modificar cantidad de producto en el carrito

**Como** cliente, **quiero** modificar la cantidad de un producto en mi carrito, **para** ajustar mi compra antes de pagar.

#### Criterios de Aceptación:
- Dentro del carrito, el cliente debe poder modificar la cantidad de un producto usando controles visibles (botones `+` y `-`) o un campo numérico editable.
- Al modificar la cantidad, el subtotal del producto y el total del carrito deben actualizarse automáticamente.
- Si el cliente cambia la cantidad a `0`, el producto debe ser eliminado del carrito.
- La cantidad no puede superar el stock disponible del producto.

---

### HU-C10: Eliminar producto del carrito

**Como** cliente, **quiero** eliminar un producto de mi carrito, **para** descartar ítems que ya no deseo comprar.

#### Criterios de Aceptación:
- Cada producto en el carrito debe tener un botón o ícono (ej: un basurero) para eliminarlo.
- Al eliminar un producto, la interfaz del carrito y el monto total deben actualizarse automáticamente sin recargar la página.
- Si se intenta eliminar un producto que ya no está en el carrito, el sistema debe manejarlo sin errores visibles para el usuario.

---
## Proceso de Pedido (Checkout)
---

### HU-C11: Iniciar proceso de pago

**Como** cliente, **quiero** iniciar el proceso de pago desde mi carrito, **para** finalizar mi compra.

#### Criterios de Aceptación:
- Un botón de "Iniciar Pago" o "Finalizar Compra" debe estar visible en el carrito.
- Este botón solo estará habilitado si el carrito contiene al menos un producto.
- Al hacer clic, el sistema debe redirigir al cliente al primer paso del proceso de checkout (ej: dirección de envío).

---

### HU-C12: Ingresar dirección de envío

**Como** cliente, **quiero** ingresar mi dirección de envío, **para** que la tienda sepa dónde entregar mi pedido.

#### Criterios de Aceptación:
- El formulario de dirección debe incluir los campos obligatorios: calle, altura, ciudad, provincia y código postal. El campo "departamento" será opcional.
- El sistema debe validar que todos los campos obligatorios estén completos.
- Si el usuario tiene una dirección guardada, debe poder seleccionarla, editarla o ingresar una nueva.
- La dirección ingresada debe guardarse en el perfil del usuario para futuras compras.

---

### HU-C13: Agregar método de pago

**Como** cliente registrado, **quiero** poder agregar un método de pago válido en mi cuenta, **para** realizar compras de productos en la tienda online de forma rápida y segura.

#### Criterios de Aceptación:
- El cliente debe estar autenticado para agregar un método de pago.
- El formulario para agregar tarjeta debe incluir los campos: `Nombre del titular`, `Número de tarjeta`, `Fecha de vencimiento` (Mes/Año) y `CVV`.
- El sistema debe validar en tiempo real el formato del número de tarjeta y detectar la marca (Visa, MasterCard, etc.).
- La fecha de vencimiento no puede ser una fecha pasada.
- Por seguridad (normativa PCI), el sistema no debe almacenar el número de tarjeta completo ni el CVV, sino un `token` generado por la pasarela de pagos.
- Al guardar, el nuevo método de pago debe aparecer en la lista del cliente, mostrando solo la marca y los últimos 4 dígitos (ej: "Visa terminada en 1234").
- Si la validación de la tarjeta falla, el sistema debe mostrar un mensaje de error claro.

---

### HU-C14: Eliminar método de pago

**Como** cliente registrado, **quiero** poder eliminar un método de pago guardado en mi cuenta, **para** mantener actualizada mi información financiera y evitar el uso de métodos obsoletos o no deseados.

#### Criterios de Aceptación:
- El cliente debe estar autenticado para ver y eliminar sus métodos de pago.
- Cada método de pago listado debe tener una opción visible para "Eliminar".
- Antes de la eliminación definitiva, el sistema debe mostrar un cuadro de diálogo de confirmación (ej: "¿Está seguro de que desea eliminar esta tarjeta?").
- Una vez confirmado, el método de pago debe ser eliminado de la cuenta del cliente.
- La lista de métodos de pago en la interfaz debe actualizarse automáticamente sin necesidad de recargar la página.

---

### HU-C15: Modificar método de pago

**Como** cliente registrado, **quiero** poder modificar los datos de un método de pago existente, **para** corregir errores o actualizar información como la fecha de vencimiento o el número de tarjeta.

#### Criterios de Aceptación:
- El cliente debe estar autenticado para modificar sus métodos de pago.
- Cada método de pago listado debe tener una opción visible para "Editar".
- Al editar, el cliente solo podrá modificar la `Fecha de vencimiento` y el `Nombre del titular`.
- Por seguridad, el `Número de tarjeta` no será editable y se mostrará ofuscado (ej: "************1234").
- Si el cliente necesita cambiar el número de tarjeta, la interfaz debe indicar que debe eliminar la tarjeta actual y agregar una nueva.
- Al guardar los cambios, la información del método de pago debe actualizarse y mostrarse un mensaje de confirmación.

---

### HU-C16: Confirmación de pedido

**Como** cliente, **quiero** confirmar mi pedido, **para** recibir una notificación de que mi compra fue exitosa.

#### Criterios de Aceptación:
- Al confirmar el pedido, el pedido pasa a estado “`CONFIRMADO`”.
- El sistema debe enviar un correo electrónico de confirmación al cliente con el resumen del pedido.
- La página final debe mostrar un mensaje de "Compra exitosa" y el resumen del pedido.
- El stock de los productos comprados debe descontarse del inventario.
- Si el pedido no se confirma dentro de las 24 horas (ej: pago pendiente), el sistema cambiará su estado a `"CANCELADO"` y repondrá el stock.

---
## Seguimiento de Pedidos
---

### HU-C17: Ver historial de pedidos

**Como** cliente registrado, **quiero** ver un historial de todos mis pedidos anteriores, **para** llevar un registro de mis compras.

#### Criterios de Aceptación:
- La sección "Mis Pedidos" debe ser accesible solo para usuarios autenticados.
- Se debe mostrar una lista de pedidos ordenada del más reciente al más antiguo.
- Cada ítem de la lista debe incluir: ID del pedido, fecha, estado y monto total.
- El historial debe ser paginado (ej: mostrando 10 pedidos por página).
- Debe existir una opción para filtrar pedidos por estado (ej: "Entregados") y buscar por ID de pedido.

---

### HU-C18: Ver estado de un pedido específico

**Como** cliente registrado, **quiero** ver el estado actual de un pedido, **para** saber cuándo lo recibiré.

#### Criterios de Aceptación:
- Al hacer clic en un pedido del historial, se debe mostrar su detalle.
- El estado del pedido (ej: `"CONFIRMADO"`, `"CANCELADO"`) debe mostrarse de forma clara.
- Un cliente solo puede ver el detalle de sus propios pedidos.
- El sistema debe notificar al cliente vía correo electrónico cuando el estado de su pedido cambie a `"Enviado"` o `"Entregado"`.

---

## Rol: Vendedor
---
### Gestión de Productos
---

### HU-V01: Cargar nuevo producto

**Como** vendedor, **quiero** cargar un nuevo producto con fotos, nombre, descripción y precio, **para** publicarlo en el catálogo de la tienda.

#### Criterios de Aceptación:
- El vendedor debe estar autenticado para acceder a la función de carga de productos.
- El formulario de nuevo producto debe contener como campos obligatorios: `Nombre`, `Descripción`, `Precio`, `Stock inicial` y al menos una `Imagen`.
- El sistema debe validar que el `Precio` y el `Stock` sean números mayores o iguales a cero.
- El sistema debe permitir cargar múltiples imágenes para un mismo producto.
- Al guardar, el producto debe crearse con un estado inicial de "Activo" y ser visible en la tienda.
- El sistema debe mostrar un mensaje de confirmación de que el producto se ha cargado exitosamente.

---

### HU-V02: Modificar producto existente

**Como** vendedor, **quiero** modificar los datos de un producto existente, **para** mantener la información del catálogo actualizada.

#### Criterios de Aceptación:
- Desde el listado de productos, debe existir una opción para "Editar" cada producto.
- Al editar, se debe presentar un formulario con toda la información actual del producto (nombre, descripción, precio, stock, fotos, etc.).
- Todos los campos del producto deben ser editables.
- Al guardar los cambios, la información del producto debe actualizarse inmediatamente en el catálogo visible para los clientes.
- El sistema debe mostrar un mensaje de confirmación de que los cambios se guardaron correctamente.

---

### HU-V03: Pausar y reactivar un producto

**Como** vendedor, **quiero** pausar temporalmente un producto, **para** que no aparezca en el catálogo mientras no tenga stock disponible o por otras razones.

#### Criterios de Aceptación:
- En la lista de productos del vendedor, cada producto debe tener un control (botón o interruptor) para cambiar su estado entre "Activo" y "Pausado".
- Cuando un producto está en estado "Pausado", no debe ser visible en el catálogo público de la tienda ni aparecer en los resultados de búsqueda.
- Si un cliente intenta acceder a la URL de un producto pausado, debe ver un mensaje de "Producto no disponible".
- El vendedor debe poder reactivar un producto pausado en cualquier momento, haciéndolo visible nuevamente.

---

### HU-V04: Eliminar un producto

**Como** vendedor, **quiero** eliminar un producto del catálogo, **para** dejar de ofrecer ítems que ya no están disponibles permanentemente.

#### Criterios de Aceptación:
- Debe existir una opción para "Eliminar" en la gestión de cada producto.
- Antes de la eliminación definitiva, el sistema debe solicitar una confirmación al vendedor (ej: "¿Está seguro? Esta acción no se puede deshacer").
- Por integridad de datos, un producto que ya tiene ventas asociadas no se eliminará de la base de datos (eliminación física), sino que se marcará como "Archivado" (eliminación lógica).
- Un producto archivado no aparecerá en la tienda ni en el panel de gestión principal del vendedor, pero sus datos se conservarán en los reportes de ventas históricos.

---

### HU-V05: Clasificar productos por categoría

**Como** vendedor, **quiero** poder clasificar mis productos en distintas categorías, **para** un mejor orden de la información y facilitar la búsqueda a los clientes.

#### Criterios de Aceptación:
- El vendedor debe poder crear, editar y eliminar categorías desde un panel de gestión de categorías.
- Al crear o editar un producto, el vendedor debe poder asignarle una o varias categorías existentes.
- Las categorías deben aparecer como filtros de búsqueda en el catálogo de la tienda para los clientes.

---
### HU-V06: Actualizar precios por porcentaje

**Como** vendedor, **quiero** poder actualizar los precios de mis productos en base a un porcentaje de mi elección, **para** simplificar el proceso de actualización masiva.

#### Criterios de Aceptación:
- El vendedor debe tener una herramienta de "Actualización masiva de precios".
- La herramienta debe permitir seleccionar a qué productos aplicar el cambio (ej: todos, por categoría).
- El vendedor debe poder ingresar un valor porcentual (positivo para aumentar, negativo para disminuir).
- El sistema debe mostrar una vista previa de los precios antiguos y los nuevos precios calculados antes de aplicar los cambios.
- El vendedor debe confirmar la operación para que los precios se actualicen de forma definitiva.

---

### HU-V07: Gestionar ofertas de productos

**Como** vendedor, **quiero** poder poner algunos de mis productos en oferta, **para** hacerlos más atractivos para los clientes.

#### Criterios de Aceptación:
- Al editar un producto, debe existir una sección para gestionar ofertas.
- El vendedor debe poder definir un precio de oferta o un porcentaje de descuento.
- Opcionalmente, el vendedor puede establecer una fecha de inicio y fin para la oferta.
- En la tienda, el producto en oferta debe mostrar el precio original tachado y el nuevo precio de oferta destacado.
- Una vez finalizado el período de la oferta, el precio debe volver automáticamente al valor original.

---
### HU-V08: Consultar reporte de ventas

**Como** vendedor, **quiero** consultar las ventas de mis productos, **para** tener control de mis ingresos.

#### Criterios de Aceptación:
- Debe existir una sección de "Reportes" o "Ventas" en el panel del vendedor.
- El reporte debe mostrar un listado de los pedidos, incluyendo fecha, productos vendidos, cantidad y monto total.
- El vendedor debe poder filtrar las ventas por rango de fechas (ej: hoy, última semana, mes actual).
- El sistema debe mostrar un resumen con el total de ingresos y la cantidad de productos vendidos en el período seleccionado.
- Opcionalmente, debe permitir exportar el reporte en formato CSV o PDF.

---

### HU-V09: Consultar reporte de stock

**Como** vendedor, **quiero** ver un reporte de stock disponible, **para** saber cuándo necesito reabastecer.

#### Criterios de Aceptación:
- El reporte de stock debe listar todos los productos del vendedor junto con la cantidad de unidades disponibles.
- El vendedor debe poder ordenar la lista por cantidad de stock (ascendente o descendente) para identificar fácilmente los productos por agotarse.
- Los productos con stock por debajo de un umbral predefinido (ej: menos de 5 unidades) deben resaltarse visualmente.
- El reporte debe ser exportable (ej: a CSV) para un análisis externo.