package it.salone.business;

import java.util.List;
import it.salone.Enum.RicercaBy;
import it.salone.model.Appuntamento;
import jakarta.ejb.Local;

@Local
public interface AppuntamentoEjbLocal {
	
	public boolean inserisciAppuntamentoInArchivio(Appuntamento a);

	public List<Appuntamento> getAppuntamentiBy(RicercaBy tipoParametro, String parametro, String parametro2);

	public boolean modificaAppuntamentoInArchivio(Appuntamento a);

	public boolean eliminaAppuntamentoInArchivio(Appuntamento a);
	
	public void aggiornaStorico();
	
	public List<Appuntamento> getAppuntamentiStorico(String nome);

}
