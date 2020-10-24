

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SailorSearch")
public class SailorSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public SailorSearch() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/experiment_4", "root", "password");
			
			Statement stmt = con.createStatement();
			
			String query = "select * from sailor_53 where SID = " + request.getParameter("sailor_sid");
			
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next() == false) {
				out.println("<h2>ID not Found</h2><br>");
				out.println("<h3>enter valid SID</h3><br>");
				out.println("<h4><a href = 'sailor_search_sid.html'>ReEnter SID here</a><h4>");
				
			}
			
			else {
					String result = "ID: "+rs.getInt("SID") + ", Name: " + rs.getString("SNAME") + ", Rating: " + rs.getInt("RATING") + ", Age: " + rs.getInt("AGE");
					out.println("<h2>"+result+"</h2><br>");
			}
			
			rs.close();
			stmt.close();
			con.close();
			
		}
		catch (ClassNotFoundException e) {
			out.println("Class not found");
			e.printStackTrace();
		}
		catch(SQLException e) {
			out.println("SQL EXCEPTION");
			e.printStackTrace();
		}
		
	}

}
