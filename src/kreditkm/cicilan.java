/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kreditkm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
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
public class cicilan extends javax.swing.JPanel {

//    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd";
//    public static String tanggalSekarang(){
//        
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
//        return sdf.format(cal.getTime());
//    }
    /**
     * Creates new form pnlPelanggan
     */
    private boolean action=false;
    private String tglbr = "";
    private String tbyr = "";
    private String sis = "";
    private String angske = "";
    private String lmcil = "";
    public cicilan() {
        initComponents();
        //tglbayarr.setText(tanggalSekarang());
        getAutoKodeCicilan();
    }
    
    private void getAutoKodeCicilan(){
        try{
            DB db = new DB("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
            db.createQuery("select max(nomorbayar) from bayarcicilan");

            if(db.getResult().next()) {
                String a = db.getResult().getString("max(nomorbayar)");
                String i = a.substring(6);
                String e = String.valueOf(Integer.parseInt(i)+1);
                
                String q =String.valueOf(e);
                    if(q.length()==1){
                        nobayar.setText("CICIL-000"+e);
                    }else if(q.length()==2){
                        nobayar.setText("CICIL-00"+e);
                    }else if(q.length()==3){
                        nobayar.setText("CICIL-0"+e);
                    }else if(q.length()==4){
                        nobayar.setText("CICIL-"+e);
                        }
            }else{
                nobayar.setText("CICIL-0001");
            }
            db.closeResult();
            db.closeConnection();
        }catch(Exception x) {
            System.out.println(x);
        }
    }

     private void datakredit() {
        String[] judul={"Kode Kredit","Tanggal Kredit","Kode Pelanggan","Kode Motor","Jatuh Tempo"}; 
        DefaultTableModel tabModel = new DefaultTableModel (null,judul);
            try {
                DB db = new DB ("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
                db.createQuery("Select * from belikredit order by kodekredit");
                while (db.getResult().next()) {
                    String kodekredit_p = db.getResult().getString("kodekredit");
                    String tglkredit = db.getResult().getString("tanggalkredit");
                    String kodepelanggan = db.getResult().getString("kodecust");
                    String kodemotor = db.getResult().getString("kodemotor");
                    String jtempo = db.getResult().getString("jatuhtempo");
            
                    Object[] data = {kodekredit_p,tglkredit,kodepelanggan,kodemotor,jtempo};
                    tabModel.addRow(data);
                    //getAutoKodePelanggan();
                }
                db.closeResult();
                db.closeConnection();
        
                tbpopupkrd.setModel(tabModel);
                tbpopupkrd.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD, 12));
                tbpopupkrd.getTableHeader().setOpaque(false);
                tbpopupkrd.getTableHeader().setBackground(new Color(251,253,138));
                tbpopupkrd.setRowHeight(25);
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                for(int a=0;a<tbpopupkrd.getColumnCount();a++){
                    tbpopupkrd.getColumnModel().getColumn(a).setCellRenderer(centerRenderer);
                }
                scrollpopupkrd.getViewport().add(tbpopupkrd);
                scrollpopupkrd.setViewportView(tbpopupkrd);
                }
                catch (Exception ex) {
                     System.out.println(ex); 
                }
     }
     
     private void getHitungBayarTotal(){
         String d=denda.getText();
         
         if(d.equals("Motor Di Sita")){
           totalbayar.setText("0");  
         }else{
           totalbayar.setText(String.valueOf((Integer.parseInt(angsuran.getText())*Integer.parseInt(bulancil.getText()))));
         }
      }
       private void getDenda(){
          String blntlt = String.valueOf(Integer.parseInt(tglbr.substring(5,7))-Integer.parseInt(jthtempo.getText().substring(5,7)));
          int thn = Integer.parseInt(tglbr.substring(0,4));
          System.out.println(blntlt);
          int blnbyr = Integer.parseInt(tglbr.substring(5,7));
          
            switch (blntlt) {
              case "0":
                  {
                      int a = Integer.parseInt(jthtempo.getText().substring(8));
                      int b = Integer.parseInt(tglbr.substring(8));
                      int c = b-a;
                      if(c<=0){
                          
                      denda.setText("0");
                      }
                      else{
                          denda.setText(""+c*5000);}
                  }
                  break;
              case "1": //telat 1 bulan
                  if(thn%4==0){
                      if(blnbyr == 1){
                          int a = (31 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 2){
                          int a = (31 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 3){
                          int a = (29 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 4){
                          int a = (31 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 5){
                          int a = (30 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 6){
                          int a = (31 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 7){
                          int a = (30 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 8){
                          int a = (31 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 9){
                          int a = (31 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 10){
                          int a = (30 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 11){
                          int a = (31 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 12){
                          int a = (30 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }
                  }else if(thn%4==1){
                      if(blnbyr == 1){
                          int a = (31 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 2){
                          int a = (31 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 3){
                          int a = (28 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 4){
                          int a = (31 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 5){
                          int a = (30 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 6){
                          int a = (31 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 7){
                          int a = (30 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 8){
                          int a = (31 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 9){
                          int a = (31 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 10){
                          int a = (30 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 11){
                          int a = (31 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 12){
                          int a = (30 - Integer.parseInt(jthtempo.getText().substring(8)));
                          int b = (Integer.parseInt(tglbr.substring(8)) );
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }
                  }
                  break;
              case "-10": //telat 2 bulan beda tahun
                  if(thn%4==0){
                      if(blnbyr == 1){
                          int a = 30 - Integer.parseInt(jthtempo.getText().substring(8)) + 31;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 2){
                          int a = 31 - Integer.parseInt(jthtempo.getText().substring(8)) + 31;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }
                  }
                  break;
              case "2": //telat 2 bulan
                  if(thn%4==0){
                      if(blnbyr == 3){
                          int a = 31 - Integer.parseInt(jthtempo.getText().substring(8)) + 29;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 4){
                          int a = 29 - Integer.parseInt(jthtempo.getText().substring(8)) + 31;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 5){
                          int a = 31 - Integer.parseInt(jthtempo.getText().substring(8)) + 30;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 6){
                          int a = 30 - Integer.parseInt(jthtempo.getText().substring(8)) + 31;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 7){
                          int a = 31 - Integer.parseInt(jthtempo.getText().substring(8)) + 30;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 8){
                          int a = 30 - Integer.parseInt(jthtempo.getText().substring(8)) + 31;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 9){
                          int a = 31 - Integer.parseInt(jthtempo.getText().substring(8)) + 31;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 10){
                          int a = 31 - Integer.parseInt(jthtempo.getText().substring(8)) + 30;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 11){
                          int a = 30 - Integer.parseInt(jthtempo.getText().substring(8)) + 31;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 12){
                          int a = 31 - Integer.parseInt(jthtempo.getText().substring(8)) + 30;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }
                  }else if(thn%4==1){
                      if(blnbyr == 3){
                          int a = 31 - Integer.parseInt(jthtempo.getText().substring(8)) + 28;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 4){
                          int a = 28 - Integer.parseInt(jthtempo.getText().substring(8)) + 31;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 5){
                          int a = 31 - Integer.parseInt(jthtempo.getText().substring(8)) + 30;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 6){
                          int a = 30 - Integer.parseInt(jthtempo.getText().substring(8)) + 31;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 7){
                          int a = 31 - Integer.parseInt(jthtempo.getText().substring(8)) + 30;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 8){
                          int a = 30 - Integer.parseInt(jthtempo.getText().substring(8)) + 31;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 9){
                          int a = 31 - Integer.parseInt(jthtempo.getText().substring(8)) + 31;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 10){
                          int a = 31 - Integer.parseInt(jthtempo.getText().substring(8)) + 30;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 11){
                          int a = 30 - Integer.parseInt(jthtempo.getText().substring(8)) + 31;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }else if(blnbyr == 12){
                          int a = 31 - Integer.parseInt(jthtempo.getText().substring(8)) + 30;
                          int b = (Integer.parseInt(tglbr.substring(8))-1);
                          int c = b+a;
                          denda.setText(""+c*5000);
                      }
                  }
                  break;
              case "-9": //telat 3 bulan beda tahun
                  if(thn%4==0){
                      if(blnbyr == 1){
                          int a = 31 + 30 + 31 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>90){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }else if(blnbyr == 2){
                          int a = 30 + 31 + 31 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>90){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }else if(blnbyr == 3){
                          int a = 31 + 31 + 29 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>89){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }
                  }else if(thn%4==1){
                      if(blnbyr == 1){
                          int a = 31 + 30 + 31 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>90){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }else if(blnbyr == 2){
                          int a = 30 + 31 + 31 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>90){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }else if(blnbyr == 3){
                          int a = 31 + 31 + 28 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>88){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }
                  }
                  break;
              case "3":
                  if(thn%4==0){
                      if(blnbyr == 4){
                          int a = 31 + 29 + 31 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>89){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }else if(blnbyr == 5){
                          int a = 29 + 31 + 30 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>88){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }else if(blnbyr == 6){
                          int a = 31 + 30 + 31 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>90){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }else if(blnbyr == 7){
                          int a = 30 + 31 + 30 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>89){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }else if(blnbyr == 8){
                          int a = 31 + 31 + 30 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>90){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }else if(blnbyr == 9){
                          int a = 31 + 30 + 31 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>90){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }else if(blnbyr == 10){
                          int a = 30 + 31 + 30 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>89){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }else if(blnbyr == 11){
                          int a = 31 + 30 + 31 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>90){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }else if(blnbyr == 12){
                          int a = 30 + 31 + 30 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>89){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }
                  }else if(thn%4==1){
                      if(blnbyr == 4){
                          int a = 31 + 28 + 31 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>88){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }else if(blnbyr == 5){
                          int a = 28 + 31 + 30 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>87){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }else if(blnbyr == 6){
                          int a = 31 + 30 + 31 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>90){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }else if(blnbyr == 7){
                          int a = 30 + 31 + 30 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>89){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }else if(blnbyr == 8){
                          int a = 31 + 31 + 30 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>90){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }else if(blnbyr == 9){
                          int a = 31 + 30 + 31 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>90){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }else if(blnbyr == 10){
                          int a = 30 + 31 + 30 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>89){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }else if(blnbyr == 11){
                          int a = 31 + 30 + 31 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>90){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }else if(blnbyr == 12){
                          int a = 30 + 31 + 30 - Integer.parseInt(jthtempo.getText().substring(8));
                          int b = (Integer.parseInt(tglbr.substring(8)) - 2);
                          int c = b+a;
                          if(c>89){
                              denda.setText("Motor Di Sita");
                          }else{
                              denda.setText(""+c*5000);
                          }
                      }
                  break;
              }
          }
        }
       
    private void simpancicilan(){
        try{
            DB db = new DB("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
            if(denda.getText().equals("Motor Di Sita")){
                db.createUpdate("insert into bayarcicilan(nomorbayar,tanggalbayar,kodekredit,bulan,jumlah,denda,keterangan)"
                          + "values('"+nobayar.getText()+"','"+tglbr+"','"+kodekredit.getText()+"',"
                                  + "'"+bulancil.getText()+"','"+totalbayar.getText()+"','"+denda.getText()+"','MOTOR DIAMBIL')");
            
            }else{
                db.createUpdate("insert into bayarcicilan(nomorbayar,tanggalbayar,kodekredit,bulan,jumlah,denda,keterangan)"
                          + "values('"+nobayar.getText()+"','"+tglbr+"','"+kodekredit.getText()+"',"
                                  + "'"+bulancil.getText()+"','"+totalbayar.getText()+"','"+denda.getText()+"','"+ket.getText()+"')");
            }
            
            
            
            //UPDATE KREDIT
            //--jatuhtempo
            String tanggal = tglbr.substring(8, 10);
            String bulan = tglbr.substring(5, 7);
            String tahun = tglbr.substring(0, 4);
            if(Integer.parseInt(bulan)==12){
                tahun = String.valueOf(Integer.parseInt(tahun) + 1);
                bulan = "01";
            }else{
            bulan = String.valueOf(Integer.parseInt(bulan) + 1);
        }
            String tglTempo = tahun+"-"+bulan+"-"+tanggal;
            //--telahbayar
            int tb = Integer.parseInt(totalbayar.getText());
            int byr = Integer.parseInt(cbbayar.getSelectedItem().toString());
            db.createQuery("select telahbayar,sisa,lamacicilan,angsuranke from belikredit where kodekredit='"+kodekredit.getText()+"'");
            while (db.getResult().next()){
                 tbyr = db.getResult().getString("telahbayar");
                 sis = db.getResult().getString("sisa");
                 lmcil = db.getResult().getString("lamacicilan");
                 angske = db.getResult().getString("angsuranke");
            }
            int tot=Integer.parseInt(tbyr)+Integer.parseInt(totalbayar.getText());
            int angs=Integer.parseInt(angsuranke.getText());
            
            int jumangs=angs+byr-1;
            int totangs=angs+1;
            if(Integer.parseInt(lmcil)>=Integer.parseInt(angske)){
               db.createUpdate("update belikredit set angsuranke='"+jumangs+"',telahbayar='"+tot+"',sisa='0',jatuhtempo='"+tglTempo+"',keterangan='LUNAS' where kodekredit='"+kodekredit.getText()+"'"); 
            }else{
               db.createUpdate("update belikredit set angsuranke='"+totangs+"',telahbayar='"+tot+"',sisa='"+sisa.getText()+"',jatuhtempo='"+tglTempo+"',keterangan='BELUM LUNAS' where kodekredit='"+kodekredit.getText()+"'");
            }
            
            String pesan = "Data Cicilan Telah Disimpan";
            JOptionPane.showMessageDialog(null  , pesan);
        }catch(Exception es){
            JOptionPane.showMessageDialog(null, "Data dengan Nomor Bayar '"+nobayar.getText()+"' Sudah Ada ...", "kesalahan", JOptionPane.ERROR_MESSAGE);
            System.out.println(es);
        }
    }   
    
    void refresh(){
        //JDateChooser
        tglbayar.setDate(null);
        kodekredit.setText("KRD-");
        jthtempo.setText("0000-00-00");
        action=false;
        if(action==false){
            cbbayar.removeAllItems();
        }
        //cbbayar.setSelectedIndex(0);
        ket.setText("");
        angsuranke.setText("0");
        bulankr.setText("0");
        angsuran.setText("0");
        bulancil.setText("0");
        denda.setText("0");
        totalbayar.setText("0");
        sisa.setText("0");
    }
    
    private void print() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/kredit_km?user=root&password");
            HashMap hashMap = new HashMap();
            hashMap.put("kodebayar", nobayar.getText());	
            JasperReport jreport = JasperCompileManager.compileReport("src/report/NotaCicilan.jrxml");
            JasperPrint jprint = JasperFillManager.fillReport(jreport, hashMap, koneksi);
            JasperViewer.viewReport(jprint, false);
            
        }catch(Exception se) {
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

        popupkrd = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        cari = new javax.swing.JButton();
        refreshkrd = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        close = new javax.swing.JButton();
        scrollpopupkrd = new javax.swing.JScrollPane();
        tbpopupkrd = new javax.swing.JTable();
        kGradientPanel1 = new model.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        kGradientPanel2 = new model.KGradientPanel();
        jLabel8 = new javax.swing.JLabel();
        angsuranke = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        bulankr = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        angsuran = new javax.swing.JLabel();
        bulancil = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        denda = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        totalbayar = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        sisa = new javax.swing.JLabel();
        kGradientPanel3 = new model.KGradientPanel();
        jLabel7 = new javax.swing.JLabel();
        nobayar = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        bpopupkrd = new model.KButton();
        kodekredit = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbbayar = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ket = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        jthtempo = new javax.swing.JLabel();
        tglbayar = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        simpan = new model.KButton();
        batal = new model.KButton();

        popupkrd.setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        refreshkrd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N
        refreshkrd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshkrdActionPerformed(evt);
            }
        });

        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close.png"))); // NOI18N
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        tbpopupkrd.setModel(new javax.swing.table.DefaultTableModel(
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
        tbpopupkrd.setGridColor(new java.awt.Color(0, 153, 153));
        tbpopupkrd.setSelectionBackground(new java.awt.Color(204, 204, 255));
        tbpopupkrd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbpopupkrdMouseClicked(evt);
            }
        });
        scrollpopupkrd.setViewportView(tbpopupkrd);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(refreshkrd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(close, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollpopupkrd, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(close)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cari)
                    .addComponent(refreshkrd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollpopupkrd, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout popupkrdLayout = new javax.swing.GroupLayout(popupkrd.getContentPane());
        popupkrd.getContentPane().setLayout(popupkrdLayout);
        popupkrdLayout.setHorizontalGroup(
            popupkrdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        popupkrdLayout.setVerticalGroup(
            popupkrdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setBackground(new java.awt.Color(8, 105, 114));
        setMaximumSize(new java.awt.Dimension(770, 420));
        setMinimumSize(new java.awt.Dimension(770, 420));
        setPreferredSize(new java.awt.Dimension(770, 420));

        kGradientPanel1.setkBorderRadius(0);
        kGradientPanel1.setkEndColor(new java.awt.Color(1, 169, 180));
        kGradientPanel1.setkStartColor(new java.awt.Color(135, 223, 214));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("BAYAR ANGSURAN PELANGGAN");

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

        kGradientPanel2.setkEndColor(new java.awt.Color(251, 253, 138));
        kGradientPanel2.setkStartColor(new java.awt.Color(135, 223, 214));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel8.setText("ANGSURAN KE");

        angsuranke.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        angsuranke.setText("0");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel9.setText("DARI");

        bulankr.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        bulankr.setText("0");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel16.setText("ANGSURAN");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel17.setText("Rp.");

        angsuran.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        angsuran.setText("0");

        bulancil.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        bulancil.setText("0");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel20.setText("BULAN");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel24.setText("DENDA");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel25.setText("Rp.");

        denda.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        denda.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel24))
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(bulancil, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel20))
                            .addComponent(angsuran, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(denda, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(angsuran))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bulancil)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(denda))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel30.setText("TOTAL BAYAR");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel31.setText("Rp.");

        totalbayar.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        totalbayar.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addGap(35, 35, 35)
                .addComponent(jLabel31)
                .addGap(18, 18, 18)
                .addComponent(totalbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(totalbayar))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel36.setText("SISA");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jLabel37.setText("Rp.");

        sisa.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        sisa.setText("0");

        javax.swing.GroupLayout kGradientPanel2Layout = new javax.swing.GroupLayout(kGradientPanel2);
        kGradientPanel2.setLayout(kGradientPanel2Layout);
        kGradientPanel2Layout.setHorizontalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel36)
                        .addGap(84, 84, 84)
                        .addComponent(jLabel37)
                        .addGap(18, 18, 18)
                        .addComponent(sisa, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(66, Short.MAX_VALUE))
                    .addGroup(kGradientPanel2Layout.createSequentialGroup()
                        .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(35, 35, 35)
                .addComponent(angsuranke)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(bulankr, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );
        kGradientPanel2Layout.setVerticalGroup(
            kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(angsuranke)
                    .addComponent(jLabel9)
                    .addComponent(bulankr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(kGradientPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37)
                    .addComponent(sisa))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kGradientPanel3.setkEndColor(new java.awt.Color(251, 253, 138));
        kGradientPanel3.setkStartColor(new java.awt.Color(135, 223, 214));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("KODE BAYAR");

        nobayar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nobayar.setText("CICIL-");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("KODE KREDIT");

        bpopupkrd.setText("...");
        bpopupkrd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpopupkrdActionPerformed(evt);
            }
        });

        kodekredit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        kodekredit.setText("KRD-");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("BAYAR");

        cbbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbayarActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("KETERANGAN");

        ket.setColumns(20);
        ket.setRows(5);
        jScrollPane1.setViewportView(ket);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setText("JATUH TEMPO");

        jthtempo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jthtempo.setText("00/00/0000");

        tglbayar.setDateFormatString("yyyy-MM-dd");
        tglbayar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tglbayarPropertyChange(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("TANGGAL BAYAR");

        javax.swing.GroupLayout kGradientPanel3Layout = new javax.swing.GroupLayout(kGradientPanel3);
        kGradientPanel3.setLayout(kGradientPanel3Layout);
        kGradientPanel3Layout.setHorizontalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel3Layout.createSequentialGroup()
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel15)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nobayar)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jthtempo)
                            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                                .addComponent(kodekredit)
                                .addGap(38, 38, 38)
                                .addComponent(bpopupkrd, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tglbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        kGradientPanel3Layout.setVerticalGroup(
            kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(nobayar))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tglbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(27, 27, 27)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(kodekredit)
                    .addComponent(bpopupkrd, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jthtempo))
                .addGap(18, 18, 18)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(kGradientPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(kGradientPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(kGradientPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 42, Short.MAX_VALUE))
                    .addComponent(kGradientPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        String[] judul={"Kode Kredit","Tanggal Kredit","Kode Pelanggan","Kode Motor","Jatuh Tempo"};
        DefaultTableModel tabModel = new DefaultTableModel (null,judul);
        try {
            DB db = new DB("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
            db.createQuery("select * from belikredit where "
                + "kodekredit like '%"+txtcari.getText()+"%'"
                + "OR kodecust like '%"+txtcari.getText()+"%'"
                + "OR kodemotor like '%"+txtcari.getText()+"%'"
                + "OR jatuhtempo like '%"+txtcari.getText()+"%'");
            while (db.getResult().next()) {
                String kodekrd = db.getResult().getString("kodekredit");
                String kodecst = db.getResult().getString("kodecust");
                String kodemtrr = db.getResult().getString("kodemotor");
                String tempo = db.getResult().getString("jatuhtempo");

                Object[] data = {kodekrd,kodecst,kodemtrr,tempo};
                tabModel.addRow(data);
            }

            tbpopupkrd.setModel(tabModel);
            scrollpopupkrd.getViewport().add(tbpopupkrd);
            scrollpopupkrd.setViewportView(tbpopupkrd);
            db.closeResult();
            db.closeConnection();
        }
        catch (Exception se) {
            System.out.println(se);
        }
    }//GEN-LAST:event_cariActionPerformed

    private void refreshkrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshkrdActionPerformed
        datakredit();
        txtcari.setText("");
    }//GEN-LAST:event_refreshkrdActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        popupkrd.dispose();
    }//GEN-LAST:event_closeActionPerformed

    private void tbpopupkrdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbpopupkrdMouseClicked
        
        Object ag = tbpopupkrd.getModel().getValueAt(tbpopupkrd.getSelectedRow(), 0);
        Object ah = tbpopupkrd.getModel().getValueAt(tbpopupkrd.getSelectedRow(), 4);
        kodekredit.setText(ag.toString());
        jthtempo.setText(ah.toString());
        popupkrd.dispose();
        try {
            DB db = new DB("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
            db.createQuery("select lamacicilan,angsuranke,angsuran,sisa from belikredit "
                    + "where kodekredit='"+kodekredit.getText()+"'");
            if(db.getResult().next()){
                String k = db.getResult().getString("angsuranke");
                String l = db.getResult().getString("angsuran");
                String m = db.getResult().getString("lamacicilan");
                String ss = db.getResult().getString("sisa");
                
                
                String as = String.valueOf(Integer.parseInt(k) + 1);
                String rr = String.valueOf(Integer.parseInt(m)-Integer.parseInt(k));
                angsuranke.setText(as);
                angsuran.setText(l);
                bulankr.setText(m);
                for(int i=1;i<=Integer.parseInt(rr);i++){
                        cbbayar.addItem(String.valueOf(i));
                    }
            }
//            getDenda();
            db.closeResult();
            db.closeConnection();
                       action=true;
        } catch (Exception ex){
            System.out.println(ex);
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_tbpopupkrdMouseClicked
  
    private void bpopupkrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpopupkrdActionPerformed
        popupkrd.setVisible(true);
        popupkrd.setLocation(500, 200);
        popupkrd.setSize(450, 197);
        datakredit();
    }//GEN-LAST:event_bpopupkrdActionPerformed

    private void cbbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbayarActionPerformed
        if(action==true){
        bulancil.setText(cbbayar.getSelectedItem().toString());
        getDenda();
        getHitungBayarTotal();
        
        //nyari total sisa
        try {
            DB db = new DB("jdbc:mysql://localhost:3306/kredit_km?user=root&password=");
            db.createQuery("select sisa from belikredit "
                    + "where kodekredit='"+kodekredit.getText()+"'");
            while(db.getResult().next()){
                String a = db.getResult().getString("sisa");
                
                sisa.setText(String.valueOf(Integer.parseInt(a)-Integer.parseInt(totalbayar.getText())));
            }
            }catch(Exception e){
                    System.out.print(e);
            }
        }
        
    }//GEN-LAST:event_cbbayarActionPerformed

    private void simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanActionPerformed
        simpancicilan();
        print();
        refresh();
        getAutoKodeCicilan();
    }//GEN-LAST:event_simpanActionPerformed

    private void tglbayarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tglbayarPropertyChange
        if(tglbayar.getDate()!=null){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        tglbr= String.valueOf(sdf.format(tglbayar.getDate()));
    }
    }//GEN-LAST:event_tglbayarPropertyChange

    private void batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalActionPerformed
        refresh();
    }//GEN-LAST:event_batalActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel angsuran;
    private javax.swing.JLabel angsuranke;
    private model.KButton batal;
    private model.KButton bpopupkrd;
    private javax.swing.JLabel bulancil;
    private javax.swing.JLabel bulankr;
    private javax.swing.JButton cari;
    private javax.swing.JComboBox cbbayar;
    private javax.swing.JButton close;
    private javax.swing.JLabel denda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jthtempo;
    private model.KGradientPanel kGradientPanel1;
    private model.KGradientPanel kGradientPanel2;
    private model.KGradientPanel kGradientPanel3;
    private javax.swing.JTextArea ket;
    private javax.swing.JLabel kodekredit;
    private javax.swing.JLabel nobayar;
    private javax.swing.JFrame popupkrd;
    private javax.swing.JButton refreshkrd;
    private javax.swing.JScrollPane scrollpopupkrd;
    private model.KButton simpan;
    private javax.swing.JLabel sisa;
    private javax.swing.JTable tbpopupkrd;
    private com.toedter.calendar.JDateChooser tglbayar;
    private javax.swing.JLabel totalbayar;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
