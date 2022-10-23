package netgloo.repository;

import netgloo.models.KVEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KVDatabase extends JpaRepository<KVEntity, Long> {
}
