package PartFirst;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class ChangeTicket
 */

public class ChangeTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeTicket() {
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
		String name=request.getParameter("name");
		int flightNumber=Integer.parseInt(request.getParameter("flightNumber"));
		String source=request.getParameter("source");
		String destination=request.getParameter("destination");
		String flightDate=request.getParameter("flightDate");
		Ticket ticket=new Ticket( id, flightNumber, name,source,destination, flightDate);
		
		boolean is_exist=false;
		TicketManagerJDBC TT=new TicketManagerJDBC();
		try {
			is_exist=TT.uodateOneTicket(ticket);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(is_exist){
		out.println("<br>The ticket with the ID  "+id+" changed successfuly");
		}
		else{
			out.println("<br>The ticket that you want to change does not exist.");	
		}
		
		out.println("</body>");
		out.println("</html>");
		out.close(); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
