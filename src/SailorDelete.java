

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/SailorDelete")
public class SailorDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SailorDelete() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/experiment_4", "root", "password");
			
			String SID = request.getParameter("sailor_sid");
			
			PreparedStatement pstm = con.prepareStatement("delete from sailor_53 where sid = ?");
			pstm.setInt(1, Integer.parseInt(SID));
			pstm.executeUpdate();
			
			int count = pstm.getUpdateCount();
			if (count == 0) {
				out.println("<h2>Invalid SID</h2><br>");
				out.println("<h3>Please enter valid SID</h3><br>");
				out.println("<h4><a href = 'sailor_delete_sid.html'>Re-Enetr SID here</a></h4>");
			}
			else {
				out.println("<h2>Sailor Data Deleted Successfully</h2>");
			}
			
			con.close();
			
			
		}
		catch (ClassNotFoundException e) {
			out.println("Class not found");
			e.printStackTrace();
		}
		catch (SQLException e) {
			out.println("SQL Exception");
			e.printStackTrace();
		}
	}

}
