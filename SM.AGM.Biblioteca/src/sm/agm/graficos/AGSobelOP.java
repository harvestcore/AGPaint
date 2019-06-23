package sm.agm.graficos;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import sm.image.BufferedImageOpAdapter;
import sm.image.BufferedImagePixelIterator;
import sm.image.KernelProducer;

/**
 * Operador para aplicar un filtro sobel a una imagen.
 * @author Angel
 */
public class AGSobelOP extends BufferedImageOpAdapter {

    /**
     * Constructor por defecto.
     */
    public AGSobelOP() {
        super();
    }
    
    /**
     * Filtra una imagen aplicándole un filtro sobel. Crea dos convoluciones
     * (haciendo uso de un Kernel para cada una de ellas) para calcular las
     * gradientes x e y. Tras eso recorre con iteradores la imagen y calcula el
     * la sumatoria de las muestras de cada pixel. Calcula la raiz de la suma
     * de los cuadrados de las sumatorias y lo acota a (0,255). Finalmente asigna
     * un color al nuevo pixel.
     * @param src Imagen de entrada a filtrar.
     * @param dest Imagen de salida.
     * @return Imagen de salida filtrada.
     */
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        // Compruebo imágenes de entrada
        if (src == null) throw new NullPointerException("src image is null");
        if (dest == null) throw new NullPointerException("dest image is null");
        
        // Creo kernels para las operaciones ConvolveOP, uso el tipo SOBEL.
        Kernel ksx = KernelProducer.createKernel(KernelProducer.TYPE_SOBELX_3x3);
        Kernel ksy = KernelProducer.createKernel(KernelProducer.TYPE_SOBELY_3x3);
        
        // Creop operadores ConvolveOP a partir de los kernels.
        ConvolveOp copX = new ConvolveOp(ksx);
        ConvolveOp copY = new ConvolveOp(ksy);
        
        // Creo gradientes usando los operadores anteriores.
        BufferedImage grX = copX.filter(src, null);
        BufferedImage grY = copY.filter(src, null);
        
        // Iteradores para iterar la imagen
        BufferedImagePixelIterator pixItX = new BufferedImagePixelIterator(grX);
        BufferedImagePixelIterator pixItY = new BufferedImagePixelIterator(grY);
        
        // Mientras haya elementos a iterar
        while (pixItX.hasNext() && pixItY.hasNext()) {
            // Obtengo valor de los píxeles x e y
            BufferedImagePixelIterator.PixelData px = pixItX.next();
            BufferedImagePixelIterator.PixelData py = pixItY.next();
            
            // Variables para almacenar valores temporalmente.
            int sobelx = 0, sobely = 0;
                        
            // Sumatorio de las muestras de cada pixel
            for (int i = 0; i < px.numSamples; ++i) {
                sobelx += px.sample[i];
                sobely += py.sample[i];
            }
            
            // Calculo magnitud. Hypot calcula la raiz de la suma de los cuadrados
            // de los argumentos, es decir: Math.sqrt(Math.pow(sobelx, 2) + Math.pow(sobely, 2))
            int magnitud = (int) Math.hypot(sobelx, sobely);
            
            // Acoto el valor, para que no sea negativo ni mayor a 255
            int value = sm.image.ImageTools.clampRange(magnitud, 0, 255);
            
            // Asigno el valor del pixel en la imagen de salida, usando el valor
            // de los píxeles y el color creado a partir del valor calculado.
            dest.setRGB(px.col, px.row, new Color(value, value, value).getRGB());
        }
        
        // Devuelvo imagen filtrada.
        return dest;
    }
    
}
