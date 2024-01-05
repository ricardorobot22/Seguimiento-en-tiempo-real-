/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.mavenproject2;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo Ranauro
 */
public class Mavenproject2 {

    public static void main(String[] args) {
        
        
        
        NewClass objetoConexion;
        objetoConexion = new NewClass();
        Connection cn = objetoConexion.estableceConexion();
        try{ 
            String lat,lon,tt,id;
            ServerSocket servidor = new ServerSocket(9000);
            lat="hola";
            lon="hola";
            tt="hola";
            id="hola";
             //*/ 
            String sql="INSERT INTO usuario (LATITUD,LONGITUD,TIMESTAMP,ID) VALUES (?,?,?,?)";
           // String sql2="INSERT INTO usuario2 (LATITUD,LONGITUD,TIMESTAMP,ID) VALUES (?,?,?,?)";
            
            while(true){
                Socket user = servidor.accept();
                BufferedReader entrada = new BufferedReader(new InputStreamReader(user.getInputStream()));
                PrintWriter salida = new PrintWriter(new OutputStreamWriter(user.getOutputStream()));  
                String dato = entrada.readLine();
                System.out.println(dato);
                salida.print(dato);    
                user.close();
                
                String[] parts = dato.split("\\s+");
                if (parts.length>=4){
                    lat = parts[0];
                    lon = parts[1];
                    tt = parts[2];
                    id = parts[3];
                    System.out.println(lat);
                    System.out.println(lon);
                    System.out.println(tt);
                    System.out.println(id);
                    
                }else{
                    System.err.println("Cantidad de datos menor al límite");
                    continue;
                }
                
                try {
                    PreparedStatement pst;
                  //  int idint =Integer.parseInt(id);
                  //  if(2==idint){
                  //  pst = cn.prepareStatement(sql2);  
                  //  }else{
                    pst = cn.prepareStatement(sql);
                  //  }
                    pst.setString(1,lat);
                    pst.setString(2,lon);
                    pst.setString(3,tt);
                    pst.setString(4,id);
                    int n=pst.executeUpdate();
                    /*if (n>0){
                        JOptionPane.showMessageDialog(null,"registro guardado correctamente");         
                    } */   
                } catch (SQLException ex) {
                    Logger.getLogger(Mavenproject2.class.getName()).log(Level.SEVERE, null, ex);
                }    
            }
            
        }catch(Exception e){
            System.err.println(e);
        }
    }
}
