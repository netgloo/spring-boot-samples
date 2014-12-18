package netgloo.models;

import javax.transaction.Transactional;

/**
 * Repository for the entity Person.
 * 
 * @see netgloo.models.UserBaseRepository
 */
@Transactional
public interface PersonRepository extends UserBaseRepository<Person> { }
