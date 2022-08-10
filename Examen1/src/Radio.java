public class Radio {
	
	// attributs de classe
	
	// attributs d'instance
	private String marque;
	private String modele;
	private int cd;
	private int cassette;
	private int mp3;
	private double prix;
	
	// constructeurs
	Radio() {	
	}
	
	Radio(String marque, String modele, int cd, int cassette, int mp3, 
		  double prix) {
		this.marque= marque;
		this.modele= modele;
		this.setCD(cd);
		this.setCassette(cassette);
		this.setMP3(mp3);
		this.setPrix(prix);
	}
	
	// accesseurs
	public String getMarque() {
		return this.marque;
	}
	
	public String getModele() {
		return this.modele;
	}
	
	public int getCD() {
		return this.cd;
	}
	
	public int getCassette() {
		return this.cassette;
	}
	
	public int getMP3() {
		return this.mp3;
	}
	
	public double getPrix() {
		return this.prix;
	}
	
	// mutateurs
	public void setMarque(String marque) {
		this.marque= marque;
	}
	
	public void setModele(String modele) {
		this.modele= modele;
	}
	
	public void setCD(int cd) {
		if (cd == 0 || cd == 1) {
			this.cd= cd;
		}else {
			System.out.println("code pour lecteur cd invalide");
		}
	}
	
	public void setCassette(int cassette) {
		if (cassette == 0 || cassette == 1) {
			this.cassette= cassette;
		}else {
			System.out.println("code pour lecteur cassette invalide");
		}
	}
	
	public void setMP3(int mp3) {
		if (mp3 == 0 || mp3 == 1) {
			this.mp3= mp3;
		}else {
			System.out.println("code pour lecteur mp3 invalide");
		}
	}
	
	public void setPrix(double prix) {
		if (prix > 0) {
			this.prix= prix;
		}else {
			System.out.println("prix invalide");
		}
	}
	
	// autres méthodes
	private String getLecteurString(int media) {
		if (media == 1) {
			return "oui";
		}else {
			return "non";
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		Radio autreRadio = (Radio) obj;
		if ( (this.marque).equals(autreRadio.marque) &&
			 (this.modele).equals(autreRadio.modele)) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return this.marque+"\t"+this.modele+"\t"+getLecteurString(this.cd)+
			   "\t"+getLecteurString(this.cassette)+"\t"+
			   getLecteurString(this.mp3)+"\t"+this.prix+"\n";
	}
	
	
}
