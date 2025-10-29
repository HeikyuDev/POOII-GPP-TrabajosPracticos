// Importa solo las interfaces y el Creador
import java.util.Scanner;

/**
 * Clase principal (Main) que actúa como el "Cliente" en el patrón Factory.
 * * Esta clase contiene el punto de entrada (main) de la aplicación y demuestra
 * cómo el cliente (en este caso, simulando un módulo de finanzas)
 * solicita un reporte sin conocer la implementación concreta.
 * * Está desacoplado de las clases concretas (como ReportePDF o 
 * ReportePDFFactory) y solo interactúa con las interfaces (Reporte, 
 * ReporteFactory) y la clase CreadorDeFabricas.
 */
public class Main {
    public static void main(String[] args) {

        Scanner unScanner = new Scanner(System.in);
        int valor;
        // Lógica de negocio del cliente (ej. Módulo de Finanzas)
        // ...

        System.out.println("Seleccione el Tipo de Reporte Que desea Hacer:\n1: PDF\n2: EXCEL\n3:CSV");

        ReporteFactory miFabrica;
        Reporte miReporte;

        try {
            valor = unScanner.nextInt();

            // 1. Pedimos la fábrica al "CreadorDeFabricas"
            // El cliente NO SABE cuál fábrica le están dando,
            // solo sabe que es del tipo "ReporteFactory" (la interfaz).
            miFabrica = CreadorDeFabricas.obtenerFactory(valor);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            unScanner.close();
            return; // Salir si hay error
        }

        // 2. Usamos la FÁBRICA (interfaz) para crear el PRODUCTO (interfaz)
        // El cliente no sabe si está creando un PDF o un Excel.
        // Solo llama al método de la interfaz.
        miReporte = miFabrica.crearReporte();

        // 3. Usamos el PRODUCTO (interfaz) para trabajar
        // (Suponiendo que tu interfaz Reporte tiene estos métodos)
        // miReporte.generar(datosFinancieros);
        // byte[] archivo = miReporte.getBytes();
        // System.out.println("Reporte generado. Tipo: " + miReporte.getMimeType());

        System.out.println("¡Reporte generado exitosamente!");

        unScanner.close();
    }
}