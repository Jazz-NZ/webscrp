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
import com.jazz.demo.Katedra;
import com.jazz.demo.background.database.DatabaseConnection;
import com.jazz.demo.dao.SubjectsRepo;
import com.jazz.demo.fcm.PushNotificationService;

public class ProveriMath {

	@Autowired
	private PushNotificationService pushNotificaitonService;
	
	 // @Autowired SubjectsRepo repo;
	 
//	private LinkedList<String> listaSvihPredmeta;
	private LinkedList<Katedra> listaPredmetaSaNovomVesti;
	
	
	public ProveriMath() {

	}

	// vraca listu sa predmetima za koje je izaslo novo obavestenje
	public LinkedList<Katedra> proveraMate() {

//		listaSvihPredmeta = new LinkedList<String>();
//
//		listaSvihPredmeta.add("matematika1");
//		listaSvihPredmeta.add("matematika2");
//		listaSvihPredmeta.add("matematika3");
//		listaSvihPredmeta.add("dms");
//		listaSvihPredmeta.add("numericka-analiza"); 
//		listaSvihPredmeta.add("elementi-teorije-algoritama");
//		listaSvihPredmeta.add("matematika-muzika");
//		listaSvihPredmeta.add("matematicka-logika");// ne mere preko vesti
//		listaSvihPredmeta.add("softverski-paketi");// ne mere preko vesti
//		listaSvihPredmeta.add("osnovi-kompjuterske-geometrije");// ne mere preko vesti
//		listaSvihPredmeta.add("matematicko-programiranje");// ne mere preko vesti

		try {
			
			citajIPisiUBazu();
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Greska sa sql statementima pri citanju i pisanju u bazu!");
		}
		
		return listaPredmetaSaNovomVesti;
	}
	
	public void citajIPisiUBazu() throws SQLException {
		
		DataFromSite data = new DataFromSite();
		String mathUrl = "http://math.fon.bg.ac.rs/kursevi/";
		listaPredmetaSaNovomVesti = new LinkedList<>();
		
		String predmetUBazi = null;
		String porukaUBazi = null;
		String predmetNaSajtu = null;
		String porukaNova = null;
		Katedra subject = null;
		
		//otvaranje konekcija za bazu
		Statement statementQuerryRead = DatabaseConnection.getConnection().createStatement();
		Statement statementQueryUpdate = DatabaseConnection.getConnection().createStatement();
		System.out.println("Connection to database established..");
		String queryRead = "select * from predmeti ";
        String queryUpdate = null;
		ResultSet getPredmet = statementQuerryRead.executeQuery(queryRead);
	        
		//prolazim kroz tabelu 
	   while(getPredmet.next()) {
	        	
	        //podaci za predmet iz baze
		   predmetUBazi = getPredmet.getString("predmet");
		   porukaUBazi = getPredmet.getString("poruka");
	        	
		   System.out.println("predmet : " + predmetUBazi);
		   System.out.println("poruka u bazi: " + porukaUBazi);
	        	
			   //novi podaci sa sajta
		   subject = data.getMathAllVesti(mathUrl + predmetUBazi, predmetUBazi); //samo je korisceno predmetUBazi i tako se ne proazi kroz listu
		   predmetNaSajtu =  subject.getPredmet();
		   porukaNova = subject.getPoruka();
	        		
			   //ukoliko si nasao predmet u bazi i poruka se promenila 
			   //update bazu i dodaj u listu predmeta sa novom vesti 
		   if(predmetNaSajtu.equals(predmetUBazi) && porukaNova != null && !porukaNova.equals(porukaUBazi)) {
	        			
			  System.out.println("Nova vest izasla!");
			  queryUpdate = "update predmeti set poruka = '"+ porukaNova +"' where predmet = '"+ predmetUBazi +"'";
			  statementQueryUpdate.executeUpdate(queryUpdate);
	        			
	        		//novi atributi ubaceni u bazu
			  System.out.println("predmet : " + predmetNaSajtu);
			  System.out.println("poruka: " + porukaNova);
	        			
				   //ubaci predmet u listu predmeta sa novom vesti
			  listaPredmetaSaNovomVesti.add(subject);
				   
			   }
		   
	   }
	}

	

}
