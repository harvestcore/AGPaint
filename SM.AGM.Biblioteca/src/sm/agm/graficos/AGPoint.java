package sm.agm.graficos;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Clase AGPoint
 * Un objeto de esta clase representa una punto, usando como representación interna
 * una línea cuyo inicio y fin es el mismo punto. Cuenta con atributos simples.
 * Hereda de la clase Line2D.Float para poder tener toda su funcionalidad
 * e implementa la interfaz AGShape para tener las funcionalidades comunes al resto
 * de figuras disponibles.
 * 
 * @author Angel
 */
public class AGPoint extends Line2D.Float implements AGShape {
    
    /**
     * Punto representado
     */
    private Point2D p;
    
    /**
     * Atributos simples
     */
    private final SimpleShapeAtributes atributes;
    
    /**
     * Constructor por defecto. Crea un punto
     * @param a Punto a crear
     */
    public AGPoint(Point2D a) {
        super((int) a.getX(), (int) a.getY(), (int) a.getX(), (int) a.getY());
        p = a;
        atributes = new SimpleShapeAtributes();
    }
    
    /**
     * Constructor con parámetro. Además de los puntos donde crear el punto,
     * se le pasan unos atributos para asignarselos al mismo al crearlo. Usado
     * cuando se modifican los atributos en la interfaz de AGPaint previo dibujo
     * de una figura.
     * @param a Punto de inicio del segmento que forma la línea
     * @param atbs Atributos a asignar a la figura.
     */
    public AGPoint(Point2D a, SimpleShapeAtributes atbs) {
        super((int) a.getX(), (int) a.getY(), (int) a.getX(), (int) a.getY());
        p = a;
        
        if (atbs != null)
            atributes = new SimpleShapeAtributes(atbs);
        else
            atributes = new SimpleShapeAtributes();
    }
    
    @Override
    public SimpleShapeAtributes getAtributes() { return atributes; }
    
    /**
     * Devuelve si el punto pasado como argumento está a una distancia inferior
     * a 3 del punto.
     * @param p Punto a comprobar.
     * @return Si el punto está cerca del punto referencia.
     */
    public boolean isNear(Point2D p) {
        return this.p.distance(p) <= 3.0;
    }
    
    /**
     * Devuelve si el punto pasado como argumento está contenido en el punto, es
     * decir, si está cerca de el.
     * @param p Punto a comprobar.
     * @return Si el punto está cerca del punto referencia.
     */
    @Override
    public boolean contains(Point2D p) {
        return isNear(p);
    }

    @Override
    public void setLocation(Point2D pos){
        // Punto de referencia
        double dx = pos.getX() - this.getX1();
        double dy = pos.getY() - this.getY1();
        
        // Nuevo punto para trasladar el punto
        Point2D newp2 = new Point2D.Double(this.getX2() + dx, this.getY2() + dy);
        
        // setLine, método de Line2D para situar el rectángulo
        this.setLine(pos, newp2);
        
        p = pos;
        
        // Límites de la figura
        atributes.setBounds(this.getBounds2D());
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        // Aplico atributos
        atributes.applyAtributes(g2d);
        
        // Dibujado del punto
        g2d.draw(this);            
    }
    
    @Override
    public String toString() {
        return "Punto";
    }
}