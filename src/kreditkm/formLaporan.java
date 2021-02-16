/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kreditkm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author T430
 */
public class formLaporan extends javax.swing.JFrame {

    /**
     * Creates new form formLaporan
     */
    public formLaporan() {
        initComponents();
        initUI();
        dateEnabled(false);
        setDefaultCloseOperation(formLaporan.DISPOSE_ON_CLOSE);
    }
    
    private void dateEnabled(boolean x){
        dcdari.setEnabled(x);
        dcst.setEnabled(x);
    }

    private void initUI(){ 
        getContentPane().setBackground(new Color(131,164,212));
        
        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = centerPoint.x - windowSize.width / 2;
        int dy = centerPoint.y - windowSize.height / 2;    
        setLocation(dx, dy);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel2 = new model.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        kGradientPanel1 = new model.KGradientPanel();
        cblaporan = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cetak = new model.KButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dcdari = new com.toedter.calendar.JDateChooser();
        dcst = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        kGradientPanel2.setkBorderRadius(0);
        kGradientPanel2.setkEndColor(new java.awt.Color(79, 138, 139));
        kGradientPanel2.setkStartColor(new java.awt.Color(79, 138, 139));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CETAK LAPORAN");

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(121, 121, 121))
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        kGradientPanel1.setBackground(new java.awt.Color(102, 204, 255));
        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setkEndColor(new java.awt.Color(79, 138, 139));
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 255));

        cblaporan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Data", "Motor", "Pelanggan", "Pembelian Cash", "Pembelian Kredit", "Pembayaran Cicilan" }));
        cblaporan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cblaporanItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("LAPORAN");

        cetak.setText("CETAK");
        cetak.setkBorderRadius(40);
        cetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("DARI TANGGAL");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("SAMPAI TANGGAL");

        dcdari.setDateFormatString("yyyy-MM-dd");

        dcst.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cblaporan, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dcdari, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dcst, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(44, 44, 44))
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cblaporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(jLabel3))
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dcdari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(dcst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(cetak, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakActionPerformed
        String id_laporan = cblaporan.getSelectedItem().toString();
        switch (id_laporan) {
            case "Pilih Data":
                JOptionPane.showMessageDialog(null, "Bukan Data Laporan.");
                break;
            case "Motor":
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/kredit_km?user=root&password");
                    HashMap hash = new HashMap();
                    hash.put("",null);
                    File file = new File("src/report/DataMotor.jrxml");
                    JasperDesign jasperDesign = JRXmlLoader.load(file);
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                    net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hash, koneksi);
                    JasperViewer.viewReport(jasperPrint,false);
                }catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error " + e);
                }
                this.hide();
                break;
            case "Pelanggan":
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/kredit_km?user=root&password");
                    HashMap hash = new HashMap();
                    hash.put("",null);
                    File file = new File("src/report/DataPelanggan.jrxml");
                    JasperDesign jasperDesign = JRXmlLoader.load(file);
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                    net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hash, koneksi);
                    JasperViewer.viewReport(jasperPrint, false);
                }catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error " + e);
                }
                this.hide();
                break;    
            default:
                SimpleDateFormat date;
                date = new SimpleDateFormat("yyyy-MM-dd");
                String d_dari = date.format(dcdari.getDate());
                String d_sampai = date.format(dcst.getDate());
                if(id_laporan.equals("Pembelian Cash")){
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/kredit_km?user=root&password");
                        HashMap hash = new HashMap();
                        hash.put("d_dari", d_dari);
                        hash.put("d_sampai", d_sampai);
                        File file = new File("src/report/DataCash.jrxml");
                        JasperDesign jasperDesign = JRXmlLoader.load(file);
                        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                        net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hash, koneksi);
                        JasperViewer.viewReport(jasperPrint, false);
                    }catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error " + e);
                    }
                }else if(id_laporan.equals("Pembelian Kredit")){
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/kredit_km?user=root&password");
                        HashMap hash = new HashMap();
                        hash.put("d_dari", d_dari);
                        hash.put("d_sampai", d_sampai);
                        File file = new File("src/report/DataKredit.jrxml");
                        JasperDesign jasperDesign = JRXmlLoader.load(file);
                        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                        net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hash, koneksi);
                        JasperViewer.viewReport(jasperPrint, false);
                    }catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error " + e);
                    }
                }else if(id_laporan.equals("Pembayaran Cicilan")){
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/kredit_km?user=root&password");
                        HashMap hash = new HashMap();
                        hash.put("d_dari", d_dari);
                        hash.put("d_sampai", d_sampai);
                        File file = new File("src/report/DataCicilan.jrxml");
                        JasperDesign jasperDesign = JRXmlLoader.load(file);
                        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                        net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, hash, koneksi);
                        JasperViewer.viewReport(jasperPrint, false);
                    }catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error " + e);
                    }
                }
                this.hide();
                break;
        }
    }//GEN-LAST:event_cetakActionPerformed

    private void cblaporanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cblaporanItemStateChanged
        String id_laporan = cblaporan.getSelectedItem().toString();
        if(id_laporan.equals("Pembelian Kredit") || id_laporan.equals("Pembayaran Cicilan") || id_laporan.equals("Pembelian Cash")){
            dateEnabled(true);
        }else{
            dateEnabled(false);
            dcdari.requestFocus();
        }
    }//GEN-LAST:event_cblaporanItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formLaporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formLaporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cblaporan;
    private model.KButton cetak;
    private com.toedter.calendar.JDateChooser dcdari;
    private com.toedter.calendar.JDateChooser dcst;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private model.KGradientPanel kGradientPanel1;
    private model.KGradientPanel kGradientPanel2;
    // End of variables declaration//GEN-END:variables
}
