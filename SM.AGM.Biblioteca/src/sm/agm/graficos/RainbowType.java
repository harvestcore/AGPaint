package sm.agm.graficos;

/**
 * Tipos de filtro Rainbow.
 * @author angel
 */
public enum RainbowType {

    /**
     * RGB
     */
    RGB("RGB"),

    /**
     * RBG
     */
    RBG("RBG"),

    /**
     * GRB
     */
    GRB("GRB"),

    /**
     * GBR
     */
    GBR("GBR"),

    /**
     * BGR
     */
    BGR("BGR"),

    /**
     * BRG
     */
    BRG("BRG");
    
    /**
     * Nombre del tipo de filto.
     */
    private String name;
        
    /**
     * Constructor por defecto. Asigna el nombre al filtro.
     */
    private RainbowType(String name) { this.name = name; }

    /**
     * Devuelve el nombre del filtro,
     * @return Nombre del filtro.
     */
    public String getName() { return name; }
    
    @Override
    public String toString() { return this.getName(); }
}
