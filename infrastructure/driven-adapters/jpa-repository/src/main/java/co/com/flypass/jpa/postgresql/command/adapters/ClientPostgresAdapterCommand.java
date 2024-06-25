package co.com.flypass.jpa.postgresql.command.adapters;

import co.com.flypass.jpa.postgresql.command.mappers.IClientEntityCommandMapper;
import co.com.flypass.jpa.postgresql.command.repositories.ClientRepositoryPostgres;
import co.com.flypass.models.Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional("postgresqlTransactionManager")
public class ClientPostgresAdapterCommand implements ClientRepositoryCommandPort {
    private final ClientRepositoryPostgres clientRepositoryPostgres;
    private final IClientEntityCommandMapper clientEntityMapper;
    private final IMessageProducerPort messageProducer;

    @Value("${kafka.topic.cliente}")
    private String topic;

    public ClientPostgresAdapterCommand(ClientRepositoryPostgres clientRepositoryPostgres, IClientEntityCommandMapper clientEntityMapper, IMessageProducerPort messageProducer) {
        this.clientRepositoryPostgres = clientRepositoryPostgres;
        this.clientEntityMapper = clientEntityMapper;
        this.messageProducer = messageProducer;
    }


    @Override
    public Client save(Client client) {
        client = clientEntityMapper.toClient(clientRepositoryPostgres.save(clientEntityMapper.toClientEntity(client)));
        messageProducer.sendMessage(topic, "Cliente creado con el numero de identificacion: " + client.getIdentificationNumber());
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
