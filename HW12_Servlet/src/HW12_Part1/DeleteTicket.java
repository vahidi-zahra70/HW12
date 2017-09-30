package HW12_Part1;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowTicket
 */
@WebServlet("/DeleteTicket")
public class DeleteTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTicket() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		out.println("<head>");
		out.println("<title>Buy Ticket</title>");
		out.println("</head>");
		out.println("<body>");
		int id=Integer.parseInt(request.getParameter("id"));
		boolean is_exist=false;
		TicketManagerJDBC TT=new TicketManagerJDBC();
		try {
			is_exist=TT.deleteOneTickets(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(is_exist){
		out.println("<br>The ticket with the ID  "+id+"canceled successfuly");
		}
		else{
			out.println("<br>The ticket ID that you want to cancel does not exist.");	
		}
		
		  
		out.close();  
		
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
