package Part3;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;






public class TicketManager_hiber {

	Configuration cfg=null;
	SessionFactory factory=null;

	{
		cfg= new Configuration(); 
		cfg.configure("hibernate.cfg.xml");
		factory=cfg.buildSessionFactory();
	}



	//inserting a new Ticket
	public void insertTicket(Ticket CC){

		Session session = factory.openSession();
		Transaction tx = null;

		try{
			tx = session.beginTransaction();
			session.save(CC); 
			System.out.println("successfully saved"); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}

	}

	//Show all tickets
	public ArrayList<Ticket> showAllTickets() throws SQLException{

		Session session = factory.openSession();
		Transaction tx = null;
		ArrayList<Ticket> tickets=new ArrayList<Ticket>();
		try{
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(Ticket.class);
			List tickets2 = criteria.list();
			Iterator itr = tickets2.iterator();
			while (itr.hasNext()) {
				Ticket emp = (Ticket) itr.next();
				tickets.add(emp);
			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return tickets;
	}

	//delete all tickets
	public void deleteAllTickets() throws SQLException{

		Session session = factory.openSession();
		Transaction tx = null;

		try{
			tx = session.beginTransaction();
			//for deleting all table you should write the java class name here not the table name
			//if you don't do that you will give map error
			String stringQuery = "DELETE FROM Ticket";
			Query query = session.createQuery(stringQuery);
			query.executeUpdate();
			System.out.println("successfully deleted all tickets"); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}

	}

	//delete one ticket
	public boolean deleteOneTickets(int id) throws SQLException{

		boolean is_exist=false;
		Session session = factory.openSession();
		Transaction tx = null;
		Ticket ticket;
		try{
			tx = session.beginTransaction();
			ticket=new Ticket();
			ticket=	session.get(Ticket.class, id);

			if(	ticket!=null){

				session.delete(ticket); 
				is_exist=true;
				System.out.println("successfully deleted");
			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return is_exist;
	}

	//update one ticket
	public boolean uodateOneTicket(Ticket t) throws SQLException{

		boolean is_exist=false;
		Session session = factory.openSession();
		Transaction tx = null;
		Ticket ticket;
		try{
			tx = session.beginTransaction();
			ticket=new Ticket();
			int id=t.getId();
			ticket=	session.get(Ticket.class, id);

			if(	ticket!=null){

				ticket.setFlightNumber(t.getFlightNumber());
				ticket.setName(t.getName());
				ticket.setSource(t.getSource());
				ticket.setDestination(t.getDestination());
				ticket.setFlightDate(t.getFlightDate());
				session.update(ticket);
				is_exist=true;
				System.out.println("successfully update"); 

			}
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return is_exist;
	}


	//show one ticket
	public Ticket showOneTicket(int id) throws SQLException{

		Session session = factory.openSession();
		Transaction tx = null;
		Ticket ticket=null;
		try{
			tx = session.beginTransaction();
			ticket=new Ticket();
			ticket=session.get(Ticket.class, id); 
			System.out.println("successfully show"); 
			tx.commit();
		}catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		}finally {
			session.close(); 
		}
		return ticket;
	}

}
