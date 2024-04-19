package co.com.flypass.jpa.postgresql.command.repositories;


import co.com.flypass.jpa.postgresql.command.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;


public interface ClientRepositoryPostgres extends CrudRepository<ClientEntity, Long> {

}
