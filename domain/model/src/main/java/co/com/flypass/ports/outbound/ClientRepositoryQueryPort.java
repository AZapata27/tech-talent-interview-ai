package co.com.flypass.ports.outbound;

import co.com.flypass.models.Client;

import java.util.List;

public interface ClientRepositoryQueryPort {
    List<Client> findClientById(Long clientIdentificationNumber);
}
