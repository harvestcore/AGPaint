package sm.agm.iu;

/**
 * Clase CanvasSize
 * Una objeto de esta clase representa un diálogo en el que se puede especificar
 * el nuevo tamaño que se desea para el lienzo.
 * @author Angel
 */
public class CanvasSize extends javax.swing.JDialog {
    
    /**
     * Ancho del lienzo con valor por defecto 600
     */
    private int width = 600;
    
    /**
     * Alto del lienzo con valor por defecto 600
     */
    private int height = 600;

    /**
     * Creates new form CanvasSize
     * @param parent Frame padre
     * @param modal Si se quiere bloquear el resto de ventanas
     */
    public CanvasSize(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setTitle("Nuevo tamaño de lienzo");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        imagePanel = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        inputWidthPanel = new javax.swing.JPanel();
        widthLabel = new javax.swing.JLabel();
        widthSpinner = new javax.swing.JSpinner();
        inputHeightPanel = new javax.swing.JPanel();
        heightLabel = new javax.swing.JLabel();
        heightSpinner = new javax.swing.JSpinner();
        okPanel = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        titleLabel.setText("Selecciona el nuevo tamaño de lienzo");
        titlePanel.add(titleLabel);

        getContentPane().add(titlePanel, java.awt.BorderLayout.NORTH);

        imagePanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/enlarge.png"))); // NOI18N
        imagePanel.add(image);

        getContentPane().add(imagePanel, java.awt.BorderLayout.CENTER);

        inputWidthPanel.setLayout(new javax.swing.BoxLayout(inputWidthPanel, javax.swing.BoxLayout.LINE_AXIS));

        widthLabel.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        widthLabel.setText("Ancho  ");
        inputWidthPanel.add(widthLabel);

        widthSpinner.setModel(new javax.swing.SpinnerNumberModel(500, 100, null, 1));
        widthSpinner.setMaximumSize(new java.awt.Dimension(75, 30));
        widthSpinner.setMinimumSize(new java.awt.Dimension(75, 30));
        widthSpinner.setPreferredSize(new java.awt.Dimension(75, 30));
        inputWidthPanel.add(widthSpinner);

        getContentPane().add(inputWidthPanel, java.awt.BorderLayout.WEST);

        inputHeightPanel.setLayout(new javax.swing.BoxLayout(inputHeightPanel, javax.swing.BoxLayout.LINE_AXIS));

        heightLabel.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        heightLabel.setText("Alto  ");
        inputHeightPanel.add(heightLabel);

        heightSpinner.setModel(new javax.swing.SpinnerNumberModel(400, 100, null, 1));
        heightSpinner.setMaximumSize(new java.awt.Dimension(75, 30));
        heightSpinner.setMinimumSize(new java.awt.Dimension(75, 30));
        heightSpinner.setPreferredSize(new java.awt.Dimension(75, 30));
        inputHeightPanel.add(heightSpinner);

        getContentPane().add(inputHeightPanel, java.awt.BorderLayout.EAST);

        okButton.setText("Aceptar");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        okPanel.add(okButton);

        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        okPanel.add(cancelButton);

        getContentPane().add(okPanel, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        // Cierra el diálogo
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // Obtiene valores de los spinners
        width = (int) widthSpinner.getValue();
        height = (int) heightSpinner.getValue();
        
        // Cierra el diálogo
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed
    
    /**
     * Devuelve el ancho del lienzo.
     * @return Ancho del lienzo.
     */
    public int getCanvasWidth() { return width; }

    /**
     * Devuelve el alto del lienzo.
     * @return Alto del lienzo.
     */
    public int getCanvasHeight() { return height; }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel heightLabel;
    private javax.swing.JSpinner heightSpinner;
    private javax.swing.JLabel image;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JPanel inputHeightPanel;
    private javax.swing.JPanel inputWidthPanel;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel okPanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel titlePanel;
    private javax.swing.JLabel widthLabel;
    private javax.swing.JSpinner widthSpinner;
    // End of variables declaration//GEN-END:variables
}
