package it.salone.Enum;

public enum Servizi {

	PIEGA(15), TAGLIO(25), COLORERIT(25), COLORECOMPL(35),
	TONER(15), MECHES(100), BAYALAGE(100), SHATUSH(100), PERMAN(40), 
	LISCIANTE(150), TRATT(15), MASCHERA(10), CREMALAV(5), TAGLIOU(15);

	private double prezzo;

	private Servizi(double prezzo) {
		this.prezzo = prezzo;
	}

	public double getPrezzo() {
		return prezzo;
	}
		
}
