package finalswing;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import sun.java2d.d3d.D3DRenderQueue;

public class CustomerDAO {
    
    public void insertMember(Customer customer){
        Connection cn = ConnectionString.connectme();
        PreparedStatement pst;
        ResultSet rs;
        try{
            String query1="INSERT INTO form_data (Visit,FirstName,Address,Age) values(?,?,?,?)";
                    pst=cn.prepareStatement(query1);
                    pst.setInt(1,customer.getCustomer_Visit());            
                    pst.setString(2,customer.getCustomer_firstname());
                    pst.setString(3,customer.getCustomer_Address());
                    pst.setInt(4,customer.getCustomer_age());
                    pst.execute();
                    cn.close();
                    MsgBOx.joptionMessage_Throw("! Details added Successfully !");
               }catch(SQLException | HeadlessException e){
                   System.out.println("Query wrong");
               }
    }
    
    public ArrayList<Customer> getMemberALLInfo(){
        ArrayList<Customer> userlist = new ArrayList<Customer>();
        Connection conn=ConnectionString.connectme();
        PreparedStatement pst;
        ResultSet rs;
        try {
            String qry = "select * from form_data";    
            pst = conn.prepareStatement(qry);
            rs = pst.executeQuery();
            while(rs.next())
            {
                Customer cust_obj = new Customer();
                cust_obj.setCustomer_pid(rs.getInt("pid"));
                cust_obj.setCustomer_firstname(rs.getString("FirstName"));
                cust_obj.setCustomer_Address(rs.getString("Address"));
                cust_obj.setCustomer_age(rs.getInt("Age"));
                cust_obj.setCustomer_Visit(rs.getInt("Visit"));
                userlist.add(cust_obj);
            }
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userlist;
    }
    
    public void fetchpid(Customer customer){
        //System.out.println("fetch run vo");
        Connection cn = ConnectionString.connectme();
        PreparedStatement pst;
        ResultSet rs;
        int temp=-1;
        try{
            String query1="SELECT pid  FROM form_data WHERE Visit=? AND FirstName LIKE ? AND Address LIKE ? AND Age=?";
                    pst=cn.prepareStatement(query1);  pst.setInt(1,customer.getCustomer_Visit());            
                    pst.setString(2,customer.getCustomer_firstname());  pst.setString(3,customer.getCustomer_Address());
                    pst.setInt(4,customer.getCustomer_age()); 
                    rs = pst.executeQuery();
            while(rs.next())
            {
                temp=rs.getInt("pid");
                customer.setCustomer_pid(temp);
                //return (cus_pid);
            }
            rs.close(); cn.close();
            //System.out.println("fetch sakyo "+cl.getpid());
            //System.out.println("PID Generated from fetchpid " +cus_pid);
               }catch(SQLException | HeadlessException e){
                   System.out.println("Query wrong");
               }
        //return temp;
    }
    
    public void updateCustomer(Customer customer){
      try{
            Connection con=ConnectionString.connectme();
            String qry="update form_data set Visit=?,FirstName=?,Address=?,Age=? where pid=?";
            PreparedStatement pst=con.prepareStatement(qry);
                    pst.setInt(1,customer.getCustomer_Visit());            
                    pst.setString(2,customer.getCustomer_firstname());
                    pst.setString(3,customer.getCustomer_Address());
                    pst.setInt(4,customer.getCustomer_age());
                    pst.setInt(5,customer.getCustomer_pid());
                    pst.executeUpdate();
                    con.close();
                    MsgBOx.joptionMessage_Throw("! Update Succesfull !");
        }catch(Exception e){
             System.out.println("Query ma error");
        }
        
    }
            
    public void deleteCustomer(Customer customer){
         try{
            Connection con=ConnectionString.connectme();
            String qry="DELETE FROM form_data WHERE pid=?";
            PreparedStatement pst=con.prepareStatement(qry);
                    pst.setInt(1,customer.getCustomer_pid()); 
                    pst.execute();
                    con.close();
                    MsgBOx.joptionMessage_Throw("! Delete Succesfull !");
        }catch(Exception e){
             System.out.println("Query ma error");
        }
    }
    
     public ArrayList<Customer> getsearchMemberInfo(Customer customer){
//         System.out.println(customer.getCustomer_Visit());
//         System.out.println( customer.getCustomer_firstname());
//         System.out.println(customer.getCustomer_Address() );
//         System.out.println(customer.getCustomer_age() );
        
        ArrayList<Customer> userlist1 = new ArrayList<Customer>();
        Connection conn=ConnectionString.connectme();
        ResultSet rs;
        try {
            PreparedStatement pst = null;
            String qry = "SELECT distinct pid,FirstName,Address,Age,Visit FROM form_data WHERE Visit=? OR FirstName LIKE? OR Address LIKE? OR Age=?";    
            pst = conn.prepareStatement(qry);
            pst.setInt(1,customer.getCustomer_Visit());            
            pst.setString(2,customer.getCustomer_firstname());
            pst.setString(3,customer.getCustomer_Address());
            pst.setInt(4,customer.getCustomer_age());           
            rs = pst.executeQuery();
             Customer objCustomer;
                while(rs.next())
                    {  
                        objCustomer= new Customer();
                        objCustomer.setCustomer_pid(rs.getInt("pid"));
                        objCustomer.setCustomer_firstname(rs.getString("FirstName"));
                        objCustomer.setCustomer_Address(rs.getString("Address"));
                        objCustomer.setCustomer_age(rs.getInt("Age"));
                        objCustomer.setCustomer_Visit(rs.getInt("Visit"));
                        userlist1.add(objCustomer);
                    }
            rs.close();
            conn.close();
        } catch (SQLException x) {
            System.out.println("sql error");
            //Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userlist1; 
    }
    
    
    
    
    
}