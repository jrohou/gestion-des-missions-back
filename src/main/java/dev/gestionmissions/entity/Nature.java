package dev.gestionmissions.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Nature {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private LocalDate dateDebutValidite;
	private LocalDate dateFinValidite;
	private boolean facturee;
	private boolean versementPrime;
	private BigDecimal tauxJournalierMoyen;
	private BigDecimal pourcentagePrime;
	
	public Nature() {
		super();
	}

	public Nature(String nom, LocalDate dateDebutValidite, LocalDate dateFinValidite, boolean facturee,
			boolean versementPrime, BigDecimal tauxJournalierMoyen, BigDecimal pourcentagePrime) {
		super();
		this.nom = nom;
		this.dateDebutValidite = dateDebutValidite;
		this.dateFinValidite = dateFinValidite;
		this.facturee = facturee;
		this.versementPrime = versementPrime;
		this.tauxJournalierMoyen = tauxJournalierMoyen;
		this.pourcentagePrime = pourcentagePrime;
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
	
	public BigDecimal getPourcentagePrime() {
		return pourcentagePrime;
	}

	public void setPourcentagePrime(BigDecimal pourcentagePrime) {
		this.pourcentagePrime = pourcentagePrime;
	}
	
	
	

}
