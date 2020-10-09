// First Import all  the required packagesimport javax.servlet.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class MyFirstServlet extends HttpServlet
{	
	 // JDBC driver name and database URL

    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://localhost:3306/test";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "admin";
public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
	res.setContentType("text/html");
	PrintWriter out = res.getWriter();
	/*out.println("<h1>Welcome to My Home page</h1>");
	out.println("<h2>Server output from GET method</h2>");
	out.println("<h3>Your Username: "+req.getParameter("username")+"</h3>");
	out.println("<h3>Your Password: "+req.getParameter("password")+"</h3>");*/
	String passwd = req.getParameter("password");
		Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/test", "root", "admin");
            System.out.println("<h4>Connected database successfully...</h4>");

            //STEP 4: Execute a query
            System.out.println("<h4>Creating table in given database...</h4>");
            //stmt = conn.createStatement();

            String sql = "SELECT PASSWORD FROM LOGIN_INFO WHERE USER_NAME=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, req.getParameter("username"));

            System.out.println("<h4>Connected to the database</h4>");
            ResultSet rs = pst.executeQuery();
            /*while (rs.next())
            {
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
            }*/
			String pss = null;
			while(rs.next()){
				pss=rs.getString(1);
			//out.println("<h1>"+pss+"</h1>");
			}
			if (passwd.equals(pss)) {
				//out.println("<h1>Login Success...<h1>");
				RequestDispatcher view =  getServletContext().getRequestDispatcher("/MyAccount");
				view.forward(req, res);
			}
			else{
				out.println("<h1>Login Failed...</h1>");
			}
            conn.close();
			
            System.out.println("<h4>Disconnected from database</h4>");
            System.out.println("<h4>Retried login info</h4>");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("<h4>Goodbye!</h4>");
	}
}