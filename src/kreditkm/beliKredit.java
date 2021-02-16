/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kreditkm;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.DB;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author T430
 */
public class beliKredit extends javax.swing.JPanel {

    /**
     * Creates new form beliKredit
     */
    
    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd";
    public static String tanggalSekarang(){
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }
    
    public beliKredit() {
        initComponents();
        tglkredit.setText(tanggalSekarang());
        getAutoKodeKredit();
    }

    private void getAutoKodeKredit(){
        try{
            DB db = new DB("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
            db.createQuery("select max(kodekredit) from belikredit");

            if(db.getResult().next()) {
                String a = db.getResult().getString("max(kodekredit)");
                String i = a.substring(5);
                String e = String.valueOf(Integer.parseInt(i)+1);
                //kodecash.setText("CASH-"+e);
                
                String q =String.valueOf(e);
                    if(q.length()==1){
                        kodekredit.setText("KRD-000"+e);
                    }else if(q.length()==2){
                        kodekredit.setText("KRD-00"+e);
                    }else if(q.length()==3){
                        kodekredit.setText("KRD-0"+e);
                    }else if(q.length()==4){
                        kodekredit.setText("KRD-"+e);
                        }
            }else{
                kodekredit.setText("KRD-0001");
            }
            db.closeResult();
            db.closeConnection();
        }catch(Exception x) {
            System.out.println(x);
        }
    }
    
    private void datacust() {
        String[] judul={"Kode Pelanggan","Nama Pelanggan"}; 
            DefaultTableModel tabModel = new DefaultTableModel (null,judul);
            try {
            DB db = new DB ("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
            db.createQuery("Select * from pelanggan order by kodecust");
             while (db.getResult().next()) {
                String kdecust = db.getResult().getString("kodecust");
                String nmpelanggan = db.getResult().getString("nama");

                Object[] data = {kdecust,nmpelanggan};
                tabModel.addRow(data);
                 }
            db.closeResult();
            db.closeConnection();
            tbpopupcust.setModel(tabModel);
            tbpopupcust.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD, 12));
                tbpopupcust.getTableHeader().setOpaque(false);
                tbpopupcust.getTableHeader().setBackground(new Color(251,253,138));
                tbpopupcust.setRowHeight(25);
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                for(int a=0;a<tbpopupcust.getColumnCount();a++){
                    tbpopupcust.getColumnModel().getColumn(a).setCellRenderer(centerRenderer);
                }
            scrollpopupcust.getViewport().add(tbpopupcust);
            scrollpopupcust.setViewportView(tbpopupcust);


        }
        catch (Exception ex) {
          System.out.println(ex); 
        }
    }
    
    private void datamotor() {
        String[] judul={"Kode Motor","Nama Motor","Warna"}; 
            DefaultTableModel tabModel = new DefaultTableModel (null,judul);
            try {
            DB db = new DB ("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
            db.createQuery("Select * from motor order by kodemotor");
             while (db.getResult().next()) {
                String kdemotor = db.getResult().getString("kodemotor");
                String nmmtr = db.getResult().getString("namamotor");
                String wrna = db.getResult().getString("warna");

                Object[] data = {kdemotor,nmmtr,wrna};
                tabModel.addRow(data);
                 }
            db.closeResult();
            db.closeConnection();
            tbpopupmtr.setModel(tabModel);
            tbpopupmtr.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD, 12));
                tbpopupmtr.getTableHeader().setOpaque(false);
                tbpopupmtr.getTableHeader().setBackground(new Color(251,253,138));
                tbpopupmtr.setRowHeight(25);
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                for(int a=0;a<tbpopupmtr.getColumnCount();a++){
                    tbpopupmtr.getColumnModel().getColumn(a).setCellRenderer(centerRenderer);
                }
            scrollpopupmtr.getViewport().add(tbpopupmtr);
            scrollpopupmtr.setViewportView(tbpopupmtr);


        }
        catch (Exception ex) {
          System.out.println(ex); 
        }
    }
    
    private void getDp(){
        int v_harga=Integer.parseInt(harga.getText());
        switch(cbcicil.getSelectedIndex()){
            case 1:
                bulankr.setText("6");
                int v_dp6=v_harga*35/100;
                dp.setText(String.valueOf(v_dp6));
                break;
            case 2:
                bulankr.setText("12");
                int v_dp12=v_harga*30/100;
                dp.setText(String.valueOf(v_dp12));
                break;
            case 3:
                bulankr.setText("18");
                int v_dp18=v_harga*25/100;
                dp.setText(String.valueOf(v_dp18));
                break;
            case 4:
                bulankr.setText("24");
                int v_dp24=v_harga*20/100;
                dp.setText(String.valueOf(v_dp24));
                break;
            case 5:
                bulankr.setText("30");
                int v_dp30=v_harga*15/100;
                dp.setText(String.valueOf(v_dp30));
                break;
            case 6:
                bulankr.setText("36");
                int v_dp36=v_harga*10/100;
                dp.setText(String.valueOf(v_dp36));
                break;
        }
    }
    
    private void getBunga(){
        int v_dp=Integer.parseInt(dp.getText());
        int v_harga=Integer.parseInt(harga.getText());
        int v_bulan=Integer.parseInt(bulankr.getText());
        int plafon=v_harga-v_dp;
        int v_bunga=(plafon*2/100)*v_bulan;
        bunga.setText(String.valueOf(v_bunga));
    }
    
    private void getHitungAngsuran(){
        int v_dp=Integer.parseInt(dp.getText());
        int v_harga=Integer.parseInt(harga.getText());
        int v_bulan=Integer.parseInt(bulankr.getText());
        int v_bunga=Integer.parseInt(bunga.getText());
        int plafon=v_harga-v_dp;
        int totalhutang=plafon+v_bunga;
        int v_angsuran=totalhutang/v_bulan;
        angsuran.setText(String.valueOf(v_angsuran));
    }
    
    private void getHitungSisa(){
        int v_dp=Integer.parseInt(dp.getText());
        int v_harga=Integer.parseInt(harga.getText());
        int v_bulan=Integer.parseInt(bulankr.getText());
        int v_bunga=Integer.parseInt(bunga.getText());
        int v_angsuran=Integer.parseInt(angsuran.getText());
        int plafon=v_harga-v_dp;
        int totalhutang=plafon+v_bunga;
        int v_sisa=totalhutang-v_angsuran;
        sisa.setText(String.valueOf(v_sisa));
    }
    
    private void telahBayar(){
        tlhbayar.setText(String.valueOf(Integer.parseInt(dp.getText())+Integer.parseInt(angsuran.getText())));
    }
    
    private void simpankredit() {
    String tanggal = tanggalSekarang().substring(8, 10);
    String bulan = tanggalSekarang().substring(5, 7);
    String tahun = tanggalSekarang().substring(0, 4);
        
        String blnTempo = String.valueOf(Integer.parseInt(bulan) + 1);
        String tglTempo = tahun+"-"+blnTempo+"-"+tanggal;
    try {
    DB db = new DB("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
    db.createUpdate("insert into belikredit(kodekredit,tanggalkredit,kodecust,kodemotor,uangmuka,bunga,lamacicilan,angsuran,angsuranke,telahbayar,sisa,jatuhtempo,keterangan)"
            +"values('"+kodekredit.getText()+"','"+tglkredit.getText()+"','"+kodecust.getText()+"','"+kodemtr.getText()+"','"+dp.getText()+"','"+bunga.getText()+"','"+bulankr.getText()+"','"+angsuran.getText()+"','1','"+tlhbayar.getText()+"','"+sisa.getText()+"','"+tglTempo+"','"+ket.getText()+"')");
    String pesan = ("Data Pembelian Kredit " +kodekredit.getText()+ " Telah Tersimpan");
    JOptionPane.showMessageDialog(kGradientPanel1, pesan);
    
    
    db.closeResult();
    db.closeConnection();
    }
    catch (Exception se) {
        System.out.println(se);
    }
}
    private void print() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/kredit_km?user=root&password");
            HashMap hashMap = new HashMap();
            hashMap.put("kodekredit", kodekredit.getText());	
            JasperReport jreport = JasperCompileManager.compileReport("src/report/NotaBeliKredit.jrxml");
            JasperPrint jprint = JasperFillManager.fillReport(jreport, hashMap, koneksi);
            JasperViewer.viewReport(jprint, false);
            
            
        }catch(Exception se) {
            System.out.println(se);
        }
    }
    
    void refresh(){
        //tglkredit.setText("00/00/0000");
        kodecust.setText("CUST-");
        namapel.setText("____________________");
        kodemtr.setText("MTR-");
        cbcicil.setSelectedIndex(0);
        dp.setText("0");
        ket.setText("");
        namamtr.setText("____________________");
        warna.setText("____________________");
        harga.setText("0");
        bunga.setText("0");
        tlhbayar.setText("0");
        sisa.setText("0");
        bulankr.setText("0");
        angsuran.setText("0");
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupcust = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        cari = new javax.swing.JButton();
        refreshcust = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        close = new javax.swing.JButton();
        scrollpopupcust = new javax.swing.JScrollPane();
        tbpopupcust = new javax.swing.JTable();
        popupmtr = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        cari1 = new javax.swing.JButton();
        refreshmtr = new javax.swing.JButton();
        txtcari1 = new javax.swing.JTextField();
        close1 = new javax.swing.JButton();
        scrollpopupmtr = new javax.swing.JScrollPane();
        tbpopupmtr = new javax.swing.JTable();
        kGradientPanel2 = new model.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tglkredit = new javax.swing.JLabel();
        kGradientPanel3 = new model.KGradientPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        harga = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        angsuran = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        namamtr = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        warna = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        bunga = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        tlhbayar = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        sisa = new javax.swing.JLabel();
        bulankr = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        simpan = new model.KButton();
        batal = new model.KButton();
        kGradientPanel1 = new model.KGradientPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ket = new javax.swing.JTextArea();
        namapel = new javax.swing.JLabel();
        kodecust = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbcicil = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        kodekredit = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        bpopupcust = new model.KButton();
        bpopupmtr = new model.KButton();
        kodemtr = new javax.swing.JLabel();
        dp = new javax.swing.JLabel();

        popupcust.setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        refreshcust.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N
        refreshcust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshcustActionPerformed(evt);
            }
        });

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close.png"))); // NOI18N
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        tbpopupcust.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbpopupcust.setGridColor(new java.awt.Color(0, 153, 153));
        tbpopupcust.setSelectionBackground(new java.awt.Color(204, 204, 255));
        tbpopupcust.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpopupcustMouseClicked(evt);
            }
        });
        scrollpopupcust.setViewportView(tbpopupcust);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(refreshcust, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollpopupcust, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(close)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cari)
                    .addComponent(refreshcust))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollpopupcust, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout popupcustLayout = new javax.swing.GroupLayout(popupcust.getContentPane());
        popupcust.getContentPane().setLayout(popupcustLayout);
        popupcustLayout.setHorizontalGroup(
            popupcustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        popupcustLayout.setVerticalGroup(
            popupcustLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        popupmtr.setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        cari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        cari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cari1ActionPerformed(evt);
            }
        });

        refreshmtr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N
        refreshmtr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshmtrActionPerformed(evt);
            }
        });

        close1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close.png"))); // NOI18N
        close1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close1ActionPerformed(evt);
            }
        });

        tbpopupmtr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbpopupmtr.setGridColor(new java.awt.Color(0, 153, 153));
        tbpopupmtr.setSelectionBackground(new java.awt.Color(204, 204, 255));
        tbpopupmtr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpopupmtrMouseClicked(evt);
            }
        });
        scrollpopupmtr.setViewportView(tbpopupmtr);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txtcari1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cari1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(refreshmtr, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(close1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollpopupmtr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(close1)
                    .addComponent(txtcari1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cari1)
                    .addComponent(refreshmtr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollpopupmtr, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout popupmtrLayout = new javax.swing.GroupLayout(popupmtr.getContentPane());
        popupmtr.getContentPane().setLayout(popupmtrLayout);
        popupmtrLayout.setHorizontalGroup(
            popupmtrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        popupmtrLayout.setVerticalGroup(
            popupmtrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setBackground(new java.awt.Color(8, 105, 114));
        setMaximumSize(new java.awt.Dimension(770, 420));
        setMinimumSize(new java.awt.Dimension(770, 420));
        setPreferredSize(new java.awt.Dimension(770, 420));

        kGradientPanel2.setkBorderRadius(0);
        kGradientPanel2.setkEndColor(new java.awt.Color(1, 169, 180));
        kGradientPanel2.setkStartColor(new java.awt.Color(135, 223, 214));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("BELI KREDIT");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel8.setText("TANGGAL KREDIT");

        tglkredit.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        tglkredit.setText("00/00/0000");

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(26, 26, 26)
                .addComponent(tglkredit)
                .addGap(34, 34, 34))
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(tglkredit))
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kGradientPanel3.setkEndColor(new java.awt.Color(251, 253, 138));
        kGradientPanel3.setkStartColor(new java.awt.Color(135, 223, 214));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel17.setText("HARGA");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel18.setText("Rp.");

        harga.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        harga.setText("0");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel24.setText("TELAH BAYAR");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel25.setText("ANGSURAN");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel26.setText("Rp.");

        angsuran.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        angsuran.setText("0");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel12.setText("NAMA MOTOR");

        namamtr.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        namamtr.setText("____________________");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel13.setText("WARNA MOTOR");

        warna.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        warna.setText("____________________");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel20.setText("BUNGA");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel30.setText("Rp.");

        bunga.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        bunga.setText("0");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel33.setText("Rp.");

        tlhbayar.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        tlhbayar.setText("0");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel35.setText("SISA");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel36.setText("Rp.");

        sisa.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        sisa.setText("0");

        bulankr.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        bulankr.setText("0");

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel39.setText("BULAN");

        javax.swing.GroupLayout kGradientPanel3Layout = new javax.swing.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(41, 41, 41)
                        .addComponent(warna)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel17))
                                .addGap(48, 48, 48)
                                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                        .addComponent(namamtr)
                                        .addGap(0, 11, Short.MAX_VALUE))
                                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addGap(28, 28, 28)
                                        .addComponent(harga, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel35)
                                    .addComponent(jLabel24))
                                .addGap(55, 55, 55)
                                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel30)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel26)
                                        .addGap(28, 28, 28)
                                        .addComponent(angsuran, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel33)
                                        .addGap(28, 28, 28)
                                        .addComponent(tlhbayar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel36)
                                        .addGap(28, 28, 28)
                                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                                .addComponent(bulankr, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel39))
                                            .addComponent(sisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                        .addGap(59, 59, 59))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bunga, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))))
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(namamtr))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(warna))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(harga))
                .addGap(27, 27, 27)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel30)
                    .addComponent(bunga))
                .addGap(26, 26, 26)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(tlhbayar)
                    .addComponent(jLabel24))
                .addGap(27, 27, 27)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(sisa)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bulankr)
                    .addComponent(jLabel39))
                .addGap(11, 11, 11)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(angsuran)
                    .addComponent(jLabel25))
                .addGap(21, 21, 21))
        );

        simpan.setText("SIMPAN");
        simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanActionPerformed(evt);
            }
        });

        batal.setText("BATAL");
        batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalActionPerformed(evt);
            }
        });

        kGradientPanel1.setkEndColor(new java.awt.Color(251, 253, 138));
        kGradientPanel1.setkStartColor(new java.awt.Color(135, 223, 214));

        ket.setColumns(20);
        ket.setRows(5);
        jScrollPane1.setViewportView(ket);

        namapel.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        namapel.setText("____________________");

        kodecust.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        kodecust.setText("CUST-");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel28.setText("LAMA CICILAN");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel10.setText("NAMA PELANGGAN");

        cbcicil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih Lama Cicilan-", "6", "12", "18", "24", "30", "36" }));
        cbcicil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcicilActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel16.setText("KETERANGAN");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel29.setText("UANG MUKA");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel11.setText("KODE MOTOR");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel7.setText("KODE KREDIT");

        kodekredit.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        kodekredit.setText("KRD-");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel9.setText("KODE PELANGGAN");

        bpopupcust.setText("...");
        bpopupcust.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bpopupcust.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bpopupcust.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpopupcustActionPerformed(evt);
            }
        });

        bpopupmtr.setText("...");
        bpopupmtr.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bpopupmtr.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bpopupmtr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpopupmtrActionPerformed(evt);
            }
        });

        kodemtr.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        kodemtr.setText("MTR-");

        dp.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        dp.setText("0");

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(jLabel16))
                .addGap(54, 54, 54)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(kGradientPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(kodekredit)
                            .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(kGradientPanel1Layout.createSequentialGroup()
                                    .addComponent(kodecust)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bpopupcust, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, kGradientPanel1Layout.createSequentialGroup()
                                    .addComponent(kodemtr)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bpopupmtr, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(namapel, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addComponent(dp, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbcicil, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(kodekredit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(kodecust)
                    .addComponent(bpopupcust, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(namapel))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(kodemtr)
                    .addComponent(bpopupmtr, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(cbcicil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(dp))
                .addGap(36, 36, 36)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(kGradientPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(13, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(kGradientPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        String[] judul={"Kode Pelanggan","Nama Pelanggan"};
        DefaultTableModel tabModel = new DefaultTableModel (null,judul);
        try {
            DB db = new DB("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
            db.createQuery("select * from pelanggan where "
                + "nama like '%"+txtcari.getText()+"%'"
                + "OR kodecust like '%"+txtcari.getText()+"%'");
            while (db.getResult().next()) {
                String kodepelanggan = db.getResult().getString("kodecust");
                String namapelanggan = db.getResult().getString("nama");

                Object[] data = {kodepelanggan,namapelanggan};
                tabModel.addRow(data);
            }

            tbpopupcust.setModel(tabModel);
            scrollpopupcust.getViewport().add(tbpopupcust);
            scrollpopupcust.setViewportView(tbpopupcust);
            db.closeResult();
            db.closeConnection();
        }
        catch (Exception se) {
            System.out.println(se);
        }
    }//GEN-LAST:event_cariActionPerformed

    private void refreshcustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshcustActionPerformed
        datacust();
        txtcari.setText("");
    }//GEN-LAST:event_refreshcustActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        popupcust.dispose();
    }//GEN-LAST:event_closeActionPerformed

    private void tbpopupcustMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpopupcustMouseClicked
        Object ag = tbpopupcust.getModel().getValueAt(tbpopupcust.getSelectedRow(), 0);
        Object ah = tbpopupcust.getModel().getValueAt(tbpopupcust.getSelectedRow(), 1);
        kodecust.setText(ag.toString());
        namapel.setText(ah.toString());

        popupcust.dispose();
    }//GEN-LAST:event_tbpopupcustMouseClicked

    private void cari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cari1ActionPerformed
        String[] judul={"Kode Motor","Nama Motor","Warna"};
        DefaultTableModel tabModel = new DefaultTableModel (null,judul);
        try {
            DB db = new DB("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
            db.createQuery("select * from motor where "
                + "namamotor like '%"+txtcari1.getText()+"%'"
                + "OR warna like '%"+txtcari1.getText()+"%'"
                + "OR kodemotor like '%"+txtcari1.getText()+"%'");
            while (db.getResult().next()) {
                String kodemtrr = db.getResult().getString("kodemotor");
                String namamtrr = db.getResult().getString("namamotor");
                String wrna = db.getResult().getString("warna");

                Object[] data = {kodemtrr,namamtrr,wrna};
                tabModel.addRow(data);
            }

            tbpopupmtr.setModel(tabModel);
            scrollpopupmtr.getViewport().add(tbpopupmtr);
            scrollpopupmtr.setViewportView(tbpopupmtr);
            db.closeResult();
            db.closeConnection();
        }
        catch (Exception se) {
            System.out.println(se);
        }
    }//GEN-LAST:event_cari1ActionPerformed

    private void refreshmtrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshmtrActionPerformed
        datamotor();
        txtcari1.setText("");
    }//GEN-LAST:event_refreshmtrActionPerformed

    private void close1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close1ActionPerformed
        popupmtr.dispose();
    }//GEN-LAST:event_close1ActionPerformed

    private void tbpopupmtrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpopupmtrMouseClicked
        Object ag = tbpopupmtr.getModel().getValueAt(tbpopupmtr.getSelectedRow(), 0);
        Object ah = tbpopupmtr.getModel().getValueAt(tbpopupmtr.getSelectedRow(), 1);
        Object aj = tbpopupmtr.getModel().getValueAt(tbpopupmtr.getSelectedRow(), 2);
        kodemtr.setText(ag.toString());
        namamtr.setText(ah.toString());
        warna.setText(aj.toString());

        try{
            DB db = new DB("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
            db.createQuery("select harga from motor where kodemotor ='"+kodemtr.getText()+"'");
            while (db.getResult().next()) {
                String a = db.getResult().getString("harga");

                harga.setText(a);
            }
        }catch(Exception se){
            System.out.println(se);
        }

        popupmtr.dispose();
    }//GEN-LAST:event_tbpopupmtrMouseClicked

    private void bpopupcustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpopupcustActionPerformed
        popupcust.setVisible(true);
        popupcust.setLocation(500, 200);
        popupcust.setSize(390, 197);
        datacust();
    }//GEN-LAST:event_bpopupcustActionPerformed

    private void bpopupmtrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpopupmtrActionPerformed
        popupmtr.setVisible(true);
        popupmtr.setLocation(500, 200);
        popupmtr.setSize(390, 197);
        datamotor();
    }//GEN-LAST:event_bpopupmtrActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        simpankredit();
        print();
        getAutoKodeKredit();
        refresh();
    }//GEN-LAST:event_simpanActionPerformed

    private void cbcicilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcicilActionPerformed
        getDp();
        getBunga();
        getHitungAngsuran();  
        getHitungSisa();
        telahBayar();
    }//GEN-LAST:event_cbcicilActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        refresh();
    }//GEN-LAST:event_batalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel angsuran;
    private model.KButton batal;
    private model.KButton bpopupcust;
    private model.KButton bpopupmtr;
    private javax.swing.JLabel bulankr;
    private javax.swing.JLabel bunga;
    private javax.swing.JButton cari;
    private javax.swing.JButton cari1;
    private javax.swing.JComboBox<String> cbcicil;
    private javax.swing.JButton close;
    private javax.swing.JButton close1;
    private javax.swing.JLabel dp;
    private javax.swing.JLabel harga;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private model.KGradientPanel kGradientPanel1;
    private model.KGradientPanel kGradientPanel2;
    private model.KGradientPanel kGradientPanel3;
    private javax.swing.JTextArea ket;
    private javax.swing.JLabel kodecust;
    private javax.swing.JLabel kodekredit;
    private javax.swing.JLabel kodemtr;
    private javax.swing.JLabel namamtr;
    private javax.swing.JLabel namapel;
    private javax.swing.JFrame popupcust;
    private javax.swing.JFrame popupmtr;
    private javax.swing.JButton refreshcust;
    private javax.swing.JButton refreshmtr;
    private javax.swing.JScrollPane scrollpopupcust;
    private javax.swing.JScrollPane scrollpopupmtr;
    private model.KButton simpan;
    private javax.swing.JLabel sisa;
    private javax.swing.JTable tbpopupcust;
    private javax.swing.JTable tbpopupmtr;
    private javax.swing.JLabel tglkredit;
    private javax.swing.JLabel tlhbayar;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtcari1;
    private javax.swing.JLabel warna;
    // End of variables declaration//GEN-END:variables
}
