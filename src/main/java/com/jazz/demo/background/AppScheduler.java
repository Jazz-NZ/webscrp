package com.jazz.demo.background;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.jazz.demo.DataFromSite;
import com.jazz.demo.NewNotification;
import com.jazz.demo.Katedra;
import com.jazz.demo.fcm.PushNotificationService;

@Service
public class AppScheduler {

//	ProveriMath proveri = new ProveriMath();
//	ProveriMMKLAB proveriMMKLAB = new ProveriMMKLAB();
//	ProveriIS proveriIs = new ProveriIS();
//	
//	DataFromSite data = new DataFromSite();
	

	@Autowired
	private PushNotificationService pushNotificaitonService;

	public AppScheduler() {
		// TODO Auto-generated constructor stub
	}

	// Executes each 500 ms
	@Scheduled(fixedRate = 60000)
	public void checkRecords() {
		System.err.println("Proslo 60s");

		
		//parametri za proveru za katedru iz mate
//		String urlKatedre = "http://math.fon.bg.ac.rs/kursevi/";
//		String imeTabeleUBazi = "predmetimath";
		
		//parametri za dodatne predmete iz mate
		//String urlKatedre = "http://math.fon.bg.ac.rs/";
		//String imeTabeleUBazi = "predmetimathdodatni";
		
		//parametri za proveru za kateru mmklab 
//		String urlKatedre = "http://www.mmklab.fon.bg.ac.rs/nastava/osnovne-studije/";
//		String imeTabeleUBazi = "predmetimmklab";

		//labis
//		String urlKatedre = "http://labsys.fon.bg.ac.rs/";
//		String imeTabeleUBazi = "predmetilabis";
		
		//is
//		String urlKatedre = "http://is.fon.bg.ac.rs/";
//		String imeTabeleUBazi = "predmetiis";
		
		//statlab
//		String urlKatedre = "http://statlab.fon.bg.ac.rs/predmeti/";
//		String imeTabeleUBazi = "predmetistatlab";
		
		//silab
//		String urlKatedre = "http://silab.fon.bg.ac.rs/category/%D0%B2%D0%B5%D1%81%D1%82%D0%B8/";
//		String imeTabeleUBazi = "predmetisilab";
		
		//ai
		String urlKatedre = "http://ai.fon.bg.ac.rs/osnovne/";
		String imeTabeleUBazi = "predmetiai";

		ProveriKatedru provera = new ProveriKatedru(urlKatedre, imeTabeleUBazi);
		LinkedList<Katedra> predmetiSaNovomVesti = provera.proveraKatedre();
		
		System.out.println("Predmei za koje je izasla nova vest:");
		
		for (Katedra subject : predmetiSaNovomVesti) {
			
			 NewNotification newNot = new NewNotification();
			 newNot.setPoruka(subject.getPoruka());
			 newNot.setPredmet(subject.getPredmet());
			 newNot.setLink(subject.getLink());

			 System.out.println(subject.getLink());
			 
			 
			 try { pushNotificaitonService.sendTopic(newNot,subject.getPredmet());
			  } catch
			  (FirebaseMessagingException e) { // TODO Auto-generated catch block
			  e.printStackTrace(); }
			  

			   }
			 
		}
		
		
//		  NewNotification newNot = new NewNotification();
//		  newNot.setPoruka(proveri.proveraMate().get(i).getPoruka());
//		  newNot.setPredmet("Matematika 1");
		  
		  
//		  
//		  NewNotification newNotAkt = new NewNotification();
//		  newNotAkt.setPoruka(proveri.proveraAktivnosti().getPoruka());
//		  newNotAkt.setPredmet("Matematika aktivnost");
//		 
		
		/*
		 * NewNotification newNot = new NewNotification();
		 * newNot.setPoruka(proveriMMKLAB.proveraMmklab().get(i).getPoruka());
		 * newNot.setPredmet("OIKT");
		 */
		
		/*
		 * NewNotification newNot = new NewNotification();
		 * newNot.setPoruka(proveriIs.proveraIsVesti().get(i).getPoruka());
		 * newNot.setPredmet("SPA");
		 * 
		 * 
		 * 
		 * try { pushNotificaitonService.sendTopic(newNot,"Matematika"); } catch
		 * (FirebaseMessagingException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		// dokle god lista nije prazna salje se obavestenje jer se u listi nalaze
		// predmeti za koje je izasla nova vest
		
		}
	

