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
		System.out.println("Connection to: "+ imeTabeleUBazi +"  database established..");
		String queryRead = "select * from "+ imeTabeleUBazi +" ";
        String queryUpdate = null;
		ResultSet getPredmet = statementQuerryRead.executeQuery(queryRead);
	        
	    System.err.println(imeTabeleUBazi + ":");
		//prolazim kroz tabelu 
	   while(getPredmet.next()) {
	        	
	        //podaci predmeta iz baze
		   predmetUBazi = getPredmet.getString("predmet");
		   porukaUBazi = getPredmet.getString("poruka");
	       linkPorukeUBazi = getPredmet.getString("linkPoruke"); 	
		   
		   System.out.println("predmet : " + predmetUBazi);
		   System.out.println("poruka u bazi: " + porukaUBazi);
	       System.out.println("link poruke u bazi: " + linkPorukeUBazi);
	       System.out.println();
	      
	       Katedra katedra = vratiKatedru(predmetUBazi);
		
	       //podaci sa sajta
		   porukaSajt = katedra.getPoruka();
		   linkPorukeSajt = katedra.getLink();
		   
	        		
			   //ukoliko se poruka na sajtu promenila izasla je nova vest
		   if(porukaSajt != null && !porukaSajt.equals(porukaUBazi)) {
	        			
			  System.out.println("Nova vest izasla!");
			  queryUpdate = "update "+ imeTabeleUBazi +" set poruka = '"+ porukaSajt +"',linkPoruke = '"+linkPorukeSajt +"' where predmet = '"+ predmetUBazi +"'";
			  statementQueryUpdate.executeUpdate(queryUpdate);
	        			
			  System.err.println("Novi atributi ubaceni u bazu:");
			  System.out.println("predmet: " + predmetUBazi);
			  System.out.println("nova poruka: " + porukaSajt);
			  System.out.println("link poruke: " + linkPorukeSajt);	
			  System.out.println();
				   //ubaci predmet u listu predmeta sa novom vesti
			  listaPredmetaSaNovomVesti.add(katedra);
				   
		   }
	   }
	
	   return listaPredmetaSaNovomVesti;
	}

	//vraca katedru za koju se radi webscrp
	private Katedra vratiKatedru(String predmetUBazi) {
		
		DataFromSite data = new DataFromSite();
			
		switch(imeTabeleUBazi) {
			
			case "predmetimmklab":
				return data.getMmklab(urlKatedre + predmetUBazi, predmetUBazi);

			//ona 4 predmeta 
			case "predmetimath":
				return data.getMath(urlKatedre + predmetUBazi, predmetUBazi); 
				
			case "predmetimathaktivnosti":
				return data.getMathAktivnosti(urlKatedre, predmetUBazi);
				
			//softver-otvorenog-koda, upravljanje-softverskim-projektima, veb-programiranje nemaju vesti	
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
			
			case "predmetictm":
				return data.getCtm(urlKatedre + predmetUBazi, predmetUBazi);
				
			case "predmetifinansije":
				return data.getFinansije(urlKatedre + predmetUBazi + "/Vesti.html", predmetUBazi);
				
			case "predmetiimi":
				return data.getImi(urlKatedre + predmetUBazi +"/category/vesti/" , predmetUBazi);
			
			//ova nesrecna katedra je u poslednjih pet godina izbacivala samo vesti za oi1 i oi2 i omaklo joj se skoro iz teorije igara jednu
				//mislim da bi trebali neke predmete da izbacimo jer ovde sada 5 predmeta nema vest
			case "predmetilaboi":
				if(predmetUBazi.equals("operaciona-istrazivanja-1") || predmetUBazi.equals("operaciona-istrazivanja-2"))
					return data.getLaboi(urlKatedre + predmetUBazi, predmetUBazi);
				
				return data.getLaboi(urlKatedre + "izborni-predmeti/" + predmetUBazi, predmetUBazi);
		
			//nemaju nista na sajtu 
			case "predmetiorganizacija":
				return data.getOrganizacija(urlKatedre + predmetUBazi + "/vesti-" + predmetUBazi, predmetUBazi);
			
			//dobar webscrp,ne znam zasto ne radi
			case "predmetikvalitet":
				return data.getKvalitet(urlKatedre + predmetUBazi + "/", predmetUBazi);
				
			case "predmetiekonomija":
				return data.getEkonomija(urlKatedre + "novosti", predmetUBazi);
				
			case "predmetihr":
				return data.getHr(urlKatedre, predmetUBazi);
				
			case "predmetieng":
				return data.getEng(urlKatedre, predmetUBazi);
				
		}
		
	       //nikad nece biti null
	       return null;
	}


}
