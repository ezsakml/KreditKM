/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author T430
 */
public class DB {
    
    private Statement stmt = null;
    private ResultSet rs = null;
    private Connection conn = null;
    
    public DB(String ConAddress) throws Exception, SQLException {
       
        try {
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            conn = DriverManager.getConnection(ConAddress);
            conn.setTransactionIsolation(conn.TRANSACTION_READ_UNCOMMITTED);
        }
        catch(SQLException es) {
            
            throw es;
        }
    }
    
    public void createQuery(String Query)throws Exception, SQLException {
     
       try {
          stmt = conn.createStatement();
          //eksekusi query
          rs = stmt.executeQuery(Query);
          if (stmt.execute(Query)) {
              //ambil hasil query
              rs = stmt.getResultSet();
          }
       }
       catch(SQLException es) {
         
           throw es;
       }
    }
    
    public void createUpdate(String Query)throws Exception, SQLException {
    
      try {
        stmt = conn.createStatement();
        
        int hasil = stmt.executeUpdate(Query);
       }
       catch(SQLException es) {
         
          throw es;
       }
    }
    
    public ResultSet getResult()throws Exception {
     
      ResultSet Temp = null;
      try{
        return rs;
      }
      catch (Exception ex) {
       
        return Temp;
      }
    }
    
    public void closeResult()throws SQLException, Exception {
    
      if (rs != null) {
        try {
           rs.close();
        }
        catch (SQLException sqlEx) {
           rs = null;
           throw sqlEx;
        }
      }
      if (stmt != null) {
        try {
           stmt.close();
        }
        catch (SQLException sqlEx) {
           stmt = null;
           throw sqlEx;
        }
      }
    }
    
    public void closeConnection()throws SQLException, Exception {
     
      if (conn != null) {
         try {
           conn.close();
         }
         catch(SQLException sqlEx) {
             conn = null;
         }
      }
    }
    
}
