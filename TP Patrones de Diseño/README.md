# Trabajo Práctico: Patrones de Diseño Creacionales

**Cátedra:** Programación Orientada a Objetos II  
**Tema:** Aplicación de Patrones de Diseño Creacionales  
**Lenguaje:** Java

---

## 👥 Integrantes del Equipo

| Nombre                          | Rol       |
|---------------------------------|-----------|
| Guillermo Ariel Gallo           | Estudiante |
| Pedernera Theisen Nahuel Thomas | Estudiante |
| Pergher Lucas Maurice           | Estudiante |

---

## 📋 Índice

1. [Objetivo](#objetivo)
2. [Escenario del Problema](#escenario-del-problema)
3. [Análisis y Justificación de Patrones](#análisis-y-justificación-de-patrones)
4. [Implementación](#implementación)
5. [Diagrama de Clases UML](#diagrama-de-clases-uml)
6. [Conclusiones](#conclusiones)
7. [Referencias](#referencias)

---

## 🎯 Objetivo

El objetivo de este trabajo práctico es demostrar la capacidad de:

1. Analizar un conjunto de requerimientos de software
2. Identificar los problemas de diseño creacional (instanciación) presentes en el sistema
3. Seleccionar y aplicar de forma adecuada los patrones de diseño **Singleton**, **Factory** (Factory Method o Abstract Factory) y **Builder**
4. Justificar por escrito las decisiones de diseño tomadas
5. Modelar la solución mediante un diagrama de clases UML

---

## 📦 Escenario del Problema: Sistema de Configuración y Renderizado de Reportes

Se debe diseñar el núcleo de un **sistema de generación de reportes** para una aplicación de análisis de datos. Este sistema será utilizado por múltiples módulos de la aplicación (ej. módulo de finanzas, marketing, RRHH).

El sistema presenta **tres grandes desafíos de diseño** que deben ser resueltos mediante patrones creacionales.

### 🔧 Requerimiento 1: Motor de Renderizado

**Problema:** El sistema debe exportar reportes en diferentes formatos (PDF, Excel, CSV).

**Desafíos:**
- Cada tipo de reporte tiene una lógica de renderizado completamente diferente
- El código cliente NO debe acoplarse a las clases concretas
- El sistema debe ser extensible para nuevos formatos sin modificar código existente

**Formatos requeridos:**
- `ReportePDF` → `RenderizadorPDF`
- `ReporteExcel` → `RenderizadorExcel`
- `ReporteCSV` → `RenderizadorCSV`

### 🏗️ Requerimiento 2: Construcción de Reportes

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

### ⚙️ Requerimiento 3: Gestor de Configuración Global

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

## 🧩 Análisis y Justificación de Patrones

### Requerimiento 1: Motor de Renderizado

#### Patrón Seleccionado
- **Factory Method**

#### Justificación
*[A completar]*

**¿Por qué este patrón es la solución adecuada?**
- *[Explicar las ventajas del patrón seleccionado]*

**¿Qué problemas evita?**
- *[Mencionar problemas de acoplamiento]*
- *[Explicar cómo respeta el principio Abierto/Cerrado]*

#### Alternativas Consideradas
*[Opcional: Mencionar otros patrones evaluados y por qué no fueron seleccionados]*

---

### Requerimiento 2: Construcción de Reportes

#### Patrón Seleccionado
*[A completar con Builder]*

#### Justificación
*[A completar]*

**¿Por qué este patrón es la solución adecuada?**
- *[Explicar las ventajas del patrón Builder]*

**¿Qué problemas específicos del "constructor" resuelve?**
- *[Explicar cómo evita el constructor telescópico]*
- *[Mencionar la mejora en legibilidad]*
- *[Explicar el manejo de parámetros opcionales]*

#### Ejemplo de Uso
```java
// Ejemplo de cómo se construiría un reporte usando el patrón
// [A completar con código de ejemplo]
```

---

### Requerimiento 3: Gestor de Configuración Global

#### Patrón Seleccionado
*[A completar con Singleton]*

#### Justificación
*[A completar]*

**¿Por qué este patrón es la solución adecuada?**
- *[Explicar la necesidad de una única instancia]*

**¿Cómo se garantizó la unicidad de la instancia?**
- *[Explicar la implementación técnica del Singleton]*
- *[Mencionar consideraciones de thread-safety si aplica]*

#### Implementación
*[Describir brevemente el enfoque: Singleton clásico, Eager initialization, Lazy initialization, etc.]*

---

## 💻 Implementación

### Estructura del Proyecto

```
src/
├── main/
│   └── java/
│       ├── patrones/
│       │   ├── factory/
│       │   │   ├── Renderizador.java
│       │   │   ├── RenderizadorPDF.java
│       │   │   ├── RenderizadorExcel.java
│       │   │   ├── RenderizadorCSV.java
│       │   │   └── [Clases del patrón Factory]
│       │   ├── builder/
│       │   │   ├── Reporte.java
│       │   │   ├── ReporteBuilder.java
│       │   │   └── Orientacion.java
│       │   └── singleton/
│       │       └── GestorConfiguracion.java
│       └── Main.java
└── test/
    └── [Clases de prueba]
```

### Tecnologías Utilizadas
- **Lenguaje:** Java 17+
- **Build Tool:** Maven / Gradle
- **Testing:** JUnit 5

### Instrucciones de Ejecución

```bash
# Clonar el repositorio
git clone [URL_DEL_REPOSITORIO]

# Compilar el proyecto
mvn compile

# Ejecutar la clase Main
mvn exec:java -Dexec.mainClass="Main"

# Ejecutar los tests
mvn test
```

---

## 📊 Diagrama de Clases UML

### Diagrama General del Sistema

*[Insertar aquí el diagrama de clases UML completo]*

```
[Imagen o código Mermaid/PlantUML del diagrama]
```

### Descripción del Diagrama

**Clases Principales:**
- *[Listar y describir brevemente las clases principales]*

**Relaciones:**
- *[Describir las relaciones clave entre clases]*

**Patrones Aplicados:**
- *[Indicar dónde se visualiza cada patrón en el diagrama]*

---

## ✅ Conclusiones

### Logros Alcanzados
*[A completar con los logros del trabajo]*

### Aprendizajes
*[A completar con los aprendizajes obtenidos]*

### Mejoras Futuras
*[A completar con posibles extensiones o mejoras]*

---

## 📖 Referencias

### Bibliografía
- Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1994). *Design Patterns: Elements of Reusable Object-Oriented Software*. Addison-Wesley.
- Bloch, J. (2018). *Effective Java* (3rd Edition). Addison-Wesley.

### Recursos en Línea
- [Refactoring.Guru - Design Patterns](https://refactoring.guru/design-patterns)
- [Source Making - Design Patterns](https://sourcemaking.com/design_patterns)
- [Oracle Java Documentation](https://docs.oracle.com/en/java/)

### Material de la Cátedra
- *[Agregar referencias a materiales del curso]*

---

## 📝 Notas Adicionales

### Decisiones de Diseño
*[Documentar decisiones importantes que no encajen en otras secciones]*

### Problemas Encontrados y Soluciones
*[Opcional: Documentar desafíos enfrentados durante el desarrollo]*

---

**Fecha de Entrega:** *[A completar]*  
**Institución:** *[A completar]*  
**Año Académico:** 2025

---

*Documento elaborado con fines académicos para la cátedra de Programación Orientada a Objetos II*