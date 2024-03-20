package co.com.flypass.jpa.postgresql.repositories;


import co.com.flypass.jpa.postgresql.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface ClientRepository extends CrudRepository<ClientEntity, Long>, QueryByExampleExecutor<ClientEntity> {

    List<ClientEntity> findByIdentificationNumber(Long id);

}
