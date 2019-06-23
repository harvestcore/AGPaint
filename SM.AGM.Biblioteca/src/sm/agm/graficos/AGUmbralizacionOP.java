package sm.agm.graficos;

import java.awt.image.BufferedImage;
import sm.image.BufferedImageOpAdapter;

/**
 * Operador para umbralizar imágenes.
 * @author Angel
 */
public class AGUmbralizacionOP extends BufferedImageOpAdapter {
    /**
     * Umbral de filtrado
     */
    private int umbral;
    
    /**
     * Constructor por defecto.
     * @param umbral Umbral a usar en el proceso.
     */
    public AGUmbralizacionOP(int umbral) {
        this.umbral = umbral;
    }
    
    /**
     * Filtra la imagen umbralizando sus píxeles. Si el promedio de las tres
     * componentes de cada pixel es inferior al umbral, los valores RGB del mismo
     * se ponen a 0 (resultando el color negro), en caso contrario se ponen los
     * valores a 255 (resultando el color blanco).
     * @param src Imagen a filtrar.
     * @param dest Imagen de salida.
     * @return Imagen de salida ya filtrada.
     */
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        // Compruebo imágenes de entrada
        if (src == null) throw new NullPointerException("src image is null");
        if (dest == null) dest = createCompatibleDestImage(src, null);
        
        // Vector estático de enteros para almacenar las componentes de cada pixel
        int[] pComp = new int[src.getRaster().getNumBands()];
        
        // Recorro la imagen
        for (int x = 0; x < src.getWidth(); ++x) {
            for (int y = 0; y < src.getHeight(); ++y) {
                // Tomo cada pixel
                pComp = src.getRaster().getPixel(x, y, pComp);
                
                // Calculo el promedio
                int val = (int) (pComp[0] + pComp[1] + pComp[2]) / 3;
                
                // Si el promedio es igual o mayor al umbral -> Color blanco
                if (val >= umbral)
                    pComp[0] = pComp[1] = pComp[2] = 255;
                
                // En caso contrario -> Color negro
                else
                    pComp[0] = pComp[1] = pComp[2] = 0;
                
                // Asigno el pixel en la imagen de salida
                dest.getRaster().setPixel(x, y, pComp);
            }
        }
        return dest;
    }
}
