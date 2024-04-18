package co.com.flypass.usecase;

import co.com.flypass.models.Client;
import co.com.flypass.ports.inbound.ClientUseCasePort;
import co.com.flypass.ports.outbound.ClientRepositoryCommandPort;
import co.com.flypass.ports.outbound.ClientRepositoryQueryPort;

import java.util.List;


public class ClientUseCase implements ClientUseCasePort {

    private final ClientRepositoryCommandPort clientRepositoryCommandPort;
    private final ClientRepositoryQueryPort clientRepositoryQueryPort;



    public ClientUseCase(ClientRepositoryCommandPort clientRepositoryCommandPort, ClientRepositoryQueryPort clientRepositoryQueryPort) {
        this.clientRepositoryCommandPort = clientRepositoryCommandPort;
        this.clientRepositoryQueryPort = clientRepositoryQueryPort;
    }
    //todo: Agregar logica de negocio necesaria para el buen funcionamiento.

    @Override
    public Client save(Client client) {
        return clientRepositoryCommandPort.save(client);
    }
    @Override
    public Client update(Client client) {
        return clientRepositoryCommandPort.update(client);
    }
    @Override
    public void deleteClientById(Long clientId){
        clientRepositoryCommandPort.delete(clientId);

    }
    @Override
    public List<Client> findClientById(Long clientIdentificationNumber){
        return clientRepositoryQueryPort.findClientById(clientIdentificationNumber);
    }

}
