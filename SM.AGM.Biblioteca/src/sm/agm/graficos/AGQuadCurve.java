package sm.agm.graficos;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

/**
 * Clase AGQuadCurve
 * Un objeto de esta clase representa una curva con un punto de control. Cuenta
 * con atributos complejos. Hereda de la clase QuadCurve2D.Float para poder tener
 * toda su funcionalidad e implementa la interfaz AGShape para tener las funcionalidades
 * comunes al resto de figuras disponibles.
 * 
 * @author Angel
 */
public class AGQuadCurve extends QuadCurve2D.Float implements AGShape {

    /**
     * Atributos complejos (la figura se puede rellenar con un color sólido o con un degradado.
     */
    private final ComplexShapeAtributes atributes;
    
    /**
     * Constructor por defecto. Crea un curva con inicio en p1 y fin en p2.
     * @param p1 Punto de inicio del segmento que forma la curva
     * @param p2 Punto de fin del segmento que forma la curva
     */
    public AGQuadCurve(Point2D p1, Point2D p2) {
        super((float) p1.getX(), (float) p1.getY(), (float) p1.getX(), (float) p1.getY(), (float) p2.getX(), (float) p2.getY());
        atributes = new ComplexShapeAtributes();
    }
    
    /**
     * Constructor con parámetro. Además de los puntos donde crear la curva,
     * se le pasan unos atributos para asignarselos al mismo al crearlo. Usado
     * cuando se modifican los atributos en la interfaz de AGPaint previo dibujo
     * de una figura.
     * @param p1 Punto de inicio del segmento que forma la curva
     * @param p2 Punto de fin del segmento que forma la curva
     * @param atbs Atributos a asignar a la figura.
     */
    public AGQuadCurve(Point2D p1, Point2D p2, ComplexShapeAtributes atbs) {
        super((float) p1.getX(), (float) p1.getY(), (float) p1.getX(), (float) p1.getY(), (float) p2.getX(), (float) p2.getY());
        
        if (atbs != null)
            atributes = new ComplexShapeAtributes((ComplexShapeAtributes) atbs);
        else
            atributes = new ComplexShapeAtributes();
    }

    @Override
    public void setLocation(Point2D p) {
        // Punto de referencia.
        int x = (int) (p.getX() - getX1());
        int y = (int) (p.getY() - getY1());
        
        // Cambio la posición de los tres puntos (inicio, fin y control)
        super.x1 += x;
        super.y1 += y;
        super.x2 += x;
        super.y2 += y;
        super.ctrlx += x;
        super.ctrly += y;
        
        // Límites de la figura
        atributes.setBounds(this.getBounds2D());
    }
    
    /**
     * Asigna las coordenadas del punto de control
     * @param p Nuevo punto de control
     */
    public void setControlPoint(Point2D p) { ctrlx = (float) p.getX(); ctrly = (float) p.getY(); }

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
        return "Curva 1pt";
    }
}
