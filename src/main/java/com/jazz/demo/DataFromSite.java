package com.jazz.demo;

import java.io.IOException;

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

	public Katedra getMath(String url)  {
		
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
		
		Katedra sub = new Katedra();
		sub.setPoruka(news);
		
		return sub;
	}
	
public Katedra getMathAllVesti(String url, String predmet)  {
		
	int id = -1;
	String news = null;
	String link = null;
	
	try {
		Document doc = Jsoup.connect(url).get();
		
		//System.out.println(doc);
		
		String title = doc.title();
        //System.out.println("title : " + title);
        
        Elements elements = doc.select("ul.list-unstyled");
		
        
       // System.out.println(elements.select("li.recent-news-wrap").get(0));
        
        
        link  = elements.select("li.recent-news-wrap").get(0).select("a").first().attr("href");
        
       // System.out.println(idStr);
        
        //id pravi problem, treba pretresti web scrape
        
        
        news = elements.select("li.recent-news-wrap").get(0).select("a").first().attr("title");
        System.out.println(news);
         
		
	}catch (Exception e) {
		System.err.println("Error with connecting to url or getting data from url");
	}
	
		//pogledaj ovo set id i vest
		
		Katedra sub = new Katedra();
		
		sub.setPoruka(news);
		sub.setPredmet(predmet);
		sub.setLink(link);
		
		return sub;
	}
		
	
public Katedra getMathAktivnosti(String predmet) {
	
	String url= "http://math.fon.bg.ac.rs/";
	
	int id = -1;
	String news = null;
	String link = null;
	
	try {
		Document doc = Jsoup.connect(url).get();
		
		//System.out.println(doc);
		
		String title = doc.title();
        //System.out.println("title : " + title);
        
        Elements elements = doc.select("ul.list-unstyled");
		
       // System.out.println(elements.select("li.recent-news-wrap").get(0));
        
        
        link  = elements.select("li.up-event-wrap").get(0).select("a").first().attr("href");
        
        //System.out.println(idStr);
        
        //id pravi problem, treba pretresti web scrape
        //id = Integer.parseInt(idStr.substring(7));
        
        news = elements.select("li.up-event-wrap").get(0).select("a").first().attr("title");
        System.out.println(news);
         
		
	}catch (Exception e) {
		System.err.println("Error with connecting to url or getting data from url");
	}

	Katedra sub = new Katedra();
	
	sub.setPredmet(predmet);
	sub.setPoruka(news);
	sub.setLink(link);
		
	
	return sub;
	}
	
	public Katedra getMmklab(String url, String predmet) {
		
		
//String url= "http://www.mmklab.fon.bg.ac.rs/sr/nastava/osnovne-studije/oikt/";
		
		int id = -1;
		String news = null;
		String link = null;
		
		try {
			Document doc = Jsoup.connect(url).get();
			
			//System.out.println(doc);
			
			//String title = doc.title();
            //System.out.println("title : " + title);
            
            Elements elements = doc.select("div.post.vesti");
			//System.out.println(elements);
            
            //System.out.println(elements.select("li").get(0));
            
            link  = elements.select("li").get(0).select("a").first().attr("href");
            
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

	Katedra sub = new Katedra();	
	
	sub.setPoruka(news);
	sub.setPredmet(predmet);
	sub.setLink(link);
	
	
	return sub;
	
	}

	public Katedra getIS(String url, String predmet) {
		
		int id = -1;
		String news = null;
		String link = null;
		
		try {
			Document doc = Jsoup.connect(url).get();
			
			//System.out.println(doc);
			
			String title = doc.title();
            //System.out.println("title : " + title);
            
            Elements elements = doc.select("main");
			//System.out.println(elements);
            
            //System.out.println(elements.select("div.blog-item-wrap").get(0));
            
            
            
            
            
            link  = elements.select("div.blog-item-wrap").get(0).select("a").first().attr("href");
            
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

		Katedra sub = new Katedra();
		sub.setLink(link);
		sub.setPoruka(news);
		sub.setPredmet(predmet);
		
		return sub;
		
	}
	
//	 vracamo url poslednje vesti sa fonovog sajta
	public String getFonPoslednjuVest() {
		
		String url = "http://www.fon.bg.ac.rs/obavestenja/vesti-osnovne-studije/";
		String urlPoslednjeVesti = null;
		
		try {
			
			Document document =  Jsoup.connect(url).get();
			
			Elements elements = document.select("span.posted-on");
			
			urlPoslednjeVesti = elements.get(0).select("a").attr("href");
//			System.out.println(urlPoslednjeVesti);
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return urlPoslednjeVesti;
	
	}
	
}


