package co.com.flypass.jpa.postgresql.command.repositories;


import co.com.flypass.jpa.postgresql.command.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

    List<ClientEntity> findByIdentificationNumber(Long id);

}
