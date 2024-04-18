package co.com.flypass.jpa.postgresql.command.mappers;

import co.com.flypass.jpa.postgresql.command.entities.ClientEntity;
import co.com.flypass.models.Client;

public interface IClientEntityMapper {
    Client toClient(ClientEntity clientEntity);
    ClientEntity toClientEntity(Client client);
}
