package netgloo.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.val;
import netgloo.exceptions.APIException;
import netgloo.models.KVEntity;
import netgloo.service.KVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController  // Because of SpringBoot ResponseBody implementation. We are not returning a view.
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Log
public class KVController {
   
    private final KVService kvService;

    /**
     * Retrieve a key value pair from the database
     *
     * @param id The key to retrieve from the database.
     * @return the value associated with the key.
     */
    @GetMapping("/kv/{id}")
    // Could use @GetMapping instead, but this is more explicit.
    public ResponseEntity<KVEntity> get(HttpServletRequest request, @PathVariable Long id) throws APIException {
        log.entering(this.getClass().getName(), "get", id);
        val result = kvService.get(id);
        log.exiting(this.getClass().getName(), "get", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * This is a GET all request. It is used to retrieve all data from the server, but not to modify it.
     *
     * @return all records in the database.
     */
    @GetMapping("/kv")
    public ResponseEntity<List<KVEntity>> getAll() {
        log.entering(this.getClass().getName(), "getAll");
        val result = kvService.findAll();
        log.exiting(this.getClass().getName(), "getAll", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * This is a POST request. It is used to create a whole new record for example, customer information, file upload, etc.
     *
     * @param key   The key to store in the database.
     * @param value The value to store in the database.
     * @return the value associated with the key.
     */
    @PostMapping("/kv")
    // Could use @PostMapping instead, but this is more explicit.
    public ResponseEntity<Long> post(@RequestParam String key, @RequestParam String value) throws APIException {
        log.entering(this.getClass().getName(), "post", key);
        val result = kvService.post(key, value);
        log.exiting(this.getClass().getName(), "post", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    /**
     * This is a PUT request. It is used to update an existing record.
     *
     * @param kvEntity The entity to update
     * @return the value associated with the key.
     */
    @PutMapping("/kv")
    // Could use @PutMapping instead, but this is more explicit.
    public ResponseEntity<KVEntity> put(@RequestBody @Valid KVEntity kvEntity) throws APIException {
        log.entering(this.getClass().getName(), "put", kvEntity);
        kvService.put(kvEntity);
        log.exiting(this.getClass().getName(), "put");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * This is a DELETE request. It is used to delete an existing record.
     *
     * @param id The key to delete from the database.
     * @return the value associated with the key.
     */
    @DeleteMapping("/kv/{id}")
    // Could use @DeleteMapping instead, but this is more explicit.
    public ResponseEntity<KVEntity> delete(@PathVariable Long id) throws APIException {
        log.entering(this.getClass().getName(), "delete", id);
        kvService.delete(id);
        log.exiting(this.getClass().getName(), "delete");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
