package sm.agm.graficos;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

/**
 * Clase AGRectangle
 * Un objeto de esta clase representa un rectángulo. Cuenta con atributos complejos.
 * Hereda de la clase Rectangle para poder tener toda su funcionalidad
 * e implementa la interfaz AGShape para tener las funcionalidades comunes al resto
 * de figuras disponibles.
 * 
 * @author Angel
 */
public class AGRectangle extends Rectangle implements AGShape {
    
    /**
     * Atributos complejos (la figura se puede rellenar con un color sólido o con un degradado.
     */
    private final ComplexShapeAtributes atributes;
    
    /**
     * Constructor por defecto. Crea un rectángulo en el punto indicado.
     * @param p Punto donde se crea el rectángulo.
     */
    public AGRectangle(Point2D p) {
        super((Point) p);
        atributes = new ComplexShapeAtributes();
    }
    
    /**
     * Constructor con parámetro. Además del punto donde crear el rectángulo,
     * se le pasan unos atributos para asignarselos al mismo al crearlo. Usado
     * cuando se modifican los atributos en la interfaz de AGPaint previo dibujo
     * de una figura.
     * @param p Punto donde se crea el rectángulo.
     * @param atbs Atributos a asignar a la figura.
     */
    public AGRectangle(Point2D p, Atributes atbs) {
        super((Point) p);
        
        if (atbs != null)
            atributes = new ComplexShapeAtributes((ComplexShapeAtributes) atbs);
        else
            atributes = new ComplexShapeAtributes();
    }

    @Override
    public void setLocation(Point2D p) {
        // setLocation, método de Rectangle para situar el rectángulo
        super.setLocation((Point) p);
        
        // Límites de la figura
        atributes.setBounds(this.getBounds2D());
    }

    @Override
    public ComplexShapeAtributes getAtributes() { return atributes; }
    
    @Override
    public void draw(Graphics2D g2d) {
        // Aplico atributos
        atributes.applyAtributes(g2d);
        
        // Relleno o dibujo en función de las propiedades
        if (atributes.fill() || atributes.gradient()) g2d.fill(this);
        else g2d.draw(this);            
    }

    @Override
    public String toString() {
        return "Rectángulo";
    }
}
