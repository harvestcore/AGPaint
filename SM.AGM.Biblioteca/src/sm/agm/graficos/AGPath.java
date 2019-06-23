package sm.agm.graficos;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Clase AGPath
 * Un objeto de esta clase representa un trazado libre. Cuenta con atributos simples.
 * Hereda de la clase Path2D.Float para poder tener toda su funcionalidad
 * e implementa la interfaz AGShape para tener las funcionalidades comunes al resto
 * de figuras disponibles.
 * 
 * @author Angel
 */
public class AGPath extends Path2D.Float implements AGShape {
    
    /**
     * Atributos simples
     */
    private final SimpleShapeAtributes atributes;
    
    /**
     * Puntos de todo el trazado libre
     */
    private ArrayList<Point> points = new ArrayList<>();
    
    /**
     * Constructor por defecto. Crea un trazado libre empezando en el punto
     * pasado como parámetro.
     * @param p Primer punto del trazado
     */
    public AGPath(Point2D p) {
        super(Path2D.WIND_EVEN_ODD);
        atributes = new SimpleShapeAtributes();
        moveTo(p.getX(), p.getY());
    }
    
    /**
     * Constructor con parámetro. Además del primer punto donde crear el trazado,
     * se le pasan unos atributos para asignarselos al mismo al crearlo. Usado
     * cuando se modifican los atributos en la interfaz de AGPaint previo dibujo
     * de una figura.
     * @param p Primer punto del trazado
     * @param atbs Atributos a asignar a la figura.
     */
    public AGPath(Point2D p, SimpleShapeAtributes atbs) {
        super(Path2D.WIND_EVEN_ODD);
        
        if (atbs != null)
            atributes = new SimpleShapeAtributes((SimpleShapeAtributes) atbs);
        else
            atributes = new SimpleShapeAtributes();
        
        moveTo(p.getX(), p.getY());
    }

    @Override
    public void setLocation(Point2D p) {
        // Elimino todos los puntos del path actual
        super.reset();
        
        // En el vector auxiliar los tengo todos, por lo que lo itero y los traslado
        if (points.size() > 0) {
            // Punto de referencia
            int x = (int) (p.getX() - points.get(0).x);
            int y = (int) (p.getY() - points.get(0).y);
            
            // Recorro puntos, los traslado y los agrego al path actual
            for (Point pp: points) {
                pp.translate(x, y);
                if (pp == points.get(0)) super.moveTo(pp.x, pp.y);
                else {
                    super.lineTo(pp.x, pp.y);
                }
            }
        }
        
        // Límites de la figura
        atributes.setBounds(this.getBounds2D());
    }
    
    /**
     * Agrega un punto al trazado libre
     * @param a Coordenada X del punto a agregar.
     * @param b Coordenada Y del punto a agregar.
     */
    public void addPoint(int a, int b) {
        points.add(new Point(a, b));
        super.lineTo(a, b);
    }

    @Override
    public SimpleShapeAtributes getAtributes() { return atributes; }
    
    @Override
    public void draw(Graphics2D g2d) {
        // Aplico atributos
        atributes.applyAtributes(g2d);
        
        // Dibujado del trazado
        g2d.draw(this);            
    }

    @Override
    public String toString() {
        return "Trazado libre";
    }
}
