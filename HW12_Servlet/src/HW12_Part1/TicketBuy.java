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
 * Servlet implementation class TicketBuy
 */
@WebServlet("/TicketBuy")
public class TicketBuy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketBuy() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
		out.println("<head>");
		out.println("<title>Buy Ticket</title>");
		out.println("</head>");
		out.println("<body>");
		int id = 0;
		String name=request.getParameter("name");
		int flightNumber=Integer.parseInt(request.getParameter("flightNumber"));
		String source=request.getParameter("source");
		String destination=request.getParameter("destination");
		String flightDate=request.getParameter("flightDate");
		Ticket ticket=new Ticket( id, flightNumber, name,source,destination, flightDate);
		TicketManagerJDBC TT=new TicketManagerJDBC();
		try {
			id=TT.insertTicket(ticket);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("<br>Dear "+name+", your ticket seccessfully saved.");
		out.println("<br>Your ticket ID is: "+id);
		
		  
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
