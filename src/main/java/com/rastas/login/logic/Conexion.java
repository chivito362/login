
package com.rastas.login.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public  class Conexion {
    public static Connection conectar(String bd) throws SQLException{
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bd, "root", "");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null;
    }
    public static boolean comprobar_datos(String usuario,String pw){
        try {
            Connection conn=Conexion.conectar("login");
            
            String sql="SELECT usuario,contrasenia,estado FROM usuarios";
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet resultado=ps.executeQuery();
            
            while(resultado.next()){
                if(resultado.getString("usuario").equals(usuario)&& resultado.getInt("estado")==1){
                    if(resultado.getString("contrasenia").equals(pw)){
                        JOptionPane.showMessageDialog(null, "Ingreso Exitoso");
                        return true;
                    }else{
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                        return false;
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "El usuario no se encuentra Registrdo");
                    return false;
                }
            }
            
        } catch (SQLException ex) {
            ex.fillInStackTrace();
            return false;
        }
        return false;
    }
}
