package it.salone.dao;

import java.util.ArrayList;
import java.util.List;
import it.salone.Enum.RicercaBy;
import it.salone.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ClienteDaoImpl implements IClienteDao {

	private static List<Cliente> listaClienti;
	private EntityManager em;

	static {
		listaClienti = new ArrayList<Cliente>();
	}

	public ClienteDaoImpl(EntityManager em) {
		this.em = em;
	}

	public static List<Cliente> getListaClienti() {
		return listaClienti;
	}

	private boolean verificaNomeCognome(Cliente unCliente) {

		listaClienti.clear();
		TypedQuery<Cliente> lista = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
		listaClienti.addAll(lista.getResultList());
		for (int i = 0; i < listaClienti.size(); i++) {
			if (listaClienti.get(i).getId() == unCliente.getId()) {
				listaClienti.remove(i);
			}
		}

		return listaClienti.stream().anyMatch(cli -> cli.getNome().equalsIgnoreCase(unCliente.getNome())
				& cli.getCognome().equalsIgnoreCase(unCliente.getCognome()));
	}

	private boolean verificaNumeroTelefono(Cliente unCliente) {

		listaClienti.clear();
		TypedQuery<Cliente> lista = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
		listaClienti.addAll(lista.getResultList());
		Cliente c = em.find(Cliente.class, unCliente.getId());
		if (c != null) {
			if (c.getNumeroTelefono().equalsIgnoreCase(unCliente.getNumeroTelefono())) {
				return false;
			}
		}
		return listaClienti.stream()
				.anyMatch(cli -> cli.getNumeroTelefono().equalsIgnoreCase(unCliente.getNumeroTelefono()));
	}

	@Override
	public boolean inserisciClienteInArchivio(Cliente unCliente) {

		if (verificaNomeCognome(unCliente)) {
			System.out.println("Cliente già presente in archivio");
			return false;
		}
		if (!unCliente.getNumeroTelefono().equals("0000") && verificaNumeroTelefono(unCliente)) {
			System.out.println("Numero di telefono già presente in archivio");
			return false;
		}
		try {
			em.persist(unCliente);
			System.out.println("Inserimento avvenuto con successo");
			return true;
		} catch (Exception e) {
			System.out.println("Inserimento fallito!!!");
			return false;
		}
	}

	@Override
	public List<Cliente> getClientiBy(RicercaBy tipoParametro, String parametro) {

		List<Cliente> lista = new ArrayList<Cliente>();
		Cliente c = null;
		if (tipoParametro.equals(RicercaBy.ID)) {
			c = em.find(Cliente.class, Integer.parseInt(parametro));
			lista.add(c);
		} else if (tipoParametro.equals(RicercaBy.NOME)) {
			TypedQuery<Cliente> l = em.createQuery("SELECT c FROM Cliente c WHERE c.nome LIKE :nome", Cliente.class);
			l.setParameter("nome", parametro + "%");
			lista = l.getResultList();
		} else if (tipoParametro.equals(RicercaBy.COGNOME)) {
			TypedQuery<Cliente> l = em.createQuery("SELECT c FROM Cliente c WHERE c.cognome LIKE :cognome",
					Cliente.class);
			l.setParameter("cognome", parametro + "%");
			lista = l.getResultList();
		} else if (tipoParametro.equals(RicercaBy.NUMEROTELEFONO)) {
			TypedQuery<Cliente> l = em
					.createQuery("SELECT c FROM Cliente c WHERE c.numeroTelefono LIKE :numeroTelefono", Cliente.class);
			l.setParameter("numeroTelefono", parametro);
			lista = l.getResultList();
		} else {
			TypedQuery<Cliente> l = em.createQuery("SELECT c FROM Cliente c ORDER BY c.nome", Cliente.class);
			lista = l.getResultList();
		}

		return lista;
	}

	@Override
	public boolean modificaClienteInArchivio(Cliente cliente) {

		if (verificaNomeCognome(cliente)) {
			System.out.println("Cliente già presente in archivio");
			return false;
		}
		if (!cliente.getNumeroTelefono().equals("0000") && verificaNumeroTelefono(cliente)) {
			System.out.println("Numero di telefono già presente in archivio");
			return false;
		}
		try {
			em.merge(cliente);
			System.out.println("Modifica avvenuta con successo");
			return true;
		} catch (Exception e) {
			System.out.println("Modifica fallita!!!");
			return false;
		}
	}

	@Override
	public boolean eliminaClienteInArchivio(Cliente cliente) {

		try {
			Cliente c = em.find(Cliente.class, cliente.getId());
			if (c != null) {
				em.remove(c);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
