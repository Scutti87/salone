package it.salone.dao;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import it.salone.Enum.RicercaBy;
import it.salone.Enum.Servizi;
import it.salone.model.Appuntamento;
import it.salone.model.AppuntamentoStorico;
import it.salone.model.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class AppuntamentoDaoImpl implements IAppuntamentoDao {

	private static List<Appuntamento> listaAppuntamenti;
	private EntityManager em;

	static {
		listaAppuntamenti = new ArrayList<Appuntamento>();
	}

	public AppuntamentoDaoImpl(EntityManager em) {
		this.em = em;
	}

	private boolean verificaDataCliente(Appuntamento a) {

		listaAppuntamenti.clear();
		TypedQuery<Appuntamento> lista = em.createQuery("SELECT a FROM Appuntamento a", Appuntamento.class);
		listaAppuntamenti.addAll(lista.getResultList());

		return listaAppuntamenti.stream()
				.anyMatch(app -> app.getData().equals(a.getData()) && app.getCliente().equals(a.getCliente()));
	}

	@Override
	public boolean inserisciAppuntamentoInArchivio(Appuntamento a) {

		if (verificaDataCliente(a)) {
			System.out.println("Appuntamento già presente in archivio");
			return false;
		}
		try {
			em.persist(a);
			System.out.println("Inserimento avvenuto con successo");
			return true;
		} catch (Exception e) {
			System.out.println("Inserimento fallito!!!");
			return false;
		}
	}

	@Override
	public List<Appuntamento> getAppuntamentiBy(RicercaBy tipoParametro, String parametro, String parametro2) {

		List<Appuntamento> lista = new ArrayList<Appuntamento>();
		Appuntamento a = null;

		if (tipoParametro.equals(RicercaBy.ID)) {
			a = em.find(Appuntamento.class, Integer.parseInt(parametro));
			lista.add(a);
		} else if (tipoParametro.equals(RicercaBy.NOME)) {
			TypedQuery<Appuntamento> l = em.createQuery(
					"SELECT a FROM Appuntamento a WHERE a.cliente.nome LIKE :nome ORDER BY a.data", Appuntamento.class);
			l.setParameter("nome", parametro + "%");
			lista = l.getResultList();
		} else if (tipoParametro.equals(RicercaBy.DATA)) {
			TypedQuery<Appuntamento> l = em.createQuery(
					"SELECT a FROM Appuntamento a WHERE a.data = :data ORDER BY a.data", Appuntamento.class);
			l.setParameter("data", Date.valueOf(parametro));
			lista = l.getResultList();
		} else if (tipoParametro.equals(RicercaBy.IDCLIENTE)) {
			TypedQuery<Appuntamento> l = em.createQuery(
					"SELECT a FROM Appuntamento a WHERE a.cliente.id = :idCliente ORDER BY a.data", Appuntamento.class);
			l.setParameter("idCliente", Integer.parseInt(parametro2));
			lista = l.getResultList();
		} else if (tipoParametro.equals(RicercaBy.DATACLIENTE)) {
			TypedQuery<Appuntamento> l = em.createQuery(
					"SELECT a FROM Appuntamento a WHERE a.data = :data AND a.cliente.id = :idCliente ORDER BY a.data",
					Appuntamento.class);
			l.setParameter("data", Date.valueOf(parametro));
			l.setParameter("idCliente", Integer.parseInt(parametro2));
			lista = l.getResultList();
		} else if (tipoParametro.equals(RicercaBy.ALL)) {
			TypedQuery<Appuntamento> l = em.createQuery("SELECT a FROM Appuntamento a ORDER BY a.data",
					Appuntamento.class);
			lista = l.getResultList();
			if (lista.isEmpty()) {
				lista.add(new Appuntamento(Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), List.of(Servizi.PIEGA), new Cliente("Evelina", "Cappellacci", "0000", null)));
			}
		}
		return lista;
	}

	@Override
	public boolean eliminaAppuntamentoInArchivio(Appuntamento a) {

		try {
			Appuntamento app = em.find(Appuntamento.class, a.getId());
			em.remove(app);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean modificaAppuntamentoInArchivio(Appuntamento a) {

		try {
			em.merge(a);
			System.out.println("Modifica avvenuta con successo");
			return true;
		} catch (Exception e) {
			System.out.println("Modifica fallita!!!");
			return false;
		}
	}

	@Override
	public void aggiornaStorico() {

		List<Appuntamento> lista = new ArrayList<Appuntamento>();
		TypedQuery<Appuntamento> l = em.createQuery("SELECT a FROM Appuntamento a WHERE a.data < :oggi",
				Appuntamento.class);
		l.setParameter("oggi", Date.valueOf(LocalDate.now()));
		lista = l.getResultList();

		for (Appuntamento a : lista) {
			AppuntamentoStorico as = new AppuntamentoStorico();
			as.setId(a.getId());
			as.setData(a.getData());
			as.setOra(a.getOra());
			as.setListaServizi(a.getListaServizi());
			as.setCliente(a.getCliente());

			em.persist(as);
		}

		em.flush();

		Query elimina = em.createQuery("DELETE FROM Appuntamento a WHERE a.data < :oggi");
		elimina.setParameter("oggi", Date.valueOf(LocalDate.now()));
		elimina.executeUpdate();

	}
	@Override
	public List<Appuntamento> getAppuntamentiStorico(String nome) {
		List<Appuntamento> lista = new ArrayList<Appuntamento>();
		TypedQuery<Appuntamento> l = em.createQuery(
				"SELECT a FROM AppuntamentoStorico a WHERE a.cliente.nome LIKE :nome ORDER BY a.data DESC", Appuntamento.class);
		l.setParameter("nome", nome + "%");
		lista = l.getResultList();
		return lista;
	}

}
