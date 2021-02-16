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
import model.DB;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author T430
 */
public class beliCash extends javax.swing.JPanel {

    /**
     * Creates new form beliCash
     */
    public beliCash() {
        initComponents();
        getAutoKodeCash();
        tglcash.setText(tanggalSekarang());
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
    
    private void getAutoKodeCash(){
        try{
            DB db = new DB("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
            db.createQuery("select max(kodecash) from belicash");

            if(db.getResult().next()) {
                String a = db.getResult().getString("max(kodecash)");
                String i = a.substring(5);
                String e = String.valueOf(Integer.parseInt(i)+1);
                //kodecash.setText("CASH-"+e);
                
                String q =String.valueOf(e);
                    if(q.length()==1){
                        kodecash.setText("CASH-000"+e);
                    }else if(q.length()==2){
                        kodecash.setText("CASH-00"+e);
                    }else if(q.length()==3){
                        kodecash.setText("CASH-0"+e);
                    }else if(q.length()==4){
                        kodecash.setText("CASH-"+e);
                        }
            }else{
                kodecash.setText("CASH-0001");
            }
            db.closeResult();
            db.closeConnection();
        }catch(Exception x) {
            System.out.println(x);
        }
    }
    
    void jumlahbelicash(){
        try{
            String a = String.valueOf(Integer.parseInt(jmlbeli.getText().replaceAll(",",""))*Integer.parseInt(harga.getText()));
            htotal.setText((String)a);
        }catch(Exception se){
        }
    }
    
    void bayarcash(){
        try{
            String a = String.valueOf(Integer.parseInt(bayar.getText().replaceAll(",", ""))-Integer.parseInt(htotal.getText()));
            kembali.setText((String) a);
        }catch(Exception se){
            
        }
    }
    
    void simpancash() {
        try {
                DB db = new DB("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
                db.createUpdate("insert into belicash(kodecash,tanggalcash,kodecust,kodemotor,jumlah,total,bayar,kembali,keterangan)"
                        +"values('"+kodecash.getText()+"','"+tglcash.getText()+"','"+kodecust.getText()+"','"+kodemtr.getText()+"','"+jmlbeli.getText()+"','"+htotal.getText()+"','"+bayar.getText()+"','"+kembali.getText()+"','"+ket.getText()+"')");
                String pesan = ("Data Pembelian Cash " +kodecash.getText()+ " Telah Tersimpan");
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
            hashMap.put("kodecash", kodecash.getText());	
            JasperReport jreport = JasperCompileManager.compileReport("src/report/NotaBeliCash.jrxml");
            JasperPrint jprint = JasperFillManager.fillReport(jreport, hashMap, koneksi);
            JasperViewer.viewReport(jprint, false);
            
            
        }catch(Exception se) {
            System.out.println(se);
        }
    }
    
    void refresh(){
        //tglcash.setText("00/00/0000");
        //kodecash.setText("CASH-");
        kodecust.setText("CUST-");
        namapel.setText("_______________");
        kodemtr.setText("MTR-");
        namamtr.setText("_______________");
        warna.setText("_______________");
        ket.setText("");
        harga.setText("0000");
        jmlbeli.setText("");
        htotal.setText("0000");
        bayar.setText("");
        kembali.setText("0000");
        getAutoKodeCash();
        tglcash.setText(tanggalSekarang());
    }

    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd";
    public static String tanggalSekarang(){
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    
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
        refresh = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        close = new javax.swing.JButton();
        scrollpopupcust = new javax.swing.JScrollPane();
        tbpopupcust = new javax.swing.JTable();
        popupmtr = new javax.swing.JFrame();
        jPanel2 = new javax.swing.JPanel();
        cari1 = new javax.swing.JButton();
        refresh1 = new javax.swing.JButton();
        txtcari1 = new javax.swing.JTextField();
        close1 = new javax.swing.JButton();
        scrollpopupmtr = new javax.swing.JScrollPane();
        tbpopupmtr = new javax.swing.JTable();
        kGradientPanel1 = new model.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tglcash = new javax.swing.JLabel();
        kGradientPanel2 = new model.KGradientPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        harga = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jmlbeli = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        htotal = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        bayar = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        kembali = new javax.swing.JLabel();
        simpan = new model.KButton();
        batal = new model.KButton();
        kGradientPanel3 = new model.KGradientPanel();
        jLabel7 = new javax.swing.JLabel();
        kodecash = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        bpopupcust = new model.KButton();
        kodecust = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        bpopupmtr = new model.KButton();
        kodemtr = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        namapel = new javax.swing.JLabel();
        namamtr = new javax.swing.JLabel();
        warna = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ket = new javax.swing.JTextArea();

        popupcust.setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

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
                        .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(refresh))
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

        refresh1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N
        refresh1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refresh1ActionPerformed(evt);
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
                        .addComponent(refresh1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(refresh1))
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

        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setkEndColor(new java.awt.Color(1, 169, 180));
        kGradientPanel1.setkStartColor(new java.awt.Color(135, 223, 214));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("BELI CASH");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("TANGGAL CASH");

        tglcash.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tglcash.setText("00/00/0000");

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 444, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(38, 38, 38)
                .addComponent(tglcash)
                .addGap(32, 32, 32))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tglcash)
                        .addGroup(kGradientPanel1Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jLabel8)))
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kGradientPanel2.setkEndColor(new java.awt.Color(251, 253, 138));
        kGradientPanel2.setkStartColor(new java.awt.Color(135, 223, 214));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel17.setText("HARGA");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel18.setText("Rp.");

        harga.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        harga.setText("0000");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel20.setText("JUMLAH BELI");

        jmlbeli.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jmlbeliCaretUpdate(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel21.setText("HARGA TOTAL");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel22.setText("Rp.");

        htotal.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        htotal.setText("0000");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel24.setText("BAYAR");

        bayar.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                bayarCaretUpdate(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel25.setText("KEMBALI");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel26.setText("Rp.");

        kembali.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        kembali.setText("0000");

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25))
                .addGap(41, 41, 41)
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(kGradientPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel18)
                            .addGap(18, 18, 18)
                            .addComponent(harga))
                        .addComponent(jmlbeli, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(kGradientPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel26)
                            .addGap(18, 18, 18)
                            .addComponent(kembali))
                        .addComponent(bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(kGradientPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel22)
                            .addGap(18, 18, 18)
                            .addComponent(htotal))))
                .addGap(35, 35, 35))
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(harga))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jmlbeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(htotal))
                .addGap(23, 23, 23)
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(kembali))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        simpan.setText("SIMPAN DAN CETAK");
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

        kGradientPanel3.setkEndColor(new java.awt.Color(251, 253, 138));
        kGradientPanel3.setkStartColor(new java.awt.Color(135, 223, 214));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel7.setText("KODE CASH");

        kodecash.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        kodecash.setText("CASH-");

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

        kodecust.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        kodecust.setText("CUST-");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel10.setText("NAMA PELANGGAN");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel11.setText("KODE MOTOR");

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

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel12.setText("NAMA MOTOR");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel13.setText("WARNA MOTOR");

        namapel.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        namapel.setText("_______________");

        namamtr.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        namamtr.setText("_______________");

        warna.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        warna.setText("_______________");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel16.setText("KETERANGAN");

        ket.setColumns(20);
        ket.setRows(5);
        jScrollPane1.setViewportView(ket);

        javax.swing.GroupLayout kGradientPanel3Layout = new javax.swing.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel7))
                                .addGap(55, 55, 55)
                                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(namamtr)
                                    .addComponent(warna)
                                    .addComponent(kodecash)
                                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(namapel)
                                            .addComponent(kodemtr)
                                            .addComponent(kodecust))
                                        .addGap(18, 18, 18)
                                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(bpopupmtr, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bpopupcust, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(40, Short.MAX_VALUE))))
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(kodecash))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel9))
                            .addComponent(kodecust))
                        .addGap(18, 18, 18)
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(namapel))
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel11))
                            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(kodemtr))))
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addComponent(bpopupcust, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(bpopupmtr, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel12))
                    .addComponent(namamtr))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel3Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel13))
                    .addComponent(warna))
                .addGap(27, 27, 27)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(kGradientPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kGradientPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bpopupcustActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpopupcustActionPerformed
        popupcust.setVisible(true);
        popupcust.setLocation(500, 200);
        popupcust.setSize(390, 197);
        datacust();
    }//GEN-LAST:event_bpopupcustActionPerformed

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

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        datacust();
        txtcari.setText("");
    }//GEN-LAST:event_refreshActionPerformed

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

    private void refresh1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refresh1ActionPerformed
        datamotor();
        txtcari1.setText("");
    }//GEN-LAST:event_refresh1ActionPerformed

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

    private void bpopupmtrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpopupmtrActionPerformed
        popupmtr.setVisible(true);
        popupmtr.setLocation(500, 200);
        popupmtr.setSize(390, 197);
        datamotor();
    }//GEN-LAST:event_bpopupmtrActionPerformed

    private void jmlbeliCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jmlbeliCaretUpdate
        jumlahbelicash();
    }//GEN-LAST:event_jmlbeliCaretUpdate

    private void bayarCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_bayarCaretUpdate
        bayarcash();
    }//GEN-LAST:event_bayarCaretUpdate

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        simpancash();
        print();
        refresh();
//        getAutoKodeCash();
//        tglcash.setText(tanggalSekarang());
    }//GEN-LAST:event_simpanActionPerformed

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        refresh();
    }//GEN-LAST:event_batalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private model.KButton batal;
    private javax.swing.JTextField bayar;
    private model.KButton bpopupcust;
    private model.KButton bpopupmtr;
    private javax.swing.JButton cari;
    private javax.swing.JButton cari1;
    private javax.swing.JButton close;
    private javax.swing.JButton close1;
    private javax.swing.JLabel harga;
    private javax.swing.JLabel htotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jmlbeli;
    private model.KGradientPanel kGradientPanel1;
    private model.KGradientPanel kGradientPanel2;
    private model.KGradientPanel kGradientPanel3;
    private javax.swing.JLabel kembali;
    private javax.swing.JTextArea ket;
    private javax.swing.JLabel kodecash;
    private javax.swing.JLabel kodecust;
    private javax.swing.JLabel kodemtr;
    private javax.swing.JLabel namamtr;
    private javax.swing.JLabel namapel;
    private javax.swing.JFrame popupcust;
    private javax.swing.JFrame popupmtr;
    private javax.swing.JButton refresh;
    private javax.swing.JButton refresh1;
    private javax.swing.JScrollPane scrollpopupcust;
    private javax.swing.JScrollPane scrollpopupmtr;
    private model.KButton simpan;
    private javax.swing.JTable tbpopupcust;
    private javax.swing.JTable tbpopupmtr;
    private javax.swing.JLabel tglcash;
    private javax.swing.JTextField txtcari;
    private javax.swing.JTextField txtcari1;
    private javax.swing.JLabel warna;
    // End of variables declaration//GEN-END:variables

    
}
