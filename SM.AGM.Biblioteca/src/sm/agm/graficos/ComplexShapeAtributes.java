package sm.agm.graficos;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Clase ComplexShapeAtributes
 * 
 * Esta clase amplía los atributos de su clase padre SimpleShapeAtributes. Clase
 * usada principalmente para dar propiedades al resto de figuras disponibles.
 * 
 * Los atributos adicionales son:
 * Relleno/gradiente o no de la figura.
 * Color de relleno/gradiente.
 * Tipo de gradiente.
 * Puntos para direccionar la gradiente.
 * 
 * @author angel
 */
public class ComplexShapeAtributes extends SimpleShapeAtributes {
    
    /**
     * Indica si la figura está rellena o no.
     */
    private boolean fill;
    
    /**
     * Color de relleno/gradiente.
     */
    private Color backgroundColor;
    
    /**
     * Indica si el relleno de la figura es una gradiente o no.
     */
    private boolean gradient;
    
    /**
     * Tipo de gradiente.
     */
    private GradientType gradientType;
    
    /**
     * Objeto GradientPaint con la gradiente.
     */
    private GradientPaint gradientColor;
    
    /**
     * Primer punto de referencia para crear la gradiente.
     */
    private Point2D g1;
    
    /**
     * Segundo punto de referencia para crear la gradiente.
     */
    private Point2D g2;
    
    /**
     * Constructor por defecto con parámetros básicos.
     */
    public ComplexShapeAtributes() {
        fill = false;
        gradient = false;
        backgroundColor = Color.GRAY;
        gradientType = GradientType.TOPDOWN;
    }
    
    /**
     * Constructor de copia. Usado cuando se preseleccionan propiedades en la interfaz de AGPaint.
     * @param c Atributos a copiar.
     */
    public ComplexShapeAtributes(ComplexShapeAtributes c) {
        super(c);
        this.fill = c.fill();
        this.backgroundColor = c.getBackgroundColor();
        this.gradient = c.gradient();
        this.gradientType = c.getGradientType();
        this.gradientColor = c.gradientColor;
        this.g1 = c.g1;
        this.g2 = c.g2;
    }

    /**
     * Devuelve si la figura está rellena o no.
     * @return Si la figura está rellena o no.
     */
    public boolean fill() { return fill; }

    /**
     * Devuelve si la figura está rellena con una gradiente o no.
     * @return Si la figura está rellena con una gradiente o no.
     */
    public boolean gradient() { return gradient; }

    /**
     * Devuelve el color de fondo/gradiente.
     * @return Color de fondo/gradiente.
     */
    public Color getBackgroundColor() { return backgroundColor; }

    /**
     * Devuelve el objeto GradientPaint con el degradado. Usado para crear el mismo.
     * @return Degradado.
     */
    public GradientPaint getGradient() { return gradientColor; }

    /**
     * Devuelve el tipo de gradiente.
     * @see GradientType
     * @return Tipo de gradiente.
     */
    public GradientType getGradientType() { return gradientType; }

    /**
     * Asigna si queremos rellenar la figura o no.
     * @param fill Si queremos rellenar la figura o no.
     */
    public void setFill(boolean fill) { this.fill = fill; }

    /**
     * Asigna si queremos activar el rellenado con gradiente o no.
     * @param gradient Si queremos activar el rellenado con gradiente o no.
     */
    public void setGradient(boolean gradient) { this.gradient = gradient; }

    /**
     * Asigna el color de fondo/gradiente.
     * @param backgroundColor Nuevo color de fondo/gradiente.
     */
    public void setBackgroundColor(Color backgroundColor) { this.backgroundColor = backgroundColor; }

    /**
     * Asigna el tipo de gradiente.
     * @param gradientType Nuevo tipo de gradiente.
     */
    public void setGradientType(GradientType gradientType) { this.gradientType = gradientType; };

    /**
     * Actualiza la gradiente creando un nuevo objeto GradientPaint. Usado cuando
     * se cambia de tipo de gradiente.
     */
    public void updateGradient() { gradientColor = new GradientPaint(g1, getStrokeColor(), g2, backgroundColor); }
    
    /**
     * Crea la gradiente a partir de los límites de la figura, del tipo de gradiente
     * y de los colores de trazado y fondo/gradiente.
     * @param bounds Límites de la figura
     */
    public void createGradient(Rectangle2D bounds) {        
        switch (gradientType) {
            case LEFTRIGHT:
                g1 = new Point2D.Float((float) bounds.getMinX(), (float) bounds.getMinY() / 2);
                g2 = new Point2D.Float((float) bounds.getMaxX(), (float) bounds.getMinY() / 2);
                break;
                
            case RIGHTLEFT:
                g1 = new Point2D.Float((float) bounds.getMaxX(), 0);
                g2 = new Point2D.Float((float) bounds.getMinX(), 0);
                break;
                
            case TOPDOWN:
                g1 = new Point2D.Float(0, (float) bounds.getMinY());
                g2 = new Point2D.Float(0, (float) bounds.getMaxY());
                break;
                
            case DOWNTOP:
                g1 = new Point2D.Float(0, (float) bounds.getMaxY());
                g2 = new Point2D.Float(0, (float) bounds.getMinY());                
                break;
        }
        
        updateGradient();
    }
    
    @Override
    public void applyAtributes(Graphics2D g2d) {
        super.applyAtributes(g2d);
        
        // RELLENO
        if (fill) {
            g2d.setColor(backgroundColor);
//            g2d.fill(s);
        }

        if (gradient) {
            g2d.setPaint(gradientColor);
//            g2d.fill(s);
        }
    }
}
