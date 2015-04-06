package netgloo.controllers;

import netgloo.models.User;
import netgloo.models.UserRepository;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

  @Autowired
  private UserRepository userRepository;
  
  /**
   * GET  /  -> show the index page.
   */
  @RequestMapping("/")
  public String index() {
    return "index.html";
  }
  
  /**
   * POST  /user   -> create a new user.
   * 
   * @param user A json object representing the user.
   * @return An http 2xx status in case of success.
   */
  @RequestMapping(
      value = "/user",
      method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public ResponseEntity<?> addUser(@RequestBody User user) {
    user.setCreateTime(new DateTime());
    userRepository.save(user);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  
} // class MainController
