import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.JOptionPane;

import java.sql.*;

public class Signup extends HttpServlet
{
  public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
    String uname = req.getParameter("Name");
    String email=req.getParameter("Email");
    String pwd = req.getParameter("Password");

    //connect with dbs
    try{
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Pandu20031101");
      
      
      PreparedStatement pst = con.prepareStatement("insert into lmslogin values(?,?,?)");
          pst.setString(1,uname);
          pst.setString(2, email);
          pst.setString(3,pwd);
          int i = pst.executeUpdate();
          //Check if values are saved successfully or not
          System.out.println("done");
          if(i!=0){
            res.sendRedirect("for_got_password.html");
          }
          else{
            System.out.println("failed to insert the data");
          }
        }
      catch(Exception e){
      res.sendError(500,"Our application is failed due to:" + e);
      }

      
  
    }
  }