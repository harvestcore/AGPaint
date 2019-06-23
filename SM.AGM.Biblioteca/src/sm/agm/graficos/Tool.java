package sm.agm.graficos;

/**
 * Tipos de herramientas disponibles.
 * @author Angel
 */
public enum Tool {

    /**
     * Punto
     */
    POINT("Punto"),

    /**
     * Línea
     */
    LINE("Línea"),

    /**
     * Rectángulo
     */
    RECTANGLE("Rectángulo"),

    /**
     * Elipse
     */
    ELLIPSE("Elipse"),

    /**
     * Rectángulo redondeado
     */
    ROUNDRECTANGLE("Rectángulo redondeado"),

    /**
     * Arco
     */
    ARC("Arco"),

    /**
     * Curva con un punto
     */
    CUBICCURVE("Curva con un punto"),

    /**
     * Curva con dos puntos
     */
    QUADCURVE("Curva con dos puntos"),

    /**
     * Trazado libre
     */
    PATH("Trazado libre"),

    /**
     * Ninguna herramienta.
     */
    NONE("-");
    
    /**
     * Nombre de la herramienta.
     */
    private String name;
        
    /**
     * Constructor por defecto. Asigna el nombre a la herramienta.
     */
    private Tool(String name) { this.name = name; }

    /**
     * Devuelve el nombre de la herramienta.
     * @return Nombre de la herramienta.
     */
    public String getName() { return name; }
    
    @Override
    public String toString() { return this.getName(); }
}
