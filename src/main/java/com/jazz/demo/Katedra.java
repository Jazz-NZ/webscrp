package com.jazz.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity //jpa dependency
public class Katedra {   
	
	@Id
	private String topic;

	private String link;
	
	private String poruka;
	
	
	
	
	public String getPredmet() { 
		return topic;
	}
	public void setPredmet(String predmet) {
		this.topic = predmet;
	}
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getPoruka() {
		return poruka;
	}
	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}

}
