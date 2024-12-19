package it.salone.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Persona {
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "numeroTelefono")
	private String numeroTelefono;
	
	public Persona() {}

	public Persona(String nome, String cognome, String numeroTelefono) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.numeroTelefono = numeroTelefono;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	
	

}
