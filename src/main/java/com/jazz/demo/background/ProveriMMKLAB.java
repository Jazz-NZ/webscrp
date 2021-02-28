package com.jazz.demo.background;

import java.util.LinkedList;

import com.jazz.demo.DataFromSite;
import com.jazz.demo.Katedra;

public class ProveriMMKLAB {

	
	public LinkedList<Katedra> proveraMmklab(){
		
		DataFromSite data = new DataFromSite();

		String url = "http://www.mmklab.fon.bg.ac.rs/nastava/osnovne-studije/";

		LinkedList<String> list = new LinkedList<String>();
		
		list.add("oikt/");
		list.add("aros/");
		list.add("rmt/");
		list.add("drs/");
		list.add("multimediji/");
		list.add("dki");
		list.add("multimedijalna-produkcija/");
		list.add("mobilno-racunarstvo/");
		list.add("zrs");
		
		
		
		LinkedList<Katedra> subjects = new LinkedList<>();

		for (int i = 0; i < list.size(); i++) {

			subjects.add(data.getMmklab(url+list.get(i),list.get(i) ));

		}

		return subjects;
	}
	
	
}
