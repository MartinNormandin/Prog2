
public class Vol {
	
	public static int nbVols = 0;
	
	private int num;
	private String dest;
	private Date depart;
	private int reservation;
	
		
	Vol(int num, String dest, Date depart, int reservation){
		this.num = num;
		this.dest = dest;
		this.depart = depart;
		this.reservation = reservation;
		++nbVols;
	}
	
	public int getNum() {
		return this.num;
	}
	
	public String getDest() {
		return this.dest;
	}
	
	public Date getDepart() {
		return this.depart;
	}
	
	public int getReservation() {
		return this.reservation;
	}
	
	public void setReservation(int reservation) {
		if (reservation > 0 && reservation <= 340) {
			this.reservation = reservation;
		}else {
			System.out.println("nombre de réservations invalide");
		}
	}
	
	public void setDepart(Date depart) {
		this.depart = depart;
		
	}
	
	public String toString() {
		if(this.dest.length() > 15) {
			return this.num+"\t"+this.dest+"\t"+this.depart+"\t"+this.reservation+"\n";
		}else {
			return this.num+"\t"+this.dest+"\t\t"+this.depart+"\t"+this.reservation+"\n";
		}
	}
	

}
