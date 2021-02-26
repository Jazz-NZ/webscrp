package com.jazz.demo.background;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.jazz.demo.DataFromSite;
import com.jazz.demo.NewNotification;
import com.jazz.demo.Subject;
import com.jazz.demo.background.database.DatabaseConnection;
import com.jazz.demo.dao.SubjectsRepo;
import com.jazz.demo.fcm.PushNotificationService;

public class ProveriMath {

	@Autowired
	private PushNotificationService pushNotificaitonService;
	
	 // @Autowired SubjectsRepo repo;
	 

	public ProveriMath() {

	}

	// vraca listu sa predmetima za koje je izaslo novo obavestenje

	public LinkedList<Subject> proveraMate() {

		DataFromSite data = new DataFromSite();

		String mathUrl = "http://math.fon.bg.ac.rs/kursevi/";

		LinkedList<String> list = new LinkedList<String>();

		list.add("matematika1");
		list.add("matematika2");
		list.add("matematika3");
		list.add("dms");
		list.add("numericka-analiza");
		list.add("elementi-teorije-algoritama");
		list.add("matematika-muzika");
		list.add("matematicka-logika");
		list.add("softverski-paketi");
		list.add("osnovi-kompjuterske-geometrije");
		list.add("matematicko-programiranje");

		
		LinkedList<Subject> subjects = new LinkedList<>();


			//citaj iz baze
			//proveri da li je izasla nova vest,ako jeste update vest i poruku i onda dodaj u listu
			
			String predmetUBazi = null;
			int vestUBazi = 0;
			String porukaUBazi = null;
			
			String predmetProvera = null;
			int vestNova = 0;
			String porukaNova = null;
			Subject subject = null;
			
			try {
			
				Statement statementQuerryRead = DatabaseConnection.getConnection().createStatement();
				Statement statementQueryUpdate = DatabaseConnection.getConnection().createStatement();
				System.out.println("Connection to database established..");
				String queryRead = "select * from predmeti ";
		        String queryUpdate = null;
				ResultSet getPredmet = statementQuerryRead.executeQuery(queryRead);
		        
				//prolazim kroz tabelu 
		        while(getPredmet.next()) {
		        	
		        	//podaci za predmet
		        	predmetUBazi = getPredmet.getString("predmet");
		        	vestUBazi = getPredmet.getInt("vest");
		        	porukaUBazi = getPredmet.getString("poruka");
		        	
		        	System.out.println("predmet : " + predmetUBazi);
	    			System.out.println("vest u bazi: " + vestUBazi);
	    			System.out.println("poruka u bazi: " + porukaUBazi);
		        	
		        	for (int i = 0; i < list.size(); i++) {
		        		
		        		System.out.println("nesto");
		        		
		        		// zbog ovoga je vrv sporo zato sto svak put poziva JSoup i uzima url
		        		//kako da na drugi nacin uzmem predmet,vest i poruku za svaki predmet?
		        		// ne znam sta tacno radi getMathAll i da li sme toliko puta da se pozove
		        		predmetProvera = data.getMathAllVesti(mathUrl + list.get(i), list.get(i)).getPredmet();
		        		vestNova = data.getMathAllVesti(mathUrl + list.get(i), list.get(i)).getVest();
		        		porukaNova = data.getMathAllVesti(mathUrl + list.get(i), list.get(i)).getPoruka();
		        		subject = data.getMathAllVesti(mathUrl + list.get(i), list.get(i));
		        		
		        		if(predmetProvera.equals(predmetUBazi) && vestNova > vestUBazi) {
		        			
		        			//update table - nova vest je izasla
		        			System.out.println("Nova vest izasla!");
		        			queryUpdate = "update predmeti set vest = ' "+ vestNova +" ',poruka = '"+ porukaNova +"' where predmet = '"+ predmetUBazi +"'";
		        			statementQueryUpdate.executeUpdate(queryUpdate);
		        			
		        			//novi atributi ubaceni u bazu
		        			System.out.println("predmet : " + predmetProvera);
			    			System.out.println("nova vest: " + vestNova);
			    			System.out.println("poruka: " + porukaNova);
		        			
			    			//ubaci predmet u listu
			        		subjects.add(subject);
		        		}
		        		
		        	}
		        	
		        }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		return subjects;
	}
	
	public Subject proveraAktivnosti() { 
		
		DataFromSite data = new DataFromSite();
		Subject subject = data.getMathAktivnosti();
		
		//logika za bazu npr vrati neku defoltnu poruku i kad je vrati u AppSheduler se proveri poruka i ne posalje se obavestenje
		
		System.out.println("Sta ti ispisujes? " + subject.getPredmet());
		return subject;
	}

}
