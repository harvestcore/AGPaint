package sm.agm.graficos;

import java.awt.image.BufferedImage;
import sm.image.BinaryOp;

/**
 * Operador para "dividir" imágenes.
 * @author Angel
 */
public class AGDivisionOP extends BinaryOp {

    /**
     * Constructor por defecto.
     * @param img Imagen a filtrar.
     */
    public AGDivisionOP(BufferedImage img) {
        super(img);
    }

    @Override
    public int binaryOp(int i, int i1) {
        // Calcula el cociente de los valores de entrada. Si el divisor es 0 se
        // toma 0 como valor final.
        int value = (i1 != 0) ? (int) (i / i1) : 0;
        
        // Se devuelve el valor acotado a (0, 255)
        return sm.image.ImageTools.clampRange(value, 0, 255);
    }
    
}