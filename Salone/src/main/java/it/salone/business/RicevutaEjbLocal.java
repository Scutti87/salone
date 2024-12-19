package it.salone.business;

import java.util.List;

import it.salone.Enum.RicercaBy;
import it.salone.model.Ricevuta;
import jakarta.ejb.Local;

@Local
public interface RicevutaEjbLocal {
	
	public boolean inserisciRicevutaInArchivio(Ricevuta r);

	public List<Ricevuta> getRicevuteBy(RicercaBy tipoParametro, String parametro, String parametro2);

	public boolean eliminaRicevutaInArchivio(Ricevuta r);

	public boolean modificaRicevutaInArchivio(Ricevuta r);

}
