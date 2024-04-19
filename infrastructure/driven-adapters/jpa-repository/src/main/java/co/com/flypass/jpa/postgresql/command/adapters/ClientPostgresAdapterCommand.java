package co.com.flypass.jpa.postgresql.command.adapters;

import co.com.flypass.jpa.postgresql.command.mappers.IClientEntityCommandMapper;
import co.com.flypass.jpa.postgresql.command.repositories.ClientRepositoryPostgres;
import co.com.flypass.models.Client;
import co.com.flypass.ports.outbound.ClientRepositoryCommandPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional("postgresqlTransactionManager")
public class ClientPostgresAdapterCommand implements ClientRepositoryCommandPort {
    private final ClientRepositoryPostgres clientRepositoryPostgres;
    private final IClientEntityCommandMapper clientEntityMapper;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC = "cliente";

    public ClientPostgresAdapterCommand(ClientRepositoryPostgres clientRepositoryPostgres, IClientEntityCommandMapper clientEntityMapper, KafkaTemplate<String, Object> kafkaTemplate) {
        this.clientRepositoryPostgres = clientRepositoryPostgres;
        this.clientEntityMapper = clientEntityMapper;
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public Client save(Client client) {
        client = clientEntityMapper.toClient(clientRepositoryPostgres.save(clientEntityMapper.toClientEntity(client)));
        kafkaTemplate.send(TOPIC, "Cliente creado con el numero de identificacion: " + client.getIdentificationNumber());
        return client;
    }

    @Override
    public Client update(Client client) {
        return clientEntityMapper.toClient(clientRepositoryPostgres.save(clientEntityMapper.toClientEntity(client)));
    }


    @Override
    public void delete(Long clientId) {
        clientRepositoryPostgres.deleteById(clientId);
    }
}
