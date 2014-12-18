package netgloo.models;

import javax.transaction.Transactional;

/**
 * Repository for the entity Company.
 * 
 * @see netgloo.models.UserBaseRepository
 */
@Transactional
public interface CompanyRepository extends UserBaseRepository<Company> { }
