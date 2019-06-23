/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.agm.graficos;

import java.awt.image.BufferedImage;
import sm.image.BufferedImageOpAdapter;

/**
 * Operador para aplicar un filtro "arcoiris" a una imagen.
 * Cada tipo de filtro (6) comprueba qué color es el que predomina en cada pixel,
 * definiendo un valor usado como divisor a la hora de formar el nuevo valor de
 * cada componente. Por otro lado se combina (suma) cada componente con la siguiente,
 * es decir: Rojo con Verde, Verde con Azul y Azul con Rojo. La imagen resultante
 * tiene una tonalidad pastel del color mas oscuro (debido a la división que se hace)
 * y también un granulado de los colores que son mas vivos..
 * @author Angel
 */
public class AGRainbowOP extends BufferedImageOpAdapter {
    
    /**
     * Tipo de filtrado, hay 6:
     * RGB
     * RBG
     * GRB
     * GBR
     * BRG
     * BGR
     */
    private RainbowType type = RainbowType.RGB;
    
    /**
     * Constructor por defecto. Se le indica el tipo de filtro a usar.
     * @param type Tipo de filtro
     */
    public AGRainbowOP (RainbowType type) {
        super();
        this.type = type;
    }
    
    /**
     * Devuelve el valor usado como factor de división. Según el tipo de filtro
     * se comprueba qué componente es la que predomina, y se le asigna un valor
     * aleatorio dentro de un rango:
     * Rango 1: 1, 2, 3
     * Rango 2: 4, 5, 6
     * Rango 3: 7, 8, 9
     * @param r Componente rojo del pixel.
     * @param g Componente verde del pixel.
     * @param b Componente azul del pixel.
     * @return Valor obtenido según aleatoriedad y tipo de filtrado
     */
    public int getValue(int r, int g, int b) {
        int value = 1;
        
        switch (type) {
            case RGB:
                if (r > g && r > b) value = 1 + (int) (Math.random() * 4); // 1, 2, 3
                if (g > r && g > b) value = 4 + (int) (Math.random() * 3); // 4, 5, 6
                if (b > r && b > g) value = 7 + (int) (Math.random() * 3); // 7, 8, 9
                break;
                
            case GRB:
                if (g > r && g > b) value = 1 + (int) (Math.random() * 4); // 1, 2, 3
                if (r > g && r > b) value = 4 + (int) (Math.random() * 3); // 4, 5, 6
                if (b > r && b > g) value = 7 + (int) (Math.random() * 3); // 7, 8, 9
                break;
                
            case BGR:
                if (b > r && b > g) value = 1 + (int) (Math.random() * 4); // 1, 2, 3
                if (g > r && g > b) value = 4 + (int) (Math.random() * 3); // 4, 5, 6
                if (r > g && r > b) value = 7 + (int) (Math.random() * 3); // 7, 8, 9
                break;
                
            case RBG:
                if (r > g && r > b) value = 1 + (int) (Math.random() * 4); // 1, 2, 3
                if (b > r && b > g) value = 4 + (int) (Math.random() * 3); // 4, 5, 6
                if (g > r && g > b) value = 7 + (int) (Math.random() * 3); // 7, 8, 9
                break;
                
            case BRG:
                if (b > r && b > g) value = 1 + (int) (Math.random() * 4); // 1, 2, 3
                if (r > g && r > b) value = 4 + (int) (Math.random() * 3); // 4, 5, 6
                if (g > r && g > b) value = 7 + (int) (Math.random() * 3); // 7, 8, 9
                break;
                
            case GBR:
                if (g > r && g > b) value = 1 + (int) (Math.random() * 4); // 1, 2, 3
                if (b > r && b > g) value = 4 + (int) (Math.random() * 3); // 4, 5, 6
                if (r > g && r > b) value = 7 + (int) (Math.random() * 3); // 7, 8, 9
                break;
        }
        
        return value;
    }

    /**
     * Filtra la imagen según el tipo de filtro seleccionado.
     * @param src Imagen de entrada a filtrar.
     * @param dest Imagen de salida.
     * @return Imagen de salida filtrada.
     */
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        // Compruebo las imágenes de entrada
        if (src == null) throw new NullPointerException("src image is null");
        if (dest == null) throw new NullPointerException("dest image is null");
        
        // Itero los píxeles de la imagen src
        for (int x = 0; x < src.getWidth(); ++x) {
            for (int y = 0; y < src.getHeight(); ++y) {
                // Obtengo componentes de cada pixel desplazando bits
                int sa = (src.getRGB(x, y) >> 24) & 0xff;
                int sr = (src.getRGB(x, y) >> 16) & 0xff;
                int sg = (src.getRGB(x, y) >> 8) & 0xff;
                int sb = src.getRGB(x, y) & 0xff;
                
                // Obtengo valor (divisor) según componentes del pixel y según
                // tipo de filtro
                int value = getValue(sr, sg, sb);
                
                // Calculo nuevas componentes del pixel
                int r = (int) Math.min(255, sr / value + sg / value);
                int g = (int) Math.min(255, sg / value + sb / value);
                int b = (int) Math.min(255, sb / value + sr / value);
                
                // Creo pixel, desplazando de forma inversa
                int sp = (sa << 24) | (r << 16) | (g << 8) | b;
                
                // Aplico el nuevo pixel en la imagen de salida
                dest.setRGB(x, y, sp);
            }
        }

        // Devuelvo imagen filtrada
        return dest;
    }
}
