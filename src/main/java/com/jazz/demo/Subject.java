package com.jazz.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity //jpa dependency
public class Subject {
	
	@Id
	private int id;
	
	private int vest;
	private String poruka;
	private String topic;
	
	
	
	public String getPredmet() {
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
