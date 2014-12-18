package netgloo.models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Base repository for the entity User and its subclasses, extending the 
 * CrudRepository interface provided by spring data jpa.
 * The following methods are some of the ones available from CrudRepository: 
 * save, delete, deleteAll, findOne and findAll.
 * 
 * All methods in this repository will be available in the UserRepository,
 * in the PersonRepository and in the CompanyRepository.
 * 
 * @author netgloo
 */
@NoRepositoryBean
public interface UserBaseRepository<T extends User> 
extends CrudRepository<T, Long> {

  /**
   * Method findByEmail
   * 
   * @param email the user email.
   * @return the user having the passed email or null if no user is found.
   */
  public T findByEmail(String email);
  
} // UserBaseRepository
