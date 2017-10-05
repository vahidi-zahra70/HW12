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

public class flightNumberfilter implements Filter {

	/**
	 * Default constructor. 
	 */
	public flightNumberfilter() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		PrintWriter out=resp.getWriter();
		boolean is_date=false;
		try{
			int flightNumber=Integer.parseInt(req.getParameter("flightNumber"));
			String flightDate=req.getParameter("flightDate");
			is_date=Pattern.matches("\\d\\d\\d\\d/\\d\\d/\\d\\d",flightDate);
			if(flightNumber>=100 && flightNumber<1000  && is_date){
				chain.doFilter(req, resp);//sends request to next resource
			}
			else{
				out.print("Your flight Number or depart time is incorrect!");
				RequestDispatcher rd=req.getRequestDispatcher("BuyT.html");
				rd.include(req, resp);
			}
		}
		catch(NumberFormatException e){
			out.print("Your flight Number or depart time is incorrect!");
			RequestDispatcher rd=req.getRequestDispatcher("BuyT.html");
			rd.include(req, resp);

		}

	}
	public void destroy() {}





}
