# Código de muestra - Escenario 1
---

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