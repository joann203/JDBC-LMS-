import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Signin extends HttpServlet
{
  public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
    System.out.println("service called...");
    //to send the response to the browser
    PrintWriter out = res.getWriter();

    //set the contentType as html using res object
    res.setContentType("text/html");

    //capture the user data coming from html form
    String email = req.getParameter("Email");
    String pwd = req.getParameter("Password");

    //connect with dbs
    try{
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","Pandu20031101");

      String vsql = "select * from lmslogin where email=? and pwd=?";

      PreparedStatement pstmt = con.prepareStatement(vsql);
      pstmt.setString(1,email);
      pstmt.setString(2,pwd);
      ResultSet rs = pstmt.executeQuery();
        
if( rs.next() ){
        req.setAttribute("email",email);
        res.sendRedirect("studenthome.html");
        
      }else{
        out.println("Invalid username/password<br>");
        RequestDispatcher rd = req.getRequestDispatcher("for_got_password.html");
        rd.include(req,res);
      }
      out.println("</body>");
      out.println("<html>");
      con.close();
    }catch(Exception e){
      res.sendError(500,"Our application is failed due to:" + e);
    }
  }
}