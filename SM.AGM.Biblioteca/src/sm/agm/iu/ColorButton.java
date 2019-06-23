package sm.agm.iu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 * Clase ColorButton
 * Una objeto de esta clase representa un panel con un botón de color que se puede
 * añadir a un combobox.
 * @author Angel
 */
public class ColorButton extends JPanel implements ListCellRenderer<Color>{
    
    /**
     * Botón que aparecerá como elemento en el listado
     */
    private JButton button = new JButton();

    /**
     * Constructor por defecto.
     */
    public ColorButton() {
        // Layout del panel
        setLayout(new BorderLayout(0, 0));
        
        // Tamaño del panel
        setPreferredSize(new Dimension(23, 23));
        
        // Tamaño del botón
        button.setPreferredSize(new Dimension(20, 20));
        
        // Texto del botón
        button.setText("");
        
        // Agrego al panel el botón en el centro del layout
        add(button, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Color> list, Color value, int index, boolean isSelected, boolean cellHasFocus) {       
        button.setBackground(value);
        button.setToolTipText(value.toString());
        return this;
    }
}
