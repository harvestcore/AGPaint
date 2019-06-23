package sm.agm.graficos;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import javafx.util.Pair;
import sm.image.BufferedImageOpAdapter;

/**
 * Operador para aplicar un filtro de "ajedrez" a una imagen.
 * Según un valor dado se divide la imagen en cuadrantes (valor^2) y aleatoriamente
 * se intercambian unos por otros.
 * @author Angel
 */
public class AGChessOP extends BufferedImageOpAdapter {

    /**
     * Número de lados/cuadrantes en los que se divide la imagen
     */
    private int sides = 5;
    
    /**
     * Constructor por defecto.
     * @param sides Número de lados/cuadrantes en los que se divide la imagen
     */
    public AGChessOP(int sides) {
        super();
        this.sides = sides;
    }
        
    /**
     * Obtiene los píxeles de un cuadrante dado.
     * @param p Par de enteros con el cuadrante a obtener. El formato es (0,0)...(sides-1, sides-1)
     * @param img Imagen de la que extraer el cuadrante
     * @return Matriz de pixeles con los datos del cuadrante
     */
    public ArrayList<ArrayList<int[]>> getCuadrante(Pair<Integer, Integer> p, BufferedImage img) {
        // Creo matriz de salida
        ArrayList<ArrayList<int[]>> matrix = new ArrayList<>();
        WritableRaster raster = img.getRaster();
        
        // Obtengo el número de pixeles horizontales y verticales en los que dividir la imagen. Redondeo al alza
        int portionWidth = (int) Math.ceil(raster.getWidth() / sides);
        int portionHeight = (int) Math.ceil(raster.getHeight() / sides);
          
        // Itero los píxeles de cada cuadrante
        for (int x = portionWidth * p.getKey(); x < portionWidth * (p.getKey() + 1); ++x) {
            // Fila de la matriz
            ArrayList<int[]> r = new ArrayList<>();
            for (int y = portionHeight * p.getValue(); y < portionHeight * (p.getValue() + 1); ++y) {
                // Obtengo datos del pixel
                int[] pixelComp = new int[raster.getNumBands()];
                pixelComp = raster.getPixel(x, y, pixelComp);
                
                // Agrego pixel a fila
                r.add(pixelComp);
            }
            
            // Agrego fila a la matriz
            matrix.add(r);
        }
        
        return matrix;
    }
    
    /**
     * Copia una imagen. Para ello extraigo su ColorModel, su Raster y su valor AlphaPremultiplied.
     * @param src Imagen a copiar.
     * @return Imagen copiada.
     */
    public BufferedImage copyImage(BufferedImage src) {
        ColorModel cm = src.getColorModel();
        WritableRaster wr = src.copyData(null);
        boolean alphapre = src.isAlphaPremultiplied();
        return new BufferedImage(cm, wr, alphapre, null);
    }
    
    /**
     * Filtra la imagen dandole el aspecto de "ajedrez", desordenando por cuadrantes la imagen
     * @param src Imagen de entrada a filtrar
     * @param dest Imagen de salida
     * @return Imagen filtrada
     */
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        // Compruebo imágenes de entrada
        if (src == null) throw new NullPointerException("src image is null");
        if (dest == null) throw new NullPointerException("dest image is null");
                                
        // Obtengo raster de ambas imágenes
        WritableRaster srcRaster = src.getRaster();
        WritableRaster destRaster = dest.getRaster();
        
        // Copio la imagen de entrada
        BufferedImage copy = copyImage(src);
        
        // Ancho y alto de la imagen de entrada
        int width = srcRaster.getWidth();
        int height = srcRaster.getHeight();
        
        // Número de cuadrantes totales que contendrá la imagen
        int square = sides * sides;
        
        // Vector de pair que contiene el orden aleatorio de cuadrantes
        ArrayList<Pair<Integer, Integer>> rset = new ArrayList<>();
        
        // Relleno el vector anterior con "square" items
        while (rset.size() != square) {
            // Pair aleatorio
            int r1 = (int) (Math.random() * sides);
            int r2 = (int) (Math.random() * sides);
            Pair<Integer, Integer> p = new Pair(r1, r2);
            
            // Si el vector no contiene esa combinación la agrego
            if (!rset.contains(p))
                rset.add(p);
        }
                
        // Obtengo el número de pixeles horizontales y verticales en los que dividir la imagen. Redondeo al alza
        int portionWidth = (int) Math.ceil(width / sides);
        int portionHeight = (int) Math.ceil(height / sides);
        
        // Índices usados para iterar la imagen y los cuadrantes
        int kx = 0, ky = 0;
        int x = 0, y = 0;
        
        // Mientras queden combinaciones en el vector anterior
        while (!rset.isEmpty()) {
            // Par aleatorio
            int r1 = (int) (Math.random() * sides);
            int r2 = (int) (Math.random() * sides);
            Pair<Integer, Integer> p = new Pair(r1, r2);           
            
            // Si ese par está en el vector
            if (rset.contains(p)) {
                // Calculo donde empieza y donde acaba el bucle que rellena los pixeles
                // de la imagen de salida
                int fromX = (kx % sides) * portionWidth;
                int fromY = (ky % sides) * portionHeight;
                int toX = fromX + portionWidth;
                int toY = fromY + portionHeight;

                // Obtengo cuadrante con pixeles
                ArrayList<ArrayList<int[]>> cuadrante = getCuadrante(p, copy);
                
                // Itero los pixeles del cuadrante "final" y le pongo los valores de
                // los píxeles de "cuadrante"
                for (int m = fromX; m < toX; ++m) {
                    for (int n = fromY; n < toY; ++n) {
                        destRaster.setPixel(m, n, cuadrante.get(x % portionWidth).get(y % portionHeight));
                        ++y;
                    }
                    ++x;
                }
                
                // Control de índices
                x = y = 0;
                ky++;
                if (ky == sides) {
                    kx = (kx + 1) % sides;
                    ky = 0;
                }                    
                
                // Elimino el par del vector
                rset.remove(p);
            }
        }
        
        // Devuelvo imagen filtrada
        return dest;
    }
    
}
