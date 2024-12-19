package it.salone.model;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "clienti")
public class Cliente extends Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "idCliente")
	private int id;
	@Column(name = "schedaTecnica")
	private String schedaTecnica;
	
	public Cliente() {}
	
	public Cliente(String nome, String cognome, String numeroTelefono, String schedaTecnica) {
		super(nome, cognome, numeroTelefono);
		this.schedaTecnica = schedaTecnica;		
	}
	
	public Cliente(int id, String nome, String cognome, String numeroTelefono, String schedaTecnica) {
		super(nome, cognome, numeroTelefono);
		this.schedaTecnica = schedaTecnica;	
		this.id = id;
	}

	public String getSchedaTecnica() {
		return schedaTecnica;
	}

	public void setSchedaTecnica(String schedaTecnica) {
		this.schedaTecnica = schedaTecnica;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id + "," + getNome() + "," + getCognome() + "," + getNumeroTelefono() + "," + schedaTecnica;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, schedaTecnica);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return id == other.id && Objects.equals(schedaTecnica, other.schedaTecnica);
	}

}
