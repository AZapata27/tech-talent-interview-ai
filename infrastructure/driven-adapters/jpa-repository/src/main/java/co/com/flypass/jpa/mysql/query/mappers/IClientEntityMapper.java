package co.com.flypass.jpa.mysql.query.mappers;

import co.com.flypass.jpa.mysql.query.entities.ClientEntity;
import co.com.flypass.models.Client;

public interface IClientEntityMapper {
    Client toClient(ClientEntity clientEntity);
    ClientEntity toClientEntity(Client client);
}
