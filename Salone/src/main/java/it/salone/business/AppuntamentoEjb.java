package it.salone.business;

import java.util.List;
import it.salone.Enum.RicercaBy;
import it.salone.dao.AppuntamentoDaoImpl;
import it.salone.dao.IAppuntamentoDao;
import it.salone.model.Appuntamento;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateful
@LocalBean
public class AppuntamentoEjb implements AppuntamentoEjbLocal {

	@PersistenceContext(name = "Salone")
	EntityManager em;

	private IAppuntamentoDao appuntamentoDao;

	public AppuntamentoEjb() {
	}

	@PostConstruct
	public void setEntityManager() {
		appuntamentoDao = new AppuntamentoDaoImpl(em);
	}

	@Override
	public boolean inserisciAppuntamentoInArchivio(Appuntamento a) {

		boolean inserito = appuntamentoDao.inserisciAppuntamentoInArchivio(a);
		return inserito;
	}

	@Override
	public List<Appuntamento> getAppuntamentiBy(RicercaBy tipoParametro, String parametro, String parametro2) {
		
		return appuntamentoDao.getAppuntamentiBy(tipoParametro, parametro, parametro2);
	}

	@Override
	public boolean modificaAppuntamentoInArchivio(Appuntamento a) {
		boolean modificato = appuntamentoDao.modificaAppuntamentoInArchivio(a);
		return modificato;
	}

	@Override
	public boolean eliminaAppuntamentoInArchivio(Appuntamento a) {
		boolean eliminato = appuntamentoDao.eliminaAppuntamentoInArchivio(a);
		return eliminato;
	}

	@Override
	public void aggiornaStorico() {
		appuntamentoDao.aggiornaStorico();
	}

	@Override
	public List<Appuntamento> getAppuntamentiStorico(String nome) {
		
		return appuntamentoDao.getAppuntamentiStorico(nome);
	}
}
