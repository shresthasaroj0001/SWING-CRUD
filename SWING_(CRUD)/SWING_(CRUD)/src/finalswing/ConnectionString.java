/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalswing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Saroj
 */
public class ConnectionString {
    public static Connection connectme(){
   try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/curd","root","");
                return cn;
	    	}catch(ClassNotFoundException a){
	    		System.err.println("Failed to Load Driver");
	    	}catch (SQLException a){
	    		System.err.println("Unable to Connect");
	    	}   
   return null;
   }    
}
