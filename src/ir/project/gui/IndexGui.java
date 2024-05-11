package ir.project.gui;


import ir.project.index.BiWordIndex;
import ir.project.index.IncidenceMatrix;
import ir.project.index.InvertedIndex;
import ir.project.processing.Tokenization;
import ir.project.index.PositionalIndex;
import ir.project.processing.Lemmatization;
import ir.project.processing.Normalization;
import ir.project.processing.Stemming;
import ir.project.processing.StopWord;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import javax.swing.JCheckBox;

public class IndexGui extends javax.swing.JFrame {

    Scanner in = new Scanner(System.in);

    public IndexGui() {
        initComponents();
    }

    @SuppressWarnings("checked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(466, 408));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Technology Search Engine");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Term Document Incidence Matric", "Inverted Index", "Bi-word Index", "Positional Index" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setText("Index Type  ");

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jCheckBox1.setText("Tokenization");
        jCheckBox1.setName("preprocessingoptions"); // NOI18N
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jCheckBox2.setText("Normalization");
        jCheckBox2.setName("preprocessingoptions"); // NOI18N
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jCheckBox3.setText("Stemming");
        jCheckBox3.setName("preprocessingoptions"); // NOI18N
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jCheckBox4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jCheckBox4.setText("Lemmatization");
        jCheckBox4.setName("preprocessingoptions"); // NOI18N
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        jCheckBox5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jCheckBox5.setText("Stop Words");
        jCheckBox5.setName("preprocessingoptions"); // NOI18N
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Pre Processing ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox5)
                            .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jCheckBox4)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(111, 111, 111)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4)
                    .addComponent(jCheckBox1)
                    .addComponent(jLabel2))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox2))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        System.out.println("Tokenization");
        JCheckBox checkBox = (JCheckBox) evt.getSource();
        if (checkBox.isSelected()) {
            Tokenization gui = new Tokenization();
            gui.tokenizeFiles(Tokenization.getFileList());
            gui.setVisible(true);
        }

    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        System.out.println("Normalization");
        JCheckBox checkBox = (JCheckBox) evt.getSource();
        if (checkBox.isSelected()) {
            Normalization gui = new Normalization();
            gui.normalizeFiles(Normalization.getFileList());
            gui.setVisible(true);
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        System.out.println("Stop-Words");
        JCheckBox checkBox = (JCheckBox) evt.getSource();
        if (checkBox.isSelected()) {
            Stemming gui = new Stemming();
            gui.stemFiles(Stemming.getFileList());
            gui.setVisible(true);
        }
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        System.out.println("Lemmatization");
        JCheckBox checkBox = (JCheckBox) evt.getSource();
        if (checkBox.isSelected()) {
            Lemmatization gui = new Lemmatization();
            gui.setVisible(true);
        }
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        System.out.println("Stop-Words");
        JCheckBox checkBox = (JCheckBox) evt.getSource();
        if (checkBox.isSelected()) {
            StopWord gui = new StopWord();
            gui.countWordFrequency(StopWord.getFileList());
            int threshold = 10;
            gui.displayStopWords(threshold);
            gui.setVisible(true);
        }
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

        String selectedOption = (String) jComboBox1.getSelectedItem();
        if (selectedOption.equals("Bi-word Index")) {
            List<File> fileList = BiWordIndex.getFileList(); // Get the list of files to be indexed
            BiWordIndex biWordIndex = new BiWordIndex();
            biWordIndex.createAndShowGUI();
            biWordIndex.generateBiWordIndex(fileList);

            // Perform any additional operations with the bi-word index as needed
        } else if (selectedOption.equals("Positional Index")) {
            List<File> fileList = PositionalIndex.getFileList(); // Get the list of files to be indexed
            PositionalIndex positionalIndex = new PositionalIndex();
            positionalIndex.createAndShowGUI();
            positionalIndex.generatePositionalIndex(fileList);

            // Perform any additional operations with the bi-word index as needed
        } else if (selectedOption.equals("Term Document Incidence Matric")) {
            IncidenceMatrix incidenceMatrix = new IncidenceMatrix();
            incidenceMatrix.createAndShowGUI();
            incidenceMatrix.generateIncidenceMatrix(IncidenceMatrix.getFileList());

        } else if (selectedOption.equals("Inverted Index")) {

            InvertedIndex gui = new InvertedIndex();
            List<File> files = InvertedIndex.getFileList();
            gui.buildIndex(files);
            gui.displayIndex();
            gui.setVisible(true);
        }

    }//GEN-LAST:event_jComboBox1ActionPerformed

    public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }
    } catch (ClassNotFoundException ex) {
        java.util.logging.Logger.getLogger(IndexGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(IndexGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(IndexGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(IndexGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new IndexGui().setVisible(true);
        }
    });
}

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

}
