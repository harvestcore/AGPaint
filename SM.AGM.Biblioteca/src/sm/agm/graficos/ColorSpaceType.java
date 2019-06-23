package sm.agm.graficos;

/**
 * Tipos de espacio de color.
 * @author angel
 */
public enum ColorSpaceType {

    /**
     * sRGB
     */
    RGB("sRGB"),

    /**
     * YCbCr
     */
    YCC("YCbCr"),

    /**
     * Escala de grises
     */
    GRAY("Gray");
    
    /**
     * Nombre del espacio de color.
     */
    private String name;
        
    /**
     * Constructor por defecto. Asigna el nombre al espacio de color.
     */
    private ColorSpaceType(String name) { this.name = name; }

    /**
     * Devuelve el nombre del espacio de color.
     * @return Nombre del espacio de color.
     */
    public String getName() { return name; }
    
    @Override
    public String toString() { return this.getName(); }
}
