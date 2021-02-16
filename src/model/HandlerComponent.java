/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
/**
 * Class untuk membantu konfigurasi setiap COMPONENT
 * yang terdapat pada java dengan koneksi Database.
 * Instansiasi class terlebih dahulu ketika menggunakan 
 * HandlerComponent ini.
 * @author REDHAT
 * @version 0.1a
 */
public class HandlerComponent {
    protected Connection CONN;
    protected ResultSet HANDLER_RES;
    public String[] COLUMN_TYPE, COLUMN_NAME;
    public Object[] RES_OBJ;
    protected File[] REPORT_JASPER = new File[7];
    protected String[] 
    FILE_PATH = {"/View/REPORT_HeadReInvent",
        "/View/REPORT_Inventory",
        "/View/REPORT_Order",
        "/View/REPORT_ReInventory",
        "/View/STRUK_Order",
        "/View/REPORT_Supplier",
        "/View/SubSTRUCK"};
    
    public HandlerComponent() throws SQLException{
        CONN = new Database().inisiasiDB();
        REPORT_JASPER[0] = new File("/View/REPORT_HeadReInvent.jrxml");
        REPORT_JASPER[1] = new File("/View/REPORT_Inventory.jrxml");
        REPORT_JASPER[2] = new File("/View/REPORT_Order.jrxml");
        REPORT_JASPER[3] = new File("/View/REPORT_ReInventory.jrxml");
        REPORT_JASPER[4] = new File("/View/REPORT_Supplier.jrxml");
        REPORT_JASPER[5] = new File("/View/STRUK_Order.jrxml");
        REPORT_JASPER[6] = new File("/View/SubSTRUCK.jrxml");
    }
    
    public void initiateResourceFile() {
        for(int x=0; x<7; x++) {
            copyFile(getClass().getResourceAsStream(FILE_PATH[x]+".jrxml"), "src"+FILE_PATH[x]+".jrxml");
            copyFile(getClass().getResourceAsStream(FILE_PATH[x]+".jasper"), "src"+FILE_PATH[x]+".jasper");
        }
        copyFile(getClass().getResourceAsStream("/View/Logo.jpg"), "src/View/Logo.jpg");
        copyFile(getClass().getResourceAsStream("/View/logopecel-transculent.png"), "src/View/logopecel-transculent.png");
        copyFile(getClass().getResourceAsStream("/View/logopecel.png"), "src/View/logopecel.png");
    }
    
    public static boolean copyFile(InputStream SOURCE, String DESTINATION) {
        boolean success = false;
        try {
            PrintStream fileErr = new PrintStream("./InitiateFileErrorLog.txt");
            System.setErr(fileErr);
            System.out.println("Copying ->" + SOURCE + "\n\tto ->" + DESTINATION);
            File fl = new File(DESTINATION);
            fl.getParentFile().mkdirs();
            Files.copy(SOURCE, Paths.get(DESTINATION), StandardCopyOption.REPLACE_EXISTING);
            success = true;
        } catch (Exception ex) {
            System.err.println(ex);
            JOptionPane.showMessageDialog(null, ex);
            System.setErr(System.err);
            ex.printStackTrace();
        }   
        return success;
    }
    // The Old Way to Initiate Table
    /*
        Object[] baris = {"ID Supplier", "Nama Supplier", "Alamat","No. Telepon"}; //buat di tabel form
        tabmode = new DefaultTableModel(null, baris);
        tabelsup.setModel(tabmode);
        String sql = "select * from datasup";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery (sql);
            while (hasil.next()){
                String skd = hasil.getString("id_sup"); 
                String snm = hasil.getString("nm_sup");
                String smerk = hasil.getString("almt_sup");
                String sharga = hasil.getString("no_telp");
                String[]data = {skd,snm,smerk,sharga,};
                tabmode.addRow(data);
            }
        } catch (Exception e){
        }
    */
    public DefaultTableModel initTable(JTable TABLE, String SQL, Database DBClass,DefaultTableModel MODEL) {
        try {
            HANDLER_RES = DBClass.getSQL(SQL);
            int count = HANDLER_RES.getMetaData().getColumnCount();
            COLUMN_NAME = new String[count];
            COLUMN_TYPE = new String[count];
            RES_OBJ = new Object[count];
            for(int x=0; x<count; x++) {
                COLUMN_NAME[x] = HANDLER_RES.getMetaData().getColumnName(x+1);
                COLUMN_TYPE[x] = HANDLER_RES.getMetaData().getColumnTypeName(x+1);
                System.out.println("Nama Kolom "+(x+1)+" : "+COLUMN_NAME[x]+"\t| Tipe : "+COLUMN_TYPE[x]);
            }
            MODEL = (DefaultTableModel) TABLE.getModel();
            TABLE.setModel(MODEL);
            while(HANDLER_RES.next())  {
                for(int y=0; y<count; y++) {
                    switch(COLUMN_TYPE[y]) {
                        case "INT" :
                            RES_OBJ[y] = HANDLER_RES.getInt(y+1);
                            break;
                        case "DOUBLE" :
                            RES_OBJ[y] = HANDLER_RES.getDouble(y+1);
                            break;
                        case "FLOAT" :
                            RES_OBJ[y] = HANDLER_RES.getFloat(y+1);
                            break;
                        case "VARCHAR" :
                            RES_OBJ[y] = HANDLER_RES.getString(y+1);
                            break;
                        case "TEXT" :
                            RES_OBJ[y] = HANDLER_RES.getString(y+1);
                            break;
                        case "DATE" :
                            RES_OBJ[y] = HANDLER_RES.getString(y+1);
                            break;
                        default :
                            RES_OBJ[y] = "GAGAL (HandlerComponent.Java)";
                            break;
                    }
                }
                MODEL.addRow(RES_OBJ);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return MODEL;
    }
    
    public void initTable(JTable TABLE, String SQL, Database DBClass) {
        DefaultTableModel MODEL;
        try {
            HANDLER_RES = DBClass.getSQL(SQL);
            int count = HANDLER_RES.getMetaData().getColumnCount();
            COLUMN_NAME = new String[count];
            COLUMN_TYPE = new String[count];
            RES_OBJ = new Object[count];
            for(int x=0; x<count; x++) {
                COLUMN_NAME[x] = HANDLER_RES.getMetaData().getColumnName(x+1);
                COLUMN_TYPE[x] = HANDLER_RES.getMetaData().getColumnTypeName(x+1);
                System.out.println("Nama Kolom "+(x+1)+" : "+COLUMN_NAME[x]+"\t| Tipe : "+COLUMN_TYPE[x]);
            }
            MODEL = new DefaultTableModel(null, COLUMN_NAME);
            TABLE.setModel(MODEL);
            while(HANDLER_RES.next())  {
                for(int y=0; y<count; y++) {
                    switch(COLUMN_TYPE[y]) {
                        case "INT" :
                            RES_OBJ[y] = HANDLER_RES.getInt(y+1);
                            break;
                        case "DOUBLE" :
                            RES_OBJ[y] = HANDLER_RES.getDouble(y+1);
                            break;
                        case "FLOAT" :
                            RES_OBJ[y] = HANDLER_RES.getFloat(y+1);
                            break;
                        case "VARCHAR" :
                            RES_OBJ[y] = HANDLER_RES.getString(y+1);
                            break;
                        case "TEXT" :
                            RES_OBJ[y] = HANDLER_RES.getString(y+1);
                            break;
                        case "DATE" :
                            RES_OBJ[y] = HANDLER_RES.getString(y+1);
                            break;
                        case "BLOB" :
                            RES_OBJ[y] = HANDLER_RES.getBlob(y+1);
                            break;
                        default :
                            RES_OBJ[y] = "GAGAL (HandlerComponent.Java)";
                            break;
                    }
                }
                MODEL.addRow(RES_OBJ);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void initComboBox(JComboBox JCOMBO, String SQL, Database DBClass, String[] DATA) {
        try {
            HANDLER_RES = DBClass.getSQL(SQL);
            HANDLER_RES.last();
            int size = HANDLER_RES.getRow();
            System.out.println("\n\nTotal Row : "+size);
            HANDLER_RES.beforeFirst();
            size=0;
            while(HANDLER_RES.next()) {
                DATA[size] = HANDLER_RES.getString(1);
                JCOMBO.addItem(HANDLER_RES.getString(2));
                size++;
            }
        } catch (SQLException Ex) {
            Ex.printStackTrace();
        }
    }
    
    public static class LimitInput extends DocumentFilter {
        public static int LIMIT;
        
        public LimitInput(int limit) {
            if (limit <= 0) {
                throw new IllegalArgumentException("Limit can not be <= 0");
            }
            LimitInput.LIMIT = limit;
        }
        
        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attr) throws BadLocationException{
            int curLength = fb.getDocument().getLength();
            int overLim = (curLength + text.length()) - LIMIT - length;
            if(overLim > 0) 
                text = text.substring(0, text.length() - overLim);
            if(text.length() > 0)
                super.replace(fb, offset, length, text, attr);
        }
    }
    
    public static class Report {
        private static String fileName;
        public static Map param;
        private Connection conn;
        Database DB;
        public Report() {
            fileName = "";
            param = new HashMap();
        }
        
        public static void setFileName(String aFileName) {
            fileName = aFileName;
        }
        
        public static void setParam(String paramName, String paramValue) {
            param.put(paramName, paramValue);
        }
        
        public static void setParam(String paramName, Integer paramValue) {
            param.put(paramName, paramValue);
        }
        
        public static void setParam(String paramName, Double paramValue) {
            param.put(paramName, paramValue);
        }
        
        public static void setParam(String paramName, Float paramValue) {
            param.put(paramName, paramValue);
        }
        
        public static void setParam(String paramName, Date paramValue) {
            param.put(paramName, paramValue);
        }
        
        public void printReport() {
            String fName = "src/View/"+fileName+".jrxml";
            String fFill = "src/View/"+fileName+".jasper";
            try {
                DB = new Database();
                conn = DB.inisiasiDBnoStatic();
                JasperCompileManager.compileReport(fName);
                JasperFillManager.fillReport(fFill, param, conn);
                JasperPrint jp = JasperFillManager.fillReport(fFill, param, conn);
                JasperViewer.viewReport(jp, false);
            } catch (SQLException | JRException ex) {
                ex.printStackTrace();
            }
        }
    }
}
