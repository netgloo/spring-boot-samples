package netgloo.search;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import netgloo.models.User;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

/**
 * Search methods for the entity User using Hibernate search.
 * The Transactional annotation ensure that transactions will be opened and
 * closed at the beginning and at the end of each method.
 * 
 * @author netgloo
 */
@Repository
@Transactional
public class UserSearch {

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // Spring will inject here the entity manager object
  @PersistenceContext
  private EntityManager entityManager;


  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  /**
   * A basic search for the entity User. The search is done by exact match per
   * keywords on fields name, city and email.
   * 
   * @param text The query text.
   */
  public List<User> search(String text) {
    
    // get the full text entity manager
    FullTextEntityManager fullTextEntityManager =
      org.hibernate.search.jpa.Search.
      getFullTextEntityManager(entityManager);
    
    // create the query using Hibernate Search query DSL
    QueryBuilder queryBuilder = 
      fullTextEntityManager.getSearchFactory()
      .buildQueryBuilder().forEntity(User.class).get();
    
    // a very basic query by keywords
    org.apache.lucene.search.Query query =
      queryBuilder
        .keyword()
        .onFields("name", "city", "email")
        .matching(text)
        .createQuery();

    // wrap Lucene query in an Hibernate Query object
    org.hibernate.search.jpa.FullTextQuery jpaQuery =
      fullTextEntityManager.createFullTextQuery(query, User.class);
  
    // execute search and return results (sorted by relevance as default)
    @SuppressWarnings("unchecked")
    List<User> results = jpaQuery.getResultList();
    
    return results;
  } // method search


} // class
