package it.salone.business;

import java.util.List;
import it.salone.Enum.RicercaBy;
import it.salone.dao.ClienteDaoImpl;
import it.salone.dao.IClienteDao;
import it.salone.model.Cliente;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateful
@LocalBean
public class ClienteEjb implements ClienteEjbLocal {

	@PersistenceContext(name = "Salone")
	EntityManager em;

	private IClienteDao clienteDao;

	public ClienteEjb() {
	}

	@PostConstruct
	public void setEntityManager() {
		clienteDao = new ClienteDaoImpl(em);
	}

	@Override
	public boolean inserisciClienteInArchivio(Cliente cliente) {

		boolean inserito = clienteDao.inserisciClienteInArchivio(cliente);
		return inserito;
	}

	public int getMaxId() {
	    String query = "SELECT MAX(c.id) FROM Cliente c";
	    Integer maxId = (Integer) em.createQuery(query).getSingleResult();
	    return (maxId != null) ? maxId : 0;
	}
	
	@Override
	public List<Cliente> getClientiBy(RicercaBy tipoParametro, String parametro) {

		return clienteDao.getClientiBy(tipoParametro, parametro);
	}

	@Override
	public boolean modificaClienteInArchivio(Cliente cliente) {

		boolean modificato = clienteDao.modificaClienteInArchivio(cliente);
		return modificato;
	}

	@Override
	public boolean eliminaClienteInArchivio(Cliente cliente) {
		
		boolean eliminato = clienteDao.eliminaClienteInArchivio(cliente);
		return eliminato;
	}


}
