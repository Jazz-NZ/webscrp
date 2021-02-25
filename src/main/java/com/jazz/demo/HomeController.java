package com.jazz.demo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.jazz.demo.dao.SubjectsRepo;
import com.jazz.demo.fcm.PushNotificationService;

@Controller
public class HomeController {
	
	@Autowired
	SubjectsRepo repo;
	
	ToShow g_toshow = null;
	
	//koristi se consturctor injection
	private PushNotificationService pushNotificaitonService;
	
	public HomeController(PushNotificationService pushNotificaitonService) {
		this.pushNotificaitonService = pushNotificaitonService;
	}
	
	@GetMapping(value = "/")
	public String home() {
		
		DataFromSite data = new DataFromSite();
		Subject  neki =	data.getMath("http://math.fon.bg.ac.rs/vesti");
			
		int id = 560;
		
		 ModelAndView mv = new ModelAndView();
		 
		
		//101 mata
		Subject mata = repo.findById(101).orElse(new Subject());
		
		System.out.println("Vest pre update " + mata.getVest());
		
		
		
		if(id>mata.getVest()) {
			
			mata.setVest(id);
			repo.save(mata);
		}
		
		System.out.println("Vest posle update "+mata.getVest());
		
		

		
		
		System.out.println(id);
		return "home.jsp";
	}

	@RequestMapping("/show")
	public ModelAndView show(){
		
		//preuzima podatke sa sajta
		DataFromSite data = new DataFromSite();
		//getMath vraca  objekat tipa Subject
		Subject  neki =	data.getMath("http://math.fon.bg.ac.rs/vesti");
		
		ModelAndView mv = new ModelAndView("show.jsp");
		Subject mata = repo.findById(101).orElse(new Subject());
		
		String nesto;
		
		
		NewNotification newNot = new NewNotification();
		
		int id = 560;
		 
		
		//101 mata
		//Subject mata = repo.findById(101).orElse(new Subject());
		
		System.out.println("Vest pre update " + mata.getVest());
		
		if(neki.getVest()>mata.getVest()) {
			
			mata.setVest(neki.getVest());
			mata.setPoruka(neki.getPoruka());
			
			newNot.setPoruka(mata.getPoruka());
			newNot.setPredmet("Matematika");
			
			//salje obavestenje samo kada je izasno novo
			
			 try {
					pushNotificaitonService.sendTopic(newNot,"Matematika1");
					
					
				} catch (FirebaseMessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			repo.save(mata);
			
		}
		Subject mata1 = new Subject();
		mata1.setPoruka("Nikola");
		mata1.setId(102);
		mata1.setVest(44);
		//repo.save(mata1);
		
		
		
		
		 nesto = mata.getPoruka();
		 
		 
		 
		 System.out.println("Vest posle update: "+mata.getVest());
		 mv.addObject(nesto);
		
		return mv;
		
	}
	
	
	// u test.jsp se nalazi samo prikaz padajuceg menija koji treba da se prikaze
	@RequestMapping("/test")
	public ModelAndView test() {
		
		ModelAndView mv = new ModelAndView("test.jsp");
		
		
		
		
		return mv;
		
	}
	
	//podaci bi mogli da se ucitaju kao lista stringova, vrednosti u check boxu
	// bi trebale da idu 1,2,3... i na osnovu toga de se ucitaju odredjeni sajtovi
	
	//sve vrednosti se prikazuju u print.jsp u
	@RequestMapping(value = { "/selected" }, method = RequestMethod.GET) 
	public ModelAndView addAlien(MathFon mathfon) { // dobija deo linka koji vodi do odgovarajuceg sajta
		ModelAndView mv = new ModelAndView("print.jsp");
		
		
		System.out.println(mathfon.getMata1() + " "+ mathfon.getMata2() + " " + mathfon.getUmp());
		
		
		
		DataFromSite data = new DataFromSite();
		
		String mathUrl = "http://math.fon.bg.ac.rs/kursevi/";
		
		
		
		
		LinkedList<String> list = mathfon.returnList();
		LinkedList<Subject> subjects = new LinkedList<>();
		
		
		
		
		for(int i = 0; i<list.size(); i++) {
			
			subjects.add( data.getMathAllVesti(mathUrl+list.get(i),list.get(i)));
			
			
		}
		
		//u print.jsp ispisuje listu objekata
		
		mv.addObject("lists", subjects);
		
		
		
		return mv;
	}
/*
 
 	@RequestMapping(value = { "/employee" }, method = RequestMethod.GET)
public ModelAndView listEmployee() {
    System.out.println("Kontroler EmployeeController");
    LinkedList<String> list = getList();
    ModelAndView map = new ModelAndView("index");
    map.addObject("lists", list);

    return map;
}
 	
 */
	
}
