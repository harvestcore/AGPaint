package sm.agm.graficos;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

/**
 * Clase 
 * 
 * Esta clase amplía los atributos de su clase padre Atributes. Clase usada
 * principalmente para dar propiedades al punto, línea y trazado libre.
 * 
 * Los atributos adicionales son:
 * Tipo de trazado.
 * Tamaño de trazado.
 * 
 * @author Angel
 */
public class SimpleShapeAtributes extends Atributes {
    
    /**
     * Objeto Stroke usado para crear el tipo de trazado.
     */
    private Stroke stroke;
    
    /**
     * Tamaño del trazado.
     */
    private int strokeSize;
    
    /**
     * Objeto StrokeType con el tipo de trazado.
     */
    private StrokeType strokeType;
    
    /**
     * Constructor por defecto con parámetros básicos.
     */
    public SimpleShapeAtributes() {
        strokeType = StrokeType.LINE;
        stroke = new BasicStroke(strokeSize);
        strokeSize = 1;
    }
    
    /**
     * Constructor de copia. Usado cuando se preseleccionan propiedades en la interfaz de AGPaint.
     * @param s Atributos a copiar.
     */
    public SimpleShapeAtributes(SimpleShapeAtributes s) {
        super(s);
        this.stroke = s.getStroke();
        this.strokeSize = s.getStrokeSize();
        this.strokeType = s.getStrokeType();
    }

    /**
     * Devuelve el objeto Stroke con el trazado.
     * @return Objeto Stroke con el trazado.
     */
    public Stroke getStroke() { return stroke; }

    /**
     * Devuelve el tamaño del trazado.
     * @return Tamaño del trazado.
     */
    public int getStrokeSize() { return strokeSize; }

    /**
     * Devuelve el tipo de trazado.
     * @return Tipo de trazado.
     */
    public StrokeType getStrokeType() { return strokeType; }

    /**
     * Asigna el tipo de trazado. Además asigna el tamaño de trazado según valor actual y crea el trazado..
     * @param strokeType Tipo de trazado.
     */
    public void setStroke(StrokeType strokeType) { this.strokeType = strokeType; setStrokeSize(strokeSize); }
    
    /**
     * Asigna el tamaño del trazado y lo crea.
     * @param strokeSize Tamaño del trazado.
     */
    public void setStrokeSize(int strokeSize) {
        this.strokeSize = strokeSize;
        
        if (strokeType == StrokeType.LINE)
            stroke = new BasicStroke(strokeSize);
        else {
            float pattern[] = {4.0f, 6.0f};
            stroke = new BasicStroke(strokeSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 10.0f, pattern, 0.0f);
        }
            
    }
    
    @Override
    public void applyAtributes(Graphics2D g2d) {
        super.applyAtributes(g2d);
        
        // STROKE
        g2d.setStroke(stroke);
    }
}
