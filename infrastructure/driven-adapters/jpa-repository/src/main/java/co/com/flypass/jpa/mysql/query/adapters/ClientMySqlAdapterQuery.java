package co.com.flypass.jpa.mysql.query.adapters;


import co.com.flypass.jpa.mysql.query.mappers.IClientEntityQueryMapper;
import co.com.flypass.jpa.mysql.query.repositories.ClientMySqlRepository;
import co.com.flypass.ports.outbound.IMessageProducerPort;
import co.com.flypass.models.Client;
import co.com.flypass.ports.outbound.ClientRepositoryQueryPort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional("mysqlTransactionManager")
public class ClientMySqlAdapterQuery implements ClientRepositoryQueryPort {
    private final ClientMySqlRepository clientMySqlRepository;
    private final IClientEntityQueryMapper clientEntityMapper;
    private final IMessageProducerPort messageProducer;


    private static final String TOPIC = "cliente";

    public ClientMySqlAdapterQuery(ClientMySqlRepository clientMySqlRepository, IClientEntityQueryMapper clientEntityMapper, IMessageProducerPort messageProducer) {
        this.clientMySqlRepository = clientMySqlRepository;
        this.clientEntityMapper = clientEntityMapper;
        this.messageProducer = messageProducer;
    }


    @Override
    public List<Client> findClientById(Long clientIdentificationNumber) {
        messageProducer.sendMessage(TOPIC, "Buscando cliente con el numero de identificacion: " + clientIdentificationNumber);
        return clientMySqlRepository.findByIdentificationNumber(clientIdentificationNumber).stream().map(clientEntityMapper::toClient).toList();
    }
}
