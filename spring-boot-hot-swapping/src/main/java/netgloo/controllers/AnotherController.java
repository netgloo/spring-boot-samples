package netgloo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AnotherController {

  @RequestMapping("/example-2")
  public String example1() {
    return "folder/example";
  }

}
