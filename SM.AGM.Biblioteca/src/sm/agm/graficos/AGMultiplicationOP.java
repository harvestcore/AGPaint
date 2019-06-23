package sm.agm.graficos;

import java.awt.image.BufferedImage;
import sm.image.BinaryOp;

/**
 * Operador para "multiplicar" imágenes.
 * @author Angel
 */
public class AGMultiplicationOP extends BinaryOp {

    /**
     * Constructor por defecto.
     * @param img Imagen a filtrar.
     */
    public AGMultiplicationOP(BufferedImage img) {
        super(img);
    }

    @Override
    public int binaryOp(int i, int i1) {
        // Toma los valores de entrada, los multiplica y los acota a (0, 255)
        return sm.image.ImageTools.clampRange(i * i1, 0, 255);
    }
    
}
