package com.jazz.demo.fcm;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.jazz.demo.NewNotification;

@RestController
public class PushNotificationController {

    private PushNotificationService pushNotificationService;

    public PushNotificationController(PushNotificationService pushNotificationService) {
        this.pushNotificationService = pushNotificationService;
    }

    NewNotification newNot = new NewNotification();
    @RequestMapping(
    		  value = "/notification/topic", 
    		  produces = "application/json", 
    		  method = {RequestMethod.GET, RequestMethod.POST})
    //@PostMapping("/notification/topic")
    
    
    public ResponseEntity sendNotification() throws FirebaseMessagingException {
    	NewNotification newNot = new NewNotification();
    	newNot.setPoruka("Poruka");
    	newNot.setPredmet("Predmet");
    	pushNotificationService.sendTopic(newNot,"Matematika1");
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

	/*
	 * @PostMapping("/notification/token")
	 * 
	 * public ResponseEntity sendTokenNotification(@RequestBody
	 * PushNotificationRequest request) {
	 * pushNotificationService.sendPushNotificationToToken(request); return new
	 * ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(),
	 * "Notification has been sent."), HttpStatus.OK); }
	 */
    @GetMapping("/notification/token")
    public ResponseEntity sendTokenNotification() throws FirebaseMessagingException {
    	pushNotificationService.sendToToken();
    	 return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(),"Notification has been sent."), HttpStatus.OK); }
    	
    
    @RequestMapping(
  		  value = "/notification/data", 
  		  produces = "application/json", 
  		  method = {RequestMethod.GET, RequestMethod.POST})

    //@PostMapping("/notification/data")
    public ResponseEntity sendDataNotification(@RequestBody PushNotificationRequest request) {
        pushNotificationService.sendPushNotification(request);
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }
    	
    
    @RequestMapping(
    		  value = "/notification", 
    		  produces = "application/json", 
    		  method = {RequestMethod.GET, RequestMethod.POST})
    //@PostMapping("/notification")
    public ResponseEntity sendSampleNotification() {
        pushNotificationService.sendSamplePushNotification();
        
        return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }
}