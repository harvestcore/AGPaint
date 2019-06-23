package paintagm;

import java.awt.Rectangle;
import javax.swing.DefaultComboBoxModel;
import sm.agm.graficos.AGShape;
import sm.agm.graficos.ComplexShapeAtributes;
import sm.agm.graficos.SimpleShapeAtributes;
import sm.agm.graficos.StrokeType;
import sm.agm.lienzomanager.LienzoEvent;
import sm.agm.lienzomanager.LienzoListener;

/**
 * Clase LienzoAdapter
 * Implementa la interfaz LienzoListener y maneja los eventos que se producen en
 * el lienzo.
 * @author Angel
 */
public class LienzoAdapter implements LienzoListener {
    
    @Override
    public void shapeAdded(LienzoEvent evt) {
        // Si el evento no es nulo
        if (evt.getShape() != null) {
            // Agrego al combobox la figura
            ((DefaultComboBoxModel)PaintAGM.ventanaPrincipal.vShapeComboBox.getModel()).addElement((AGShape) evt.getShape());

            // Obtengo los límites de la figura
            Rectangle bounds = ((AGShape) evt.getShape()).getBounds();
            
            // Actualizo las áreas de texto con las coordendas de la figura
            PaintAGM.ventanaPrincipal.inputX.setText("" + (int) bounds.getX());
            PaintAGM.ventanaPrincipal.inputY.setText("" + (int) bounds.getY());
        }
    }
    
    @Override
    public void shapeSelected(LienzoEvent evt) {
        // Obtengo ventana interna actual
        PaintAGM.ventanaPrincipal.setCurrentWindow();
        
        // Se deselecciona la figura actual, si la hay
        PaintAGM.ventanaPrincipal.currentWindow.getLienzo().deselectCurrentShape();
        
        // Se selecciona la forma del evento
        PaintAGM.ventanaPrincipal.currentWindow.getLienzo().selectAGShape(evt.getShape());
        
        // Se selecciona en el combobox
        PaintAGM.ventanaPrincipal.vShapeComboBox.setSelectedItem(evt.getShape());
        
        // Se resetea el grupo de herramientas
        PaintAGM.ventanaPrincipal.grupoHerramientas.clearSelection();
        
        // Actualizo las formas de la ventana en el combobox
        PaintAGM.ventanaPrincipal.updatevShapeCombobox(PaintAGM.ventanaPrincipal.currentWindow);

        // Botón edición (mover con el cursor)
        PaintAGM.ventanaPrincipal.editButton.setSelected(PaintAGM.ventanaPrincipal.currentWindow.getLienzo().edit());

        // Botón de relleno/gradiente
        if (evt.getShape().getAtributes() instanceof ComplexShapeAtributes) {
            PaintAGM.ventanaPrincipal.fillButton.setSelected(((ComplexShapeAtributes) evt.getShape().getAtributes()).fill());
            PaintAGM.ventanaPrincipal.gradientButton.setSelected(((ComplexShapeAtributes) evt.getShape().getAtributes()).gradient());
            PaintAGM.ventanaPrincipal.gradientTypeComboBox.setSelectedItem(((ComplexShapeAtributes) evt.getShape().getAtributes()).getGradientType());
        }

        // Botón de antialiasing
        PaintAGM.ventanaPrincipal.smoothButton.setSelected(evt.getShape().getAtributes().smooth());

        // Slider transparencia
        PaintAGM.ventanaPrincipal.alphaSlider.setValue(evt.getShape().getAtributes().getAlphaValue());

        // Color trazado y fondo/gradiente
        PaintAGM.ventanaPrincipal.colorChooserButton.setBackground(evt.getShape().getAtributes().getStrokeColor());
        if (evt.getShape().getAtributes() instanceof ComplexShapeAtributes) {
            PaintAGM.ventanaPrincipal.backgroundColorChooserButton.setBackground(((ComplexShapeAtributes) evt.getShape().getAtributes()).getBackgroundColor());
        }

        // Trazado
        if (evt.getShape().getAtributes() instanceof SimpleShapeAtributes) {
            PaintAGM.ventanaPrincipal.strokeSpinner.setValue(((SimpleShapeAtributes) evt.getShape().getAtributes()).getStrokeSize());
            PaintAGM.ventanaPrincipal.strokeButton.setSelected(((SimpleShapeAtributes) evt.getShape().getAtributes()).getStrokeType() == StrokeType.DASHED);
            PaintAGM.ventanaPrincipal.updateStrokeButton();
        }

        // Valores de brillo y rotación
        PaintAGM.ventanaPrincipal.brightnessSlider.setValue(PaintAGM.ventanaPrincipal.currentWindow.getLienzo().getBrightnessValue());
        PaintAGM.ventanaPrincipal.rotationSlider.setValue(PaintAGM.ventanaPrincipal.currentWindow.getLienzo().getRotationValue());

        // Botones de las herramientas
        switch (PaintAGM.ventanaPrincipal.currentWindow.getLienzo().getTool()){
            case POINT: PaintAGM.ventanaPrincipal.pencilButton.setSelected(true); break;
            case LINE: PaintAGM.ventanaPrincipal.lineButton.setSelected(true); break;
            case RECTANGLE: PaintAGM.ventanaPrincipal.rectangleButton.setSelected(true); break;
            case ELLIPSE: PaintAGM.ventanaPrincipal.ellipseButton.setSelected(true); break;
            case ROUNDRECTANGLE: PaintAGM.ventanaPrincipal.roundRectangleButton.setSelected(true); break;
            case ARC: PaintAGM.ventanaPrincipal.arcButton.setSelected(true); break;
            case CUBICCURVE: PaintAGM.ventanaPrincipal.cubicCurveButton.setSelected(true); break;
            case QUADCURVE: PaintAGM.ventanaPrincipal.quadCurveButton.setSelected(true); break;
            case PATH: PaintAGM.ventanaPrincipal.pathButton.setSelected(true); break;
        }
    }
}
