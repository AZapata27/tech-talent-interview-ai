package co.com.flypass.ports.inbound;


import co.com.flypass.models.Client;

import java.util.List;

public interface ClientUseCasePort {

    Client save(Client client);

    Client update(Client client);

    void deleteClientById(Long clientId);

    List<Client> findClientById(Long clientIdentificationNumber);

}
