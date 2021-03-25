package com.jazz.demo.fcm;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.jazz.demo.NewNotification;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class PushNotificationService {

    @Value("#{${app.notifications.defaults}}")
    private Map<String, String> defaults;

    private Logger logger = LoggerFactory.getLogger(PushNotificationService.class);
    private FCMService fcmService;

    public PushNotificationService(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    @Scheduled(initialDelay = 60000, fixedDelay = 60000)
    public void sendSamplePushNotification() {
        try {
            fcmService.sendMessageWithoutData(getSamplePushNotificationRequest());
            System.out.println("Poslato");
            
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
        }
    }
    //odavde se salje obavestenje
    public void sendTopic(NewNotification newNot, String topic) throws FirebaseMessagingException {
    	//String topic = "Matematika1"; //highScores

    	// See documentation on defining a message payload.
    	Message message = Message.builder()
    	    .putData("predmet", newNot.getPredmet())
    	    .putData("poruka", newNot.getPoruka())
    	    .putData("link", newNot.getLink())
    	    .setTopic(topic)
    	    .build();

    	// Send a message to the devices subscribed to the provided topic.
    	String response = FirebaseMessaging.getInstance().send(message);
    	// Response is a message ID string.
    	System.out.println("Successfully sent message: " + response);
    }
    public void sendToToken() throws FirebaseMessagingException {
        // [START send_to_token]
        // This registration token comes from the client FCM SDKs.
        String registrationToken = "eFlL2sVbdXg:APA91bHa074aXqZORDGGIRiV75DDQxPsT1QVdS9nX0iV2jLuAHNgoJTsb5BehRJV4w_T_ki4QLA0OOHJ_nCrvTxj1o8YTTBJNNPF6XJGUwKQoWc79bej5c9YAVgrtfueNf2pXxpMQg2J";
       // registrationToken = "cY6czlx1S2yCvMrulhw3hv:APA91bEKsayT1fF19iUCjhM_NdeD41pk48aDIGzipkd3yeEY7j5RZ9r0CReUYABI3qhlpd_bgSsXMdiblHh5Vggd1ll-mxidzFsvkmHi32zK73LaYfuaZgcZXODSFR_8JpryNI7vRNpg";
        
        // See documentation on defining a message payload.
        Message message = Message.builder()
            .putData("score", "Nikola")
            .putData("time", "2:45")
            .setToken(registrationToken)
            .build();

        // Send a message to the device corresponding to the provided
        // registration token.
        String response = FirebaseMessaging.getInstance().send(message);
        // Response is a message ID string.
        System.out.println("Successfully sent message: " + response);
        // [END send_to_token]
      }

    public void sendPushNotification(PushNotificationRequest request) {
        try {
            fcmService.sendMessage(getSamplePayloadData(), request);
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
        }
    }

    public void sendPushNotificationWithoutData(PushNotificationRequest request) {
        try {
            fcmService.sendMessageWithoutData(request);
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
        }
    }


    public void sendPushNotificationToToken(PushNotificationRequest request) {
        try {
            fcmService.sendMessageToToken(request);
        } catch (InterruptedException | ExecutionException e) {
            logger.error(e.getMessage());
        }
    }


    private Map<String, String> getSamplePayloadData() {
        Map<String, String> pushData = new HashMap<>();
        pushData.put("messageId", defaults.get("payloadMessageId"));
        pushData.put("text", defaults.get("payloadData") + " " + LocalDateTime.now());
        return pushData;
    }


    private PushNotificationRequest getSamplePushNotificationRequest() {
        PushNotificationRequest request = new PushNotificationRequest(defaults.get("title"),
                defaults.get("message"),
                defaults.get("topic"));
        return request;
    }


}