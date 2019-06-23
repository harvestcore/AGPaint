package sm.agm.graficos;

import java.awt.Graphics2D;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;

/**
 * Clase AGQuadCurve
 * Un objeto de esta clase representa un arco con inicio, fin y ángulo. Cuenta
 * con atributos complejos. Hereda de la clase Arc2D.Float para poder tener
 * toda su funcionalidad e implementa la interfaz AGShape para tener las funcionalidades
 * comunes al resto de figuras disponibles.
 * 
 * @author Angel
 */
public class AGArc extends Arc2D.Float implements AGShape {
    
    /**
     * Atributos complejos (la figura se puede rellenar con un color sólido o con un degradado.
     */
    private final ComplexShapeAtributes atributes;
    
    /**
     * Constructor por defecto. Crea un arco en el punto p1.
     * @param p1 Punto de inicio del arco
     */
    public AGArc(Point2D p1) {
        super((float) p1.getX(), (float) p1.getY(), 1, 1, 0, 0, Arc2D.OPEN);
        atributes = new ComplexShapeAtributes();
    }
    
    /**
     * Constructor con parámetro. Además del punto donde crear el arco,
     * se le pasan unos atributos para asignarselos al mismo al crearlo. Usado
     * cuando se modifican los atributos en la interfaz de AGPaint previo dibujo
     * de una figura.
     * @param p1 Punto de inicio del arco
     * @param atbs Atributos a asignar a la figura.
     */
    public AGArc(Point2D p1, ComplexShapeAtributes atbs) {
        super((float) p1.getX(), (float) p1.getY(), 1, 1, 0, 0, Arc2D.OPEN);
        
        if (atbs != null)
            atributes = new ComplexShapeAtributes((ComplexShapeAtributes) atbs);
        else
            atributes = new ComplexShapeAtributes();
    }
    
    @Override
    public void setLocation(Point2D p) {
        // setFrame, método de Arc2D para situar el rectángulo
        super.setFrame(p.getX(), p.getY(), getWidth(), getHeight());
        
        // Límites de la figura
        atributes.setBounds(this.getBounds2D());
    }
    
    /**
     * Asigna el ángulo del arco.
     * @param angle Nuevo ángulo del arco
     */
    public void setAngle(float angle) { extent = angle; }

    /**
     * Asigna el punto de comienzo del arco.
     * @param st Nuevo punto de comienzo
     */
    public void setStart(float st) { start = st; }

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
        return "Arco";
    }
}
