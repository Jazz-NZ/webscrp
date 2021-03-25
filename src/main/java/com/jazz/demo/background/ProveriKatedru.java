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
			
			//vraca listu predmeta sa novom porukom i cita i pise u bazu
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
		
		LinkedList<Katedra> listaPredmetaSaNovomVesti = new LinkedList<>();
		
		//podaci za uporedjivanje
		String predmetUBazi = null;
		String porukaUBazi = null;
		String linkPorukeUBazi = null;
//		String predmetNaSajtu = null;
		String porukaSajt = null;
		String linkPorukeSajt = null;
		
		//otvaranje konekcija za bazu
		Statement statementQuerryRead = DatabaseConnection.getConnection().createStatement();
		Statement statementQueryUpdate = DatabaseConnection.getConnection().createStatement();
		System.out.println("Connection to database established..");
		String queryRead = "select * from "+ imeTabeleUBazi +" ";
        String queryUpdate = null;
		ResultSet getPredmet = statementQuerryRead.executeQuery(queryRead);
	        
		//prolazim kroz tabelu 
	   while(getPredmet.next()) {
	        	
	        //podaci predmeta iz baze
		   predmetUBazi = getPredmet.getString("predmet");
		   porukaUBazi = getPredmet.getString("poruka");
	       linkPorukeUBazi = getPredmet.getString("linkPoruke"); 	
		   
		   System.out.println("predmet : " + predmetUBazi);
		   System.out.println("poruka u bazi: " + porukaUBazi);
	       System.out.println("link poruke u bazi: " + linkPorukeUBazi);

	      
	       Katedra katedra = vratiKatedru(predmetUBazi);
		
	       //podaci sa sajta
		   porukaSajt = katedra.getPoruka();
		   linkPorukeSajt = katedra.getLink();
		   
	        		
			   //ukoliko se poruka na sajtu promenila izasla je nova vest
		   if(porukaSajt != null && !porukaSajt.equals(porukaUBazi)) {
	        			
			  System.out.println("Nova vest izasla!");
			  queryUpdate = "update "+ imeTabeleUBazi +" set poruka = '"+ porukaSajt +"',linkPoruke = '"+linkPorukeSajt +"' where predmet = '"+ predmetUBazi +"'";
			  statementQueryUpdate.executeUpdate(queryUpdate);
	        			
	        		//novi atributi ubaceni u bazu
			  System.out.println("predmet : " + predmetUBazi);
			  System.out.println("poruka: " + porukaSajt);
			  System.out.println("link poruke: " + linkPorukeSajt);		
				   //ubaci predmet u listu predmeta sa novom vesti
			  listaPredmetaSaNovomVesti.add(katedra);
				   
		   }
	   }
	
	   return listaPredmetaSaNovomVesti;
	}

	//vraca katedru za koju se radi webscrp
	private Katedra vratiKatedru(String predmetUBazi) {

		//Koje su nam mogucnosti za predmete koji nemaju uopste vesti za svoju stranicu?
		//da izbacimo samo poslednju vest na celoj katedri ili samo te predmete da izbacimo 
		//jer su skoro svi takvi predmeti uslovni na 4.god a to nam i onako nije jaci deo ciljne grupe
		
		DataFromSite data = new DataFromSite();
			
		switch(imeTabeleUBazi) {
			
			case "predmetimmklab":
				return data.getMmklab(urlKatedre + predmetUBazi, predmetUBazi);

			//ona 4 predmeta 
			case "predmetimath":
				return  data.getMathAllVesti(urlKatedre + predmetUBazi, predmetUBazi); 
				
			//softver-otvorenog-koda, upravljanje-softverskim-projektima, veb-programiranje nemau vesti	
			case "predmetiai":
				return data.getAi(urlKatedre + predmetUBazi, predmetUBazi);
					
			case "predmetilabis":
				return  data.getLabsys(urlKatedre + predmetUBazi, predmetUBazi);
				
			case "predmetiis":
				return  data.getIS(urlKatedre + predmetUBazi + "/vesti", predmetUBazi);
				
			//statistika-u-menadzmentu-2 - jedini predmet na statlabu za koji uopste nigde nema vesti
			case "predmetistatlab":
				return data.getStatlab(urlKatedre + predmetUBazi, predmetUBazi);
				
			//softverski-paterni i napredne-java-tehnologije-2 nemaju vesti
			case "predmetisilab":
				return data.getSilab(urlKatedre + predmetUBazi +"/?lang=lat", predmetUBazi);
			
				
				
			}
		
	       //nikad nece biti null
	       return null;
	}


}
