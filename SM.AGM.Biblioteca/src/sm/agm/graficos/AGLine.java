package sm.agm.graficos;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Clase AGLine
 * Un objeto de esta clase representa una línea. Cuenta con atributos simples.
 * Hereda de la clase Line2D.Float para poder tener toda su funcionalidad
 * e implementa la interfaz AGShape para tener las funcionalidades comunes al resto
 * de figuras disponibles.
 * 
 * @author Angel
 */
public class AGLine extends Line2D.Float implements AGShape {
    
    /**
     * Atributos simples
     */
    private final SimpleShapeAtributes atributes;
    
    /**
     * Constructor por defecto. Crea una línea con inicio en p1 y fin en p2.
     * @param a Punto de inicio del segmento que forma la línea
     * @param b Punto de fin del segmento que forma la línea
     */
    public AGLine(Point2D a, Point2D b) {
        super((int) a.getX(), (int) a.getY(), (int) b.getX(), (int) b.getY());
        atributes = new SimpleShapeAtributes();
    }
    
    /**
     * Constructor con parámetro. Además de los puntos donde crear la línea,
     * se le pasan unos atributos para asignarselos al mismo al crearlo. Usado
     * cuando se modifican los atributos en la interfaz de AGPaint previo dibujo
     * de una figura.
     * @param a Punto de inicio del segmento que forma la línea
     * @param b Punto de fin del segmento que forma la línea
     * @param atbs Atributos a asignar a la figura.
     */
    public AGLine(Point2D a, Point2D b, SimpleShapeAtributes atbs) {
        super((int) a.getX(), (int) a.getY(), (int) b.getX(), (int) b.getY());
        
        if (atbs != null)
            atributes = new SimpleShapeAtributes(atbs);
        else
            atributes = new SimpleShapeAtributes();
    }
    
    @Override
    public SimpleShapeAtributes getAtributes() { return atributes; }
    
    /**
     * Devuelve si el punto pasado como argumento está a una distancia inferior
     * a 3 de la línea.
     * @param p Punto a comprobar.
     * @return Si el punto está cerca de la línea.
     */
    public boolean isNear(Point2D p) {
        return this.ptLineDist(p) <= 3.0;
    }
    
    /**
     * Devuelve si el punto pasado como argumento está contenido en la línea, es
     * decir, si está cerca de ella.
     * @param p Punto a comprobar.
     * @return Si el punto está cerca de la línea.
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
        
        // Nuevo punto para trasladar la línea
        Point2D newp2 = new Point2D.Double(this.getX2() + dx, this.getY2() + dy);
        
        // setLine, método de Line2D para situar la línea
        this.setLine(pos, newp2);
        
        // Límites de la figura
        atributes.setBounds(this.getBounds2D());
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        // Aplico atributos
        atributes.applyAtributes(g2d);
        
        // Dibujado de la línea
        g2d.draw(this);            
    }

    @Override
    public String toString() {
        return "Línea";
    }
}
