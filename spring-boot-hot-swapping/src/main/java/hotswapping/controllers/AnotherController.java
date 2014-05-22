package hotswapping.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AnotherController {

  @RequestMapping("/example")
  public String example1() {
    return "folder/example";
  }

}
