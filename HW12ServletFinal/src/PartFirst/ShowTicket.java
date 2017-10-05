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
 * Servlet implementation class ShowTicket
 */

public class ShowTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTicket() {
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
		Ticket ticket=new Ticket();
		TicketManagerJDBC TT=new TicketManagerJDBC();
		try {
			ticket=TT.showOneTicket(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(ticket!=null){
			out.println("<br>The information of your ticket is a below:");
			out.println("<br>Ticket ID: "+ticket.getId());
			out.println("<br>Ticket Name: "+ticket.getName());
			out.println("<br>Flight Number: "+ticket.getFlightNumber());
			out.println("<br>From: "+ticket.getSource());
			out.println("<br>To: "+ticket.getDestination());
			out.println("<br>Depart: "+ticket.getFlightDate());
		}
		else{
			out.println("<br>Unfortunately the ticket ID that you inserted does not exist.");
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
