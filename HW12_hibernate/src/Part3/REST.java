package Part3;



import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("/tickets")  
public class REST{


	//insert a new ticket
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTicket(Ticket t) throws SQLException {

		TicketManager_hiber TT=new TicketManager_hiber();
		TT.insertTicket(t);
		String result = "The ticket inserted successfully: ";
		System.out.println(result);
		return Response.status(200).entity(t).build();
	}

	//show all tickets
	@GET
	@Produces(MediaType.APPLICATION_JSON)  
	public  ArrayList<Ticket>   showAllTickets() throws SQLException{

		TicketManager_hiber TT=new TicketManager_hiber();
		return TT.showAllTickets();
	}

	//Delete all tickets
	@DELETE
	public Response deleteAllTickets() throws SQLException{
		TicketManager_hiber TT=new TicketManager_hiber();
		TT.deleteAllTickets();
		String result = "All tickets deleted successfully: ";
		System.out.println(result);
		return Response.status(200).entity(result).build();

	}

	//Delete one ticket
	@DELETE
	@Path("/{id}")
	public Response deleteOneTickets(@PathParam("id") String id) throws SQLException{
		TicketManager_hiber TT=new TicketManager_hiber();
		String result;
		if(TT.deleteOneTickets(Integer.parseInt(id))){
			result = "The ticket with id "+id+" deleted successfully.";
			System.out.println(result);
		}
		else{
			result = "The ticket with id "+id+" which you want to delete does not exist.";
			System.out.println(result);
		}
		return Response.status(200).entity(result).build();
	}

	//show one ticket
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)  
	public Ticket  selectTicket(@PathParam("id") String id) throws NumberFormatException, SQLException{

		TicketManager_hiber TT=new TicketManager_hiber();
		return  TT.showOneTicket(Integer.parseInt(id));
	}

	//update one ticket
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response  UpdateTicket(@PathParam("id") String id,Ticket t) throws NumberFormatException, SQLException{
		t.setId(Integer.parseInt(id));
		TicketManager_hiber TT=new TicketManager_hiber();
		String result;
		if(TT.uodateOneTicket(t)){
			result = "The ticket with id "+id+" updated successfully.";
			System.out.println(result);
		}
		else{
			result = "The ticket with id "+id+" which you want to update does'nt exist.";
			System.out.println(result);
		}
		return Response.status(200).entity(result).build();	
	}



}

