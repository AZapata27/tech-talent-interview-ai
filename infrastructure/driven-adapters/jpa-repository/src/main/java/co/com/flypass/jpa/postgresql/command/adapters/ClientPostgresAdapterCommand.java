package co.com.flypass.jpa.postgresql.command.adapters;

import co.com.flypass.jpa.postgresql.command.mappers.IClientEntityMapper;
import co.com.flypass.jpa.postgresql.command.repositories.ClientRepository;
import co.com.flypass.models.Client;
import co.com.flypass.ports.outbound.ClientRepositoryCommandPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class ClientPostgresAdapterCommand implements ClientRepositoryCommandPort {
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
    public Client save(Client client) {
        client = clientEntityMapper.toClient(clientRepository.save(clientEntityMapper.toClientEntity(client)));
        kafkaTemplate.send(TOPIC, "Cliente creado con el numero de identificacion: " + client.getIdentificationNumber());
        return client;
    }

    @Override
    public Client update(Client client) {
        return clientEntityMapper.toClient(clientRepository.save(clientEntityMapper.toClientEntity(client)));
    }


    @Override
    public void delete(Long clientId) {
        clientRepository.deleteById(clientId);
    }
}
