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
``` Java
import java.util.\*;

// --- Interfaz Común ---

interface Componente {

String getNombre();

double getPrecio();

}

// --- Componentes Simples ---

class ComponenteSimple implements Componente {

private String nombre;

private double precio;

public ComponenteSimple(String nombre, double precio) {

this.nombre = nombre;

this.precio = precio;

}

@Override

public String getNombre() {

return nombre;

}

@Override

public double getPrecio() {

return precio;

}

}

// --- Componentes Compuestos ---

class ComponenteCompuesto implements Componente {

private String nombre;

private List&lt;Componente&gt; componentes = new ArrayList<>();

public ComponenteCompuesto(String nombre) {

this.nombre = nombre;

}

public void agregar(Componente c) {

componentes.add(c);

}

@Override

public String getNombre() {

return nombre;

}

@Override

public double getPrecio() {

return componentes.stream()

.mapToDouble(Componente::getPrecio)

.sum();

}

}

// --- Decorador Base ---

abstract class ExtraDecorator implements Componente {

protected Componente componente;

public ExtraDecorator(Componente componente) {

this.componente = componente;

}

@Override

public String getNombre() {

return componente.getNombre();

}

@Override

public double getPrecio() {

return componente.getPrecio();

}

}

// --- Decoradores Concretos ---

class GarantiaExtendida extends ExtraDecorator {

public GarantiaExtendida(Componente componente) {

super(componente);

}

@Override

public double getPrecio() {

return componente.getPrecio() \* 1.10; // +10%

}

@Override

public String getNombre() {

return componente.getNombre() + " + Garantía Extendida";

}

}

class ServicioInstalacion extends ExtraDecorator {

public ServicioInstalacion(Componente componente) {

super(componente);

}

@Override

public double getPrecio() {

return componente.getPrecio() + 50;

}

@Override

public String getNombre() {

return componente.getNombre() + " + Servicio de Instalación";

}

}

// --- Cliente ---

public class CarritoCompras {

public static void main(String\[\] args) {

Componente cpu = new ComponenteSimple("CPU", 250);

Componente ram1 = new ComponenteSimple("Memoria RAM", 80);

Componente ram2 = new ComponenteSimple("Memoria RAM", 80);

Componente ssd = new ComponenteSimple("Disco SSD", 100);

// Placa madre compuesta

ComponenteCompuesto placaMadre = new ComponenteCompuesto("Placa Madre");

placaMadre.agregar(cpu);

placaMadre.agregar(ram1);

placaMadre.agregar(ram2);

// Gabinete compuesto

ComponenteCompuesto gabinete = new ComponenteCompuesto("Gabinete");

gabinete.agregar(placaMadre);

gabinete.agregar(ssd);

// Agregar extras (decoradores)

Componente gabineteConExtras = new GarantiaExtendida(

new ServicioInstalacion(gabinete)

);

System.out.println(gabineteConExtras.getNombre() + " - Precio total: \$" + gabineteConExtras.getPrecio());

}

}
```
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
``` Java
// --- Subsistemas existentes ---

class ConectorDB {

public void conectar() {

System.out.println("Conectando a base de datos...");

}

}

class LectorDeDatos {

public String obtenerCUITCliente(int idCliente) {

System.out.println("Leyendo CUIT del cliente desde la base de datos...");

return "20-12345678-9";

}

}

class ServicioWebAFIP {

public void autenticar() {

System.out.println("Autenticando con servicio web de AFIP...");

}

public DatosFiscales obtenerDatosFiscales(String cuit) {

System.out.println("Obteniendo datos fiscales de AFIP para CUIT: " + cuit);

return new DatosFiscales(cuit, 50000.0);

}

}

class DatosFiscales {

String cuit;

double ingresosBrutos;

public DatosFiscales(String cuit, double ingresosBrutos) {

this.cuit = cuit;

this.ingresosBrutos = ingresosBrutos;

}

}

class ProcesadorDeImpuestos {

public double calcularImpuesto(DatosFiscales datos) {

System.out.println("Calculando impuesto para ingresos: \$" + datos.ingresosBrutos);

return datos.ingresosBrutos \* 0.21;

}

}

class RenderizadorPDF {

public void generarPDF(String cuit, double impuesto) {

System.out.println("Generando PDF para CUIT " + cuit + " con impuesto: \$" + impuesto);

System.out.println("Archivo PDF generado correctamente.\\n");

}

}

// --- Fachada ---

class GeneradorDeReportesFiscales {

private ConectorDB conectorDB;

private LectorDeDatos lector;

private ServicioWebAFIP afip;

private ProcesadorDeImpuestos procesador;

private RenderizadorPDF renderizador;

public GeneradorDeReportesFiscales() {

conectorDB = new ConectorDB();

lector = new LectorDeDatos();

afip = new ServicioWebAFIP();

procesador = new ProcesadorDeImpuestos();

renderizador = new RenderizadorPDF();

}

public void generarReporteFiscal(int idCliente) {

conectorDB.conectar();

String cuit = lector.obtenerCUITCliente(idCliente);

afip.autenticar();

DatosFiscales datos = afip.obtenerDatosFiscales(cuit);

double impuesto = procesador.calcularImpuesto(datos);

renderizador.generarPDF(cuit, impuesto);

}

}

// --- Cliente ---

public class Main {

public static void main(String\[\] args) {

GeneradorDeReportesFiscales fachada = new GeneradorDeReportesFiscales();

fachada.generarReporteFiscal(1); // solo una llamada

}
}
```
### **Diagrama UML:**
*ver adjunto en sección "Diagramas" del repositorio*

---