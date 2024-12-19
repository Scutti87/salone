package it.salone.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import it.salone.Enum.RicercaBy;
import it.salone.model.Ricevuta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class RicevutaDaoImpl implements IRicevutaDao {

	private static List<Ricevuta> listaRicevute;
	private EntityManager em;

	public RicevutaDaoImpl(EntityManager em) {
		super();
		this.em = em;
	}

	static {
		listaRicevute = new ArrayList<Ricevuta>();
	}

	private boolean verificaDataCliente(Ricevuta r) {

		listaRicevute.clear();
		TypedQuery<Ricevuta> lista = em.createQuery("SELECT r FROM Ricevuta r", Ricevuta.class);
		listaRicevute.addAll(lista.getResultList());

		return listaRicevute.stream()
				.anyMatch(app -> app.getData().equals(r.getData()) && app.getCliente().equals(r.getCliente()));
	}

	@Override
	public boolean inserisciRicevutaInArchivio(Ricevuta r) {
		if (verificaDataCliente(r)) {
			System.out.println("Ricevuta già presente in archivio");
			return false;
		}
		try {
			em.persist(r);
			System.out.println("Inserimento avvenuto con successo");
			return true;
		} catch (Exception e) {
			System.out.println("Inserimento fallito!!!");
			return false;
		}
	}

	@Override
	public List<Ricevuta> getRicevuteBy(RicercaBy tipoParametro, String parametro, String parametro2) {

		List<Ricevuta> lista = new ArrayList<Ricevuta>();
		Ricevuta r = null;

		if (tipoParametro.equals(RicercaBy.ID)) {
			r = em.find(Ricevuta.class, Integer.parseInt(parametro));
			lista.add(r);
		} else if (tipoParametro.equals(RicercaBy.NOME)) {
			TypedQuery<Ricevuta> l = em.createQuery(
					"SELECT r FROM Ricevuta r WHERE r.cliente.nome LIKE :nome ORDER BY r.data", Ricevuta.class);
			l.setParameter("nome", parametro + "%");
			lista = l.getResultList();
		} else if (tipoParametro.equals(RicercaBy.DATA)) {
			TypedQuery<Ricevuta> l = em.createQuery("SELECT r FROM Ricevuta r WHERE r.data = :data ORDER BY r.data",
					Ricevuta.class);
			l.setParameter("data", Date.valueOf(parametro));
			lista = l.getResultList();
		} else if (tipoParametro.equals(RicercaBy.IDCLIENTE)) {
			TypedQuery<Ricevuta> l = em.createQuery(
					"SELECT r FROM Ricevuta r WHERE r.cliente.id = :idCliente ORDER BY r.data", Ricevuta.class);
			l.setParameter("idCliente", Integer.parseInt(parametro2));
			lista = l.getResultList();
		} else if (tipoParametro.equals(RicercaBy.DATACLIENTE)) {
			TypedQuery<Ricevuta> l = em.createQuery(
					"SELECT r FROM Ricevuta r WHERE r.data = :data AND r.cliente.id = :idCliente ORDER BY r.data",
					Ricevuta.class);
			l.setParameter("data", Date.valueOf(parametro));
			l.setParameter("idCliente", Integer.parseInt(parametro2));
			lista = l.getResultList();
		} else if (tipoParametro.equals(RicercaBy.ALL)) {
			TypedQuery<Ricevuta> l = em.createQuery("SELECT r FROM Ricevuta r ORDER BY r.data", Ricevuta.class);
			lista = l.getResultList();
			if (lista.isEmpty()) {
				lista.add(new Ricevuta());
			}
		}
		return lista;
	}

	@Override
	public boolean eliminaRicevutaInArchivio(Ricevuta r) {
		try {
			Ricevuta ric = em.find(Ricevuta.class, r.getId());
			em.remove(ric);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean modificaRicevutaInArchivio(Ricevuta r) {
		if (!verificaDataCliente(r)) {
			System.out.println("Ricevuta non presente in archivio");
			return false;
		}
		try {
			em.merge(r);
			System.out.println("Modifica avvenuta con successo");
			return true;
		} catch (Exception e) {
			System.out.println("Modifica fallita!!!");
			return false;
		}
	}

}
