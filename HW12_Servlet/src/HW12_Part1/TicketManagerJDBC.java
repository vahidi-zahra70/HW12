package HW12_Part1;


import java.sql.*;
import java.util.ArrayList;


public class TicketManagerJDBC {

	static Connection conn;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = ProjectConnection.getConnection();

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}	

	//inserting a new Ticket
	public int insertTicket(Ticket CC) throws SQLException {

		String query="INSERT INTO ticket (flightnumber,name,source,destination,flightdate) VALUES (?,?,?,?,?)";
		PreparedStatement pstmt5=conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
		pstmt5.setInt(1, CC.getFlightNumber());
		pstmt5.setString(2, CC.getName());
		pstmt5.setString(3,CC.getSource());
		pstmt5.setString(4,CC.getDestination());
		pstmt5.setString(5,CC.getFlightDate());

		pstmt5.addBatch();
		int[] rs8=pstmt5.executeBatch();


		ResultSet rset=pstmt5.getGeneratedKeys();
		rset.next();
		long id = rset.getLong(1);
		System.out.println(id);

		if (pstmt5 != null)
			pstmt5.close();
		if (rset != null)
			rset.close();

		return (int) id;
	}

	//Show all tickets
	public ArrayList<Ticket> showAllTickets() throws SQLException{

		Statement stmt = conn.createStatement();
		ArrayList<Ticket> tickets=new ArrayList<Ticket>();
		ResultSet rs1 = stmt.executeQuery("SELECT * FROM ticket");
		while ((rs1.next())){
			int id=rs1.getInt(1);
			int fligthNumber=rs1.getInt(2);
			String name=rs1.getString(3);
			String source=rs1.getString(4);
			String destination=rs1.getString(5);
			String flightDate=rs1.getString(6);
			tickets.add(new Ticket(id,fligthNumber,name,source,
					destination,flightDate));

		}

		if (stmt != null)
			stmt.close();
		if (rs1 != null)
			rs1.close();

		return tickets;
	}
	//delete all tickets
	public void deleteAllTickets() throws SQLException{
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("delete FROM ticket");
		if (stmt != null)
			stmt.close();
	}

	//delete one ticket
	public boolean deleteOneTickets(int id) throws SQLException{
		boolean is_exist=false;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM ticket where id='"+id+"'");
		if(rs.next()){
			stmt.executeUpdate("delete FROM ticket where id='"+id+"'");
			is_exist=true;
		}

		if (stmt != null)
			stmt.close();
		if (rs != null)
			rs.close();

		return is_exist;
	}


	//update one ticket
	public boolean uodateOneTicket(Ticket t) throws SQLException{
		boolean is_exist=false;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM ticket where id='"+t.getId()+"'");
		
		if(rs.next()){
			stmt.executeUpdate("Update ticket set  flightnumber=' " +t.getFlightNumber()+" ', "
					+ "name='"+t.getName()+" ',source='"+t.getSource()+"',destination='"+t.getDestination()+"',flightdate='"+t.getFlightDate()+"' where id=' " + t.getId() + " '");
			is_exist=true;
		}

		if (stmt != null)
			stmt.close();
		if (rs != null)
			rs.close();

		return is_exist;
	}


	//show one ticket
	public Ticket showOneTicket(int id) throws SQLException{

		Statement stmt = conn.createStatement();
		ResultSet rs1 = stmt.executeQuery("SELECT * FROM ticket where id='"+id+"'");
		if(rs1.next()){

			int fligthNumber=rs1.getInt(2);
			String name=rs1.getString(3);
			String source=rs1.getString(4);
			String destination=rs1.getString(5);
			String flightDate=rs1.getString(6);
			Ticket ticket=new Ticket(id,fligthNumber,name,source,
					destination,flightDate);
			if (stmt != null)
				stmt.close();
			if (rs1 != null)
				rs1.close();

			return ticket;

		}

		if (stmt != null)
			stmt.close();
		if (rs1 != null)
			rs1.close();

		return null;
	}
}
