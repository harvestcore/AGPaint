package sm.agm.graficos;

import java.awt.image.BufferedImage;
import sm.image.BufferedImageOpAdapter;

/**
 * Operador para aplicar un filtro de color sepia a una imagen.
 * @author Angel
 */
public class AGSepiaOP extends BufferedImageOpAdapter {
    
    /**
     * Constructor por defecto.
     */
    public AGSepiaOP () {
        super();
    }

    /**
     * Filtra la imagen aplicando una tonalildad sepia a todos los pixeles. Para
     * ello los itera, toma las componentes RGB de cada uno y las multiplica por
     * valores predefinidos que convierten el color de cada pixel en su homónimo
     * de color sepia.
     * @param src Imagen de entrada a filtrar
     * @param dest Imagen de salida
     * @return Imagen de salida filtrada
     */
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        // Se comprueban las imagenes
        if (src == null) throw new NullPointerException("src image is null");
        if (dest == null) throw new NullPointerException("dest image is null");
        
        // Se recorren todos los píxeles
        for (int x = 0; x < src.getWidth(); ++x) {
            for (int y = 0; y < src.getHeight(); ++y) {
                // Obtengo las componentes del pixel desplazando los bits de la
                // posición de memoria donde se encuetran. Funciona del mismo
                // modo que obteniendo el pixel y almacenándolo en un vector
                // estático del tipo int[]
                int sa = (src.getRGB(x, y) >> 24) & 0xff;
                int sr = (src.getRGB(x, y) >> 16) & 0xff;
                int sg = (src.getRGB(x, y) >> 8) & 0xff;
                int sb = src.getRGB(x, y) & 0xff;
                
                // Multiplico por los valores predefinidos y acoto para que el
                // valor de una componente no supere 255.
                int r = (int) Math.min(255, 0.393 * sr + 0.769 * sg + 0.189 * sb);
                int g = (int) Math.min(255, 0.349 * sr + 0.686 * sg + 0.168 * sb);
                int b = (int) Math.min(255, 0.272 * sr + 0.534 * sg + 0.131 * sb);
                
                // Desplazo los bits de forma inversa para formar el pixel.
                int sp = (sa << 24) | (r << 16) | (g << 8) | b;
                
                // Aplico el valor del pixel en la imagen de salida.
                dest.setRGB(x, y, sp);
            }
        }

        // Devuelvo imagen filtrada.
        return dest;
    }
}
