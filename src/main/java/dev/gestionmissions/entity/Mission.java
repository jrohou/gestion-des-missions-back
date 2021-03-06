package dev.gestionmissions.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Mission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate dateDebut;
	private LocalDate dateFin;
	@ManyToOne
	@JoinColumn(name="nature")
	private Nature nature;
	private String villeDepart;
	private String villeArrivee;
	@Enumerated(EnumType.STRING)
	private Statut statut;
	@ManyToOne
	private Transport transport;
	private BigDecimal montantPrime;
	private String matricule;
	
	public Mission() {
		super();
	}

	public Mission(LocalDate dateDebut, LocalDate dateFin, Nature nature, String villeDepart, String villeArrivee,
			Statut statut, Transport transport, BigDecimal montantPrime, String matricule) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.nature = nature;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.statut = statut;
		this.transport = transport;
		this.montantPrime = montantPrime;
		this.matricule = matricule;
		
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Nature getNature() {
		return nature;
	}

	public void setNature(Nature nature) {
		this.nature = nature;
	}

	public String getVilleDepart() {
		return villeDepart;
	}

	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}

	public String getVilleArrivee() {
		return villeArrivee;
	}

	public void setVilleArrivee(String villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public BigDecimal getMontantPrime() {
		return montantPrime;
	}

	public void setMontantPrime(BigDecimal montantPrime) {
		this.montantPrime = montantPrime;
	}

	/**
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	/**
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	
	
	
}
