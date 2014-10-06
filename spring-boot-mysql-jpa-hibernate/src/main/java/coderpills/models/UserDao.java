package coderpills.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

/**
 * Class UserDao
 * <br/>
 * Class used for data acces for the entity User.
 */
@Repository
@Transactional
public class UserDao {
  
  // ==============
  // PRIVATE FIELDS
  // ==============
  
  @PersistenceContext
  private EntityManager _entityManager;
  
  // ==============
  // PUBLIC METHODS
  // ==============
  
  public void create(User user) {
    _entityManager.persist(user);
    return;
  }
  
  public void delete(User user) {
    if (_entityManager.contains(user))
      _entityManager.remove(user);
    else
      _entityManager.remove(_entityManager.merge(user));
    return;
  }
  
  @SuppressWarnings("unchecked")
  public List<User> getAll() {
    return _entityManager.createQuery("from User").getResultList();
  }
  
  public User getByEmail(String email) {
    return (User) _entityManager.createQuery(
        "from User where email = :email")
        .setParameter("email", email)
        .getSingleResult();
  }

  public User getById(long id) {
    return _entityManager.find(User.class, id);
  }

  public void update(User user) {
    _entityManager.merge(user);
    return;
  }

} // class UserDao
