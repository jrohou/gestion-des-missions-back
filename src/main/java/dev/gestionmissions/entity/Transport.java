package dev.gestionmissions.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transport {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String modeTransport;
	
	public Transport(){
		super();
	}
	
	public Transport(String modeTransport){
		this.modeTransport=modeTransport;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the modeTransport
	 */
	public String getModeTransport() {
		return modeTransport;
	}
	/**
	 * @param modeTransport the modeTransport to set
	 */
	public void setModeTransport(String modeTransport) {
		this.modeTransport = modeTransport;
	}
	
	
	
	

}
