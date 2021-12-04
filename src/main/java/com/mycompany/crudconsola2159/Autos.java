package com.mycompany.crudconsola2159;

import org.mariadb.jdbc.internal.util.exceptions.MariaDbSqlException;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLDataException;
import java.sql.*;
import java.util.Scanner;

public class Autos {

    public static void main(String[] args) {

        System.out.println("CRUD Consola");
        int opc = 0;
        Scanner sc = new Scanner(System.in);
        String patenteNumero = "";
        boolean patenteActiva;
        String marca = "";
        String categoria = "";
        String color = "";
        
        do {
            do {
                System.out.println("--------------------");
                System.out.println("1-Ver datos");
                System.out.println("2-Ingresar Datos");
                System.out.println("3-Modificar Datos");
                System.out.println("4-Eliminar Datos");
                System.out.println("5-salir");
                System.out.println("");
                System.out.println("Ingrese opcion: ");
                opc = sc.nextInt();

            } while (opc < 1 || opc > 6);

            switch (opc) {
                case 1:
                    consultar();
                    break;

                case 2:
                    System.out.println("Ingrese chapa patente: ");
                    patenteNumero = sc.next();
                    patenteActiva = false;
                    System.out.println("Ingrese la marca: ");
                    marca = sc.next();
                    System.out.println("Ingrese la categoria: ");
                    categoria = sc.next();
                    System.out.println("Ingrese el color: ");
                    color = sc.next();
                    
                    insertar(patenteNumero, patenteActiva, marca, categoria, color);
                    break;

                case 3:
                    actualizar();
                    break;

                case 4:
                    eliminar();
                    break;

                case 5:
                    System.out.println("Gracias por utilizar la aplicacion");
                    break;
            }
        } while (opc != 5);
        
//        insertar();
//        eliminar();
//        actualizar();
//        consultar();
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertar(String p, boolean pa, String m, String c, String co) {
        try {
            Connection conexion = conectarBaseDeDatos();
            String sql = "INSERT INTO autos (patenteNumero, patenteActiva, marca, categoria, color)"
                    + "VALUES ('" + p + "', " + pa + ", '" + m + "', '" + c + "', '" + co + "')";
            Statement query = conexion.createStatement();
            query.execute(sql);

            System.out.println("Se ingreso un registro.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void eliminar() {
        try {
            Connection conexion = conectarBaseDeDatos();
            String sql = "DELETE FROM autos WHERE color = 'Verde'";
            Statement query = conexion.createStatement();
            query.execute(sql);

            System.out.println("Se eliminaron los registros con color verde");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void actualizar() {
        try {
            Connection conexion = conectarBaseDeDatos();
            String sql = "UPDATE autos SET patenteNumero = 'EF200GH' WHERE patenteNumero = 'AB100CD'";
            Statement query = conexion.createStatement();
            query.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
