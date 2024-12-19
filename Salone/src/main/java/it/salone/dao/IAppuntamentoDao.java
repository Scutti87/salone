package it.salone.dao;

import java.util.List;
import it.salone.Enum.RicercaBy;
import it.salone.model.Appuntamento;

public interface IAppuntamentoDao {

	public boolean inserisciAppuntamentoInArchivio(Appuntamento a);

	public List<Appuntamento> getAppuntamentiBy(RicercaBy tipoParametro, String parametro, String parametro2);

	public boolean eliminaAppuntamentoInArchivio(Appuntamento a);

	public boolean modificaAppuntamentoInArchivio(Appuntamento a);
	
	public void aggiornaStorico();
	
	public List<Appuntamento> getAppuntamentiStorico(String nome);

}
