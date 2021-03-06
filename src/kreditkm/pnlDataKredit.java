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
public class pnlDataKredit extends javax.swing.JPanel {

    
    /**
     * Creates new form pnlPelanggan
     */
    public pnlDataKredit() {
        initComponents();
        datakredit();
    }

    private void datakredit() {
        String[] judul={"Kode Kredit","Tanggal Kredit","Kode Pelanggan","Kode Motor","Uang Muka","Bunga","Lama Cicilan","Jatuh Tempo"}; 
        DefaultTableModel tabModel = new DefaultTableModel (null,judul);
            try {
                DB db = new DB ("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
                db.createQuery("Select * from belikredit order by kodekredit");
                while (db.getResult().next()) {
                    String kodekredit_p = db.getResult().getString("kodekredit");
                    String tglkredit = db.getResult().getString("tanggalkredit");
                    String kodepelanggan = db.getResult().getString("kodecust");
                    String kodemotor = db.getResult().getString("kodemotor");
                    String uangmuka = db.getResult().getString("uangmuka");
                    String bunga = db.getResult().getString("bunga");
                    String lmcil = db.getResult().getString("lamacicilan");
                    String jtempo = db.getResult().getString("jatuhtempo");
            
                    Object[] data = {kodekredit_p,tglkredit,kodepelanggan,kodemotor,uangmuka,bunga,lmcil,jtempo};
                    tabModel.addRow(data);
                    //getAutoKodePelanggan();
                }
                db.closeResult();
                db.closeConnection();
        
                tablekredit.setModel(tabModel);
                tablekredit.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD, 12));
                tablekredit.getTableHeader().setOpaque(false);
                tablekredit.getTableHeader().setBackground(new Color(251,253,138));
                tablekredit.setRowHeight(25);
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                for(int a=0;a<tablekredit.getColumnCount();a++){
                    tablekredit.getColumnModel().getColumn(a).setCellRenderer(centerRenderer);
                }
                scrolldatakredit.getViewport().add(tablekredit);
                scrolldatakredit.setViewportView(tablekredit);
                }
                catch (Exception ex) {
                     System.out.println(ex); 
                }
    }
    
    void refresh(){
        kodekredit.setText("KRD-");
        tglbelikredit.setText("00/00/0000");
        kodecust.setText("CUST-");
        namaplg.setText("_____________");
        kodemotor.setText("MTR-");
        namamtr.setText("_____________");
        harga.setText("0000");
        dp.setText("0000");
        bunga.setText("0000");
        lamacicilan.setText("0000");
        angsuranke.setText("0000");
        angsuran.setText("0000");
        tlhbayar.setText("0000");
        sisa.setText("0000");
        jthtempo.setText("0000");
        keterangan.setText("_____________");
        txtcari.setText("");
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        kodecust = new javax.swing.JLabel();
        tglbelikredit = new javax.swing.JLabel();
        namapel = new javax.swing.JLabel();
        namaplg = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        kodemotor = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        namamtr = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        bunga = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        kodekredit = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        harga = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        dp = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lmcicilan = new javax.swing.JLabel();
        lamacicilan = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        angsuranke = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        angsuran = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        tlhbayar = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        sisa = new javax.swing.JLabel();
        jthtempo = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        keterangan = new javax.swing.JLabel();
        hapus = new model.KButton();
        txtcari = new javax.swing.JTextField();
        cari = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        kGradientPanel1 = new model.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        scrolldatakredit = new javax.swing.JScrollPane();
        tablekredit = new javax.swing.JTable();

        setBackground(new java.awt.Color(8, 105, 114));
        setMaximumSize(new java.awt.Dimension(770, 420));
        setMinimumSize(new java.awt.Dimension(770, 420));
        setPreferredSize(new java.awt.Dimension(770, 420));

        kGradientPanel2.setkBorderRadius(0);
        kGradientPanel2.setkEndColor(new java.awt.Color(251, 253, 138));
        kGradientPanel2.setkStartColor(new java.awt.Color(135, 223, 214));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel3.setText("TANGGAL BELI KREDIT");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel4.setText("KODE PELANGGAN");

        kodecust.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        kodecust.setText("CUST-");

        tglbelikredit.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        tglbelikredit.setText("00/00/0000");

        namapel.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        namapel.setText("NAMA PELANGGAN");

        namaplg.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        namaplg.setText("_____________");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel11.setText("KODE MOTOR");

        kodemotor.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        kodemotor.setText("MTR-");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel12.setText("NAMA MOTOR");

        namamtr.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        namamtr.setText("_____________");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel13.setText("BUNGA");

        bunga.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        bunga.setText("000");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel2.setText("KODE KREDIT");

        kodekredit.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        kodekredit.setText("KRD-");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel17.setText("HARGA");

        harga.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        harga.setText("000");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel18.setText("Rp.");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel19.setText("UANG MUKA");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel20.setText("Rp.");

        dp.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        dp.setText("000");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel21.setText("Rp.");

        lmcicilan.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lmcicilan.setText("LAMA CICILAN");

        lamacicilan.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lamacicilan.setText("000");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel23.setText("ANGSURAN KE-");

        angsuranke.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        angsuranke.setText("000");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel24.setText("ANGSURAN");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel25.setText("Rp.");

        angsuran.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        angsuran.setText("000");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel26.setText("TELAH BAYAR");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel27.setText("Rp.");

        tlhbayar.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        tlhbayar.setText("000");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel28.setText("SISA");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel29.setText("Rp.");

        sisa.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        sisa.setText("000");

        jthtempo.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jthtempo.setText("00/00/0000");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel30.setText("JATUH TEMPO");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel31.setText("KETERANGAN");

        keterangan.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        keterangan.setText("_____________");

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(436, 436, 436))
                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                        .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(87, 87, 87)
                                .addComponent(kodekredit))
                            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(namapel)
                                    .addComponent(jLabel11))
                                .addGap(36, 36, 36)
                                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(kodemotor)
                                    .addComponent(namaplg)
                                    .addComponent(kodecust)
                                    .addComponent(tglbelikredit)
                                    .addComponent(namamtr))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lmcicilan)
                                    .addComponent(jLabel23))
                                .addGap(59, 59, 59))
                            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel17))
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel18))
                                    .addComponent(jLabel20)
                                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel21)))
                                .addGap(26, 26, 26)))
                        .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(harga)
                            .addComponent(dp)
                            .addComponent(lamacicilan)
                            .addComponent(bunga)
                            .addComponent(angsuranke))
                        .addGap(70, 70, 70)))
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jLabel24)
                    .addComponent(jLabel26)
                    .addComponent(jLabel28)
                    .addComponent(jLabel31))
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(tlhbayar))
                            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel25))
                                .addGap(22, 22, 22)
                                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(angsuran, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(sisa, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addComponent(jthtempo))
                        .addGap(47, 47, 47))
                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(keterangan)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                        .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel28))
                            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel21))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lmcicilan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel23))
                            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(angsuran)
                                    .addComponent(jLabel25))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tlhbayar)
                                    .addComponent(jLabel27))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sisa)
                                    .addComponent(jLabel29))
                                .addGap(18, 18, 18)
                                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jthtempo)
                                    .addComponent(jLabel30)))
                            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                                .addComponent(harga)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dp)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bunga)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lamacicilan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(angsuranke)))
                        .addGap(18, 18, 18)
                        .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(keterangan)))
                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                        .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(kodekredit))
                        .addGap(17, 17, 17)
                        .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tglbelikredit))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(kodecust))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(namapel)
                            .addComponent(namaplg))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(kodemotor))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(namamtr))))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setkEndColor(new java.awt.Color(1, 169, 180));
        kGradientPanel1.setkStartColor(new java.awt.Color(135, 223, 214));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("DATA PEMBELIAN KREDIT");

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablekredit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablekredit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablekreditMouseClicked(evt);
            }
        });
        scrolldatakredit.setViewportView(tablekredit);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrolldatakredit, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cari)
                    .addComponent(refresh)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrolldatakredit, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 200, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(234, Short.MAX_VALUE)
                    .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(6, 6, 6)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        try {
            DB db = new DB("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
            db.createUpdate("Delete from belikredit where kodekredit = '"+kodekredit.getText()+"'");
            String pesan = ("Data Pembelian " +kodekredit.getText()+ " Telah Dihapus!");
            JOptionPane.showMessageDialog(kGradientPanel1, pesan);
            
            db.closeResult();
            db.closeConnection();
            refresh();
            datakredit();
        }catch (Exception se){
            System.out.println(se);
        }
    }//GEN-LAST:event_hapusActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        String[] judul={"Kode Kredit","Tanggal Kredit","Kode Pelanggan","Kode Motor","Uang Muka","Bunga","Lama Cicilan","Jatuh Tempo"};
        DefaultTableModel tabModel = new DefaultTableModel (null,judul);
        try {
            DB db = new DB("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
            db.createQuery("select * from belikredit where "
                + "kodekredit like '%"+txtcari.getText()+"%'"
                + "OR tanggalkredit like '%"+txtcari.getText()+"%'"
                + "OR kodemotor like '%"+txtcari.getText()+"%'"
                + "OR uangmuka like '%"+txtcari.getText()+"%'"
                + "OR bunga like'%"+txtcari.getText()+"%'"
                + "OR lamacicilan like '%"+txtcari.getText()+"%'"
                + "OR angsuran like '%"+txtcari.getText()+"%'"
                + "OR telahbayar like '%"+txtcari.getText()+"%'"
                + "OR sisa like '%"+txtcari.getText()+"%'"
                + "OR jatuhtempo like '%"+txtcari.getText()+"%'"
                + "OR keterangan like '%"+txtcari.getText()+"%'"
                + "OR kodecust like '%"+txtcari.getText()+"%'");
            while (db.getResult().next()) {
                String kodepelanggan = db.getResult().getString("kodecust");
                String kodemotorr = db.getResult().getString("kodemotor");
                String uangmuka = db.getResult().getString("uangmuka");
                String bungaa = db.getResult().getString("bunga");
                String lmcil = db.getResult().getString("lamacicilan");
                String angs = db.getResult().getString("angsuran");
                String tbayar = db.getResult().getString("telahbayar");
                String sis = db.getResult().getString("sisa");
                String tempo = db.getResult().getString("jatuhtempo");
                String kett = db.getResult().getString("keterangan");
                String tglk = db.getResult().getString("tanggalkredit");
                String kreditt = db.getResult().getString("kodekredit");

                Object[] data = {kreditt,tglk,kodepelanggan,kodemotorr,uangmuka,bungaa,lmcil,angs,tbayar,sis,tempo,kett};
                tabModel.addRow(data);
            }

            tablekredit.setModel(tabModel);
            scrolldatakredit.getViewport().add(tablekredit);
            scrolldatakredit.setViewportView(tablekredit);
            //datakredit();
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
        datakredit();
    }//GEN-LAST:event_refreshActionPerformed

    private void tablekreditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablekreditMouseClicked
        Object a = tablekredit.getModel().getValueAt(tablekredit.getSelectedRow(), 0);
        Object b = tablekredit.getModel().getValueAt(tablekredit.getSelectedRow(), 1);
        Object c = tablekredit.getModel().getValueAt(tablekredit.getSelectedRow(), 2);
        Object d = tablekredit.getModel().getValueAt(tablekredit.getSelectedRow(), 3);
        Object e = tablekredit.getModel().getValueAt(tablekredit.getSelectedRow(), 4);
        Object f = tablekredit.getModel().getValueAt(tablekredit.getSelectedRow(), 5);
        Object g = tablekredit.getModel().getValueAt(tablekredit.getSelectedRow(), 6);
        Object h = tablekredit.getModel().getValueAt(tablekredit.getSelectedRow(), 7);
        kodekredit.setText(a.toString());
        tglbelikredit.setText(b.toString());
        kodecust.setText(c.toString());
        kodemotor.setText(d.toString());
        dp.setText(e.toString());
        bunga.setText(f.toString());
        lamacicilan.setText(g.toString());
        jthtempo.setText(h.toString());
        
        try {
            DB db = new DB("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
            db.createQuery("select pelanggan.nama, motor.namamotor, motor.harga, belikredit.kodekredit, belikredit.angsuranke, belikredit.angsuran, belikredit.telahbayar, belikredit.sisa, belikredit.keterangan from motor,pelanggan,belikredit "
                    + "where belikredit.kodecust=pelanggan.kodecust "
                    + "AND belikredit.kodemotor=motor.kodemotor "
                    + "AND belikredit.kodekredit = '"+kodekredit.getText()+"'");
            while(db.getResult().next()){
                String i = db.getResult().getString("nama");
                String j = db.getResult().getString("namamotor");
                String k = db.getResult().getString("angsuranke");
                String l = db.getResult().getString("angsuran");
                String m = db.getResult().getString("telahbayar");
                String n = db.getResult().getString("sisa");
                String o = db.getResult().getString("keterangan");
                String p = db.getResult().getString("harga");
                
                namaplg.setText(i);
                namamtr.setText(j);
                angsuranke.setText(k);
                angsuran.setText(l);
                tlhbayar.setText(m);
                sisa.setText(n);
                keterangan.setText(o);
                harga.setText(p);
            }
            
            datakredit();
            txtcari.setText("");
            db.closeResult();
            db.closeConnection();
        } catch (Exception ex){
            System.out.println(ex);
        }
    }//GEN-LAST:event_tablekreditMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel angsuran;
    private javax.swing.JLabel angsuranke;
    private javax.swing.JLabel bunga;
    private javax.swing.JButton cari;
    private javax.swing.JLabel dp;
    private model.KButton hapus;
    private javax.swing.JLabel harga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jthtempo;
    private model.KGradientPanel kGradientPanel1;
    private model.KGradientPanel kGradientPanel2;
    private javax.swing.JLabel keterangan;
    private javax.swing.JLabel kodecust;
    private javax.swing.JLabel kodekredit;
    private javax.swing.JLabel kodemotor;
    private javax.swing.JLabel lamacicilan;
    private javax.swing.JLabel lmcicilan;
    private javax.swing.JLabel namamtr;
    private javax.swing.JLabel namapel;
    private javax.swing.JLabel namaplg;
    private javax.swing.JButton refresh;
    private javax.swing.JScrollPane scrolldatakredit;
    private javax.swing.JLabel sisa;
    private javax.swing.JTable tablekredit;
    private javax.swing.JLabel tglbelikredit;
    private javax.swing.JLabel tlhbayar;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
