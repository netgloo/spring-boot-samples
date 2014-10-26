package coderpills.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

/**
 * Class UserDao
 * <br />
 * This class is used to access data for the User entity.
 * Repository annotation allows the component scanning support to find and 
 * configure the DAO wihtout any XML configuration and also provide the Spring 
 * exceptiom translation.
 * Since we've setup setPackagesToScan and transaction manager on
 * DatabaseConfig, any bean method annotated with Transactional will cause
 * Spring to magically call begin() and commit() at the start/end of the
 * method. If exception occurs it will also call rollback().
 */
@Repository
@Transactional
public class UserDao {
  
  // ==============
  // PRIVATE FIELDS
  // ==============
  
  // An EntityManager will be automatically injected from entityManagerFactory
  // setup on DatabaseConfig class.
  @PersistenceContext
  private EntityManager _entityManager;
  
  // ==============
  // PUBLIC METHODS
  // ==============
  
  /**
   * Method create
   * <br/>
   * Save the user in the database.
   */
  public void create(User user) {
    _entityManager.persist(user);
    return;
  }
  
  /**
   * Method delete
   * <br/>
   * Delete the user from the database.
   */
  public void delete(User user) {
    if (_entityManager.contains(user))
      _entityManager.remove(user);
    else
      _entityManager.remove(_entityManager.merge(user));
    return;
  }
  
  /**
   * Method getAll
   * <br/>
   * Return all the users stored in the database.
   */
  @SuppressWarnings("unchecked")
  public List<User> getAll() {
    return _entityManager.createQuery("from User").getResultList();
  }
  
  /**
   * Method getByEmail
   * <br/>
   * Return the user having the passed email.
   */
  public User getByEmail(String email) {
    return (User) _entityManager.createQuery(
        "from User where email = :email")
        .setParameter("email", email)
        .getSingleResult();
  }

  /**
   * Method getById
   * <br/>
   * Return the user having the passed id.
   */
  public User getById(long id) {
    return _entityManager.find(User.class, id);
  }

  /**
   * Method update
   * <br/>
   * Update the passed user in the database.
   */
  public void update(User user) {
    _entityManager.merge(user);
    return;
  }

} // class UserDao
