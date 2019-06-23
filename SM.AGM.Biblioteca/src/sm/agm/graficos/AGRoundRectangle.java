package sm.agm.graficos;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Clase AGRoundRectangle
 * Un objeto de esta clase representa un rectángulo con los border redondeados.
 * Cuenta con atributos complejos. Hereda de la clase RoundRectangle2D.Float
 * para poder tener toda su funcionalidad e implementa la interfaz AGShape para
 * tener las funcionalidades comunes al resto de figuras disponibles.
 * 
 * @author Angel
 */
public class AGRoundRectangle extends RoundRectangle2D.Float implements AGShape {
    
    /**
     * Atributos complejos (la figura se puede rellenar con un color sólido o con un degradado.
     */
    private final ComplexShapeAtributes atributes;
    
    /**
     * Constructor por defecto. Crea un rectángulo redondeado en el punto indicado.
     * @param p Punto donde se crea el rectángulo redondeado.
     */
    public AGRoundRectangle(Point2D p) {
        super((float) p.getX(), (float) p.getY(), 0, 0, 25, 25);
        atributes = new ComplexShapeAtributes();
    }
    
    /**
     * Constructor con parámetro. Además del punto donde crear el rectángulo
     * redondeado, se le pasan unos atributos para asignarselos al mismo al
     * crearlo. Usado cuando se modifican los atributos en la interfaz de AGPaint
     * previo dibujo de una figura.
     * @param p Punto donde se crea el rectángulo redondeado.
     * @param atbs Atributos a asignar a la figura.
     */
    public AGRoundRectangle(Point2D p, ComplexShapeAtributes atbs) {
        super((float) p.getX(), (float) p.getY(), 0, 0, 25, 25);
        
        if (atbs != null)
            atributes = new ComplexShapeAtributes((ComplexShapeAtributes) atbs);
        else
            atributes = new ComplexShapeAtributes();
    }
    
    @Override
    public void setLocation(Point2D p) {
        // setFrame, método de RoundRectangle2D para situar el rectángulo redondeado
        super.setFrame(p.getX(), p.getY(), getWidth(), getHeight());
        
        // Límites de la figura
        atributes.setBounds(this.getBounds2D());
    }
    
    /**
     * Especifica el ancho del arco de las esquinas.
     * @param w Ancho del arco de las esquinas.
     */
    public void setArchWidth(float w) { arcwidth = w; }

    /**
     * Especifica el alto del arco de las esquinas.
     * @param h Alto del arco de las esquinas.
     */
    public void setArchHeight(float h) { archeight = h; }
    
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
        return "Rectángulo redondeado";
    }
}
