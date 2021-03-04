package com.jazz.demo.background;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import com.jazz.demo.DataFromSite;
import com.jazz.demo.Katedra;
import com.jazz.demo.background.database.DatabaseConnection;

public class ProveriKatedru {

	//dve nezavinse prom za svaki predmet su adresa stranice katedre i ime tabele te stranice u bazi
	private String urlKatedre ; 
	private String imeTabeleUBazi;
	
	public ProveriKatedru(){
		
	}
	
	public ProveriKatedru(String urlKatedre,String imeTabeleUBazi) {
		this.urlKatedre = urlKatedre;
		this.imeTabeleUBazi = imeTabeleUBazi;
	}

	
	public String getUrlKatedre() {
		return urlKatedre;
	}

	public void setUrlKatedre(String urlKatedre) {
		this.urlKatedre = urlKatedre;
	}

	public String getImeTabeleUBazi() {
		return imeTabeleUBazi;
	}

	public void setImeTabeleUBazi(String imeTabeleUBazi) {
		this.imeTabeleUBazi = imeTabeleUBazi;
	}

	//vraca listu sa predmetima za koje je izaslo novo obavestenje za odgovarajucu katedru
	
	public LinkedList<Katedra> proveraKatedre(){
		
		try {
			
			return citajIPisiUBazu();
		
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Greska sa sql statementima pri citanju i pisanju u bazu!");
		}
		
		//ovo se nece nikad ispisati
		System.out.println("Provera katedre ne radi!");
		return null;
	}
	
	private LinkedList<Katedra> citajIPisiUBazu() throws SQLException {
		
		DataFromSite data = new DataFromSite();
		LinkedList<Katedra> listaPredmetaSaNovomVesti = new LinkedList<>();
		
		String predmetUBazi = null;
		String porukaUBazi = null;
		String predmetNaSajtu = null;
		String porukaNova = null;
		Katedra subject = null;
		
		//otvaranje konekcija za bazu
		Statement statementQuerryRead = DatabaseConnection.getConnection().createStatement();
		Statement statementQueryUpdate = DatabaseConnection.getConnection().createStatement();
		System.out.println("Connection to database established..");
		String queryRead = "select * from "+ imeTabeleUBazi +" ";
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
		   
		   //OVDE JE SAMO ZAJEB STO MORA DA SE PITA OVAKO zbog metoda nad objektom data
		   // i onda u sustini nisto nismo uradili :D ,kako resiti ovo da moze da radi ovako ?
		   if(imeTabeleUBazi.equals("predmetimmklab")) 
			   subject = data.getMmklab(urlKatedre + predmetUBazi, predmetUBazi); 
		   
		   else if(imeTabeleUBazi.equals("predmetimath"))
			   subject = data.getMathAllVesti(urlKatedre + predmetUBazi, predmetUBazi); 

		   
		   predmetNaSajtu =  subject.getPredmet();
		   porukaNova = subject.getPoruka();
	        		
			   //ukoliko si nasao predmet u bazi i poruka se promenila 
		   if(predmetNaSajtu.equals(predmetUBazi) && porukaNova != null && !porukaNova.equals(porukaUBazi)) {
	        			
			  System.out.println("Nova vest izasla!");
			  queryUpdate = "update "+ imeTabeleUBazi +" set poruka = '"+ porukaNova +"' where predmet = '"+ predmetUBazi +"'";
			  statementQueryUpdate.executeUpdate(queryUpdate);
	        			
	        		//novi atributi ubaceni u bazu
			  System.out.println("predmet : " + predmetNaSajtu);
			  System.out.println("poruka: " + porukaNova);
	        			
				   //ubaci predmet u listu predmeta sa novom vesti
			  listaPredmetaSaNovomVesti.add(subject);
				   
		   }
	   }
	
	   return listaPredmetaSaNovomVesti;
	}


}
