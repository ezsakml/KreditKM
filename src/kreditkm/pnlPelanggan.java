/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kreditkm;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.DB;
/**
 *
 * @author T430
 */
public class pnlPelanggan extends javax.swing.JPanel {
     

    /**
     * Creates new form pnlPelanggan
     */
    public pnlPelanggan() {
        initComponents();
        datapelanggan();
        
    }
    
    private void datapelanggan() {
        String[] judul={"Kode Pelanggan","Nama Pelanggan","Alamat","Telepon","KTP","KK","Slip Gaji"}; 
        DefaultTableModel tabModel = new DefaultTableModel (null,judul);
            try {
                DB db = new DB ("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
                db.createQuery("Select * from pelanggan order by kodecust");
                while (db.getResult().next()) {
                    String kodepelanggan = db.getResult().getString("kodecust");
                    String namapelanggan = db.getResult().getString("nama");
                    String alamatt = db.getResult().getString("alamat");
                    String telepon = db.getResult().getString("telepon");
                    String ktp = db.getResult().getString("noktp");
                    String kkk = db.getResult().getString("kk");
                    String gaji = db.getResult().getString("slipgaji");
            
                    Object[] data = {kodepelanggan,namapelanggan,alamatt,telepon,ktp,kkk,gaji};
                    tabModel.addRow(data);
                    //getAutoKodePelanggan();
                }
                db.closeResult();
                db.closeConnection();
                tablepelanggan.setModel(tabModel);
                tablepelanggan.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD, 12));
                tablepelanggan.getTableHeader().setOpaque(false);
                tablepelanggan.getTableHeader().setBackground(new Color(251,253,138));
                tablepelanggan.setRowHeight(25);
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                for(int a=0;a<tablepelanggan.getColumnCount();a++){
                    tablepelanggan.getColumnModel().getColumn(a).setCellRenderer(centerRenderer);
                }
                
                scrolldatapelanggan.getViewport().add(tablepelanggan);
                scrolldatapelanggan.setViewportView(tablepelanggan);
                }
                catch (Exception ex) {
                     System.out.println(ex); 
                }
    }
    
    void refresh(){
        kodecust.setText("kodecust");
        nama.setText("");
        alamat.setText("");
        tlp.setText("");
        noktp.setText("");
        sgaji.setText("");
        kk.setText("");
        txtcari.setText("");
    }

    void hapuspelanggan(){
        try {
            DB db = new DB("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
            db.createUpdate("Delete from pelanggan where kodecust = '"+kodecust.getText()+"'");
            String pesan = ("Data Pelanggan Telah Dihapus " +kodecust.getText()+ " Telah Dihapus");
            JOptionPane.showMessageDialog(kGradientPanel1, pesan);
            db.closeResult();
            db.closeConnection();
        }catch (Exception se){
            System.out.println(se);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kGradientPanel1 = new model.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        kodecust = new javax.swing.JLabel();
        scrolldatapelanggan = new javax.swing.JScrollPane();
        tablepelanggan = new javax.swing.JTable();
        edit = new model.KButton();
        hapus = new model.KButton();
        refresh = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        cari = new javax.swing.JButton();
        kGradientPanel2 = new model.KGradientPanel();
        sgaji = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tlp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        kk = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        alamat = new javax.swing.JTextArea();
        nama = new javax.swing.JTextField();
        noktp = new javax.swing.JTextField();

        setBackground(new java.awt.Color(8, 105, 114));
        setMaximumSize(new java.awt.Dimension(770, 420));
        setMinimumSize(new java.awt.Dimension(770, 420));
        setPreferredSize(new java.awt.Dimension(770, 420));

        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setkEndColor(new java.awt.Color(1, 169, 180));
        kGradientPanel1.setkStartColor(new java.awt.Color(135, 223, 214));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("DATA PELANGGAN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel2.setText("KODE PELANGGAN");

        kodecust.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        kodecust.setText("kodecust");

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 439, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kodecust)
                .addGap(19, 19, 19))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addGap(0, 13, Short.MAX_VALUE)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(kodecust)
                    .addComponent(jLabel1))
                .addGap(9, 9, 9))
        );

        tablepelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablepelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablepelangganMouseClicked(evt);
            }
        });
        scrolldatapelanggan.setViewportView(tablepelanggan);

        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        kGradientPanel2.setkBorderRadius(0);
        kGradientPanel2.setkEndColor(new java.awt.Color(251, 253, 138));
        kGradientPanel2.setkStartColor(new java.awt.Color(135, 223, 214));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("NAMA");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("ALAMAT");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("NO TELEPON");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("LEMBAR");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("NO KTP");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("KK");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("SLIP GAJI");

        alamat.setColumns(20);
        alamat.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        alamat.setRows(5);
        alamat.setOpaque(false);
        jScrollPane1.setViewportView(alamat);

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(59, 59, 59)
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(noktp, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kk, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tlp, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                        .addComponent(sgaji, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)))
                .addGap(84, 84, 84))
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                        .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(kGradientPanel2Layout.createSequentialGroup()
                                    .addGap(5, 5, 5)
                                    .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(sgaji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel8))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tlp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5))
                                    .addGap(18, 18, 18)
                                    .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(kk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7)))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel6)
                    .addComponent(noktp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrolldatapelanggan))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cari)
                        .addComponent(refresh)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addComponent(scrolldatapelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tablepelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablepelangganMouseClicked
        Object a = tablepelanggan.getModel().getValueAt(tablepelanggan.getSelectedRow(), 0);
        Object b =  tablepelanggan.getModel().getValueAt(tablepelanggan.getSelectedRow(), 1);
        Object c = tablepelanggan.getModel().getValueAt(tablepelanggan.getSelectedRow(), 2);
        Object d = tablepelanggan.getModel().getValueAt(tablepelanggan.getSelectedRow(), 3);
        Object e = tablepelanggan.getModel().getValueAt(tablepelanggan.getSelectedRow(), 4);
        Object f = tablepelanggan.getModel().getValueAt(tablepelanggan.getSelectedRow(), 5);
        Object g = tablepelanggan.getModel().getValueAt(tablepelanggan.getSelectedRow(), 6);
        
        kodecust.setText(a.toString());
        nama.setText(b.toString());
        alamat.setText(c.toString());
        tlp.setText(d.toString());
        noktp.setText(e.toString());
        kk.setText(f.toString());
        sgaji.setText(g.toString());
        
        datapelanggan();
        txtcari.setText("");
    }//GEN-LAST:event_tablepelangganMouseClicked

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        hapuspelanggan();
        refresh();
        datapelanggan();
    }//GEN-LAST:event_hapusActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        try {
                DB db = new DB("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
                db.createUpdate("update pelanggan set "
                        + "nama='"+nama.getText()+"',"
                        + "alamat='"+alamat.getText()+"',"
                        + "telepon='"+tlp.getText()+"',"
                        + "noktp='"+noktp.getText()+"',"
                        + "kk='"+kk.getText()+"',"
                        + "slipgaji='"+sgaji.getText()+"' where kodecust='"+kodecust.getText()+"'");
                String pesan = ("Data Pelanggan " +kodecust.getText()+ "Berhasil diedit");
                JOptionPane.showMessageDialog(kGradientPanel1, pesan);
                //getAutoKodePelanggan();
                datapelanggan();
                refresh();
                db.closeResult();
                db.closeConnection();
            }
            catch (Exception se) {
                System.out.println(se);
            }
    }//GEN-LAST:event_editActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        String[] judul={"Kode Pelanggan","Nama Pelanggan","Alamat","Telepon","KTP","KK","Slip Gaji"}; 
        DefaultTableModel tabModel = new DefaultTableModel (null,judul);
        try {
                DB db = new DB("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
                db.createQuery("select * from pelanggan where "
                        + "nama like '%"+txtcari.getText()+"%'"
                        + "OR alamat like '%"+txtcari.getText()+"%'"
                        + "OR telepon like '%"+txtcari.getText()+"%'"
                        + "OR noktp like '%"+txtcari.getText()+"%'"
                        + "OR kk like'%"+txtcari.getText()+"%'"
                        + "OR slipgaji like '%"+txtcari.getText()+"%'"
                        + "OR kodecust like '%"+txtcari.getText()+"%'");
                while (db.getResult().next()) {
                    String kodepelanggan = db.getResult().getString("kodecust");
                    String namapelanggan = db.getResult().getString("nama");
                    String alamat_p = db.getResult().getString("alamat");
                    String telepon = db.getResult().getString("telepon");
                    String ktp = db.getResult().getString("noktp");
                    String kk_p = db.getResult().getString("kk");
                    String gaji = db.getResult().getString("slipgaji");
            
                    Object[] data = {kodepelanggan,namapelanggan,alamat_p,telepon,ktp,kk_p,gaji};
                    tabModel.addRow(data);
                }
                
                tablepelanggan.setModel(tabModel);
                scrolldatapelanggan.getViewport().add(tablepelanggan);
                scrolldatapelanggan.setViewportView(tablepelanggan);
                //datapelanggan();
                //refresh();
                db.closeResult();
                db.closeConnection();
            }
            catch (Exception se) {
                System.out.println(se);
            } 
    }//GEN-LAST:event_cariActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        refresh();
        datapelanggan();
    }//GEN-LAST:event_refreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea alamat;
    private javax.swing.JButton cari;
    private model.KButton edit;
    private model.KButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private model.KGradientPanel kGradientPanel1;
    private model.KGradientPanel kGradientPanel2;
    private javax.swing.JTextField kk;
    private javax.swing.JLabel kodecust;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField noktp;
    private javax.swing.JButton refresh;
    private javax.swing.JScrollPane scrolldatapelanggan;
    private javax.swing.JTextField sgaji;
    private javax.swing.JTable tablepelanggan;
    private javax.swing.JTextField tlp;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
