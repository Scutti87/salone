package it.salone.dao;

import java.util.List;
import it.salone.Enum.RicercaBy;
import it.salone.model.Cliente;



public interface IClienteDao {

	public boolean inserisciClienteInArchivio(Cliente cliente);
	
	public List<Cliente> getClientiBy(RicercaBy tipoParametro, String parametro);
	
	public boolean eliminaClienteInArchivio(Cliente cliente);

	public boolean modificaClienteInArchivio(Cliente cliente);
	
//	public Cliente ricercaClienteByNomeCognome(String nome, String cognome);
//	
//	public Cliente ricercaClienteByNumeroTelefono(String numeroTelefono);
//	
//	public ArrayList<Cliente> stampaListaClienti();
	
}
