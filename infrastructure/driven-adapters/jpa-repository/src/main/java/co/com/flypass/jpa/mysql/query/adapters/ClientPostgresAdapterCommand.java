package co.com.flypass.jpa.mysql.query.adapters;


import co.com.flypass.jpa.mysql.query.mappers.IClientEntityMapper;
import co.com.flypass.jpa.mysql.query.repositories.ClientRepository;
import co.com.flypass.models.Client;
import co.com.flypass.ports.outbound.ClientRepositoryQueryPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public class ClientPostgresAdapterCommand implements ClientRepositoryQueryPort {
    private final ClientRepository clientRepository;
    private final IClientEntityMapper clientEntityMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC = "cliente";

    public ClientPostgresAdapterCommand(ClientRepository clientRepository, IClientEntityMapper clientEntityMapper, KafkaTemplate<String, Object> kafkaTemplate) {
        this.clientRepository = clientRepository;
        this.clientEntityMapper = clientEntityMapper;
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public List<Client> findClientById(Long clientIdentificationNumber) {
        kafkaTemplate.send(TOPIC, "Buscando cliente con el numero de identificacion: " + clientIdentificationNumber);
        return clientRepository.findByIdentificationNumber(clientIdentificationNumber).stream().map(clientEntityMapper::toClient).toList();
    }
}
