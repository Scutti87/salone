package it.salone.business;

import java.util.List;

import it.salone.Enum.RicercaBy;
import it.salone.model.Cliente;
import jakarta.ejb.Local;

@Local
public interface ClienteEjbLocal {

	public boolean inserisciClienteInArchivio(Cliente cliente);

	public List<Cliente> getClientiBy(RicercaBy tipoParametro, String parametro);

	public boolean modificaClienteInArchivio(Cliente cliente);

	public boolean eliminaClienteInArchivio(Cliente cliente);
}
