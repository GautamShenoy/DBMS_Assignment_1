

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
//import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SailorInsert")
public class SailorInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SailorInsert() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/experiment_4", "root", "password");

			java.sql.Statement stmt = con.createStatement();
			
			String SID = request.getParameter("sailor_sid");
			String SName = request.getParameter("sailor_name");
			String Rating = request.getParameter("sailor_rating");
			String Age = request.getParameter("sailor_age");
			
			PreparedStatement pstm = con.prepareStatement("insert into sailor_53 values(?,?,?,?)");
			pstm.setInt(1, Integer.parseInt(SID));
            pstm.setString(2, SName);
            pstm.setInt(3, Integer.parseInt(Rating));
            pstm.setInt(4, Integer.parseInt(Age));
			
            pstm.executeUpdate();
            
			out.println("<h1>Data Entered Successfully</h1>");
			
			stmt.close();
			con.close();
			
		}
		catch (ClassNotFoundException e) {
			out.println("class not found");
			e.printStackTrace();
		}
		catch (SQLIntegrityConstraintViolationException e) {
			out.println("<h2>Sailor with that I'd Already exist in our database</h2><br>");
			out.println("<h2>Please enter an unique Sailor I'd</h2><br>");
			out.println("<h3><a href = 'sailor_input.html'>Re-Enter Entry here</a></h3>");
		}
		catch (SQLException e) {
			if (e.getMessage().contains("primary key constraint")) {
				out.println("Entry Already Exists");
			}
			else {
				out.println("Couldnt insert data");
			}
			e.printStackTrace();
		}
		
		
		
	}
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("Do Get Method");
	}

}
