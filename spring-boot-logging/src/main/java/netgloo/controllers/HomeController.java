package netgloo.controllers;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

  // Define the log object for this class
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @RequestMapping("/")
  @ResponseBody
  public String index() {
    
    // Log a simple message
    log.debug("debug level log");
    log.info("info level log");
    log.error("error level log");
    
    // Log a formatted string with parameters
    log.info("another info log with {}, {} and {} arguments", 1, "2", 3.0);
    
    return "Proudly handcrafted by " +
        "<a href='http://netgloo.com/en'>Netgloo</a> :)";
  }

} // class HomeController
