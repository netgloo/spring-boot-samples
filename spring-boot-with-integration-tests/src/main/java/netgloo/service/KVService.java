package netgloo.service;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.val;
import netgloo.exceptions.APIException;
import netgloo.exceptions.APIIdNotFoundException;
import netgloo.models.KVEntity;
import netgloo.repository.KVDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class KVService {

    @Autowired
    private final KVDatabase kvDatabase;


    public KVEntity get(Long id) throws APIException {
        log.entering(this.getClass().getName(), "post", id);
        val result = kvDatabase.findById(id);
        log.exiting(this.getClass().getName(), "post", result);
        return result.orElseThrow(() -> new APIIdNotFoundException(id));
    }

    public Long post(String key, String value) throws APIException {
        log.entering(this.getClass().getName(), "post", key);
        val result = kvDatabase.save(new KVEntity(key, value));
        log.exiting(this.getClass().getName(), "post", result);
        return result.getKVEntityId().longValue();
    }

    public KVEntity put(KVEntity entity) throws APIException {
        log.entering(this.getClass().getName(), "put");
        val result = kvDatabase
                .findById(entity.getKVEntityId())
                .map(kvEntity -> {
                    kvEntity.setKVEntityKey(entity.getKVEntityKey());
                    kvEntity.setKVEntityValue(entity.getKVEntityValue());
                    return kvDatabase.save(kvEntity);
                })
                .orElseThrow(() -> new APIIdNotFoundException(entity.getKVEntityId()));
        log.exiting(this.getClass().getName(), "post", result);
        return result;
    }

    public void delete(Long id) {
        log.entering(this.getClass().getName(), "delete", id);
        kvDatabase.deleteById(id);
        log.exiting(this.getClass().getName(), "delete");

    }

    public List<KVEntity> findAll() {
        return kvDatabase.findAll();
    }

    public List<KVEntity> searchByKey(String key) {
        KVEntity kvEntity = new KVEntity(null, key, null);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("key", ExampleMatcher.GenericPropertyMatcher::contains)
                .withIgnorePaths("id", "value");
        Example<KVEntity> example = Example.of(kvEntity, matcher);
        return kvDatabase.findAll(example);
    }
}
