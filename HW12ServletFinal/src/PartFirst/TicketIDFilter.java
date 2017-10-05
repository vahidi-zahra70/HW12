package PartFirst;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.*;

/**
 * Servlet Filter implementation class flightNumber_filter
 */

public class TicketIDFilter implements Filter {

	/**
	 * Default constructor. 
	 */
	public TicketIDFilter() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		PrintWriter out=resp.getWriter();
		
		try{
			int ticketID=Integer.parseInt(req.getParameter("id"));
			System.out.println(ticketID);
			chain.doFilter(req, resp);
		}
		catch(NumberFormatException e){
			System.out.println("why");
			out.print("Please enter a number as the ticket ID!");
			RequestDispatcher rd=req.getRequestDispatcher("BuyT.html");
			rd.include(req, resp);

		}

	}
	public void destroy() {}





}
