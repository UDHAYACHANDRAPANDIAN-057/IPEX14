/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author jkuma
 */
@WebService(serviceName = "web")
public class web {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "display")
    public String display(@WebParam(name = "productid") int productid) {
        //TODO write your implementation code here:
        StringBuilder result = new StringBuilder();
          try{
            Connection c=DriverManager.getConnection("jdbc:derby://localhost:1527/products");
            Statement st=c.createStatement();
            ResultSet rs=st.executeQuery("Select name,price,description from PRODUCT where pid="+productid+"");
            while(rs.next())
            {
                    String name = rs.getString("name");
                    int price=rs.getInt("price");
                    String desc=rs.getString("description");
                    
                    result.append(name).append(" ").append(String.valueOf(price)).append(" ").append(desc);
               
            }
        }
        catch(SQLException e)
        {
            
        }
        
        
        return result.toString();
    }
       
}
