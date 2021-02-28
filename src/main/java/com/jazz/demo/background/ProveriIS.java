package com.jazz.demo.background;

import java.util.LinkedList;

import com.jazz.demo.DataFromSite;
import com.jazz.demo.Katedra;

public class ProveriIS {
	
	public LinkedList<Katedra> proveraIsVesti(){
		
		DataFromSite data = new DataFromSite();

		String url = "http://is.fon.bg.ac.rs/";
		String vesti = "/vesti/";

		LinkedList<String> list = new LinkedList<String>();
		
		list.add("strukture-podataka-i-algoritmi");
		list.add("vesti-analiza");
		list.add("baze-podataka");
		list.add("modelovanje-poslovnih-procesa");
		list.add("programski-jezici");
		list.add("uvod-u-informacione-sisteme");
		list.add("fpis-osnovne");
		
		
		
		LinkedList<Katedra> subjects = new LinkedList<>();

		for (int i = 0; i < list.size(); i++) {

			subjects.add(data.getIS(url+list.get(i)+vesti,list.get(i) ));

		}

		return subjects;
	}

	
	
	public LinkedList<Katedra> proveraIsRezultati(){
		
		DataFromSite data = new DataFromSite();

		String url = "http://is.fon.bg.ac.rs/";
		String rezultati = "/rezultati/";

		LinkedList<String> list = new LinkedList<String>();
		
		list.add("strukture-podataka-i-algoritmi");
		list.add("vesti-analiza");
		list.add("baze-podataka");
		list.add("modelovanje-poslovnih-procesa");
		list.add("programski-jezici");
		list.add("uvod-u-informacione-sisteme");
		list.add("fpis-osnovne");
		
		
		
		
		LinkedList<Katedra> subjects = new LinkedList<>();

		for (int i = 0; i < list.size(); i++) {

			subjects.add(data.getIS(url+list.get(i)+rezultati,list.get(i) ));

		}

		return subjects;
	}
}
