package com.jazz.demo.background;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.jazz.demo.DataFromSite;
import com.jazz.demo.NewNotification;
import com.jazz.demo.Subject;
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

		for (int i = 0; i < list.size(); i++) {

			subjects.add(data.getMathAllVesti(mathUrl + list.get(i), list.get(i)));

		}

		// ovde treba logika za bazu

		return subjects;
	}
	
	public Subject proveraAktivnosti() {
		
		DataFromSite data = new DataFromSite();
		Subject sub = data.getMathAktivnosti();
		
		//logika za bazu npr vrati neku defoltnu poruku i kad je vrati u AppSheduler se proveri poruka i ne posalje se obavestenje
		
		return sub;
	}

}
