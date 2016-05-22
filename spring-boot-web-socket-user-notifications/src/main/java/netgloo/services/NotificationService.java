package netgloo.services;

import netgloo.Notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Service class for sending notification messages.
 */
@Service
public class NotificationService {
  
  // The SimpMessagingTemplate is used to send Stomp over WebSocket messages.
  @Autowired
  private SimpMessagingTemplate messagingTemplate;
  
  /**
   * Send notification to the user subscribed on channel "/user/queue/notify".
   * 
   * @param user The username for the user to send notification.
   */
  public void notify(Notification notification, String user) {
    messagingTemplate.convertAndSendToUser(user, "/queue/notify", notification);
    return;
  }
  
} // class NotificationService
