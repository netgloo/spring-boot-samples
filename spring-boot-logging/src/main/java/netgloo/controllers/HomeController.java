package netgloo.controllers;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

  // Define the log object for this class
  private static final Logger _log =
      LoggerFactory.getLogger(HomeController.class);

  @RequestMapping("/")
  @ResponseBody
  public String index() {
    
    // Log a simple message
    _log.debug("debug level log");
    _log.info("info level log");
    _log.error("error level log");
    
    // Log a formatted string with parameters
    _log.info("another info log with {}, {} and {} arguments", 1, "2", 3.0);
    
    return "<a href='http://netgloo.com'>by netgloo</a>";
  }

} // BasicController
