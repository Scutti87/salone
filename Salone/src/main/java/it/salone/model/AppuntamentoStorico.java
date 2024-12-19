package it.salone.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import it.salone.Enum.Servizi;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "appuntamentistorico")
public class AppuntamentoStorico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idAppuntamento")
	private int id;
	@Column(name = "data")
	private Date data;
	@Column(name = "ora")
	private Time ora;
	@ElementCollection(fetch = FetchType.EAGER) 
	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "listaserviziSto", joinColumns = @JoinColumn(name = "Appuntamento_idAppuntamentoSto"))
	@Column(name = "listaServizi")
	private List<Servizi> listaServizi;
	@OneToOne(cascade = {})
	@JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
	private Cliente cliente;

	public AppuntamentoStorico() {
		super();
	}

	public AppuntamentoStorico(int id, Date data, Time ora, List<Servizi> listaServizi, Cliente cliente) {
		super();
		this.id = id;
		this.data = data;
		this.ora = ora;
		this.listaServizi = listaServizi;
		this.cliente = cliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Time getOra() {
		return ora;
	}

	public void setOra(Time ora) {
		this.ora = ora;
	}

	public List<Servizi> getListaServizi() {
		return listaServizi;
	}

	public void setListaServizi(List<Servizi> listaServizi) {
		this.listaServizi = listaServizi;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
