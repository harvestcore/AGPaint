package sm.agm.graficos;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Clase AGEllipse
 * Un objeto de esta clase representa una elipse. Cuenta con atributos complejos.
 * Hereda de la clase Ellipse2D.Float para poder tener toda su funcionalidad
 * e implementa la interfaz AGShape para tener las funcionalidades comunes al resto
 * de figuras disponibles.
 * 
 * @author Angel
 */
public class AGEllipse extends Ellipse2D.Float implements AGShape{
    
    /**
     * Atributos complejos (la figura se puede rellenar con un color sólido o con un degradado.
     */
    private final ComplexShapeAtributes atributes;
        
    /**
     * Constructor por defecto. Crea una elipse en el punto indicado.
     * @param p Punto donde se crea la elipse.
     */
    public AGEllipse(Point2D p) {
        super((int) p.getX(), (int) p.getY(), 1, 1);
        atributes = new ComplexShapeAtributes();
    }
    
    /**
     * Constructor con parámetro. Además del punto donde crear la elipse,
     * se le pasan unos atributos para asignarselos al mismo al crearlo. Usado
     * cuando se modifican los atributos en la interfaz de AGPaint previo dibujo
     * de una figura.
     * @param p Punto donde se crea la elipse.
     * @param atbs Atributos a asignar a la figura.
     */
    public AGEllipse(Point2D p, ComplexShapeAtributes atbs) {
        super((int) p.getX(), (int) p.getY(), 1, 1);
        
        if (atbs != null)
            atributes = new ComplexShapeAtributes((ComplexShapeAtributes) atbs);
        else
            atributes = new ComplexShapeAtributes();
    }

    @Override
    public void setLocation(Point2D p) {
        // setFrame, método de Ellipse2D para situar la elipse
        super.setFrame(p.getX(), p.getY(), getWidth(), getHeight());
        
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
        return "Elipse";
    }
}
