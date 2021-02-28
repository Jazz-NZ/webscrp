package com.jazz.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity //jpa dependency
public class Katedra {   //da klasa subject bude katedra
	
	@Id
	private int id; // obrisati id i vest
	
	private int vest;
	private String poruka;
	private String topic;
	
	
	
	public String getPredmet() { // topic da bude predmet
		return topic;
	}
	public void setPredmet(String predmet) {
		this.topic = predmet;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVest() {
		return vest;
	}
	public void setVest(int vest) {
		this.vest = vest;
	}
	
	public String getPoruka() {
		return poruka;
	}
	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}

}
