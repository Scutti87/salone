package it.salone.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import it.salone.Enum.Servizi;
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
@Table(name = "ricevute")
public class Ricevuta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idRicevuta")
	private int idRicevuta;
	@Column(name = "data")
	private Date data;
	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	@CollectionTable(name = "listaserviziRic", joinColumns = @JoinColumn(name = "Appuntamento_idRicevuta"))
	@Column(name = "listaServizi")
	private List<Servizi> listaServizi;
	@Column(name = "totale")
	private double totale;
	@OneToOne(cascade = {})
	@JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
	private Cliente cliente;

	public Ricevuta() {
	}

	public Ricevuta(int idFich, Date data, List<Servizi> listaServizi, double totale, Cliente cliente) {
		this.idRicevuta = idFich;
		this.data = data;
		this.listaServizi = listaServizi;
		this.totale = totale;
		this.cliente = cliente;
	}

	public Ricevuta(Date data, List<Servizi> listaServizi, double totale, Cliente cliente) {

		this.data = data;
		this.listaServizi = listaServizi;
		this.totale = totale;
		this.cliente = cliente;
	}

	public int getId() {
		return idRicevuta;
	}

	public void setId(int idRicevuta) {
		this.idRicevuta = idRicevuta;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<Servizi> getListaServizi() {
		return listaServizi;
	}

	public void setListaServizi(List<Servizi> listaServizi) {
		this.listaServizi = listaServizi;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Ricevuta [idRicevuta=" + idRicevuta + ", data=" + data + ", listaServizi=" + listaServizi + ", totale="
				+ totale + ", cliente=" + cliente + "]";
	}

}
