import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class MyAccount extends HttpServlet
{
public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
	res.setContentType("text/html");
	PrintWriter out = res.getWriter();
	out.println("<h1>Welcome to your account "+ req.getParameter("username")+"</h1>");
	out.println("<h2>Server output from GET method</h2>");
	out.println("<h3>Your Username: "+req.getParameter("username")+"</h3>");
	out.println("<h3>Your Password: "+req.getParameter("password")+"</h3>");
	}
}