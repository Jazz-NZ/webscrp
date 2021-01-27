package com.jazz.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class DataFromSite {

	
	private String poruka;
	
	public String getPoruka() {
		return poruka;
	}

	public void setPoruka(String poruka) {
		this.poruka = poruka;
	}

	public Subject getMath(String url)  {
		
		int id = -1;
		String news = null;
		
		try {
			Document doc = Jsoup.connect(url).get();
			
			String title = doc.title();
            //System.out.println("title : " + title);
            
            Elements elements = doc.select("div.col-lg-12.col-md-12");
			
            String idStr  = elements.get(2).select("a").first().attr("href");
            id = Integer.parseInt(idStr.substring(7));
            
            news = elements.get(2).select("a").first().attr("title");
            System.out.println(news);
             
			
		}catch (Exception e) {
			System.err.println("Error with connecting to url or geting data from url");
		}
		
		//ubaciti bazu podataka
		
		Subject sub = new Subject();
		sub.setId(101);
		sub.setVest(id);
		sub.setPoruka(news);
		
		return sub;
	}
	
public Subject getMathAll(String url)  {
		
		int id = -1;
		String news = null;
		
		try {
			Document doc = Jsoup.connect(url).get();
			
			String title = doc.title();
            //System.out.println("title : " + title);
            
            Elements elements = doc.select("ul.list-unstyled");
			
            String idStr  = elements.get(2).select("a").first().attr("href");
            id = Integer.parseInt(idStr.substring(7));
            
            news = elements.get(2).select("a").first().attr("title");
            System.out.println(news);
             
			
		}catch (Exception e) {
			System.err.println("Error with connecting to url or getting data from url");
		}
		
		//pogledaj ovo set id i vest
		
		Subject sub = new Subject();
		sub.setId(101);
		sub.setVest(id);
		sub.setPoruka(news);
		
		return sub;
	}

}
