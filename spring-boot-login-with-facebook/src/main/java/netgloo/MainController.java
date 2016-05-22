package netgloo;

import javax.inject.Inject;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

//  @RequestMapping(value = "/")
//  public String index() {
//    return "index";
//  }

  @Inject
  private Facebook facebook;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String helloFacebook(Model model) {

    if (!facebook.isAuthorized()) {
      return "redirect:/connect/facebook";
    }

    model.addAttribute(facebook.userOperations().getUserProfile());
    PagedList<Post> homeFeed = facebook.feedOperations().getHomeFeed();
    model.addAttribute("feed", homeFeed);

    return "hello";
  }

} // class MainController
