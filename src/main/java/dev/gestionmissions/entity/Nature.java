package dev.gestionmissions.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Nature {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String nom;
	private LocalDate dateDebutValidite;
	private LocalDate dateFinValidite;
	private boolean facturee;
	private boolean versementPrime;
	private BigDecimal tauxJournalierMoyen;
	
	public Nature() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public LocalDate getDateDebutValidite() {
		return dateDebutValidite;
	}

	public void setDateDebutValidite(LocalDate dateDebutValidite) {
		this.dateDebutValidite = dateDebutValidite;
	}

	public LocalDate getDateFinValidite() {
		return dateFinValidite;
	}

	public void setDateFinValidite(LocalDate dateFinValidite) {
		this.dateFinValidite = dateFinValidite;
	}

	public boolean isFacturee() {
		return facturee;
	}

	public void setFacturee(boolean faturee) {
		this.facturee = faturee;
	}

	public boolean isVersementPrime() {
		return versementPrime;
	}

	public void setVersementPrime(boolean versementPrime) {
		this.versementPrime = versementPrime;
	}

	public BigDecimal getTauxJournalierMoyen() {
		return tauxJournalierMoyen;
	}

	public void setTauxJournalierMoyen(BigDecimal tauxJournalierMoyen) {
		this.tauxJournalierMoyen = tauxJournalierMoyen;
	}
	
	
	

}
