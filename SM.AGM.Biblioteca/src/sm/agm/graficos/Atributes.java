package sm.agm.graficos;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

/**
 * Clase Atributes
 * 
 * Esta clase abstracta contiene los atributos básicos que tienen todas las figuras
 * que se pueden dibujar en AGPaint. De esta cuelga una serie de clases que aumentan
 * su funcionalidad y propiedades.
 * 
 * Los atributos básicos que contiene esta clase son:
 * Color de trazado.
 * Transparencia de la figura.
 * Procesado gráfico (Antialiasing)
 * 
 * Además incluye los límites (bounds) de la figura, para a partir de ellos mostrar
 * su bounding box cuando se encuentre seleccionada.
 * 
 * @author Angel
 */
public abstract class Atributes {
    
    /**
     * Color de trazado
     */
    private Color strokeColor;
    
    /**
     * AlphaComposite, usado para dar transparencia a la figura
     */
    private AlphaComposite alphaComposite;
    
    /**
     * Valor de transparencia. Valor entre 0f y 1f.
     */
    private float alpha;
    
    /**
     * Booleano que controla si está activado el antialiasing.
     */
    private boolean smooth;
    
    /**
     * Booleano que comtrola si la figura está seleccionada o no.
     */
    private boolean selected;
    
    /**
     * Límites de la forma, usado para dibujar el boundingbox de la figura.
     */
    private Rectangle2D bounds;

    /**
     * Constructor por defecto. Aplica unos parámetros básicos a los atributos.
     */
    public Atributes() {
        strokeColor = Color.BLACK;
        alpha = 1.0f;
        alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
        smooth = false;
        selected = false;
        bounds = new Rectangle(0, 0, 0, 0);
    }
    
    /**
     * Constructor de copia. Usado cuando se preseleccionan propiedades en la interfaz de AGPaint.
     * @param a Atributos a copiar.
     */
    public Atributes(Atributes a) {
        this.strokeColor = a.getStrokeColor();
        this.alphaComposite = a.getAlphaComposite();
        this.alpha = (float) a.getAlphaValue();
        this.smooth = a.smooth();
        this.selected = a.selected();
        this.bounds = a.getBounds();
    }
    
    /**
     * Asigna los límites de la figura.
     * @param bounds Objeto Rectangle con los límites de la figura.
     */
    public void setBounds(Rectangle2D bounds) { this.bounds = bounds; }    

    /**
     * Devuelve los límites de la figura.
     * @return Límites de la figura
     */
    public Rectangle2D getBounds() { return bounds; }

    /**
     * Devuelve el color de trazado.
     * @return Color de trazado.
     */
    public Color getStrokeColor() { return strokeColor; }

    /**
     * Devuelve el objeto AlphaComposite usado para generar la transparencia.
     * @return Objeto AlphaComposite usado para generar la transparencia.
     */
    public AlphaComposite getAlphaComposite() { return alphaComposite; }

    /**
     * Devuelve si está activado el antialiasing o no.
     * @return Si está activado el antialiasing o no.
     */
    public boolean smooth() { return smooth; }

    /**
     * Devuelve si la figura está seleccionada o no.
     * @return Si la figura está seleccionada o no.
     */
    public boolean selected() { return selected; }

    /**
     * Devuelve el valor de la transparencia.
     * @return El valor de la transparencia.
     */
    public int getAlphaValue() { return (int) alpha * 100; }
    
    /**
     * Asigna el color de trazado.
     * @param strokeColor Nuevo color de trazado.
     */
    public void setStrokeColor(Color strokeColor) { this.strokeColor = strokeColor; }

    /**
     * Activa o desactiva el antialiasing.
     * @param smooth Si se quiere activar el antialiasing o no.
     */
    public void setSmooth(boolean smooth) { this.smooth = smooth; }

    /**
     * Indica si se quiere seleccionar o no la figura.
     * @param selected Si se quiere seleccionar o no la figura.
     */
    public void setSelected(boolean selected) { this.selected = selected; }
    
    /**
     * Asigna un valor a la transparencia y crea el objeto AlphaComposite necesario para aplicar la misma.
     * @param alpha Valor de transparencia.
     */
    public void setAlpha(float alpha) {
        this.alpha = alpha;
        alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);        
    }
    
    /**
     * Aplica los atributos de esta clase.
     * @param g2d Objeto Graphics2D en el cual se asignan las propiedades de esta clase.
     */
    public void applyAtributes(Graphics2D g2d) {
        
        // Boundingbox
        if (selected) {
            g2d.setColor(Color.BLACK);
            float pattern[] = {2.0f};
            g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 10.0f, pattern, 0.0f));
            int value = 15;
            g2d.drawRect((int) bounds.getX() - value, (int) bounds.getBounds2D().getY() - value, (int) bounds.getBounds2D().getWidth() + (value*2), (int) bounds.getBounds2D().getHeight() + (value*2));
        }
        
        // Transparencia
        g2d.setComposite(alphaComposite);
        
        // Antialiasing
        if (smooth) {
            RenderingHints render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHints(render);
            render.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY); 
        } else {
            RenderingHints render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
            g2d.setRenderingHints(render);
            render.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        }
        
        // Color de trazado
        g2d.setColor(strokeColor);
    }
}