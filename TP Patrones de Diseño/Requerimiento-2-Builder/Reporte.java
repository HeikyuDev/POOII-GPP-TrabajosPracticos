
import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase inmutable que representa un Reporte.
 * Se construye mediante el patrón Builder.
 */
public final class Reporte {
    // Obligatorios
    private final String titulo;
    private final String cuerpoPrincipal;

    // Opcionales
    private final String encabezado;
    private final String pieDePagina;
    private final LocalDate fecha;
    private final String autor;
    private final Orientacion orientacion;

    // Orientación del reporte: enum anidado para evitar import faltante
    public enum Orientacion {
        VERTICAL,
        HORIZONTAL
    }

    // Constructor privado que recibe el builder
    private Reporte(Builder b) {
        // Copiamos los valores del builder al objeto inmutable
        this.titulo = b.titulo;
        this.cuerpoPrincipal = b.cuerpoPrincipal;
        this.encabezado = b.encabezado;
        this.pieDePagina = b.pieDePagina;
        this.fecha = b.fecha;
        this.autor = b.autor;
        this.orientacion = b.orientacion;
    }

    // Getters (solo lectura)
    public String getTitulo() { return titulo; }
    public String getCuerpoPrincipal() { return cuerpoPrincipal; }
    public String getEncabezado() { return encabezado; }
    public String getPieDePagina() { return pieDePagina; }
    public LocalDate getFecha() { return fecha; }
    public String getAutor() { return autor; }
    public Orientacion getOrientacion() { return orientacion; }

    @Override
    public String toString() {
        return "Reporte{" +
                "titulo='" + titulo + '\'' +
                ", cuerpoPrincipal='" + (cuerpoPrincipal == null ? "null" : cuerpoPrincipal.substring(0, Math.min(40, cuerpoPrincipal.length())) + (cuerpoPrincipal.length()>40?"...":"")) + '\'' +
                ", encabezado='" + encabezado + '\'' +
                ", pieDePagina='" + pieDePagina + '\'' +
                ", fecha=" + fecha +
                ", autor='" + autor + '\'' +
                ", orientacion=" + orientacion +
                '}';
    }

    // Builder estático interno
    // es para que se pueda usar sin instanciar Reporte primero
    public static class Builder {
        // Obligatorios
        private final String titulo;
        private final String cuerpoPrincipal;

        // Opcionales inicializados con valores por defecto (si aplica)
        private String encabezado;
        private String pieDePagina;
        private LocalDate fecha;
        private String autor;
        private Orientacion orientacion = Orientacion.VERTICAL;

        /**
         * Constructor del builder con los parámetros obligatorios.
         * @param titulo título del reporte (no null)
         * @param cuerpoPrincipal contenido principal (no null)
         */
        public Builder(String titulo, String cuerpoPrincipal) {
            this.titulo = Objects.requireNonNull(titulo, "titulo no puede ser null");
            this.cuerpoPrincipal = Objects.requireNonNull(cuerpoPrincipal, "cuerpoPrincipal no puede ser null");
        }

        public Builder encabezado(String encabezado) {
            this.encabezado = encabezado;
            return this;
        }

        public Builder pieDePagina(String pieDePagina) {
            this.pieDePagina = pieDePagina;
            return this;
        }

        public Builder fecha(LocalDate fecha) {
            this.fecha = fecha;
            return this;
        }

        public Builder autor(String autor) {
            this.autor = autor;
            return this;
        }

        public Builder orientacion(Orientacion orientacion) {
            if (orientacion != null) this.orientacion = orientacion;
            return this;
        }

        public Reporte build() {
            // aquí podés validar reglas adicionales (ej: título mínimo, fecha no futura, etc.)
            return new Reporte(this);
        }
    }
}

