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
	
	
	public Katedra getLabsys(String url, String predmet) {
		String news = null;
		String link = null;
		//String url = "http://labsys.fon.bg.ac.rs/teorija-sistema";

		try {
			Document doc = Jsoup.connect(url).get();

		    //System.out.println(doc);

			// String title = doc.title();
			// System.out.println("title : " + title);

			Elements elements = doc.select("h2.node-title");
			System.out.println(elements.select("h2.node-title"));

			link = elements.select("h2.node-title").get(0).select("a").first().attr("href");
			System.out.println(link);

			news = elements.select("h2.node-title").get(0).text();
		    System.out.println(news);

			
		} catch (Exception e) {
			System.err.println("Greska: preuzimanje sa sajta!");
		}
		
		Katedra sub = new Katedra();
		sub.setLink(link);
		sub.setPoruka(news);
		sub.setPredmet(predmet);
		
		return sub;
	}
	
	public Katedra getStatlab(String url, String predmet) {
		String news = null;
		String link = null;
		//String url = "http://statlab.fon.bg.ac.rs/predmeti/teorija-verovatnoce-2/";

		try {
			Document doc = Jsoup.connect(url).get();

		    //System.out.println(doc);

			// String title = doc.title();
			// System.out.println("title : " + title);

			Elements elements = doc.select("h4");
			System.out.println(elements.select("h4").get(0));

			link = elements.select("h4").get(0).select("a").first().attr("href");
			System.out.println(link);

			news = elements.select("h4").get(0).text();
			System.out.println(news);

			
		} catch (Exception e) {
			System.err.println("Greska: preuzimanje sa sajta!");
		}
		
		Katedra sub = new Katedra();
		sub.setLink(link);
		sub.setPoruka(news);
		sub.setPredmet(predmet);
		
		return sub;
	}
	
	public Katedra getSilab(String url, String predmet) {
		
		//String url= "http://silab.fon.bg.ac.rs/category/%d0%b2%d0%b5%d1%81%d1%82%d0%b8/programiranje-1/?lang=lat";
		
		int id = -1;
		String news = null;
		String link = null;
		
		try {
			Document doc = Jsoup.connect(url).get();
			
			//System.out.println(doc);
			
			String title = doc.title();
            //System.out.println("title : " + title);
            
            Elements elements = doc.select("header.entry-header");
			//System.out.println(elements);
            
            //System.out.println(elements.select("h1.entry-title").get(0));
            
            
            
            
            
            link  = elements.select("h1.entry-title").get(0).select("a").first().attr("href");
            
            System.out.println(link);
            
            //id pravi problem, treba pretresti web scrape
            //id = Integer.parseInt(idStr.substring(7));
            
            news = elements.select("h1.entry-title").get(0).select("a").first().attr("title");
            //System.out.println(news);
            news = news.replace("Permalink to ", "");
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
	
	public Katedra getAi(String url, String predmet) {
		
		String news = null;
		String link = null;
		//String url = "http://ai.fon.bg.ac.rs/osnovne/programiranje-2/";

		try {
			Document doc = Jsoup.connect(url).get();

		    //System.out.println(doc);

			// String title = doc.title();
			// System.out.println("title : " + title);

			Elements elements = doc.select("h1.excerpt-title");
			System.out.println(elements.select("h1.excerpt-title"));

			link = elements.select("h1.excerpt-title").get(1).select("a").first().attr("href");
			System.out.println(link);

			news = elements.select("h1.excerpt-title").get(1).text();
			System.out.println(news);

			
		} catch (Exception e) {
			System.err.println("Greska: preuzimanje sa sajta!");
		}
		
		Katedra sub = new Katedra();
		sub.setLink(link);
		sub.setPoruka(news);
		sub.setPredmet(predmet);
		
		return sub;
	}
	
	public Katedra getKvalitet(String url, String predmet) {
		
		String news = null;
		String link = null;
		/*
		 * String url =
		 * "http://kvalitet.fon.bg.ac.rs/%D0%BF%D1%80%D0%B5%D0%B4%D0%BC%D0%B5%" +
		 * "D1%82/%d0%be%d1%81%d0%bd%d0%be%d0%b2%d0%bd%d0%b5-%d1%81%d1%82%d1%83%d0" +
		 * "%b4%d0%b8%d1%98%d0%b5/%d0%be%d0%b1%d0%b0%d0%b2%d0%b5%d0%b7%d0%bd%d0%b8" +
		 * "-%d0%bf%d1%80%d0%b5%d0%b4%d0%bc%d0%b5%d1%82%d0%b8/%d0%be%d1%81%d0%bd%d0" +
		 * "%be%d0%b2%d0%b5-%d0%ba%d0%b2%d0%b0%d0%bb%d0%b8%d1%82%d0%b5%d1%82%d0%b0/";
		 */
		try {
			Document doc = Jsoup.connect(url).get();

		    //System.out.println(doc);

			// String title = doc.title();
			// System.out.println("title : " + title);

			Elements elements = doc.select("h2.entry-title");
			System.out.println(elements.select("h2.entry-title"));

			link = elements.select("h2.entry-title").get(0).select("a").first().attr("href");
			System.out.println(link);

			news = elements.select("h2.entry-title").get(0).text();
		    System.out.println(news);

			
		} catch (Exception e) {
			System.err.println("Greska: preuzimanje sa sajta!");
		}
		
		Katedra sub = new Katedra();
		sub.setLink(link);
		sub.setPoruka(news);
		sub.setPredmet(predmet);
		
		return sub;
	}
	
	public Katedra getOrganizacija(String url, String predmet) {
		
		//String url= "http://organizacija.fon.bg.ac.rs/category/osnovi-organizacije/vesti-osnovi-organizacije/";
		
		int id = -1;
		String news = null;
		String link = null;
		
		try {
			Document doc = Jsoup.connect(url).get();
			
			//System.out.println(doc);
			
			String title = doc.title();
            //System.out.println("title : " + title);
            
            Elements elements = doc.select("h3.header_font.bd_headings_text_shadow");
			//System.out.println(elements);
            
            //System.out.println(elements.select("h3.header_font.bd_headings_text_shadow").get(0));
            
            
            
            
            
           link  = elements.select("h3.header_font.bd_headings_text_shadow").get(0).select("a").first().attr("href");
            
           System.out.println(link);
            
            //id pravi problem, treba pretresti web scrape
            //id = Integer.parseInt(idStr.substring(7));
            
            news = elements.select("h3.header_font.bd_headings_text_shadow").get(0).text();
            System.out.println(news);
           // news = news.replace("Permalink to ", "");
           // System.out.println(news);
             
			
		}catch (Exception e) {
			System.err.println("Error with connecting to url or getting data from url");
		}
		

		Katedra sub = new Katedra();
		sub.setLink(link);
		sub.setPoruka(news);
		sub.setPredmet(predmet);
		
		return sub;
	}
	
	public Katedra getLaboi(String url, String predmet) {

		String news = null;
		String link = null;
		//String url = "http://laboi.fon.bg.ac.rs/osnovne-studije/operaciona-istrazivanja-2/";
		
		try {
			Document doc = Jsoup.connect(url).get();
			
			//System.out.println(doc);
			
			//String title = doc.title();
	        //System.out.println("title : " + title);
	        
	        Elements elements = doc.select("div.post-title");
			
	        
	        System.out.println(elements.select("h2.entry-title").get(0));
	        
	        
	        link  = elements.select("h2.entry-title").get(0).select("a").first().attr("href");
	        
	        System.out.println(link);
	        
	        //id pravi problem, treba pretresti web scrape
	        
	        
	        news = elements.select("h2.entry-title").get(0).select("a").text();
	        System.out.println(news);
	}catch(Exception e){
		System.err.println("Greska: preuzimanje sa sajta!");
		}
		Katedra sub = new Katedra();
		sub.setLink(link);
		sub.setPoruka(news);
		sub.setPredmet(predmet);
		
		return sub;
	}

	public Katedra getImi(String url, String predmet) {
		
			//String url= "http://imi.fon.bg.ac.rs/inzenjering-procesa/category/vesti/";
		
		int id = -1;
		String news = null;
		String link = null;
		
		try {
			Document doc = Jsoup.connect(url).get();
			
			//System.out.println(doc);
			
			String title = doc.title();
            //System.out.println("title : " + title);
            
            Elements elements = doc.select("header.entry-header");
			//System.out.println(elements);
            
            //System.out.println(elements.select("h1.entry-title").get(0));
            
            
            
            
            
            link  = elements.select("h1.entry-title").get(0).select("a").first().attr("href");
            
            System.out.println(link);
            
            //id pravi problem, treba pretresti web scrape
            //id = Integer.parseInt(idStr.substring(7));
            
            news = elements.select("h1.entry-title").get(0).select("a").first().attr("title");
            //System.out.println(news);
            news = news.replace("Permalink to ", "");
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
	
	public Katedra getHr(String url, String predmet) {
		
			//String url= "http://hr.fon.bg.ac.rs/";
		
		int id = -1;
		String news = null;
		String link = null;
		
		try {
			Document doc = Jsoup.connect(url).get();
			
			//System.out.println(doc);
			
			//String title = doc.title();
            //System.out.println("title : " + title);
            
            Elements elements = doc.select("div.post-grid-text-wrap");
			//System.out.println(elements);
            
            System.out.println(elements.select("h3.title").get(0));
            
            
            
            
            
            link  = elements.select("h3.title").get(0).select("a").first().attr("href");
            
            System.out.println(link);
            
            //id pravi problem, treba pretresti web scrape
            //id = Integer.parseInt(idStr.substring(7));
            
            news = elements.select("h3.title").get(0).select("a").text();
            System.out.println(news);
           // news = news.replace("Permalink to ", "");
           // System.out.println(news);
            
            String s = news.toLowerCase();
            
            if(s.contains("sociologija"))
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
}


