package netgloo.models;

import javax.persistence.Entity;

/**
 * The Company entity (a subtype of User).
 * 
 * @see netgloo.models.User
 */
@Entity
public class Company extends User {

  // ==============
  // PRIVATE FIELDS
  // ==============
  
  // Company's name
  private String name;

  // Company's headquarters city
  private String hqCity;
 
  // ==============
  // PUBLIC METHODS
  // ==============
  
  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @return the hqCity
   */
  public String getHqCity() {
    return hqCity;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @param hqCity the hqCity to set
   */
  public void setHqCity(String hqCity) {
    this.hqCity = hqCity;
  }

} // class Company
