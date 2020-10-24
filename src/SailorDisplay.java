

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


@WebServlet("/SailorDisplay")
public class SailorDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
    public SailorDisplay() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/experiment_4", "root", "password");
			Statement stmt = con.createStatement();
			
			String query = "SELECT * FROM SAILOR_53;";
			
			stmt.execute(query);
			
			ResultSet rs = stmt.getResultSet();
			
			while (rs.next()) {
				String result = "ID: "+rs.getInt("SID") + ", Name: " + rs.getString("SNAME") + ", Rating: " + rs.getInt("RATING") + ", Age: " + rs.getInt("AGE");
				out.println(result+"<br>");
			}
			
			rs.close();
			stmt.close();
			con.close();

		}
		catch (ClassNotFoundException e) {
			out.println("class not found");
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
