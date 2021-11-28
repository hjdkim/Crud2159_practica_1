package com.mycompany.crudconsola2159;

import org.mariadb.jdbc.internal.util.exceptions.MariaDbSqlException;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLDataException;
import java.sql.*;

public class Autos {
    
    public static void main(String[] args) {
        insertar();
        consultar();
    }
    
    private static Connection conectarBaseDeDatos() throws SQLException {
        Connection conexion = null;
        try {
            String driver = "org.mariadb.jdbc.Driver";
            String url = "jdbc:mariadb://localhost:3306/javacac2159";
            String usuario = "root";
            String clave = "";
            conexion = DriverManager.getConnection(url, usuario, clave);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return conexion;
    }
    
    private static void consultar() {
        try {
            Connection conexion = conectarBaseDeDatos();
            String sql = "SELECT * FROM autos";
            Statement query = conexion.createStatement();
            ResultSet resultado = query.executeQuery(sql);
            
            while (resultado.next()) {
                System.out.println("----------------------------------");
                System.out.println(resultado.getString("patenteNumero"));
                System.out.println(resultado.getString("patenteActiva"));
                System.out.println(resultado.getString("marca"));
                System.out.println(resultado.getString("categoria"));
                System.out.println(resultado.getString("color"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    private static void insertar() {
        try {
            Connection conexion = conectarBaseDeDatos();
            String sql = "INSERT INTO autos (patenteNumero, patenteActiva, marca, categoria, color)"
                    + "VALUES ('GH789IJ', 0, 'Audi', 'Grando', 'Gris')";
            Statement query = conexion.createStatement();
            query.execute(sql);
            
            System.out.println("Se ingreso un registro.");
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
