package co.com.flypass.jpa.mysql.query.repositories;


import co.com.flypass.jpa.mysql.query.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

    List<ClientEntity> findByIdentificationNumber(Long identificationNumber);

}
