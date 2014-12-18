package netgloo.controllers;

import netgloo.models.Company;
import netgloo.models.CompanyRepository;
import netgloo.models.Person;
import netgloo.models.PersonRepository;
import netgloo.models.User;
import netgloo.models.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller class for testing user's repositories classes.
 *
 * @author netgloo
 */
@Controller
public class UserController {

  // ==============
  // PRIVATE FIELDS
  // ==============

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private UserRepository userRepository;

  // ==============
  // PUBLIC METHODS
  // ==============
  
  /**
   * /user/create/person?email=[email]&firstName=[firstName] -> create a new 
   * person user and save it in the database.
   * 
   * @param email The person's email
   * @param firstName The person's first name
   * @return a string describing if the person is succesfully created or not.
   */
  @RequestMapping("/user/create/person")
  @ResponseBody
  public String createPerson(String email, String firstName) {
    try {
      Person person = new Person();
      person.setEmail(email);
      person.setFirstName(firstName);
      personRepository.save(person);
    }
    catch (Exception ex) {
      return "Error creating the person: " + ex.toString();
    }
    return "Person succesfully created!";
  }

  /**
   * /user/create/company?email=[email]&name=[name] -> create a new company 
   * user and save it in the database.
   * 
   * @param email The company's email
   * @param name The company's name
   * @return A string describing if the company is succesfully created or not.
   */
  @RequestMapping("/user/create/company")
  @ResponseBody
  public String createCompany(String email, String name) {
    try {
      Company company = new Company();
      company.setEmail(email);
      company.setName(name);
      companyRepository.save(company);
    }
    catch (Exception ex) {
      return "Error creating the company: " + ex.toString();
    }
    return "Company succesfully created!";
  }

  /**
   * /user/delete?id=[id] -> delete the user having the passed id.
   * 
   * @param id The id for the user to delete
   * @return A string describing if the user is succesfully deleted or not.
   */
  @RequestMapping("/user/delete")
  @ResponseBody
  public String deleteUser(long id) {
    try {
      userRepository.delete(userRepository.findOne(id));
    }
    catch (Exception ex) {
      return "Error deleting the user:" + ex.toString();
    }
    return "User succesfully deleted!";
  }
  
  /**
   * /user/delete/person?id=[id] -> delete the person user having the passed id.
   * 
   * @param id The id for the person to delete
   * @return A string describing if the person is succesfully deleted or not.
   */
  @RequestMapping("/user/delete/person")
  @ResponseBody
  public String deletePerson(long id) {
    try {
      personRepository.delete(new Person(id));
    }
    catch (Exception ex) {
      return "Error deleting the user:" + ex.toString();
    }
    return "User succesfully deleted!";
  }
  
  /**
   * /user/get?email=[email] -> return the user having the passed email.
   * 
   * @param email The email to search in the database.
   * @return The user id or a message error if the user is not found.
   */
  @RequestMapping("/user/get")
  @ResponseBody
  public String getUser(String email) {
    String userId = "";
    String userType = "";
    try {
      User user = userRepository.findByEmail(email);
      userId = String.valueOf(user.getId());
      
      // get the user type
      if (user instanceof Person)
        userType = "Person";
      else if (user instanceof Company)
        userType = "Company";
      
    }
    catch (Exception ex) {
      return "User not found";
    }
    return "The " + userType + " id is: " + userId;
  }
  
  /**
   * /user/update?id=[id]&email=[email]&name=[name] -> get the user with passed
   * id and change its email and name (the firstName if the user is of type 
   * Person).
   * 
   * @param id The id of the user to update.
   * @param email The new email value.
   * @param name The new name for the user.
   * @return A string describing if the user is succesfully updated or not.
   */
  @RequestMapping("/user/update")
  @ResponseBody
  public String update(Long id, String email, String name) {
    try {
      User user = userRepository.findOne(id);
      user.setEmail(email);
      
      // switch on the user type
      if (user instanceof Person) {
        Person person = (Person)user;
        person.setFirstName(name);
      }
      if (user instanceof Company) {
        Company company = (Company)user;
        company.setName(name);
      }
      
      // updates the user accordingly to its type (Person or Company)
      userRepository.save(user);
    }
    catch (Exception ex) {
      return "Error: " + ex.toString();
    }
    return "User successfully updated.";
  }
  
} // class UserController
