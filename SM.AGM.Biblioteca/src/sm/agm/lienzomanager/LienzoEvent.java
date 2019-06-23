package sm.agm.lienzomanager;

import java.util.EventObject;
import sm.agm.graficos.AGShape;

/**
 * Clase LienzoEvent
 * Un objeto de esta clase representa un evento lanzado en el lienzo. Hereda de
 * la clase EventObject y se compone de una forma de la clase AGShape.
 * @author Angel
 */
public class LienzoEvent extends EventObject {
    /**
     * Forma almacenada en el evento
     */
    private AGShape shape;
    
    /**
     * Constructor por defecto.
     * @param o Necesario por la superclase
     * @param shape Forma a pasar al evento
     */
    public LienzoEvent(Object o, AGShape shape) {
        super(o);
        this.shape = shape;
    }
    
    /**
     * Devuelve la forma.
     * @return La forma almacenada.
     */
    public AGShape getShape() { return shape; }
}
