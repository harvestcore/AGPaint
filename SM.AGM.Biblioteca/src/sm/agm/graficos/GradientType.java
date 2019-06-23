package sm.agm.graficos;

/**
 * Tipos de gradiente.
 * @author Angel
 */
public enum GradientType {

    /**
     * De arriba a abajo.
     */
    TOPDOWN("Top-Down"),

    /**
     * De abajo a arriba.
     */
    DOWNTOP("Down-Top"),

    /**
     * De izquierda a derecha.
     */
    LEFTRIGHT("Left-Right"),

    /**
     * De derecha a izquierda.
     */
    RIGHTLEFT("Right-Left");
    
    /**
     * Nombre del degradado.
     */
    private String name;
        
    /**
     * Constructor por defecto. Asigna el nombre al degradado.
     */
    private GradientType(String name) { this.name = name; }

    /**
     * Devuelve el nombre del degradado.
     * @return Nombre del degradado.
     */
    public String getName() { return name; }
    
    @Override
    public String toString() { return this.getName(); }
}
