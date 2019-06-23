package sm.agm.graficos;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;

/**
 * Interfaz abstracta que implementan las clases que crean las figuras disponibles.
 * Tiene tres métodos:
 * Obtención de atributos de la figura.
 * Dibujar la figura con sus atributos.
 * Posicionar la figura en el lienzo.
 * @author Angel
 */
public interface AGShape extends Shape {
    
    /**
     * Cambia la posición de la figura en el lienzo.
     * @param p Punto usado como referencia para posicionar la figura en el lienzo.
     */
    public void setLocation(Point2D p);
    
    /**
     * Dibuja en el lienzo la figura con sus atributos.
     * @param g2d Objeto Graphics2D usado para dibujar la figura con sus atributos.
     */
    public void draw(Graphics2D g2d);

    /**
     * Devuelve los atributos de la figura.
     * @return Atributos de la figura.
     */
    public Atributes getAtributes();
}
