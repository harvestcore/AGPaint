package sm.agm.graficos;

/**
 * Tipos de filtro de imagen.
 * @author Angel
 */
public enum ImageFilterType {

    /**
     * Emborronamiento medio
     */
    HALFBLURRING("Emborronamiento medio"),

    /**
     * Emborronamiento binomial
     */
    BINOMIALBLURRING("Emborronamiento binomial"),

    /**
     * Enfoque
     */
    FOCUS("Enfoque"),

    /**
     * Relieve
     */
    RELIEF("Relieve"),

    /**
     * Detector de fronteras laplaciano
     */
    LAPLACIANBORDERDETECTOR("Detector de fronteras laplaciano"),

    /**
     * Ningún filtro
     */
    NONE("Ninguno");

    /**
     * Nombre del tipo de filtro.
     */
    private String filter;
        
    /**
     * Constructor por defecto. Asigna el nombre al filtro.
     */
    private ImageFilterType(String filter) { this.filter = filter; }

    /**
     * Devuelve el nombre del filtro.
     * @return Nombre del filtro.
     */
    public String getFilter() { return filter; }
    
    @Override
    public String toString() { return this.getFilter(); }
}
