package sm.agm.lienzomanager;

import java.util.EventListener;

/**
 * Interfaz LienzoListener.
 * Esta interfaz es implementada por aquellos adapters que quieran hacer uso de
 * los eventos relacionados con el lienzo (LienzoEvent)
 * @author Angel
 */
public interface LienzoListener extends EventListener {

    /**
     * Se agrega una figura al lienzo.
     * @param evt Evento con la figura agregada.
     */
    public void shapeAdded(LienzoEvent evt);

    /**
     * Se selecciona una figura del lienzo. Maneja todo lo relacionado con la
     * actualización de los atributos en pantalla.
     * @param evt Evento con la figura seleccionada.
     */
    public void shapeSelected(LienzoEvent evt);
}
