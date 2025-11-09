# Código de muestra - Escenario 2 
---

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