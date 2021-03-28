package com.jazz.demo.background;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.jazz.demo.DataFromSite;
import com.jazz.demo.NewNotification;
import com.jazz.demo.background.database.DatabaseConnection;
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
	@Scheduled(fixedRate = 120000)
	public void checkRecords() {
		System.err.println("Proslo 120s");

			//OSTALO : EKONOMIJA, HR , ENG
		// I OVO TREBA DA SE CUVA U BAZI ? TABELA JOS JEDNA
		
		//parametri za dodatne predmete iz mate - obrisao sam ovu tabelu
		//String urlKatedre = "http://math.fon.bg.ac.rs/";
		//String imeTabeleUBazi = "predmetimathdodatni";
		
		
		try { posaljiNotiSvimPredmetima();
		
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.err.println("Greska za connection1"); 
		}

			 
	}

	private void posaljiNotiSvimPredmetima() throws SQLException {

		String imeTabeleUBazi = null;
		String urlKatedre = null;
		
		Statement statementQuerryRead = DatabaseConnection.getConnection().createStatement();
		System.out.println("Connection1 to database established..");
		String queryRead = "select * from katedre ";
		ResultSet getKatedra = statementQuerryRead.executeQuery(queryRead);

		while(getKatedra.next()) {
			
			imeTabeleUBazi = getKatedra.getString("katedra");
			urlKatedre = getKatedra.getString("urladresa");
			
			ProveriKatedru provera = new ProveriKatedru(urlKatedre, imeTabeleUBazi);
			LinkedList<Katedra> predmetiSaNovomVesti = provera.proveraKatedre();
			
			System.err.println(imeTabeleUBazi +" za koje je izasla nova vest:");
			
			for (Katedra subject : predmetiSaNovomVesti) {
				
				 NewNotification newNot = new NewNotification();
				 newNot.setPoruka(subject.getPoruka());
				 newNot.setPredmet(subject.getPredmet());
				 newNot.setLink(subject.getLink());

//				 System.out.println(subject.getLink());
				 System.out.println(subject.getPredmet());
				 
				 
				 try { pushNotificaitonService.sendTopic(newNot,subject.getPredmet());
				  } catch
				  (FirebaseMessagingException e) { // TODO Auto-generated catch block
				  e.printStackTrace(); }
				  

			}
			
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
	

