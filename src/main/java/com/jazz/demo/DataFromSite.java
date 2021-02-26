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
		
		/*
		 * int id = -1; String news = null;
		 * 
		 * try { Document doc = Jsoup.connect(url).get();
		 * 
		 * String title = doc.title(); //System.out.println("title : " + title);
		 * 
		 * Elements elements = doc.select("div.col-lg-12.col-md-12");
		 * 
		 * String idStr = elements.get(2).select("a").first().attr("href"); id =
		 * Integer.parseInt(idStr.substring(7));
		 * 
		 * news = elements.get(2).select("a").first().attr("title");
		 * System.out.println(news);
		 * 
		 * 
		 * }catch (Exception e) {
		 * System.err.println("Error with connecting to url or geting data from url"); }
		 */	
		//ubaciti bazu podataka
		
		
		int id = -1;
		String news = null;
		
		try {
			Document doc = Jsoup.connect(url).get();
			
			//System.out.println(doc);
			
			//String title = doc.title();
            //System.out.println("title : " + title);
            
            Elements elements = doc.select("ul.list-unstyled");
			
            
           // System.out.println(elements.select("li.recent-news-wrap").get(0));
        
            
            String idStr  = elements.select("li.recent-news-wrap").get(0).select("a").first().attr("href");
            
           // System.out.println(idStr);
            
            //id pravi problem, treba pretresti web scrape
            id = Integer.parseInt(idStr.substring(7));
            
            news = elements.select("li.recent-news-wrap").get(0).select("a").first().attr("title");
            System.out.println(news);
             
			
		}catch (Exception e) {
			System.err.println("Error with connecting to url or getting data from url");
		}
		
		Subject sub = new Subject();
		sub.setId(101);
		sub.setVest(id);
		sub.setPoruka(news);
		
		return sub;
	}
	
public Subject getMathAllVesti(String url, String predmet)  {
		
	int id = -1;
	String news = null;
	
	try {
		Document doc = Jsoup.connect(url).get();
		
		//System.out.println(doc);
		
		String title = doc.title();
        //System.out.println("title : " + title);
        
        Elements elements = doc.select("ul.list-unstyled");
		
        
       // System.out.println(elements.select("li.recent-news-wrap").get(0));
        
        
        
        
        
        String idStr  = elements.select("li.recent-news-wrap").get(0).select("a").first().attr("href");
        
       // System.out.println(idStr);
        
        //id pravi problem, treba pretresti web scrape
        id = Integer.parseInt(idStr.substring(7));
        
        news = elements.select("li.recent-news-wrap").get(0).select("a").first().attr("title");
        System.out.println(news);
         
		
	}catch (Exception e) {
		System.err.println("Error with connecting to url or getting data from url");
	}

	
		//pogledaj ovo set id i vest
		
		Subject sub = new Subject();
		sub.setId(101);
		sub.setVest(id);
		sub.setPoruka(news);
		sub.setPredmet(predmet);
		
		return sub;
	}
		
	
public Subject getMathAktivnosti() {
	
	String url= "http://math.fon.bg.ac.rs/";
	
	int id = -1;
	String news = null;
	
	try {
		Document doc = Jsoup.connect(url).get();
		
		//System.out.println(doc);
		
		String title = doc.title();
        //System.out.println("title : " + title);
        
        Elements elements = doc.select("ul.list-unstyled");
		
        
       // System.out.println(elements.select("li.recent-news-wrap").get(0));
        
        
        
        
        
        String idStr  = elements.select("li.up-event-wrap").get(0).select("a").first().attr("href");
        
        System.out.println(idStr);
        
        //id pravi problem, treba pretresti web scrape
        //id = Integer.parseInt(idStr.substring(7));
        
        news = elements.select("li.up-event-wrap").get(0).select("a").first().attr("title");
        System.out.println(news);
         
		
	}catch (Exception e) {
		System.err.println("Error with connecting to url or getting data from url");
	}

	Subject sub = new Subject();
	sub.setId(101);
	sub.setVest(id);
	sub.setPoruka(news);
	sub.setPredmet("Matematika");
		
	
	return sub;
	}
	
	public Subject getMmklab(String url, String predmet) {
		
		
//String url= "http://www.mmklab.fon.bg.ac.rs/sr/nastava/osnovne-studije/oikt/";
		
		int id = -1;
		String news = null;
		
		try {
			Document doc = Jsoup.connect(url).get();
			
			//System.out.println(doc);
			
			//String title = doc.title();
            //System.out.println("title : " + title);
            
            Elements elements = doc.select("div.post.vesti");
			//System.out.println(elements);
            
            //System.out.println(elements.select("li").get(0));
            
            String idStr  = elements.select("li").get(0).select("a").first().attr("href");
            
            //System.out.println(idStr);
            
            //id pravi problem, treba pretresti web scrape
            //id = Integer.parseInt(idStr.substring(7));
            
            news = elements.select("li").get(0).select("a").first().attr("title");
            //System.out.println(news);
            news = news.replace("Permanent Link to ", "");
            System.out.println(news);
             
			
		}catch (Exception e) {
			System.err.println("Error with connecting to url or getting data from url");
		}

	Subject sub = new Subject();	
	sub.setId(101);
	sub.setPoruka(news);
	sub.setPredmet(predmet);
	sub.setVest(1);
	
	return sub;
	
	}

	public Subject getIS(String url, String predmet) {
		
		int id = -1;
		String news = null;
		
		try {
			Document doc = Jsoup.connect(url).get();
			
			//System.out.println(doc);
			
			String title = doc.title();
            //System.out.println("title : " + title);
            
            Elements elements = doc.select("main");
			//System.out.println(elements);
            
            //System.out.println(elements.select("div.blog-item-wrap").get(0));
            
            
            
            
            
            String idStr  = elements.select("div.blog-item-wrap").get(0).select("a").first().attr("href");
            
           // System.out.println(idStr);
            
            //id pravi problem, treba pretresti web scrape
            //id = Integer.parseInt(idStr.substring(7));
            
            news = elements.select("div.blog-item-wrap").get(0).select("a").first().attr("title");
            //System.out.println(news);
            //news = news.replace("Permanent Link to ", "");
            System.out.println(news);
             
			
		}catch (Exception e) {
			System.err.println("Error with connecting to url or getting data from url");
		}

		Subject sub = new Subject();
		sub.setId(101);
		sub.setVest(id);
		sub.setPoruka(news);
		sub.setPredmet(predmet);
		
		return sub;
		
	}
	
	
}


