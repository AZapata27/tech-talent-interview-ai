package co.com.flypass.ports.outbound;


import co.com.flypass.models.Client;

public interface ClientRepositoryCommandPort {

    Client save(Client client);

    Client update(Client client);

    void delete(Long clientId);

}
