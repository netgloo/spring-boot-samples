package netgloo.controllers;

import netgloo.Notification;
import netgloo.services.NotificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

  @Autowired
  private NotificationService notificationService;

  /**
   * GET  /  -> show the index page.
   */
  @RequestMapping("/")
  public String index() {
    return "index";
  }

  /**
   * GET  /notifications  -> show the notifications page.
   */
  @RequestMapping("/notifications")
  public String notifications() {
    return "notifications";
  }

  /**
   * POST  /some-action  -> do an action.
   * 
   * After the action is performed will be notified UserA.
   */
  @RequestMapping(value = "/some-action", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<?> someAction() {

    // Do an action here
    // ...
    
    // Send the notification to "UserA" (by username)
    notificationService.notify(
      new Notification("hello"), // notification object
      "UserA"                    // username
    );
    
    // Return an http 200 status code
    return new ResponseEntity<>(HttpStatus.OK);
  }

} // class MainController
