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

	ProveriMath proveri = new ProveriMath();
	ProveriMMKLAB proveriMMKLAB = new ProveriMMKLAB();
	ProveriIS proveriIs = new ProveriIS();
	
	DataFromSite data = new DataFromSite();
	

	@Autowired
	private PushNotificationService pushNotificaitonService;

	public AppScheduler() {
		// TODO Auto-generated constructor stub
	}

	// Executes each 500 ms
	@Scheduled(fixedRate = 60000)
	public void checkRecords() {
		System.err.println("Proslo 60s");

		

		ProveriMath proveraMate = new ProveriMath();
		LinkedList<Katedra> predmetiSaNovomVesti = proveraMate.proveraMate();
		
		System.out.println("Predmet za koje je izasla nova vest:");
		for (Katedra subject : predmetiSaNovomVesti) {
			
			 NewNotification newNot = new NewNotification();
			 newNot.setPoruka(subject.getPoruka());
			 newNot.setPredmet(subject.getPredmet());
			 
			 try { pushNotificaitonService.sendTopic(newNot,subject.getPredmet());
			  } catch
			  (FirebaseMessagingException e) { // TODO Auto-generated catch block
			  e.printStackTrace(); }
			  
			 
			 System.out.println(subject.getPredmet());
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
	

