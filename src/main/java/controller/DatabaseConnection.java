/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.*;
import javax.swing.JOptionPane;

public class DatabaseConnection {
    
    private static  Connection obj;
     
    public static Connection getConnection()
    {
        if (obj == null)
        {
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                obj =  DriverManager.getConnection("jdbc:mysql://localhost:3306/Cafe?useSSL=false","root","3243");
            }catch(Exception ee){
                JOptionPane.showMessageDialog(null, ee.toString());
            }
        }    
        return obj;
    }
    
}
