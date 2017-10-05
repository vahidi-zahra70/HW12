package PartFirst;

public class Ticket {
	private int id;
	private int flightNumber;
	private String name;
	private String source;
	private String destination;
	private String flightDate;
	
	
	public Ticket(int id,int flightNumber,String name,String source,
			String destination,String flightDate){
		
		this.id=id;
		this.flightNumber=flightNumber;
		this.name=name;
		this.source=source;
		this.destination=destination;
		this.flightDate=flightDate;
		
		
		
	}
	public Ticket(){
		
	}
	
	public int getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getFlightDate() {
		return flightDate;
	}
	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}
	

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSource() {
		return source;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Object getId() {
		// TODO Auto-generated method stub
		return id;
	}
	
	public String toString(){
		String a="Ticket[id= "+id+","+"flightNumber= "+flightNumber+","+"name= "+name
				+","+"source= "+source+","+"destination= "+destination+","+"flightDate= "+flightDate+"]";
		return a;
	}

}
