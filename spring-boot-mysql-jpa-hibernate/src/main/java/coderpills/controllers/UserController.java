package coderpills.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import coderpills.models.User;
import coderpills.models.UserDao;

@Controller
public class UserController {

  @Autowired
  private UserDao _userDao;
  
  @RequestMapping(value="/create")
  @ResponseBody
  public String create(String email, String name) {
    try {
      User user = new User(email, name);
      _userDao.create(user);
    }
    catch (Exception ex) {
      return ex.toString();
    }
    return "User succesfully created!";
  }
  
  @RequestMapping(value="/delete")
  @ResponseBody
  public String delete(long id) {
    try {
      User user = new User(id);
      _userDao.delete(user);
    }
    catch (Exception ex) {
      return ex.toString();
    }
    return "User succesfully deleted!";
  }
  
  @RequestMapping(value="/get-by-email")
  @ResponseBody
  public String getByEmail(String email) {
    String userId;
    try {
      User user = _userDao.getByEmail(email);
      userId = String.valueOf(user.getId());
    }
    catch (Exception ex) {
      return "User not found";
    }
    return "The user id is: " + userId;
  }
  
  @RequestMapping(value="/update")
  @ResponseBody
  public String updateName(long id, String email, String name) {
    try {
      User user = _userDao.getById(id);
      user.setEmail(email);
      user.setName(name);
      _userDao.update(user);
    }
    catch (Exception ex) {
      return ex.toString();
    }
    return "User succesfully updated!";
  }

} // class UserController
