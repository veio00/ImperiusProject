/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.*;  
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Will
 */


public class Banco {
    private static Connection open(){
        String url = String.format("jdbc:sqlserver://smart420.database.windows.net:1433;database=Smart42;user=administrador@smart420;password=Smart420;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
        Connection conn = null;

        try {
                conn = DriverManager.getConnection(url);
                String open = conn.getSchema();
                return conn;                
                                  
        }
        catch (Exception e) {
                e.printStackTrace();
        }
        return null;
        
    }
}
