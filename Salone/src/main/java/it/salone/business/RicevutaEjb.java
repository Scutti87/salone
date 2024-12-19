package it.salone.business;

import java.util.List;

import it.salone.Enum.RicercaBy;
import it.salone.dao.IRicevutaDao;
import it.salone.dao.RicevutaDaoImpl;
import it.salone.model.Ricevuta;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateful
@LocalBean
public class RicevutaEjb implements RicevutaEjbLocal {

	@PersistenceContext(name = "Salone")
	EntityManager em;

	private IRicevutaDao ricevutaDao;

	public RicevutaEjb() {
	}

	@PostConstruct
	public void setEntityManager() {
		ricevutaDao = new RicevutaDaoImpl(em);
	}

	@Override
	public boolean inserisciRicevutaInArchivio(Ricevuta r) {
		boolean inserito = ricevutaDao.inserisciRicevutaInArchivio(r);
		return inserito;
	}

	@Override
	public List<Ricevuta> getRicevuteBy(RicercaBy tipoParametro, String parametro, String parametro2) {
		return ricevutaDao.getRicevuteBy(tipoParametro, parametro, parametro2);
	}

	@Override
	public boolean eliminaRicevutaInArchivio(Ricevuta r) {
		boolean eliminato = ricevutaDao.eliminaRicevutaInArchivio(r);
		return eliminato;
	}

	@Override
	public boolean modificaRicevutaInArchivio(Ricevuta r) {
		boolean modificato = ricevutaDao.modificaRicevutaInArchivio(r);
		return modificato;
	}

}
