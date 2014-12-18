package netgloo.models;

import javax.transaction.Transactional;

/**
 * Repository for the entity User.
 * 
 * @see netgloo.models.UserBaseRepository
 */
@Transactional
public interface UserRepository extends UserBaseRepository<User> { }
