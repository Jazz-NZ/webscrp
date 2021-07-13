package com.jazz.demo;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	/*
	 * public Katedra getMathAktivnosti(String url) {
	 * 
	 * 
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
	 * 
	 * //ubaciti bazu podataka
	 * 
	 * return new Katedra();
	 * 
	 * }
	 */
	
public Katedra getMathAktivnosti(String url, String predmet) {
		
		//String url= "http://math.fon.bg.ac.rs/";
		String news = null;
		String link = null;
		
		Katedra sub = new Katedra();
		//u slucaju da izbaci try da ne bi predmet bio null
		sub.setPredmet(predmet);
	
		try {
			Document doc = Jsoup.connect(url).get();
		
			Elements elements = doc.select("ul.list-unstyled");
		
			link  = elements.select("li.up-event-wrap").get(0).select("a").first().attr("href");
        
			news = elements.select("li.up-event-wrap").get(0).select("a").first().attr("title");
			
			System.out.println(news);
			System.out.println(link);
			
			
			String checkStr = news.toLowerCase();
			
			sub.setPoruka(news);
			sub.setPredmet(predmet);
			sub.setLink(link);

			
			//proverava dms, ako je dms salje se za dms samo
			if(predmet.contains("dms")&&(checkStr.contains("диск")||checkStr.contains("дмс"))) {
				return sub;
			}
			else if(predmet.contains("matematika1")&&(checkStr.contains("математик")&&checkStr.contains("1"))){
				return sub;
				
			}else if(predmet.contains("matematika2")&&(checkStr.contains("математик")&&checkStr.contains("2"))){
				return sub;
			}
			else if(predmet.contains("matematika3")&&(checkStr.contains("математик")&&checkStr.contains("3"))){
				return sub;
			
			}
			else if(predmet.contains("analiza") && (checkStr.contains("анализе")))
				return sub;
			
			//ako je pojedinacni onda je pismenog usmenog itd
			else if(checkStr.contains("писмених")&&checkStr.contains("усмених")&&checkStr.contains("писменог колоквијума")) {
				return sub;
				
			}
			else {
				sub.setPoruka(null);
				return sub;
				
			}
			
		}catch (Exception e) {
			System.err.println("Error with connecting to url or getting data from url");
		}
		
		return sub;
		}

	
public Katedra getMath(String url, String predmet) {
	
	//String url= "http://math.fon.bg.ac.rs/kursevi/";
	String news = null;
	String link = null;
	try {
		Document doc = Jsoup.connect(url).get();


        Elements elements = doc.select("ul.list-unstyled");
        
         news = elements.select("li.recent-news-wrap").get(0).select("a").first().attr("title");
         
         link = elements.select("li.recent-news-wrap").get(0).select("a").first().attr("href");
         
         link = "http://math.fon.bg.ac.rs" + link;
         
	}catch(Exception e) {
		System.out.println("Check url or network!");
	}
	
	Katedra sub = new Katedra();

	sub.setPoruka(news);
	sub.setPredmet(predmet);
	sub.setLink(link);

	return sub;
	
}
	

	public Katedra getMmklab(String url, String predmet) {

		// String url=
		// "http://www.mmklab.fon.bg.ac.rs/sr/nastava/osnovne-studije/oikt/";

		int id = -1;
		String news = null;
		String link = null;

		try {
			Document doc = Jsoup.connect(url).get();

			// System.out.println(doc);

			// String title = doc.title();
			// System.out.println("title : " + title);

			Elements elements = doc.select("div.post.vesti");
			// System.out.println(elements);

			// System.out.println(elements.select("li").get(0));

			link = elements.select("li").get(0).select("a").first().attr("href");

			// System.out.println(idStr);

			// id pravi problem, treba pretresti web scrape
			// id = Integer.parseInt(idStr.substring(7));

			news = elements.select("li").get(0).select("a").first().attr("title");
			// System.out.println(news);
			news = news.replace("Permanent Link to ", "");
//            System.out.println(news);

		} catch (Exception e) {
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

			// System.out.println(doc);

//			String title = doc.title();
			// System.out.println("title : " + title);

			Elements elements = doc.select("main");
			// System.out.println(elements);

//            System.out.println(elements.select("div.blog-item-wrap").get(0));

			link = elements.select("div.blog-item-wrap").get(0).select("a").first().attr("href");

//            System.out.println(link);

			// id pravi problem, treba pretresti web scrape
			// id = Integer.parseInt(idStr.substring(7));

			news = elements.select("div.blog-item-wrap").get(0).select("a").first().attr("title");
			// System.out.println(news);
			// news = news.replace("Permanent Link to ", "");
//            System.out.println(news);

		} catch (Exception e) {
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

			Document document = Jsoup.connect(url).get();

			Elements elements = document.select("span.posted-on");

			urlPoslednjeVesti = elements.get(0).select("a").attr("href");
//			System.out.println(urlPoslednjeVesti);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return urlPoslednjeVesti;

	}

	public Katedra getLabsys(String url, String predmet) {
		String news = null;
		String link = null;
		String urlKatedre = "http://labsys.fon.bg.ac.rs/";

		try {
			Document doc = Jsoup.connect(url).get();

			// System.out.println(doc);

			// String title = doc.title();
			// System.out.println("title : " + title);

			Elements elements = doc.select("h2.node-title");
//			System.out.println(elements.select("h2.node-title"));

			link = elements.select("h2.node-title").get(0).select("a").first().attr("href");
//			System.out.println(link);

			news = elements.select("h2.node-title").get(0).text();
//		    System.out.println(news);

		} catch (Exception e) {
			System.err.println("Greska: preuzimanje sa sajta!");
		}

		Katedra sub = new Katedra();
		sub.setLink(urlKatedre + link);
		sub.setPoruka(news);
		sub.setPredmet(predmet);

		return sub;
	}

	public Katedra getStatlab(String url, String predmet) {
		String news = null;
		String link = null;
		// String url = "http://statlab.fon.bg.ac.rs/predmeti/teorija-verovatnoce-2/";

		try {
			Document doc = Jsoup.connect(url).get();

			// System.out.println(doc);

			// String title = doc.title();
			// System.out.println("title : " + title);

			Elements elements = doc.select("h4");
//			System.out.println(elements.select("h4").get(0));

			link = elements.select("h4").get(0).select("a").first().attr("href");
//			System.out.println(link);

			news = elements.select("h4").get(0).text();
//			System.out.println(news);

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

		// String url=
		// "http://silab.fon.bg.ac.rs/category/%d0%b2%d0%b5%d1%81%d1%82%d0%b8/programiranje-1/?lang=lat";

		int id = -1;
		String news = null;
		String link = null;

		try {
			Document doc = Jsoup.connect(url).get();

			// System.out.println(doc);

			String title = doc.title();
			// System.out.println("title : " + title);

			Elements elements = doc.select("header.entry-header");
			// System.out.println(elements);

			// System.out.println(elements.select("h1.entry-title").get(0));

			link = elements.select("h1.entry-title").get(0).select("a").first().attr("href");

//            System.out.println(link);

			// id pravi problem, treba pretresti web scrape
			// id = Integer.parseInt(idStr.substring(7));

			news = elements.select("h1.entry-title").get(0).select("a").first().attr("title");
			// System.out.println(news);
			news = news.replace("Permalink to ", "");
//            System.out.println(news);

		} catch (Exception e) {
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
		// String url = "http://ai.fon.bg.ac.rs/osnovne/programiranje-2/";

		try {
			Document doc = Jsoup.connect(url).get();

			// System.out.println(doc);

			// String title = doc.title();
			// System.out.println("title : " + title);

			Elements elements = doc.select("h1.excerpt-title");
//			System.out.println(elements.select("h1.excerpt-title"));

			link = elements.select("h1.excerpt-title").get(1).select("a").first().attr("href");
//			System.out.println(link);

			news = elements.select("h1.excerpt-title").get(1).text();
//			System.out.println(news);

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

//		System.out.println(url);

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

			// System.out.println(doc);

			// String title = doc.title();
			// System.out.println("title : " + title);

			Elements elements = doc.select("h2.entry-title");
//			System.out.println(elements.select("h2.entry-title"));

			link = elements.select("h2.entry-title").get(0).select("a").first().attr("href");
//			System.out.println(link);
//
			news = elements.select("h2.entry-title").get(0).text();
//		    System.out.println(news);
//			http://kvalitet.fon.bg.ac.rs/предмет/основне-студије/обавезни-предмети/

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

		// String url=
		// "http://organizacija.fon.bg.ac.rs/category/osnovi-organizacije/vesti-osnovi-organizacije/";

//		System.out.println(url);

		int id = -1;
		String news = null;
		String link = null;

		try {
			Document doc = Jsoup.connect(url).get();

			// System.out.println(doc);

			String title = doc.title();
			// System.out.println("title : " + title);

			Elements elements = doc.select("h3.header_font.bd_headings_text_shadow");
			// System.out.println(elements);

			// System.out.println(elements.select("h3.header_font.bd_headings_text_shadow").get(0));

			link = elements.select("h3.header_font.bd_headings_text_shadow").get(0).select("a").first().attr("href");

//           System.out.println(link);

			// id pravi problem, treba pretresti web scrape
			// id = Integer.parseInt(idStr.substring(7));

			news = elements.select("h3.header_font.bd_headings_text_shadow").get(0).text();
//            System.out.println(news);
			// news = news.replace("Permalink to ", "");
			// System.out.println(news);

		} catch (Exception e) {
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
		// String url =
		// "http://laboi.fon.bg.ac.rs/osnovne-studije/operaciona-istrazivanja-2/";

		try {
			Document doc = Jsoup.connect(url).get();

			// System.out.println(doc);

			// String title = doc.title();
			// System.out.println("title : " + title);

			Elements elements = doc.select("div.post-title");

//	        System.out.println(elements.select("h2.entry-title").get(0));

			link = elements.select("h2.entry-title").get(0).select("a").first().attr("href");

//	        System.out.println(link);

			// id pravi problem, treba pretresti web scrape

			news = elements.select("h2.entry-title").get(0).select("a").text();
//	        System.out.println(news);
		} catch (Exception e) {
			System.err.println("Greska: preuzimanje sa sajta!");
		}
		Katedra sub = new Katedra();
		sub.setLink(link);
		sub.setPoruka(news);
		sub.setPredmet(predmet);

		return sub;
	}

	public Katedra getImi(String url, String predmet) {

		// String url= "http://imi.fon.bg.ac.rs/inzenjering-procesa/category/vesti/";

		int id = -1;
		String news = null;
		String link = null;

		try {
			Document doc = Jsoup.connect(url).get();

			// System.out.println(doc);

			String title = doc.title();
			// System.out.println("title : " + title);

			Elements elements = doc.select("header.entry-header");
			// System.out.println(elements);

			// System.out.println(elements.select("h1.entry-title").get(0));

			link = elements.select("h1.entry-title").get(0).select("a").first().attr("href");

//            System.out.println(link);

			// id pravi problem, treba pretresti web scrape
			// id = Integer.parseInt(idStr.substring(7));

			news = elements.select("h1.entry-title").get(0).select("a").first().attr("title");
			// System.out.println(news);
			news = news.replace("Permalink to ", "");
//            System.out.println(news);

		} catch (Exception e) {
			System.err.println("Error with connecting to url or getting data from url");
		}

		Katedra sub = new Katedra();
		sub.setLink(link);
		sub.setPoruka(news);
		sub.setPredmet(predmet);

		return sub;
	}

	public Katedra getHr(String url, String predmet) {

		// String url= "http://hr.fon.bg.ac.rs/";

		int id = -1;
		String news = null;
		String link = null;

		try {
			Document doc = Jsoup.connect(url).get();

			Elements elements = doc.select("div.post-grid-text-wrap");

			link = elements.select("h3.title").get(0).select("a").first().attr("href");

			news = elements.select("h3.title").get(0).select("a").text();


		} catch (Exception e) {
			System.err.println("Error with connecting to url or getting data from url");
		}
		Katedra sub = new Katedra();
		sub.setLink(link);
		sub.setPoruka(news);
		sub.setPredmet(predmet);
		
		String checkStr = news.toLowerCase();

		if(predmet.equals("psihologija") && checkStr.contains("psiho"))
			return sub;
		else if(predmet.equals("sociologija") && checkStr.contains("socio"))
			return sub;
		else if(predmet.equals("mljr") && (checkStr.contains("mljr") || checkStr.contains("menadžment")))
			return sub;
		else if(predmet.equals("trening-i-razvoj") && checkStr.contains("trening"))
			return sub;
		else if(predmet.equals("e-obrazovanje") && checkStr.contains("e-obrazovanje"))
			return sub;
		else if(predmet.equals("vodstvo-i-motivisanje") && checkStr.contains("vođstvo"))
			return sub;
		else {
			sub.setPoruka(null);
			return sub;
		}
	}

	public Katedra getFinansije(String url, String predmet) {

		// String url=
		// "http://finansije.fon.bg.ac.rs/osnovne-studije/finansijski-menadzment/Vesti.html";

		int id = -1;
		String news = null;
		String link = null;

		try {
			Document doc = Jsoup.connect(url).get();

			// System.out.println(doc);

			// String title = doc.title();
			// System.out.println("title : " + title);

			Elements elements = doc.select("div.dataNav.storyNav.fix");
			// System.out.println(elements);

			// System.out.println(elements.select("h2.title").get(0));

			link = elements.select("h2.title").get(0).select("a").first().attr("href");

//            System.out.println(link);

			// id pravi problem, treba pretresti web scrape
			// id = Integer.parseInt(idStr.substring(7));

			news = elements.select("h2.title").get(0).select("a").text();
//            System.out.println(news);
			// news = news.replace("Permalink to ", "");
			// System.out.println(news);

		} catch (Exception e) {
			System.err.println("Error with connecting to url or getting data from url");
		}
		Katedra sub = new Katedra();
		sub.setLink("http://finansije.fon.bg.ac.rs" + link);
		sub.setPoruka(news);
		sub.setPredmet(predmet);

		return sub;
	}

	public Katedra getEng(String url, String predmet) {

		String news = null;
		String link = null;

		try {
			Document doc = Jsoup.connect(url).get();

			Elements elements = doc.select("h1.entry-title");

			link = elements.select("h1.entry-title").get(0).select("a").first().attr("href");

			news = elements.select("h1.entry-title").get(0).text();
		
		} catch (Exception e) {
			System.err.println("Greska: preuzimanje sa sajta!");
		}

		Katedra sub = new Katedra();
		sub.setLink(link);
		sub.setPoruka(news);
		sub.setPredmet(predmet);
		
		String checkStr = news.toLowerCase();
		
		if(predmet.equals("engleski1") && checkStr.contains("1"))
			return sub;
		else if(predmet.equals("engleski2") && checkStr.contains("2"))
			return sub;
		else if(predmet.equals("engleski3") && checkStr.contains("3"))
			return sub;
		else {
			sub.setPoruka(null);
			return sub;
		}
		
	}

	public Katedra getEkonomija(String url, String predmet) {

		String news = null;
		String link = null;
		// String url = "http://ekonomija.fon.bg.ac.rs/novosti/";

		try {
			Document doc = Jsoup.connect(url).get();

			// System.out.println(doc);

			// String title = doc.title();
			// System.out.println("title : " + title);

			Elements elements = doc.select("h2.entry-title");

			System.out.println(elements.select("h2.entry-title").get(0));

			link = elements.select("h2.entry-title").get(0).select("a").first().attr("href");

			System.out.println(link);

			// id pravi problem, treba pretresti web scrape

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

	public Katedra getCtm(String url, String predmet) {

		// String url= "http://ctm.fon.bg.ac.rs/menadzment-tehnologije-i-razvoja/";

		int id = -1;
		String news = null;
		String link = null;

		try {
			Document doc = Jsoup.connect(url).get();

			// System.out.println(doc);

			// String title = doc.title();
			// System.out.println("title : " + title);

			Elements elements = doc.select("div.post-content");
			// System.out.println(elements);

//            System.out.println(elements.select("h2.post-title.entry-title").get(0));

			link = elements.select("h2.post-title.entry-title").get(0).select("a").first().attr("href");

//            System.out.println(link);

			// id pravi problem, treba pretresti web scrape
			// id = Integer.parseInt(idStr.substring(7));

			news = elements.select("h2.post-title.entry-title").get(0).select("a").text();
//            System.out.println(news);
			// news = news.replace("Permalink to ", "");
			// System.out.println(news);

		} catch (Exception e) {
			System.err.println("Error with connecting to url or getting data from url");
		}
		Katedra sub = new Katedra();
		sub.setLink(link);
		sub.setPoruka(news);
		sub.setPredmet(predmet);

		return sub;
	}
}
