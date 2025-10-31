

import java.time.LocalDate;


public class MainDemoBuilder {
    
    public static void main(String[] args) {
        // Ejemplo 1: solo obligatorios
        Reporte r1 = new Reporte.Builder("Informe Financiero Q1", "Resumen de ingresos y egresos...")
                .build();

        // Ejemplo 2: con opciones
        Reporte r2 = new Reporte.Builder("Análisis Marketing", "Detalles de campañas y métricas...")
                .encabezado("Confidencial - Uso Interno")
                .pieDePagina("Página 1 de 10")
                .fecha(LocalDate.of(2025, 3, 10))
                .autor("Equipo Marketing")
                .orientacion(Reporte.Orientacion.HORIZONTAL)
                .build();

        System.out.println("Reporte 1: " + r1);
        System.out.println("Reporte 2: " + r2);
    }
}
