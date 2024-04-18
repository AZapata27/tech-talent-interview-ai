package co.com.flypass.apirest.handlers;

import co.com.flypass.apirest.dtos.request.client.ClientRequestDTO;
import co.com.flypass.apirest.dtos.request.client.ClientUpdateRequestDTO;
import co.com.flypass.apirest.dtos.response.ClientResponseDTO;

import java.util.List;

public interface IClientHandler {
    ClientResponseDTO save(ClientRequestDTO clientRequestDTO);

    ClientResponseDTO update(ClientUpdateRequestDTO clientUpdateRequestDTO);
    void deleteClientById(Long clientId);

    List<ClientResponseDTO> findClientById(Long clientIdentificationNumber);
}
